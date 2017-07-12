package io.norberg.automatter.processor;

import com.google.auto.service.AutoService;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import com.squareup.javapoet.WildcardTypeName;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Generated;
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
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import io.norberg.automatter.AutoMatter;

import static com.squareup.javapoet.WildcardTypeName.subtypeOf;
import static io.norberg.automatter.processor.Common.builderType;
import static io.norberg.automatter.processor.Common.rawBuilderType;
import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PRIVATE;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;
import static javax.lang.model.type.TypeKind.ARRAY;
import static javax.tools.Diagnostic.Kind.ERROR;

/**
 * An annotation processor that takes a value type defined as an interface with getter methods and
 * materializes it, generating a concrete builder and value class.
 */
@AutoService(Processor.class)
public final class AutoMatterProcessor extends AbstractProcessor {

  private static final Splitter FIELD_SPLITTER = Splitter.on("<");

  private Filer filer;
  private Elements elements;
  private Messager messager;
  private Types types;
  private Map<String, FieldProcessor> processors;
  private final DefaultProcessor defaultProcessor = new DefaultProcessor();


  @Override
  public synchronized void init(final ProcessingEnvironment processingEnv) {
    super.init(processingEnv);
    filer = processingEnv.getFiler();
    elements = processingEnv.getElementUtils();
    types = processingEnv.getTypeUtils();
    messager = processingEnv.getMessager();

    CollectionProcessor collectionProcessor = new CollectionProcessor(elements);
    OptionalProcessor optionalProcessor = new OptionalProcessor();

    processors = ImmutableMap.of(
      "java.util.Map", collectionProcessor,
      "java.util.List", collectionProcessor,
      "java.util.Set", collectionProcessor,
      "java.util.Optional", optionalProcessor,
      "com.google.common.base.Optional", optionalProcessor
    );
  }

  @Override
  public boolean process(final Set<? extends TypeElement> annotations,
                         final RoundEnvironment env) {
    final Set<? extends Element> elements = env.getElementsAnnotatedWith(AutoMatter.class);
    for (Element element : elements) {
      try {
        process(element);
      } catch (IOException e) {
        messager.printMessage(ERROR, e.getMessage());
      } catch (AutoMatterProcessorException e) {
        e.print(messager);
      }
    }
    return false;
  }

  private void process(final Element element) throws IOException, AutoMatterProcessorException {
    final Descriptor d = new Descriptor(element, elements, types);

    TypeSpec builder = builder(d);
    JavaFile javaFile = JavaFile.builder(d.packageName(), builder)
        .skipJavaLangImports(true)
        .build();
    javaFile.writeTo(filer);
  }

