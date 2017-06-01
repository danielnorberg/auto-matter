package io.norberg.automatter.processor;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ArrayTypeName;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.WildcardTypeName;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static com.squareup.javapoet.WildcardTypeName.subtypeOf;
import static io.norberg.automatter.processor.AutoMatterProcessor.builderType;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.type.TypeKind.DECLARED;

public class CollectionProcessor implements FieldProcessor {
  private final AutoMatterProcessor processor;

  public CollectionProcessor(AutoMatterProcessor processor) {
    this.processor = processor;
  }

  @Override
  public FieldSpec builderField(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException {
    return FieldSpec.builder(fieldType(d, field), fieldName(field), PRIVATE).build();
  }

  @Override
  public FieldSpec valueField(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException {
    return FieldSpec.builder(fieldType(d, field), fieldName(field), PRIVATE, FINAL).build();
  }

  @Override
  public Iterable<MethodSpec> accessors(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException {
    ArrayList<MethodSpec> result = new ArrayList<>();

    result.add(getter(d, field));
    if (isCollection(field)) {
      result.add(collectionSetter(d, field));
      result.add(collectionCollectionSetter(d, field));
      result.add(collectionIterableSetter(d, field));
      result.add(collectionIteratorSetter(d, field));
      result.add(collectionVarargSetter(d, field));

      MethodSpec adder = collectionAdder(d, field);
      if (adder != null) {
        result.add(adder);
      }
    } else {
      result.add(mapSetter(d, field));
      for (int i = 1; i <= 5; i++) {
        result.add(mapSetterPairs(d, field, i));
      }

      MethodSpec putter = mapPutter(d, field);
      if (putter != null) {
        result.add(putter);
      }
    }
    return result;
  }

  @Override
  public Iterable<Statement> defaultConstructor(Descriptor d, ExecutableElement field) {
    return Collections.emptyList();
  }

  @Override
  public Iterable<Statement> copyValueConstructor(Descriptor d, ExecutableElement field)
    throws AutoMatterProcessorException {
    ArrayList<Statement> result = new ArrayList<>();
    String fieldName = fieldName(field);
    TypeName fieldType = upperBoundedFieldType(field);
    result.add(new Statement("$T _$N = v.$N()", fieldType, fieldName, fieldName));
    result.add(new Statement("this.$N = (_$N == null) ? null : new $T(_$N)",
                              fieldName, fieldName, collectionImplType(field), fieldName));
    return result;
  }

  @Override
  public Iterable<Statement> copyBuilderConstructor(Descriptor d, ExecutableElement field)
    throws AutoMatterProcessorException {
    String fieldName = fieldName(field);
    return Collections.singletonList(new Statement(
      "this.$N = (v.$N == null) ? null : new $T(v.$N)",
      fieldName, fieldName, collectionImplType(field), fieldName));
  }

  @Override
  public BuildStatements build(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException {
    final List<Statement> statements = Lists.newArrayList();
    final String fieldName = fieldName(field);
    final TypeName fieldType = fieldType(d, field);
    final ClassName collections = ClassName.get(Collections.class);

    if (isCollection(field)) {
      final TypeName itemType = genericArgument(field, 0);

      if (shouldEnforceNonNull(field)) {
        statements.add(new Statement(
            "$T _$L = ($L != null) ? $T.$L(new $T($N)) : $T.<$T>$L()",
            fieldType, fieldName, fieldName,
            collections, unmodifiableCollection(field), collectionImplType(field), fieldName,
            collections, itemType, emptyCollection(field)));
      } else {
        statements.add(new Statement(
            "$T _$L = ($L != null) ? $T.$L(new $T($N)) : null",
            fieldType, fieldName, fieldName,
            collections, unmodifiableCollection(field), collectionImplType(field), fieldName));
      }
    } else if (isMap(field)) {
      final TypeName keyType = genericArgument(field, 0);
      final TypeName valueType = genericArgument(field, 1);

      if (shouldEnforceNonNull(field)) {
        statements.add(new Statement(
            "$T _$L = ($L != null) ? $T.unmodifiableMap(new $T($N)) : $T.<$T, $T>emptyMap()",
            fieldType, fieldName, fieldName,
            collections, collectionImplType(field), fieldName,
            collections, keyType, valueType));
      } else {
        statements.add(new Statement(
            "$T _$L = ($L != null) ? $T.unmodifiableMap(new $T($N)) : null",
            fieldType, fieldName, fieldName,
            collections, collectionImplType(field), fieldName));
      }
    }
    String parameter = "_" + fieldName;
    return new BuildStatements(statements, parameter);
  }

  @Override
  public Iterable<Statement> valueConstructor(Descriptor d, ExecutableElement field)
    throws AutoMatterProcessorException {
    final List<Statement> statements = Lists.newArrayList();
    final String fieldName = fieldName(field);
    final ClassName collectionsType = ClassName.get(Collections.class);
    if (shouldEnforceNonNull(field) && isCollection(field)) {
      final TypeName itemType = genericArgument(field, 0);
      statements.add(new Statement(
          "this.$N = ($N != null) ? $N : $T.<$T>$L()",
          fieldName, fieldName, fieldName, collectionsType, itemType, emptyCollection(field)));
    } else if (shouldEnforceNonNull(field) && isMap(field)) {
      final TypeName keyType = genericArgument(field, 0);
      final TypeName valueType = genericArgument(field, 1);
      statements.add(new Statement(
          "this.$N = ($N != null) ? $N : $T.<$T, $T>emptyMap()",
          fieldName, fieldName, fieldName, collectionsType, keyType, valueType));
    } else {
      statements.add(new Statement("this.$N = $N", fieldName, fieldName));
    }
    return statements;
  }

  private MethodSpec collectionSetter(final Descriptor d, final ExecutableElement field) {
    String fieldName = fieldName(field);
    ClassName collectionType = collectionRawType(field);
    TypeName itemType = genericArgument(field, 0);
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
    TypeName itemType = genericArgument(field, 0);
    WildcardTypeName extendedType = subtypeOf(itemType);

    MethodSpec.Builder setter = MethodSpec.methodBuilder(fieldName)
        .addModifiers(PUBLIC)
        .addParameter(ParameterizedTypeName.get(collectionType, extendedType), fieldName)
        .returns(builderType(d));

    collectionNullGuard(setter, field);
    if (shouldEnforceNonNull(field)) {
      setter.beginControlFlow("for ($T item : $N)", itemType, fieldName);
      assertNotNull(setter, "item", fieldName + ": null item");
      setter.endControlFlow();
    }

    setter.addStatement("this.$N = new $T($N)", fieldName, collectionImplType(field), fieldName);
    return setter.addStatement("return this").build();
  }

  private MethodSpec collectionIterableSetter(final Descriptor d, final ExecutableElement field) {
    String fieldName = fieldName(field);
    ClassName iterableType = ClassName.get(Iterable.class);
    TypeName itemType = genericArgument(field, 0);
    WildcardTypeName extendedType = subtypeOf(itemType);

    MethodSpec.Builder setter = MethodSpec.methodBuilder(fieldName)
        .addModifiers(PUBLIC)
        .addParameter(ParameterizedTypeName.get(iterableType, extendedType), fieldName)
        .returns(builderType(d));

    collectionNullGuard(setter, field);

    ClassName collectionType = ClassName.get(Collection.class);
    setter.beginControlFlow("if ($N instanceof $T)", fieldName, collectionType)
        .addStatement("return $N(($T<$T>) $N)", fieldName, collectionType, extendedType, fieldName)
        .endControlFlow();

    setter.addStatement("return $N($N.iterator())", fieldName, fieldName);
    return setter.build();
  }

  private MethodSpec collectionIteratorSetter(final Descriptor d, final ExecutableElement field) {
    String fieldName = fieldName(field);
    ClassName iteratorType = ClassName.get(Iterator.class);
    TypeName itemType = genericArgument(field, 0);
    WildcardTypeName extendedType = subtypeOf(itemType);

    MethodSpec.Builder setter = MethodSpec.methodBuilder(fieldName)
        .addModifiers(PUBLIC)
        .addParameter(ParameterizedTypeName.get(iteratorType, extendedType), fieldName)
        .returns(builderType(d));

    collectionNullGuard(setter, field);

    setter.addStatement("this.$N = new $T()", fieldName, collectionImplType(field))
        .beginControlFlow("while ($N.hasNext())", fieldName)
        .addStatement("$T item = $N.next()", itemType, fieldName);

    if (shouldEnforceNonNull(field)) {
      assertNotNull(setter, "item", fieldName + ": null item");
    }

    setter.addStatement("this.$N.add(item)", fieldName)
        .endControlFlow();

    return setter.addStatement("return this").build();
  }

  private MethodSpec collectionVarargSetter(final Descriptor d, final ExecutableElement field) {
    String fieldName = fieldName(field);
    TypeName itemType = genericArgument(field, 0);

    MethodSpec.Builder setter = MethodSpec.methodBuilder(fieldName)
        .addModifiers(PUBLIC)
        .addParameter(ArrayTypeName.of(itemType), fieldName)
        .varargs()
        .returns(builderType(d));

    ensureSafeVarargs(setter);

    collectionNullGuard(setter, field);

    setter.addStatement("return $N($T.asList($N))", fieldName, ClassName.get(Arrays.class), fieldName);
    return setter.build();
  }

  private void ensureSafeVarargs(MethodSpec.Builder setter) {
    // TODO: Add SafeVarargs annotation only for non-reifiable types.
    AnnotationSpec safeVarargsAnnotation = AnnotationSpec.builder(SafeVarargs.class).build();

    setter
        .addAnnotation(safeVarargsAnnotation)
        .addModifiers(FINAL); // Only because SafeVarargs can be applied to final methods.
  }

  private MethodSpec collectionAdder(final Descriptor d, final ExecutableElement field) {
    final String fieldName = fieldName(field);
    final String singular = processor.singular(fieldName);
    if (singular == null || singular.isEmpty()) {
      return null;
    }

    final String appendMethodName = "add" + capitalizeFirstLetter(singular);
    final TypeName itemType = genericArgument(field, 0);
    MethodSpec.Builder adder = MethodSpec.methodBuilder(appendMethodName)
        .addModifiers(PUBLIC)
        .addParameter(itemType, singular)
        .returns(builderType(d));

    if (shouldEnforceNonNull(field)) {
      assertNotNull(adder, singular);
    }
    lazyCollectionInitialization(adder, field);

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

  private void lazyCollectionInitialization(final MethodSpec.Builder spec, final ExecutableElement field) {
    final String fieldName = fieldName(field);
    spec.beginControlFlow("if (this.$N == null)", fieldName)
        .addStatement("this.$N = new $T()", fieldName, collectionImplType(field))
        .endControlFlow();
  }

  private String collectionType(final ExecutableElement field) {
    final String returnType = field.getReturnType().toString();
    if (returnType.startsWith("java.util.List<")) {
      return "List";
    } else if (returnType.startsWith("java.util.Set<")) {
      return "Set";
    } else if (returnType.startsWith("java.util.Map<")) {
      return "Map";
    } else {
      throw new AssertionError();
    }
  }

  private TypeName collectionImplType(final ExecutableElement field) {
    switch (collectionType(field)) {
      case "List":
        return ParameterizedTypeName.get(
            ClassName.get(ArrayList.class),
            genericArgument(field, 0));
      case "Set":
        return ParameterizedTypeName.get(
            ClassName.get(HashSet.class),
            genericArgument(field, 0));
      case "Map":
        return ParameterizedTypeName.get(
            ClassName.get(HashMap.class),
            genericArgument(field, 0), genericArgument(field, 1));
      default:
        throw new IllegalStateException("invalid collection type " + field);
    }
  }

  private ClassName collectionRawType(final ExecutableElement field) {
    final DeclaredType type = (DeclaredType) field.getReturnType();
    return ClassName.get("java.util", type.asElement().getSimpleName().toString());
  }

  private TypeName upperBoundedFieldType(final ExecutableElement field) throws AutoMatterProcessorException {
    TypeMirror type = field.getReturnType();
    if (type.getKind() == TypeKind.ERROR) {
      throw fail("Cannot resolve type, might be missing import: " + type, field);
    }
    if (type.getKind() != DECLARED) {
      return TypeName.get(type);
    }
    final DeclaredType declaredType = (DeclaredType) type;
    if (declaredType.getTypeArguments().isEmpty()) {
      return TypeName.get(type);
    }
    final ClassName raw = rawClassName(declaredType);
    if (isCollection(field)) {
      final TypeName elementType = TypeName.get(declaredType.getTypeArguments().get(0));
      return ParameterizedTypeName.get(raw, subtypeOf(elementType));
    } else if (isMap(field)) {
      final TypeName keyTypeArgument = TypeName.get(declaredType.getTypeArguments().get(0));
      final TypeName valueTypeArgument = TypeName.get(declaredType.getTypeArguments().get(1));
      return ParameterizedTypeName.get(raw, subtypeOf(keyTypeArgument), subtypeOf(valueTypeArgument));
    }
    return TypeName.get(type);
  }

  private ClassName rawClassName(final DeclaredType declaredType) {
    final String simpleName = declaredType.asElement().getSimpleName().toString();
    final String packageName = packageName(declaredType);
    return ClassName.get(packageName, simpleName);
  }

  private String packageName(final DeclaredType declaredType) {
    Element type = declaredType.asElement();
    while (type.getKind() != ElementKind.PACKAGE) {
      type = type.getEnclosingElement();
    }
    return type.toString();
  }

  private boolean isCollection(final ExecutableElement field) {
    final String returnType = field.getReturnType().toString();
    return returnType.startsWith("java.util.List<") ||
           returnType.startsWith("java.util.Set<");
  }

    private String unmodifiableCollection(final ExecutableElement field) {
    final String type = collectionType(field);
    switch (type) {
      case "List":
        return "unmodifiableList";
      case "Set":
        return "unmodifiableSet";
      case "Map":
        return "unmodifiableMap";
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
      case "Map":
        return "emptyMap";
      default:
        throw new AssertionError();
    }
  }

  private boolean isMap(final ExecutableElement field) {
    final String returnType = field.getReturnType().toString();
    return returnType.startsWith("java.util.Map<");
  }

    private MethodSpec mapSetter(final Descriptor d, final ExecutableElement field) {
    final String fieldName = fieldName(field);
    final TypeName keyType = subtypeOf(genericArgument(field, 0));
    final TypeName valueType = subtypeOf(genericArgument(field, 1));
    final TypeName paramType = ParameterizedTypeName.get(ClassName.get(Map.class), keyType, valueType);

    MethodSpec.Builder setter = MethodSpec.methodBuilder(fieldName)
        .addModifiers(PUBLIC)
        .addParameter(paramType, fieldName)
        .returns(builderType(d));

    if (shouldEnforceNonNull(field)) {
      final String entryName = variableName("entry", fieldName);
      assertNotNull(setter, fieldName);
      setter.beginControlFlow(
          "for ($T<$T, $T> $L : $N.entrySet())",
          ClassName.get(Map.Entry.class), keyType, valueType, entryName, fieldName);
      assertNotNull(setter, entryName + ".getKey()", fieldName + ": null key");
      assertNotNull(setter, entryName + ".getValue()", fieldName + ": null value");
      setter.endControlFlow();
    } else {
      setter.beginControlFlow("if ($N == null)", fieldName)
          .addStatement("this.$N = null", fieldName)
          .addStatement("return this")
          .endControlFlow();
    }

    setter.addStatement("this.$N = new $T($N)", fieldName, collectionImplType(field), fieldName);

    return setter.addStatement("return this").build();
  }

  private MethodSpec mapSetterPairs(final Descriptor d, final ExecutableElement field, int entries) {
    checkArgument(entries > 0, "entries");
    final String fieldName = fieldName(field);
    final TypeName keyType = genericArgument(field, 0);
    final TypeName valueType = genericArgument(field, 1);

    MethodSpec.Builder setter = MethodSpec.methodBuilder(fieldName)
        .addModifiers(PUBLIC)
        .returns(builderType(d));

    for (int i = 1; i < entries + 1; i++) {
      setter.addParameter(keyType, "k" + i);
      setter.addParameter(valueType, "v" + i);
    }

    // Recursion
    if (entries > 1) {
      final List<String> recursionParameters = Lists.newArrayList();
      for (int i = 1; i < entries; i++) {
        recursionParameters.add("k" + i);
        recursionParameters.add("v" + i);
      }
      setter.addStatement("$L($L)", fieldName, Joiner.on(", ").join(recursionParameters));
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
      setter.addStatement("$N = new $T()", fieldName, collectionImplType(field));
    }

    // Put
    setter.addStatement("$N.put($N, $N)", fieldName, keyName, valueName);

    return setter.addStatement("return this").build();
  }

  private MethodSpec mapPutter(final Descriptor d, final ExecutableElement field) {
    final String fieldName = fieldName(field);
    final String singular = processor.singular(fieldName);
    if (singular == null) {
      return null;
    }

    final String putSingular = "put" + capitalizeFirstLetter(singular);
    final TypeName keyType = genericArgument(field, 0);
    final TypeName valueType = genericArgument(field, 1);

    MethodSpec.Builder setter = MethodSpec.methodBuilder(putSingular)
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
    lazyMapInitialization(setter, field);
    setter.addStatement("$N.put(key, value)", fieldName);

    return setter.addStatement("return this").build();
  }

  private void lazyMapInitialization(final MethodSpec.Builder spec, final ExecutableElement field) {
    final String fieldName = fieldName(field);
    spec.beginControlFlow("if (this.$N == null)", fieldName)
        .addStatement("this.$N = new $T()", fieldName, collectionImplType(field))
        .endControlFlow();
  }

   private String variableName(final String name, final String... scope) {
    return variableName(name, ImmutableSet.copyOf(scope));
  }

  private String variableName(final String name, final Set<String> scope) {
    if (!scope.contains(name)) {
      return name;
    }
    return variableName("_" + name, scope);
  }

  private MethodSpec getter(final Descriptor d, final ExecutableElement field) throws AutoMatterProcessorException {
    String fieldName = fieldName(field);

    MethodSpec.Builder getter = MethodSpec.methodBuilder(fieldName)
      .addModifiers(PUBLIC)
      .returns(fieldType(d, field));

    if (shouldEnforceNonNull(field)) {
      getter.beginControlFlow("if (this.$N == null)", fieldName)
        .addStatement("this.$N = new $T()", fieldName, collectionImplType(field))
        .endControlFlow();
    }
    getter.addStatement("return $N", fieldName);

    return getter.build();
  }

  private String capitalizeFirstLetter(String s) {
    if (s == null) {
      throw new NullPointerException("s");
    }
    if (s.isEmpty()) {
      return "";
    }
    return s.substring(0, 1).toUpperCase() + (s.length() > 1 ? s.substring(1) : "");
  }

}
