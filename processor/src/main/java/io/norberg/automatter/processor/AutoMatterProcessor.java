package io.norberg.automatter.processor;

import static com.squareup.javapoet.WildcardTypeName.subtypeOf;
import static java.util.stream.Collectors.toSet;
import static javax.lang.model.element.Modifier.DEFAULT;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;
import static javax.lang.model.type.TypeKind.ARRAY;
import static javax.lang.model.type.TypeKind.DECLARED;
import static javax.lang.model.type.TypeKind.DOUBLE;
import static javax.tools.Diagnostic.Kind.ERROR;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.WildcardTypeName;
import io.norberg.automatter.AutoMatter;
import io.norberg.automatter.AutoMatter.Redacted;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * An annotation processor that takes a value type defined as an interface with getter methods and
 * materializes it, generating a concrete builder and value class.
 */
@AutoService(Processor.class)
public final class AutoMatterProcessor extends AbstractProcessor {

  private static final Inflector INFLECTOR = new Inflector();

  private static final Set<String> KEYWORDS =
      Collections.unmodifiableSet(
          Stream.of(
                  "abstract",
                  "continue",
                  "for",
                  "new",
                  "switch",
                  "assert",
                  "default",
                  "if",
                  "package",
                  "synchronized",
                  "boolean",
                  "do",
                  "goto",
                  "private",
                  "this",
                  "break",
                  "double",
                  "implements",
                  "protected",
                  "throw",
                  "byte",
                  "else",
                  "import",
                  "public",
                  "throws",
                  "case",
                  "enum",
                  "instanceof",
                  "return",
                  "transient",
                  "catch",
                  "extends",
                  "int",
                  "short",
                  "try",
                  "char",
                  "final",
                  "interface",
                  "static",
                  "void",
                  "class",
                  "finally",
                  "long",
                  "strictfp",
                  "volatile",
                  "const",
                  "float",
                  "native",
                  "super",
                  "while")
              .collect(toSet()));

  private static final String GENERATED_LEGACY = "javax.annotation.Generated";
  private static final String GENERATED = "javax.annotation.processing.Generated";

  private final List<Name> deferredTypes = new ArrayList<>();
  private final LinkedHashMap<Name, Descriptor> processedTypes = new LinkedHashMap<>();

  private Filer filer;
  private Elements elements;
  private Messager messager;
  private Types types;

  @Override
  public synchronized void init(final ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    filer = processingEnv.getFiler();
    elements = processingEnv.getElementUtils();
    types = processingEnv.getTypeUtils();
    this.messager = processingEnv.getMessager();
  }