  private FieldProcessor processorForField(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException {
    String prefix = FIELD_SPLITTER.split(fieldType(d, field).toString()).iterator().next();
    return processors.getOrDefault(prefix, defaultProcessor);
  }

  private TypeSpec builder(final Descriptor d) throws AutoMatterProcessorException {
    AnnotationSpec generatedAnnotation = AnnotationSpec.builder(Generated.class)
        .addMember("value", "$S", AutoMatterProcessor.class.getName())
        .build();

    TypeSpec.Builder builder = TypeSpec.classBuilder(d.builderName())
        .addTypeVariables(d.typeVariables())
        .addModifiers(FINAL)
        .addAnnotation(generatedAnnotation);

    if (d.isPublic()) {
      builder.addModifiers(PUBLIC);
    }

    for (ExecutableElement field : d.fields()) {
      builder.addField(processorForField(d, field).builderField(d, field));
    }

    builder.addMethod(defaultConstructor(d));
    builder.addMethod(copyValueConstructor(d));
    builder.addMethod(copyBuilderConstructor(d));

    for (MethodSpec accessor : accessors(d)) {
      builder.addMethod(accessor);
    }

    if (d.hasToBuilder()) {
      builder.addMethod(toBuilder(d));
    }

    builder.addMethod(build(d));
    builder.addMethod(fromValue(d));
    builder.addMethod(fromBuilder(d));

    builder.addType(valueClass(d));

    return builder.build();
  }

  private MethodSpec defaultConstructor(final Descriptor d) throws AutoMatterProcessorException {
    MethodSpec.Builder constructor = MethodSpec.constructorBuilder()
        .addModifiers(PUBLIC);

    for (ExecutableElement field : d.fields()) {
      for (Statement s : processorForField(d, field).defaultConstructor(d, field)) {
        constructor.addStatement(s.statement, s.args);
      }
    }

    return constructor.build();
  }

  private MethodSpec copyValueConstructor(final Descriptor d) throws AutoMatterProcessorException {
    final MethodSpec.Builder constructor = MethodSpec.constructorBuilder()
        .addModifiers(PRIVATE)
        .addParameter(upperBoundedValueType(d), "v");

    for (ExecutableElement field : d.fields()) {
      for (Statement s : processorForField(d, field).copyValueConstructor(d, field)) {
        constructor.addStatement(s.statement, s.args);
      }
    }

    return constructor.build();
  }

  private MethodSpec copyBuilderConstructor(final Descriptor d) throws AutoMatterProcessorException {
    final MethodSpec.Builder constructor = MethodSpec.constructorBuilder()
        .addModifiers(PRIVATE)
        .addParameter(upperBoundedBuilderType(d), "v");

    for (ExecutableElement field : d.fields()) {
      for (Statement s : processorForField(d, field).copyBuilderConstructor(d, field)) {
        constructor.addStatement(s.statement, s.args);
      }
    }

    return constructor.build();
  }

  private Set<MethodSpec> accessors(final Descriptor d) throws AutoMatterProcessorException {
    ImmutableSet.Builder<MethodSpec> result = ImmutableSet.builder();
    for (ExecutableElement field : d.fields()) {
      for (MethodSpec method: processorForField(d, field).accessors(d, field)) {
        result.add(method);
      }
    }
    return result.build();
  }

  private MethodSpec toBuilder(final Descriptor d) {
    return MethodSpec.methodBuilder("builder")
        .addModifiers(PUBLIC)
        .returns(builderType(d))
        .addStatement("return new $T(this)", builderType(d))
        .build();
  }

  private MethodSpec build(final Descriptor d) throws AutoMatterProcessorException {
    MethodSpec.Builder build = MethodSpec.methodBuilder("build")
        .addModifiers(PUBLIC)
        .returns(valueType(d));

    final List<String> parameters = Lists.newArrayList();
    for (ExecutableElement field : d.fields()) {
      BuildStatements buildStatements = processorForField(d, field).build(d, field);
      for (Statement s : buildStatements.statements) {
        build.addStatement(s.statement, s.args);
      }
      parameters.add(buildStatements.parameter);
    }

    return build.addStatement("return new $T($N)", valueImplType(d), Joiner.on(", ").join(parameters)).build();
  }

  private MethodSpec fromValue(final Descriptor d) {
    return MethodSpec.methodBuilder("from")
        .addModifiers(PUBLIC, STATIC)
        .addTypeVariables(d.typeVariables())
        .addParameter(upperBoundedValueType(d), "v")
        .returns(builderType(d))
        .addStatement("return new $T(v)", builderType(d))
        .build();
  }

  private MethodSpec fromBuilder(final Descriptor d) {
    return MethodSpec.methodBuilder("from")
        .addModifiers(PUBLIC, STATIC)
        .addTypeVariables(d.typeVariables())
        .addParameter(upperBoundedBuilderType(d), "v")
        .returns(builderType(d))
        .addStatement("return new $T(v)", builderType(d))
        .build();
  }

  private TypeSpec valueClass(final Descriptor d) throws AutoMatterProcessorException {
    TypeSpec.Builder value = TypeSpec.classBuilder("Value")
        .addTypeVariables(d.typeVariables())
        .addModifiers(PRIVATE, STATIC, FINAL)
        .addSuperinterface(valueType(d));

    for (ExecutableElement field : d.fields()) {
      value.addField(processorForField(d, field).valueField(d, field));
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

  private MethodSpec valueConstructor(final Descriptor d) throws AutoMatterProcessorException {
    MethodSpec.Builder constructor = MethodSpec.constructorBuilder()
        .addModifiers(PRIVATE);

    for (ExecutableElement field : d.fields()) {
      if (shouldEnforceNonNull(field) && !isCollection(field) && !isMap(field)) {
        assertNotNull(constructor, fieldName(field));
      }
    }

    for (ExecutableElement field : d.fields()) {
      String fieldName = fieldName(field);
      AnnotationSpec annotation = AnnotationSpec.builder(AutoMatter.Field.class)
          .addMember("value", "$S", fieldName)
          .build();
      ParameterSpec parameter = ParameterSpec.builder(fieldType(d, field), fieldName)
          .addAnnotation(annotation)
          .build();
      constructor.addParameter(parameter);

      for (Statement s : processorForField(d, field).valueConstructor(d, field)) {
        constructor.addStatement(s.statement, s.args);
      }
    }

    return constructor.build();
  }

  private MethodSpec valueGetter(final Descriptor d, final ExecutableElement field) throws AutoMatterProcessorException {
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
    MethodSpec.Builder toBuilder = MethodSpec.methodBuilder("builder")
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
    MethodSpec.Builder equals = MethodSpec.methodBuilder("equals")
        .addAnnotation(Override.class)
        .addModifiers(PUBLIC)
        .addParameter(ClassName.get(Object.class), "o")
        .returns(TypeName.BOOLEAN);

    equals.beginControlFlow("if (this == o)")
        .addStatement("return true")
        .endControlFlow();

    equals.beginControlFlow("if (!(o instanceof $T))", rawValueType(d))
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

  private CodeBlock fieldNotEqualCheck(final ExecutableElement field) throws AutoMatterProcessorException {
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
        result.beginControlFlow("if (!$T.equals($L, that.$L()))", ClassName.get(Arrays.class), name, name);
        break;
      case TYPEVAR:
      case DECLARED:
        result.beginControlFlow(
            "if ($L != null ? !$L.equals(that.$L()) : that.$L() != null)",
            name, name, name, name);
        break;
      case ERROR:
        throw fail("Cannot resolve type, might be missing import: " + returnType, field);
      default:
        throw fail("Unsupported type: " + returnType, field);
    }

    result.addStatement("return false").endControlFlow();
    return result.build();
  }

  private MethodSpec valueHashCode(final Descriptor d) throws AutoMatterProcessorException {
    MethodSpec.Builder hashcode = MethodSpec.methodBuilder("hashCode")
        .addAnnotation(Override.class)
        .addModifiers(PUBLIC)
        .returns(TypeName.INT)
        .addStatement("int result = 1")
        .addStatement("long temp");

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
              name, ClassName.get(Float.class), name);
          break;
        case DOUBLE:
          hashcode.addStatement("temp = $T.doubleToLongBits($N)", ClassName.get(Double.class), name);
          hashcode.addStatement("result = 31 * result + (int) (temp ^ (temp >>> 32))");
          break;
        case ARRAY:
          hashcode.addStatement(
              "result = 31 * result + ($N != null ? $T.hashCode($N) : 0)",
              name, ClassName.get(Arrays.class), name);
          break;
        case TYPEVAR:
        case DECLARED:
          hashcode.addStatement("result = 31 * result + ($N != null ? $N.hashCode() : 0)", name, name);
          break;
        case ERROR:
          throw fail("Cannot resolve type, might be missing import: " + type, field);
        default:
          throw fail("Unsupported type: " + type, field);
      }
    }
    return hashcode.addStatement("return result").build();
  }

