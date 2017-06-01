package io.norberg.automatter.processor;

import com.google.common.collect.Lists;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

import javax.lang.model.element.ExecutableElement;

import java.util.Collections;
import java.util.List;

import static com.squareup.javapoet.WildcardTypeName.subtypeOf;
import static io.norberg.automatter.processor.AutoMatterProcessor.builderType;
import static javax.lang.model.element.Modifier.PUBLIC;

public class OptionalProcessor extends DefaultProcessor {
  @Override
  public Iterable<MethodSpec> accessors(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException {
    List<MethodSpec> methods = Lists.newArrayList();
    methods.add(getter(d, field));
    methods.add(optionalRawSetter(d, field));
    methods.add(optionalSetter(d, field));
    return methods;
  }

  @Override
  public Iterable<Statement> defaultConstructor(Descriptor d, ExecutableElement field) {
    if (shouldEnforceNonNull(field)) {
      ClassName type = ClassName.bestGuess(optionalType(field));
      return Collections.singletonList(
          new Statement("this.$N = $T.$L()", fieldName(field), type, optionalEmptyName(field)));
    }
    return Collections.emptyList();
  }

  private MethodSpec optionalRawSetter(final Descriptor d, final ExecutableElement field) {
    String fieldName = fieldName(field);
    ClassName type = ClassName.bestGuess(optionalType(field));
    TypeName valueType = genericArgument(field, 0);

    return MethodSpec.methodBuilder(fieldName)
        .addModifiers(PUBLIC)
        .addParameter(valueType, fieldName)
        .returns(builderType(d))
        .addStatement("return $N($T.$N($N))", fieldName, type, optionalMaybeName(field), fieldName)
        .build();
  }

  private MethodSpec optionalSetter(final Descriptor d, final ExecutableElement field)
      throws AutoMatterProcessorException {
    String fieldName = fieldName(field);
    TypeName valueType = genericArgument(field, 0);
    ClassName optionalType = ClassName.bestGuess(optionalType(field));
    TypeName parameterType = ParameterizedTypeName.get(optionalType, subtypeOf(valueType));

    AnnotationSpec suppressUncheckedAnnotation = AnnotationSpec.builder(SuppressWarnings.class)
        .addMember("value", "$S", "unchecked")
        .build();

    MethodSpec.Builder setter = MethodSpec.methodBuilder(fieldName)
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

  private String optionalType(final ExecutableElement field) {
    final String returnType = field.getReturnType().toString();
    if (returnType.startsWith("java.util.Optional<")) {
      return "java.util.Optional";
    } else if (returnType.startsWith("com.google.common.base.Optional<")) {
      return "com.google.common.base.Optional";
    }
    return returnType;
  }

  private static String optionalEmptyName(final ExecutableElement field) {
    final String returnType = field.getReturnType().toString();
    if (returnType.startsWith("com.google.common.base.Optional<")) {
      return "absent";
    }
    return "empty";
  }

  private static String optionalMaybeName(final ExecutableElement field) {
    final String returnType = field.getReturnType().toString();
    if (returnType.startsWith("com.google.common.base.Optional<")) {
      return "fromNullable";
    }
    return "ofNullable";
  }
}