  @Override
  public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment env) {
    final Set<TypeElement> elements = new LinkedHashSet<>();

    // Enqueue new elements for processing
    env.getElementsAnnotatedWith(AutoMatter.class).stream()
        .filter(e -> e instanceof TypeElement)
        .forEach(e -> elements.add((TypeElement) e));

    // Enqueue deferred elements for processing
    final List<Name> previousDeferredTypes = new ArrayList<>(deferredTypes);
    deferredTypes.clear();
    for (Name deferredType : previousDeferredTypes) {
      final TypeElement deferredTypeElement =
          processingEnv.getElementUtils().getTypeElement(deferredType);
      if (deferredTypeElement == null) {
        deferredTypes.add(deferredType);
      } else {
        elements.add(deferredTypeElement);
      }
    }

    // Process elements and generate files
    for (TypeElement element : elements) {
      try {
        final Descriptor descriptor = process(element);
        processedTypes.put(element.getQualifiedName(), descriptor);
      } catch (UnresolvedTypeException e) {
        // Encountered an unresolved type while processing this type, defer it for a
        // later processing in hope that the type can be resolved in a later round.
        deferredTypes.add(element.getQualifiedName());
      } catch (AutoMatterProcessorException e) {
        e.print(messager);
      } catch (Exception e) {
        messager.printMessage(ERROR, e.getMessage());
      }
    }

    if (env.processingOver()) {
      // This was the last round, complain if some types failed due to unresolved types
      for (Name deferredType : deferredTypes) {
        final TypeElement deferredTypeElement =
            processingEnv.getElementUtils().getTypeElement(deferredType);
        messager.printMessage(
            ERROR,
            "Failed to generate @AutoMatter builder for "
                + deferredType
                + " because some fields have unresolved types",
            deferredTypeElement);
      }
      // Perform validation that can only be done after processing has been completed
      for (Descriptor descriptor : processedTypes.values()) {
        try {
          validate(descriptor);
        } catch (AutoMatterProcessorException e) {
          e.print(messager);
        } catch (Exception e) {
          messager.printMessage(ERROR, e.getMessage());
        }
      }
    }

    return false;
  }

  private void validate(Descriptor descriptor) {
    descriptor.validate(elements, types);
  }

  private Descriptor process(final Element element)
      throws IOException, AutoMatterProcessorException {
    final Descriptor d = Descriptor.of(element, elements, types);

    TypeSpec builder = builder(d);
    JavaFile javaFile =
        JavaFile.builder(d.packageName(), builder).skipJavaLangImports(true).build();
    javaFile.writeTo(filer);

    return d;
  }

  private TypeSpec builder(final Descriptor d) throws AutoMatterProcessorException {
    TypeSpec.Builder builder =
        TypeSpec.classBuilder(d.builderName())
            .addTypeVariables(d.typeVariables())
            .addModifiers(FINAL);

    TypeElement generatedAnnotationType = generatedAnnotationType();
    if (generatedAnnotationType != null) {
      AnnotationSpec generatedAnnotation =
          AnnotationSpec.builder(ClassName.get(generatedAnnotationType))
              .addMember("value", "$S", AutoMatterProcessor.class.getName())
              .build();
      builder.addAnnotation(generatedAnnotation);
    }

    builder.addAnnotation(AnnotationSpec.builder(AutoMatter.Generated.class).build());

    if (d.isPublic()) {
      builder.addModifiers(PUBLIC);
    }

    for (ExecutableElement field : d.fields()) {
      builder.addField(FieldSpec.builder(fieldType(d, field), fieldName(field), PRIVATE).build());
    }

    builder.addMethod(defaultConstructor(d));
    builder.addMethod(copyValueConstructor(d, d));
    for (Descriptor superValueType : d.superValueTypes()) {
      builder.addMethod(copyValueConstructor(d, superValueType));
    }
    builder.addMethod(copyBuilderConstructor(d, d));
    for (Descriptor superValueType : d.superValueTypes()) {
      builder.addMethod(copyBuilderConstructor(d, superValueType));
    }

    for (MethodSpec accessor : accessors(d)) {
      builder.addMethod(accessor);
    }

    if (d.isRecord()) {
      builder.addMethod(builderFactory(d));
    } else if (d.hasToBuilder()) {
      builder.addMethod(toBuilder(d));
    }

    builder.addMethod(build(d));
    builder.addMethod(fromValue(d, d));
    for (Descriptor superValueType : d.superValueTypes()) {
      builder.addMethod(fromValue(d, superValueType));
    }
    builder.addMethod(fromBuilder(d, d));
    for (Descriptor superValueType : d.superValueTypes()) {
      builder.addMethod(fromBuilder(d, superValueType));
    }

    if (!d.isRecord()) {
      builder.addType(valueClass(d));
    }

    return builder.build();
  }

  private MethodSpec builderFactory(Descriptor d) {
    return MethodSpec.methodBuilder("builder")
        .addModifiers(PUBLIC, STATIC)
        .addTypeVariables(d.typeVariables())
        .returns(builderType(d))
        .addStatement("return new $T()", builderType(d))
        .build();
  }

  private TypeElement generatedAnnotationType() {
    TypeElement generated = elements.getTypeElement(GENERATED);
    if (generated != null) {
      return generated;
    }
    return elements.getTypeElement(GENERATED_LEGACY);
  }

  private MethodSpec defaultConstructor(final Descriptor d) {
    MethodSpec.Builder constructor = MethodSpec.constructorBuilder().addModifiers(PUBLIC);

    for (ExecutableElement field : d.fields()) {
      if (isOptional(field) && shouldEnforceNonNull(field)) {
        ClassName type = ClassName.bestGuess(optionalType(field));
        constructor.addStatement(
            "this.$N = $T.$L()", fieldName(field), type, optionalEmptyName(field));
      }
    }

    return constructor.build();
  }

  private MethodSpec copyValueConstructor(final Descriptor target, final Descriptor source)
      throws AutoMatterProcessorException {
    final TypeName valueType = upperBoundedValueType(source);
    final MethodSpec.Builder constructor =
        MethodSpec.constructorBuilder().addModifiers(PRIVATE).addParameter(valueType, "v");

    final List<ExecutableElement> fields = commonFields(source, target);

    for (ExecutableElement field : fields) {
      String fieldName = fieldName(field);

      final boolean isParameterized = isFieldTypeParameterized(field);

      if (isCollection(field) || isMap(field)) {
        if (isParameterized) {
          final TypeMirror fieldType = source.fieldType(field);
          final TypeName ctorArgumentType = upperBoundedType(fieldType, 1);
          final TypeName upperBoundedFieldType = upperBoundedType(fieldType);
          if (upperBoundedFieldType.equals(ctorArgumentType)) {
            constructor.addStatement("$T _$N = v.$N()", ctorArgumentType, fieldName, fieldName);
          } else {
            constructor.addStatement(
                "@SuppressWarnings(\"unchecked\") $T _$N = ($T) ($T) v.$N()",
                ctorArgumentType,
                fieldName,
                ctorArgumentType,
                upperBoundedFieldType,
                fieldName);
          }
        } else {
          final TypeName fieldType = fieldType(target, field);
          constructor.addStatement("$T _$N = v.$N()", fieldType, fieldName, fieldName);
        }
        constructor.addStatement(
            "this.$N = (_$N == null) ? null : new $T(_$N)",
            fieldName,
            fieldName,
            collectionImplType(target, field),
            fieldName);
      } else {
        if (isParameterized) {
          final TypeMirror fieldType = source.fieldType(field);
          final TypeName upperBoundedFieldType = upperBoundedType(fieldType);
          constructor.addStatement(
              "@SuppressWarnings(\"unchecked\") $T _$N = ($T) ($T) v.$N()",
              fieldType,
              fieldName,
              fieldType,
              upperBoundedFieldType,
              fieldName);
          constructor.addStatement("this.$N = _$N", fieldName, fieldName);
        } else {
          constructor.addStatement("this.$N = v.$N()", fieldName, fieldName);
        }
      }
    }

    return constructor.build();
  }

  private boolean isFieldTypeParameterized(final ExecutableElement field) {
    final TypeMirror returnType = field.getReturnType();
    return isTypeParameterized(returnType);
  }

  private boolean isTypeParameterized(TypeMirror type) {
    if (type.getKind() != DECLARED) {
      return false;
    }
    final DeclaredType declaredType = (DeclaredType) type;
    for (final TypeMirror typeArgument : declaredType.getTypeArguments()) {
      switch (typeArgument.getKind()) {
        case TYPEVAR:
          return true;
        case DECLARED:
          if (isTypeParameterized(typeArgument)) {
            return true;
          }
      }
    }
    return false;
  }

  private MethodSpec copyBuilderConstructor(final Descriptor target, final Descriptor source)
      throws AutoMatterProcessorException {
    final MethodSpec.Builder constructor =
        MethodSpec.constructorBuilder()
            .addModifiers(PRIVATE)
            .addParameter(upperBoundedBuilderType(source), "v");

    final List<ExecutableElement> fields = commonFields(target, source);

    for (ExecutableElement field : fields) {
      String fieldName = fieldName(field);

      final boolean isParameterized = isFieldTypeParameterized(field);
      final boolean isNullable = isNullableAnnotated(field);

      if (isCollection(field) || isMap(field)) {
        if (isParameterized) {
          final TypeMirror fieldType = target.fieldType(field);
          final TypeName ctorArgumentType = upperBoundedType(fieldType, 1);
          final TypeName upperBoundedFieldType = upperBoundedType(fieldType);
          if (upperBoundedFieldType.equals(ctorArgumentType)) {
            if (isNullable) {
              constructor.addStatement(
                  "this.$N = (v.$N() == null) ? null : new $T(v.$N())",
                  fieldName,
                  fieldName,
                  collectionImplType(target, field),
                  fieldName);
            } else {
              constructor.addStatement(
                  "this.$N = new $T(v.$N())",
                  fieldName,
                  collectionImplType(target, field),
                  fieldName);
            }
          } else {
            constructor.addStatement(
                "@SuppressWarnings(\"unchecked\") $T _$N = ($T) ($T) v.$N()",
                ctorArgumentType,
                fieldName,
                ctorArgumentType,
                upperBoundedFieldType,
                fieldName);
            if (isNullable) {
              constructor.addStatement(
                  "this.$N = (_$N == null) ? null : new $T(_$N)",
                  fieldName,
                  fieldName,
                  collectionImplType(target, field),
                  fieldName);
            } else {
              constructor.addStatement(
                  "this.$N = new $T(_$N)", fieldName, collectionImplType(target, field), fieldName);
            }
          }
        } else {
          if (isNullable) {
            constructor.addStatement(
                "this.$N = (v.$N() == null) ? null : new $T(v.$N())",
                fieldName,
                fieldName,
                collectionImplType(target, field),
                fieldName);
          } else {
            constructor.addStatement(
                "this.$N = new $T(v.$N())",
                fieldName,
                collectionImplType(target, field),
                fieldName);
          }
        }
      } else {
        if (isParameterized) {
          final TypeMirror fieldType = source.fieldType(field);
          final TypeName upperBoundedFieldType = upperBoundedType(fieldType);
          constructor.addStatement(
              "@SuppressWarnings(\"unchecked\") $T _$N = ($T) ($T) v.$N()",
              fieldType,
              fieldName,
              fieldType,
              upperBoundedFieldType,
              fieldName);
          constructor.addStatement("this.$N = _$N", fieldName, fieldName);
        } else {
          constructor.addStatement("this.$N = v.$N()", fieldName, fieldName);
        }
      }
    }

    return constructor.build();
  }

  private List<ExecutableElement> commonFields(Descriptor source, Descriptor target) {
    return source.fields().stream()
        .filter(f -> target.hasField(f.getSimpleName().toString()))
        .collect(Collectors.toList());
  }

  private Set<MethodSpec> accessors(final Descriptor d) throws AutoMatterProcessorException {
    final Set<MethodSpec> result = new LinkedHashSet<>();
    for (ExecutableElement field : d.fields()) {
      result.add(getter(d, field));

      if (isOptional(field)) {
        result.add(optionalRawSetter(d, field));
        result.add(optionalSetter(d, field));
      } else if (isCollection(field)) {
        if (!isCollectionInterface(field)) {
          result.add(collectionSetter(d, field));
        }
        result.add(collectionCollectionSetter(d, field));
        result.add(collectionIterableSetter(d, field));
        result.add(collectionIteratorSetter(d, field));
        result.add(collectionVarargSetter(d, field));

        MethodSpec adder = collectionAdder(d, field);
        if (adder != null) {
          result.add(adder);
        }
      } else if (isMap(field)) {
        result.add(mapSetter(d, field));
        for (int i = 1; i <= 5; i++) {
          result.add(mapSetterPairs(d, field, i));
        }

        MethodSpec putter = mapPutter(d, field);
        if (putter != null) {
          result.add(putter);
        }
      } else {
        result.add(setter(d, field));
      }
    }
    return Collections.unmodifiableSet(result);
  }

  private MethodSpec getter(final Descriptor d, final ExecutableElement field) {
    String fieldName = fieldName(field);

    MethodSpec.Builder getter =
        MethodSpec.methodBuilder(fieldName).addModifiers(PUBLIC).returns(fieldType(d, field));

    if ((isCollection(field) || isMap(field)) && shouldEnforceNonNull(field)) {
      getter
          .beginControlFlow("if (this.$N == null)", fieldName)
          .addStatement("this.$N = new $T()", fieldName, collectionImplType(d, field))
          .endControlFlow();
    }
    getter.addStatement("return $N", fieldName);

    return getter.build();
  }

  private MethodSpec optionalRawSetter(final Descriptor d, final ExecutableElement field) {
    String fieldName = fieldName(field);
    ClassName type = ClassName.bestGuess(optionalType(field));
    TypeName valueType = genericArgument(d, field, 0);

    return MethodSpec.methodBuilder(fieldName)
        .addModifiers(PUBLIC)
        .addParameter(valueType, fieldName)
        .returns(builderType(d))
        .addStatement("return $N($T.$N($N))", fieldName, type, optionalMaybeName(field), fieldName)
        .build();
  }

  private MethodSpec optionalSetter(final Descriptor d, final ExecutableElement field) {
    String fieldName = fieldName(field);
    TypeName valueType = genericArgument(d, field, 0);
    ClassName optionalType = ClassName.bestGuess(optionalType(field));
    TypeName parameterType = ParameterizedTypeName.get(optionalType, subtypeOf(valueType));

    AnnotationSpec suppressUncheckedAnnotation =
        AnnotationSpec.builder(SuppressWarnings.class)
            .addMember("value", "$S", "unchecked")
            .build();

    MethodSpec.Builder setter =
        MethodSpec.methodBuilder(fieldName)
            .addAnnotation(suppressUncheckedAnnotation)
            .addModifiers(PUBLIC)
            .addParameter(parameterType, fieldName)
            .returns(builderType(d));

    if (shouldEnforceNonNull(field)) {
      assertNotNull(setter, fieldName);
    }

    setter.addStatement("this.$N = ($T)$N", fieldName, fieldType(d, field), fieldName);

    return setter.addStatement("return this").build();
  }

  private MethodSpec collectionSetter(final Descriptor d, final ExecutableElement field) {
    String fieldName = fieldName(field);
    ClassName collectionType = collectionRawType(field);
    TypeName itemType = genericArgument(d, field, 0);
    WildcardTypeName extendedType = subtypeOf(itemType);

    return MethodSpec.methodBuilder(fieldName)
        .addModifiers(PUBLIC)
        .addParameter(ParameterizedTypeName.get(collectionType, extendedType), fieldName)
        .returns(builderType(d))
        .addStatement("return $N((Collection<$T>) $N)", fieldName, extendedType, fieldName)
        .build();
  }

  private MethodSpec collectionCollectionSetter(final Descriptor d, final ExecutableElement field) {
    String fieldName = fieldName(field);
    ClassName collectionType = ClassName.get(Collection.class);
    TypeName itemType = genericArgument(d, field, 0);
    WildcardTypeName extendedType = subtypeOf(itemType);

    MethodSpec.Builder setter =
        MethodSpec.methodBuilder(fieldName)
            .addModifiers(PUBLIC)
            .addParameter(ParameterizedTypeName.get(collectionType, extendedType), fieldName)
            .returns(builderType(d));

    collectionNullGuard(setter, field);
    if (shouldEnforceNonNull(field)) {
      setter.beginControlFlow("for ($T item : $N)", itemType, fieldName);
      assertNotNull(setter, "item", fieldName + ": null item");
      setter.endControlFlow();
    }

    setter.addStatement("this.$N = new $T($N)", fieldName, collectionImplType(d, field), fieldName);
    return setter.addStatement("return this").build();
  }

  private MethodSpec collectionIterableSetter(final Descriptor d, final ExecutableElement field) {
    String fieldName = fieldName(field);
    ClassName iterableType = ClassName.get(Iterable.class);
    TypeName itemType = genericArgument(d, field, 0);
    WildcardTypeName extendedType = subtypeOf(itemType);

    MethodSpec.Builder setter =
        MethodSpec.methodBuilder(fieldName)
            .addModifiers(PUBLIC)
            .addParameter(ParameterizedTypeName.get(iterableType, extendedType), fieldName)
            .returns(builderType(d));

    collectionNullGuard(setter, field);

    ClassName collectionType = ClassName.get(Collection.class);
    setter
        .beginControlFlow("if ($N instanceof $T)", fieldName, collectionType)
        .addStatement("return $N(($T<$T>) $N)", fieldName, collectionType, extendedType, fieldName)
        .endControlFlow();

    setter.addStatement("return $N($N.iterator())", fieldName, fieldName);
    return setter.build();
  }

  private MethodSpec collectionIteratorSetter(final Descriptor d, final ExecutableElement field) {
    String fieldName = fieldName(field);
    ClassName iteratorType = ClassName.get(Iterator.class);
    TypeName itemType = genericArgument(d, field, 0);
    WildcardTypeName extendedType = subtypeOf(itemType);

    MethodSpec.Builder setter =
        MethodSpec.methodBuilder(fieldName)
            .addModifiers(PUBLIC)
            .addParameter(ParameterizedTypeName.get(iteratorType, extendedType), fieldName)
            .returns(builderType(d));

    collectionNullGuard(setter, field);

    setter
        .addStatement("this.$N = new $T()", fieldName, collectionImplType(d, field))
        .beginControlFlow("while ($N.hasNext())", fieldName)
        .addStatement("$T item = $N.next()", itemType, fieldName);

    if (shouldEnforceNonNull(field)) {
      assertNotNull(setter, "item", fieldName + ": null item");
    }

    setter.addStatement("this.$N.add(item)", fieldName).endControlFlow();

    return setter.addStatement("return this").build();
  }

  private MethodSpec collectionVarargSetter(final Descriptor d, final ExecutableElement field) {
    String fieldName = fieldName(field);
    TypeName itemType = genericArgument(d, field, 0);

    MethodSpec.Builder setter =
        MethodSpec.methodBuilder(fieldName)
            .addModifiers(PUBLIC)
            .addParameter(ArrayTypeName.of(itemType), fieldName)
            .varargs()
            .returns(builderType(d));

    ensureSafeVarargs(setter);

    collectionNullGuard(setter, field);

    setter.addStatement(
        "return $N($T.asList($N))", fieldName, ClassName.get(Arrays.class), fieldName);
    return setter.build();
  }

  private void ensureSafeVarargs(MethodSpec.Builder setter) {
    // TODO: Add SafeVarargs annotation only for non-reifiable types.
    AnnotationSpec safeVarargsAnnotation = AnnotationSpec.builder(SafeVarargs.class).build();

    setter
        .addAnnotation(safeVarargsAnnotation)
        .addModifiers(FINAL); // Only because SafeVarargs can be applied to final methods.

    // Suppress the following warnings for varargs setters:
    // "Redundant java.lang.SafeVarargs annotation. Varargs element type X is reifiable."
    // "Varargs method could cause heap pollution from non-reifiable varargs parameter x"
    AnnotationSpec varargWarningSuppression =
        AnnotationSpec.builder(SuppressWarnings.class).addMember("value", "$S", "varargs").build();
    setter.addAnnotation(varargWarningSuppression);
  }

  private MethodSpec collectionAdder(final Descriptor d, final ExecutableElement field) {
    final String fieldName = fieldName(field);
    final String singular = singular(fieldName);
    if (singular == null || singular.isEmpty()) {
      return null;
    }

    final String appendMethodName = "add" + capitalizeFirstLetter(singular);
    final TypeName itemType = genericArgument(d, field, 0);
    MethodSpec.Builder adder =
        MethodSpec.methodBuilder(appendMethodName)
            .addModifiers(PUBLIC)
            .addParameter(itemType, singular)
            .returns(builderType(d));

    if (shouldEnforceNonNull(field)) {
      assertNotNull(adder, singular);
    }
    lazyCollectionInitialization(d, adder, field);

    adder.addStatement("$L.add($L)", fieldName, singular);
    return adder.addStatement("return this").build();
  }

  private void collectionNullGuard(final MethodSpec.Builder spec, final ExecutableElement field) {
    String fieldName = fieldName(field);
    if (shouldEnforceNonNull(field)) {
      assertNotNull(spec, fieldName);
    } else {
      spec.beginControlFlow("if ($N == null)", fieldName)
          .addStatement("this.$N = null", fieldName)
          .addStatement("return this")
          .endControlFlow();
    }
  }

  private void lazyCollectionInitialization(
      Descriptor d, final Builder spec, final ExecutableElement field) {
    final String fieldName = fieldName(field);
    spec.beginControlFlow("if (this.$N == null)", fieldName)
        .addStatement("this.$N = new $T()", fieldName, collectionImplType(d, field))
        .endControlFlow();
  }

  private MethodSpec mapSetter(final Descriptor d, final ExecutableElement field) {
    final String fieldName = fieldName(field);
    final TypeName keyType = subtypeOf(genericArgument(d, field, 0));
    final TypeName valueType = subtypeOf(genericArgument(d, field, 1));
    final TypeName paramType =
        ParameterizedTypeName.get(ClassName.get(Map.class), keyType, valueType);

    MethodSpec.Builder setter =
        MethodSpec.methodBuilder(fieldName)
            .addModifiers(PUBLIC)
            .addParameter(paramType, fieldName)
            .returns(builderType(d));

    if (shouldEnforceNonNull(field)) {
      final String entryName = variableName("entry", fieldName);
      assertNotNull(setter, fieldName);
      setter.beginControlFlow(
          "for ($T<$T, $T> $L : $N.entrySet())",
          ClassName.get(Map.Entry.class),
          keyType,
          valueType,
          entryName,
          fieldName);
      assertNotNull(setter, entryName + ".getKey()", fieldName + ": null key");
      assertNotNull(setter, entryName + ".getValue()", fieldName + ": null value");
      setter.endControlFlow();
    } else {
      setter
          .beginControlFlow("if ($N == null)", fieldName)
          .addStatement("this.$N = null", fieldName)
          .addStatement("return this")
          .endControlFlow();
    }

    setter.addStatement("this.$N = new $T($N)", fieldName, collectionImplType(d, field), fieldName);

    return setter.addStatement("return this").build();
  }

  private MethodSpec mapSetterPairs(
      final Descriptor d, final ExecutableElement field, int entries) {
    if (entries == 0) {
      throw new IllegalArgumentException("entries == 0");
    }

    final String fieldName = fieldName(field);
    final TypeName keyType = genericArgument(d, field, 0);
    final TypeName valueType = genericArgument(d, field, 1);

    MethodSpec.Builder setter =
        MethodSpec.methodBuilder(fieldName).addModifiers(PUBLIC).returns(builderType(d));

    for (int i = 1; i < entries + 1; i++) {
      setter.addParameter(keyType, "k" + i);
      setter.addParameter(valueType, "v" + i);
    }

    // Recursion
    if (entries > 1) {
      final List<String> recursionParameters = new ArrayList<>();
      for (int i = 1; i < entries; i++) {
        recursionParameters.add("k" + i);
        recursionParameters.add("v" + i);
      }
      setter.addStatement("$L($L)", fieldName, String.join(", ", recursionParameters));
    }

    // Null checks
    final String keyName = "k" + entries;
    final String valueName = "v" + entries;
    if (shouldEnforceNonNull(field)) {
      assertNotNull(setter, keyName, fieldName + ": " + keyName);
      assertNotNull(setter, valueName, fieldName + ": " + valueName);
    }

    // Map instantiation
    if (entries == 1) {
      setter.addStatement("$N = new $T()", fieldName, collectionImplType(d, field));
    }

    // Put
    setter.addStatement("$N.put($N, $N)", fieldName, keyName, valueName);

    return setter.addStatement("return this").build();
  }

  private MethodSpec mapPutter(final Descriptor d, final ExecutableElement field) {
    final String fieldName = fieldName(field);
    final String singular = singular(fieldName);
    if (singular == null) {
      return null;
    }

    final String putSingular = "put" + capitalizeFirstLetter(singular);
    final TypeName keyType = genericArgument(d, field, 0);
    final TypeName valueType = genericArgument(d, field, 1);

    MethodSpec.Builder setter =
        MethodSpec.methodBuilder(putSingular)
            .addModifiers(PUBLIC)
            .addParameter(keyType, "key")
            .addParameter(valueType, "value")
            .returns(builderType(d));

    // Null checks
    if (shouldEnforceNonNull(field)) {
      assertNotNull(setter, "key", singular + ": key");
      assertNotNull(setter, "value", singular + ": value");
    }

    // Put
    lazMapInitialization(d, setter, field);
    setter.addStatement("$N.put(key, value)", fieldName);

    return setter.addStatement("return this").build();
  }

  private void lazMapInitialization(
      Descriptor d, final Builder spec, final ExecutableElement field) {
    final String fieldName = fieldName(field);
    spec.beginControlFlow("if (this.$N == null)", fieldName)
        .addStatement("this.$N = new $T()", fieldName, collectionImplType(d, field))
        .endControlFlow();
  }

  private MethodSpec setter(final Descriptor d, final ExecutableElement field) {
    String fieldName = fieldName(field);

    ParameterSpec.Builder parameterSpecBuilder =
        ParameterSpec.builder(fieldType(d, field), fieldName);
    if (!isPrimitive(field)) {
      AnnotationMirror nullableAnnotation = nullableAnnotation(field);
      if (nullableAnnotation != null) {
        parameterSpecBuilder.addAnnotation(AnnotationSpec.get(nullableAnnotation));
      }
    }
    MethodSpec.Builder setter =
        MethodSpec.methodBuilder(fieldName)
            .addModifiers(PUBLIC)
            .addParameter(parameterSpecBuilder.build())
            .returns(builderType(d));

    if (shouldEnforceNonNull(field)) {
      assertNotNull(setter, fieldName);
    }

    setter.addStatement("this.$N = $N", fieldName, fieldName);
    return setter.addStatement("return this").build();
  }

  private MethodSpec toBuilder(final Descriptor d) {
    return MethodSpec.methodBuilder("builder")
        .addModifiers(PUBLIC)
        .returns(builderType(d))
        .addStatement("return new $T(this)", builderType(d))
        .build();
  }

  private MethodSpec build(final Descriptor d) {
    MethodSpec.Builder build =
        MethodSpec.methodBuilder("build").addModifiers(PUBLIC).returns(valueType(d));

    // Do null checks in the build method for records.
    // Interface style types perform null checking in the constructor.
    if (d.isRecord()) {
      assertFieldsNotNull(d, build);
    }

    final List<String> parameters = new ArrayList<>();
    for (ExecutableElement field : d.fields()) {
      final String fieldName = fieldName(field);
      final TypeName fieldType = fieldType(d, field);
      final ClassName collections = ClassName.get(Collections.class);

      if (isCollection(field)) {
        final TypeName itemType = genericArgument(d, field, 0);

        if (shouldEnforceNonNull(field)) {
          build.addStatement(
              "$T _$L = ($L != null) ? $T.$L(new $T($N)) : $T.<$T>$L()",
              fieldType,
              fieldName,
              fieldName,
              collections,
              unmodifiableCollection(field),
              collectionImplType(d, field),
              fieldName,
              collections,
              itemType,
              emptyCollection(field));
        } else {
          build.addStatement(
              "$T _$L = ($L != null) ? $T.$L(new $T($N)) : null",
              fieldType,
              fieldName,
              fieldName,
              collections,
              unmodifiableCollection(field),
              collectionImplType(d, field),
              fieldName);
        }

        parameters.add("_" + fieldName);
      } else if (isMap(field)) {
        final TypeName keyType = genericArgument(d, field, 0);
        final TypeName valueType = genericArgument(d, field, 1);

        if (shouldEnforceNonNull(field)) {
          build.addStatement(
              "$T _$L = ($L != null) ? $T.$L(new $T($N)) : $T.<$T, $T>$L()",
              fieldType,
              fieldName,
              fieldName,
              collections,
              unmodifiableCollection(field),
              collectionImplType(d, field),
              fieldName,
              collections,
              keyType,
              valueType,
              emptyCollection(field));
        } else {
          build.addStatement(
              "$T _$L = ($L != null) ? $T.$L(new $T($N)) : null",
              fieldType,
              fieldName,
              fieldName,
              collections,
              unmodifiableCollection(field),
              collectionImplType(d, field),
              fieldName);
        }

        parameters.add("_" + fieldName);
      } else {
        parameters.add(fieldName(field));
      }
    }

    return build
        .addStatement("return new $T($N)", valueImplType(d), String.join(", ", parameters))
        .build();
  }

  private MethodSpec fromValue(final Descriptor target, final Descriptor source) {
    return MethodSpec.methodBuilder("from")
        .addModifiers(PUBLIC, STATIC)
        .addTypeVariables(target.typeVariables())
        .addParameter(upperBoundedValueType(source), "v")
        .returns(builderType(target))
        .addStatement("return new $T(v)", builderType(target))
        .build();
  }

  private MethodSpec fromBuilder(final Descriptor target, final Descriptor source) {
    return MethodSpec.methodBuilder("from")
        .addModifiers(PUBLIC, STATIC)
        .addTypeVariables(target.typeVariables())
        .addParameter(upperBoundedBuilderType(source), "v")
        .returns(builderType(target))
        .addStatement("return new $T(v)", builderType(target))
        .build();
  }

  private TypeSpec valueClass(final Descriptor d) throws AutoMatterProcessorException {
    TypeSpec.Builder value =
        TypeSpec.classBuilder("Value")
            .addTypeVariables(d.typeVariables())
            .addModifiers(PRIVATE, STATIC, FINAL)
            .addSuperinterface(valueType(d))
            .addAnnotation(AnnotationSpec.builder(AutoMatter.Generated.class).build());

    for (ExecutableElement field : d.fields()) {
      value.addField(
          FieldSpec.builder(fieldType(d, field), fieldName(field), PRIVATE, FINAL).build());
    }

    value.addMethod(valueConstructor(d));

    for (ExecutableElement field : d.fields()) {
      value.addMethod(valueGetter(d, field));
    }
    value.addMethod(valueToBuilder(d));
    value.addMethod(valueEquals(d));
    value.addMethod(valueHashCode(d));
    value.addMethod(valueToString(d));

    return value.build();
  }

  private MethodSpec valueConstructor(final Descriptor d) {
    MethodSpec.Builder constructor = MethodSpec.constructorBuilder().addModifiers(PRIVATE);

    assertFieldsNotNull(d, constructor);

    for (ExecutableElement field : d.fields()) {
      String fieldName = fieldName(field);
      AnnotationSpec annotation =
          AnnotationSpec.builder(AutoMatter.Field.class)
              .addMember("value", "$S", fieldName)
              .build();
      ParameterSpec parameter =
          ParameterSpec.builder(fieldType(d, field), fieldName).addAnnotation(annotation).build();
      constructor.addParameter(parameter);

      final ClassName collectionsType = ClassName.get(Collections.class);
      if (shouldEnforceNonNull(field) && isCollection(field)) {
        final TypeName itemType = genericArgument(d, field, 0);
        constructor.addStatement(
            "this.$N = ($N != null) ? $N : $T.<$T>$L()",
            fieldName,
            fieldName,
            fieldName,
            collectionsType,
            itemType,
            emptyCollection(field));
      } else if (shouldEnforceNonNull(field) && isMap(field)) {
        final TypeName keyType = genericArgument(d, field, 0);
        final TypeName valueType = genericArgument(d, field, 1);
        constructor.addStatement(
            "this.$N = ($N != null) ? $N : $T.<$T, $T>$L()",
            fieldName,
            fieldName,
            fieldName,
            collectionsType,
            keyType,
            valueType,
            emptyCollection(field));
      } else {
        constructor.addStatement("this.$N = $N", fieldName, fieldName);
      }
    }

    d.checkMethod()
        .ifPresent(
            checkMethod -> {
              if (checkMethod.getModifiers().contains(STATIC)) {
                constructor.addCode(
                    "$L.$N(this);\n", d.simpleValueTypeName(), checkMethod.getSimpleName());
              } else if (checkMethod.getModifiers().contains(DEFAULT)) {
                constructor.addCode("$N();\n", checkMethod.getSimpleName());
              }
            });

    return constructor.build();
  }

  private void assertFieldsNotNull(Descriptor d, Builder constructor) {
    for (ExecutableElement field : d.fields()) {
      if (shouldEnforceNonNull(field) && !isCollection(field) && !isMap(field)) {
        assertNotNull(constructor, fieldName(field));
      }
    }
  }

  private MethodSpec valueGetter(final Descriptor d, final ExecutableElement field) {
    String fieldName = fieldName(field);

    return MethodSpec.methodBuilder(fieldName)
        .addAnnotation(AutoMatter.Field.class)
        .addAnnotation(Override.class)
        .addModifiers(PUBLIC)
        .returns(fieldType(d, field))
        .addStatement("return $N", fieldName)
        .build();
  }

  private MethodSpec valueToBuilder(final Descriptor d) {
    MethodSpec.Builder toBuilder =
        MethodSpec.methodBuilder("builder")
            .addModifiers(PUBLIC)
            .returns(builderType(d))
            .addStatement("return new $T(this)", builderType(d));

    // Always emit toBuilder, but only annotate it with @Override if the target asked for it.
    if (d.hasToBuilder()) {
      toBuilder.addAnnotation(Override.class);
    }

    return toBuilder.build();
  }

  private MethodSpec valueEquals(final Descriptor d) throws AutoMatterProcessorException {
    MethodSpec.Builder equals =
        MethodSpec.methodBuilder("equals")
            .addAnnotation(Override.class)
            .addModifiers(PUBLIC)
            .addParameter(ClassName.get(Object.class), "o")
            .returns(TypeName.BOOLEAN);

    equals.beginControlFlow("if (this == o)").addStatement("return true").endControlFlow();

    equals
        .beginControlFlow("if (!(o instanceof $T))", rawValueType(d))
        .addStatement("return false")
        .endControlFlow();

    if (!d.fields().isEmpty()) {
      equals.addStatement("final $T that = ($T) o", unboundedValueType(d), unboundedValueType(d));

      for (ExecutableElement field : d.fields()) {
        equals.addCode(fieldNotEqualCheck(field));
      }
    }

    return equals.addStatement("return true").build();
  }

  private CodeBlock fieldNotEqualCheck(final ExecutableElement field)
      throws AutoMatterProcessorException {
    final String name = fieldName(field);
    final CodeBlock.Builder result = CodeBlock.builder();
    final TypeMirror returnType = field.getReturnType();
    switch (returnType.getKind()) {
      case LONG:
      case INT:
      case BOOLEAN:
      case BYTE:
      case SHORT:
      case CHAR:
        result.beginControlFlow("if ($L != that.$L())", name, name);
        break;
      case FLOAT:
      case DOUBLE:
        final TypeName boxed = ClassName.get(returnType).box();
        result.beginControlFlow("if ($T.compare($L, that.$L()) != 0)", boxed, name, name);
        break;
      case ARRAY:
        result.beginControlFlow(
            "if (!$T.equals($L, that.$L()))", ClassName.get(Arrays.class), name, name);
        break;
      case TYPEVAR:
      case DECLARED:
      case ERROR:
        result.beginControlFlow(
            "if ($L != null ? !$L.equals(that.$L()) : that.$L() != null)", name, name, name, name);
        break;
      default:
        throw fail("Unsupported type: " + returnType, field);
    }

    result.addStatement("return false").endControlFlow();
    return result.build();
  }

  private MethodSpec valueHashCode(final Descriptor d) throws AutoMatterProcessorException {
    MethodSpec.Builder hashcode =
        MethodSpec.methodBuilder("hashCode")
            .addAnnotation(Override.class)
            .addModifiers(PUBLIC)
            .returns(TypeName.INT)
            .addStatement("int result = 1");

    boolean needsTemp =
        d.fields().stream()
            .map(ExecutableElement::getReturnType)
            .map(TypeMirror::getKind)
            .anyMatch(DOUBLE::equals);
    if (needsTemp) {
      hashcode.addStatement("long temp");
    }

    for (ExecutableElement field : d.fields()) {
      final String name = "this." + fieldName(field);
      final TypeMirror type = field.getReturnType();
      switch (type.getKind()) {
        case LONG:
          hashcode.addStatement("result = 31 * result + (int) ($N ^ ($N >>> 32))", name, name);
          break;
        case INT:
          hashcode.addStatement("result = 31 * result + $N", name);
          break;
        case BOOLEAN:
          hashcode.addStatement("result = 31 * result + ($N ? 1231 : 1237)", name);
          break;
        case BYTE:
        case SHORT:
        case CHAR:
          hashcode.addStatement("result = 31 * result + (int) $N", name);
          break;
        case FLOAT:
          hashcode.addStatement(
              "result = 31 * result + ($N != +0.0f ? $T.floatToIntBits($N) : 0)",
              name,
              ClassName.get(Float.class),
              name);
          break;
        case DOUBLE:
          hashcode.addStatement(
              "temp = $T.doubleToLongBits($N)", ClassName.get(Double.class), name);
          hashcode.addStatement("result = 31 * result + (int) (temp ^ (temp >>> 32))");
          break;
        case ARRAY:
          hashcode.addStatement(
              "result = 31 * result + ($N != null ? $T.hashCode($N) : 0)",
              name,
              ClassName.get(Arrays.class),
              name);
          break;
        case TYPEVAR:
        case DECLARED:
        case ERROR:
          hashcode.addStatement(
              "result = 31 * result + ($N != null ? $N.hashCode() : 0)", name, name);
          break;
        default:
          throw fail("Unsupported type: " + type, field);
      }
    }
    return hashcode.addStatement("return result").build();
  }

  private MethodSpec valueToString(final Descriptor d) {
    return d.toStringMethod()
        .map(toStringMethod -> valueCustomToString(toStringMethod, d))
        .orElseGet(() -> valueDefaultToString(d));
  }

  private MethodSpec valueCustomToString(ExecutableElement toStringMethod, Descriptor d) {
    MethodSpec.Builder toString =
        MethodSpec.methodBuilder("toString")
            .addAnnotation(Override.class)
            .addModifiers(PUBLIC)
            .returns(ClassName.get(String.class));
    if (toStringMethod.getModifiers().contains(STATIC)) {
      toString.addCode(
          "return $L.$N(this);\n", d.simpleValueTypeName(), toStringMethod.getSimpleName());
    } else if (toStringMethod.getModifiers().contains(DEFAULT)) {
      toString.addCode("return $N();\n", toStringMethod.getSimpleName());
    }
    return toString.build();
  }

  private MethodSpec valueDefaultToString(final Descriptor d) {
    MethodSpec.Builder toString =
        MethodSpec.methodBuilder("toString")
            .addAnnotation(Override.class)
            .addModifiers(PUBLIC)
            .returns(ClassName.get(String.class));

    toString.addCode("return \"$L{\" +\n", d.simpleValueTypeName());

    for (int i = 0; i < d.fields().size(); i++) {
      final ExecutableElement field = d.fields().get(i);
      final String comma = (i == 0) ? "" : ", ";
      final String name = fieldName(field);

      if (field.getReturnType().getKind() == ARRAY) {
        toString.addCode(
            "\"$L$L=\" + $T.toString($L) +\n", comma, name, ClassName.get(Arrays.class), name);
      } else {
        final Redacted redacted = field.getAnnotation(Redacted.class);
        if (redacted != null) {
          toString.addCode("\"$L$L=$L\" +\n", comma, name, redacted.value());
        } else {
          toString.addCode("\"$L$L=\" + $L +\n", comma, name, name);
        }
      }
    }

    toString.addStatement("'}'");
    return toString.build();
  }

  private void assertNotNull(MethodSpec.Builder spec, String name) {
    assertNotNull(spec, name, name);
  }

  private void assertNotNull(MethodSpec.Builder spec, String name, String msg) {
    spec.beginControlFlow("if ($N == null)", name)
        .addStatement("throw new $T($S)", ClassName.get(NullPointerException.class), msg)
        .endControlFlow();
  }

  private TypeName builderType(final Descriptor d) {
    final ClassName raw = rawBuilderType(d);
    if (!d.isGeneric()) {
      return raw;
    }
    return ParameterizedTypeName.get(raw, d.typeArguments());
  }

  private TypeName upperBoundedBuilderType(final Descriptor d) {
    final ClassName raw = rawBuilderType(d);
    if (!d.isGeneric()) {
      return raw;
    }
    return ParameterizedTypeName.get(raw, upperBounded(d.typeArguments()));
  }

  private TypeName[] upperBounded(final TypeName[] typeArguments) {
    final TypeName[] typeNames = new TypeName[typeArguments.length];
    for (int i = 0; i < typeArguments.length; i++) {
      typeNames[i] = subtypeOf(typeArguments[i]);
    }
    return typeNames;
  }

  private ClassName rawBuilderType(final Descriptor d) {
    return ClassName.get(d.packageName(), d.builderName());
  }

  private ClassName rawValueType(final Descriptor d) {
    return d.valueTypeName();
  }

  private TypeName unboundedValueType(final Descriptor d) {
    final ClassName raw = rawValueType(d);
    if (!d.isGeneric()) {
      return raw;
    }
    return ParameterizedTypeName.get(raw, wildcards(d.typeVariables().size()));
  }

  private TypeName[] wildcards(final int size) {
    final WildcardTypeName wildcard = subtypeOf(ClassName.get(Object.class));
    final TypeName[] wildcards = new TypeName[size];
    for (int i = 0; i < size; i++) {
      wildcards[i] = wildcard;
    }
    return wildcards;
  }

  private TypeName valueType(final Descriptor d) {
    final ClassName raw = rawValueType(d);
    if (!d.isGeneric()) {
      return raw;
    }
    return ParameterizedTypeName.get(raw, d.typeArguments());
  }

  private TypeName upperBoundedValueType(final Descriptor d) {
    final ClassName raw = rawValueType(d);
    if (!d.isGeneric()) {
      return raw;
    }
    return ParameterizedTypeName.get(raw, upperBounded(d.typeArguments()));
  }

  private TypeName valueImplType(final Descriptor d) {
    final ClassName raw = valueImplClass(d);
    if (!d.isGeneric()) {
      return raw;
    }
    return ParameterizedTypeName.get(raw, d.typeArguments());
  }

  private ClassName valueImplClass(Descriptor d) {
    final ClassName raw;
    if (d.isRecord()) {
      raw = rawValueType(d);
    } else {
      raw = rawValueImplType(d);
    }
    return raw;
  }

  private ClassName rawValueImplType(final Descriptor d) {
    return rawBuilderType(d).nestedClass("Value");
  }

  private TypeName fieldType(final Descriptor d, final ExecutableElement field) {
    return d.fieldTypeName(field);
  }

  private TypeName upperBoundedType(TypeMirror type) throws AutoMatterProcessorException {
    return upperBoundedType(type, Integer.MAX_VALUE);
  }

  private TypeName upperBoundedType(TypeMirror type, int limit) {
    if (type.getKind() != DECLARED) {
      return TypeName.get(type);
    }
    if (limit == 0) {
      return TypeName.get(type);
    }
    final DeclaredType declaredType = (DeclaredType) type;
    if (declaredType.getTypeArguments().isEmpty()) {
      return TypeName.get(type);
    }

    final ClassName raw = ClassName.get((TypeElement) declaredType.asElement());
    final int n = declaredType.getTypeArguments().size();
    final TypeName[] typeNames = new TypeName[n];
    for (int i = 0; i < n; i++) {
      final TypeMirror typeArgument = declaredType.getTypeArguments().get(i);
      final TypeName upperBoundedTypeArgument = upperBoundedType(typeArgument, limit - 1);
      typeNames[i] = subtypeOf(upperBoundedTypeArgument);
    }
    return ParameterizedTypeName.get(raw, typeNames);
  }

  private TypeName genericArgument(Descriptor d, final ExecutableElement field, int index) {
    final ParameterizedTypeName fieldType = (ParameterizedTypeName) fieldType(d, field);
    return fieldType.typeArguments.get(index);
  }

  private TypeName collectionImplType(Descriptor d, final ExecutableElement field) {
    switch (collectionType(field)) {
      case "List":
        return ParameterizedTypeName.get(
            ClassName.get(ArrayList.class), genericArgument(d, field, 0));
      case "Set":
        return ParameterizedTypeName.get(
            ClassName.get(HashSet.class), genericArgument(d, field, 0));
      case "SortedSet":
      case "NavigableSet":
        return ParameterizedTypeName.get(
            ClassName.get(TreeSet.class), genericArgument(d, field, 0));
      case "Map":
        return ParameterizedTypeName.get(
            ClassName.get(HashMap.class),
            genericArgument(d, field, 0),
            genericArgument(d, field, 1));
      case "SortedMap":
      case "NavigableMap":
        return ParameterizedTypeName.get(
            ClassName.get(TreeMap.class),
            genericArgument(d, field, 0),
            genericArgument(d, field, 1));
      case "Collection":
        return ParameterizedTypeName.get(
            ClassName.get(ArrayList.class), genericArgument(d, field, 0));
      default:
        throw new IllegalStateException("invalid collection type " + field);
    }
  }

  private ClassName collectionRawType(final ExecutableElement field) {
    final DeclaredType type = (DeclaredType) field.getReturnType();
    return ClassName.get("java.util", type.asElement().getSimpleName().toString());
  }

  /**
   * Get the canonical string representation of a type, stripping any TYPE_USE annotations. This is
   * necessary because TYPE_USE annotations (like JSpecify's @Nullable) are included in the
   * toString() output, which breaks string-based type checks.
   */
  public static String getCanonicalTypeName(final TypeMirror type) {
    // For DeclaredType (like List<String>), get the qualified name and type arguments
    if (type.getKind() == DECLARED) {
      final DeclaredType declaredType = (DeclaredType) type;
      final String qualifiedName =
          ((TypeElement) declaredType.asElement()).getQualifiedName().toString();
      if (declaredType.getTypeArguments().isEmpty()) {
        return qualifiedName;
      }
      return qualifiedName
          + "<"
          + declaredType.getTypeArguments().stream()
              .map(AutoMatterProcessor::getCanonicalTypeName)
              .collect(Collectors.joining(", "))
          + ">";
    }
    // For other types, just use toString()
    return type.toString();
  }

  private static String optionalEmptyName(final ExecutableElement field) {
    final String returnType = getCanonicalTypeName(field.getReturnType());
    if (returnType.startsWith("com.google.common.base.Optional<")) {
      return "absent";
    }
    return "empty";
  }

  private static String optionalMaybeName(final ExecutableElement field) {
    final String returnType = getCanonicalTypeName(field.getReturnType());
    if (returnType.startsWith("com.google.common.base.Optional<")) {
      return "fromNullable";
    }
    return "ofNullable";
  }

  private boolean isCollection(final ExecutableElement field) {
    final String returnType = getCanonicalTypeName(field.getReturnType());
    return returnType.startsWith("java.util.List<")
        || returnType.startsWith("java.util.Collection<")
        || returnType.startsWith("java.util.Set<")
        || returnType.startsWith("java.util.SortedSet<")
        || returnType.startsWith("java.util.NavigableSet<");
  }

  private boolean isCollectionInterface(final ExecutableElement field) {
    final String returnType = getCanonicalTypeName(field.getReturnType());
    return returnType.startsWith("java.util.Collection<");
  }

  private String unmodifiableCollection(final ExecutableElement field) {
    final String type = collectionType(field);
    switch (type) {
      case "List":
        return "unmodifiableList";
      case "Set":
        return "unmodifiableSet";
      case "SortedSet":
        return "unmodifiableSortedSet";
      case "NavigableSet":
        return "unmodifiableNavigableSet";
      case "Map":
        return "unmodifiableMap";
      case "SortedMap":
        return "unmodifiableSortedMap";
      case "NavigableMap":
        return "unmodifiableNavigableMap";
      case "Collection":
        return "unmodifiableList";
      default:
        throw new AssertionError();
    }
  }

  private String emptyCollection(final ExecutableElement field) {
    final String type = collectionType(field);
    switch (type) {
      case "List":
        return "emptyList";
      case "Set":
        return "emptySet";
      case "SortedSet":
        return "emptySortedSet";
      case "NavigableSet":
        return "emptyNavigableSet";
      case "Map":
        return "emptyMap";
      case "SortedMap":
        return "emptySortedMap";
      case "NavigableMap":
        return "emptyNavigableMap";
      case "Collection":
        return "emptyList";
      default:
        throw new AssertionError();
    }
  }

  private String collectionType(final ExecutableElement field) {
    final String returnType = getCanonicalTypeName(field.getReturnType());
    if (returnType.startsWith("java.util.List<")) {
      return "List";
    } else if (returnType.startsWith("java.util.Set<")) {
      return "Set";
    } else if (returnType.startsWith("java.util.SortedSet<")) {
      return "SortedSet";
    } else if (returnType.startsWith("java.util.NavigableSet<")) {
      return "NavigableSet";
    } else if (returnType.startsWith("java.util.Map<")) {
      return "Map";
    } else if (returnType.startsWith("java.util.SortedMap<")) {
      return "SortedMap";
    } else if (returnType.startsWith("java.util.NavigableMap<")) {
      return "NavigableMap";
    } else if (returnType.startsWith("java.util.Collection<")) {
      return "Collection";
    } else {
      throw new AssertionError();
    }
  }

  private String optionalType(final ExecutableElement field) {
    final String returnType = getCanonicalTypeName(field.getReturnType());
    if (returnType.startsWith("java.util.Optional<")) {
      return "java.util.Optional";
    } else if (returnType.startsWith("com.google.common.base.Optional<")) {
      return "com.google.common.base.Optional";
    }
    return returnType;
  }

  private boolean isMap(final ExecutableElement field) {
    final String returnType = getCanonicalTypeName(field.getReturnType());
    return returnType.startsWith("java.util.Map<")
        || returnType.startsWith("java.util.SortedMap<")
        || returnType.startsWith("java.util.NavigableMap<");
  }

  private boolean isPrimitive(final ExecutableElement field) {
    return field.getReturnType().getKind().isPrimitive();
  }

  private boolean isOptional(final ExecutableElement field) {
    final String returnType = getCanonicalTypeName(field.getReturnType());
    return returnType.startsWith("java.util.Optional<")
        || returnType.startsWith("com.google.common.base.Optional<");
  }

  private String singular(final String name) {
    final String singular = INFLECTOR.singularize(name);
    if (KEYWORDS.contains(singular)) {
      return null;
    }
    if (elements.getTypeElement("java.lang." + singular) != null) {
      return null;
    }
    return name.equals(singular) ? null : singular;
  }

  private String variableName(final String name, final String... scope) {
    return variableName(name, Stream.of(scope).collect(toSet()));
  }

  private String variableName(final String name, final Set<String> scope) {
    if (!scope.contains(name)) {
      return name;
    }
    return variableName("_" + name, scope);
  }

  private String fieldName(final ExecutableElement field) {
    return field.getSimpleName().toString();
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return Collections.singleton(AutoMatter.class.getName());
  }

  private boolean shouldEnforceNonNull(final ExecutableElement field) {
    return !isPrimitive(field) && !isNullableAnnotated(field);
  }

  private boolean isNullableAnnotated(final ExecutableElement field) {
    return nullableAnnotation(field) != null;
  }

  private AnnotationMirror nullableAnnotation(final ExecutableElement field) {
    // Check for @Nullable on the method itself (for METHOD-targeted annotations like
    // javax.annotation.Nullable)
    for (AnnotationMirror annotation : field.getAnnotationMirrors()) {
      if (annotation.getAnnotationType().asElement().getSimpleName().contentEquals("Nullable")) {
        return annotation;
      }
    }

    // Check for @Nullable on the return type (for TYPE_USE annotations like JSpecify's @Nullable)
    final TypeMirror returnType = field.getReturnType();
    for (AnnotationMirror annotation : returnType.getAnnotationMirrors()) {
      if (annotation.getAnnotationType().asElement().getSimpleName().contentEquals("Nullable")) {
        return annotation;
      }
    }

    return null;
  }

  private AutoMatterProcessorException fail(final String msg, final Element element)
      throws AutoMatterProcessorException {
    throw new AutoMatterProcessorException(msg, element);
  }

  private static String capitalizeFirstLetter(String s) {
    if (s == null) {
      throw new NullPointerException("s");
    }
    if (s.isEmpty()) {
      return "";
    }
    return s.substring(0, 1).toUpperCase() + (s.length() > 1 ? s.substring(1) : "");
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }
}