  private MethodSpec valueToString(final Descriptor d) {
    MethodSpec.Builder toString = MethodSpec.methodBuilder("toString")
        .addAnnotation(Override.class)
        .addModifiers(PUBLIC)
        .returns(ClassName.get(String.class));

    toString.addCode("return \"$L{\" +\n", d.valueTypeName());

    for (int i = 0; i < d.fields().size(); i++) {
      final ExecutableElement field = d.fields().get(i);
      final String comma = (i == 0) ? "" : ", ";
      final String name = fieldName(field);

      if (field.getReturnType().getKind() == ARRAY) {
        toString.addCode("\"$L$L=\" + $T.toString($L) +\n", comma, name, ClassName.get(Arrays.class), name);
      } else {
        toString.addCode("\"$L$L=\" + $L +\n", comma, name, name);
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

  private TypeName upperBoundedBuilderType(final Descriptor d) {
    final ClassName raw = rawBuilderType(d);
    if (!d.isGeneric()) {
      return raw;
    }
    return ParameterizedTypeName.get(raw, upperBounded(d.typeVariables()));
  }

  private TypeName[] upperBounded(final List<TypeVariableName> typeVariables) {
    final TypeName[] typeNames = new TypeName[typeVariables.size()];
    for (int i = 0; i < typeVariables.size(); i++) {
      typeNames[i] = subtypeOf(typeVariables.get(i));
    }
    return typeNames;
  }


  private ClassName rawValueType(final Descriptor d) {
    return ClassName.get(d.packageName(), d.valueTypeName());
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
    return ParameterizedTypeName.get(raw, upperBounded(d.typeVariables()));
  }

  private TypeName valueImplType(final Descriptor d) {
    final ClassName raw = rawValueImplType(d);
    if (!d.isGeneric()) {
      return raw;
    }
    return ParameterizedTypeName.get(raw, d.typeArguments());
  }

  private ClassName rawValueImplType(final Descriptor d) {
    return rawBuilderType(d).nestedClass("Value");
  }

  private TypeName fieldType(final Descriptor d, final ExecutableElement field) throws AutoMatterProcessorException {
    final TypeMirror returnType = field.getReturnType();
    if (returnType.getKind() == TypeKind.ERROR) {
      throw fail("Cannot resolve type, might be missing import: " + returnType, field);
    }
    final TypeMirror fieldType = d.fieldTypes().get(field);
    return TypeName.get(fieldType);
  }

  private boolean isCollection(final ExecutableElement field) {
    final String returnType = field.getReturnType().toString();
    return returnType.startsWith("java.util.List<") ||
           returnType.startsWith("java.util.Set<");
  }

  private boolean isMap(final ExecutableElement field) {
    final String returnType = field.getReturnType().toString();
    return returnType.startsWith("java.util.Map<");
  }

  private boolean isPrimitive(final ExecutableElement field) {
    return field.getReturnType().getKind().isPrimitive();
  }



  private String fieldName(final ExecutableElement field) {
    return field.getSimpleName().toString();
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return ImmutableSet.of(AutoMatter.class.getName());
  }

  private boolean shouldEnforceNonNull(final ExecutableElement field) {
    return !isPrimitive(field) && !isNullableAnnotated(field);
  }

  private boolean isNullableAnnotated(final ExecutableElement field) {
    return nullableAnnotation(field) != null;
  }

  private AnnotationMirror nullableAnnotation(final ExecutableElement field) {
    for (AnnotationMirror annotation : field.getAnnotationMirrors()) {
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

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }
}
