package io.norberg.automatter.processor;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

import static com.google.common.base.Preconditions.checkArgument;

public interface FieldProcessor {
  FieldSpec builderField(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException;
  FieldSpec valueField(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException;

  Iterable<MethodSpec> accessors(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException;

  Iterable<Statement> defaultConstructor(Descriptor d, ExecutableElement field);
  Iterable<Statement> copyValueConstructor(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException;
  Iterable<Statement> copyBuilderConstructor(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException;
  BuildStatements build(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException;
  Iterable<Statement> valueConstructor(Descriptor d, ExecutableElement field) throws AutoMatterProcessorException;

  default TypeName fieldType(final Descriptor d, final ExecutableElement field) throws AutoMatterProcessorException {
    final TypeMirror returnType = field.getReturnType();
    if (returnType.getKind() == TypeKind.ERROR) {
      throw fail("Cannot resolve type, might be missing import: " + returnType, field);
    }
    final TypeMirror fieldType = d.fieldTypes().get(field);
    return TypeName.get(fieldType);
  }

  default String fieldName(final ExecutableElement field) {
    return field.getSimpleName().toString();
  }

  default boolean isPrimitive(final ExecutableElement field) {
    return field.getReturnType().getKind().isPrimitive();
  }

  default AnnotationMirror nullableAnnotation(final ExecutableElement field) {
    for (AnnotationMirror annotation : field.getAnnotationMirrors()) {
      if (annotation.getAnnotationType().asElement().getSimpleName().contentEquals("Nullable")) {
        return annotation;
      }
    }
    return null;
  }

  default boolean isNullableAnnotated(final ExecutableElement field) {
    return nullableAnnotation(field) != null;
  }

  default boolean shouldEnforceNonNull(final ExecutableElement field) {
    return !isPrimitive(field) && !isNullableAnnotated(field);
  }

  default void assertNotNull(MethodSpec.Builder spec, String name) {
    assertNotNull(spec, name, name);
  }

  default void assertNotNull(MethodSpec.Builder spec, String name, String msg) {
    spec.beginControlFlow("if ($N == null)", name)
        .addStatement("throw new $T($S)", ClassName.get(NullPointerException.class), msg)
        .endControlFlow();
  }

  default TypeName genericArgument(final ExecutableElement field, int index) {
    final DeclaredType type = (DeclaredType) field.getReturnType();
    checkArgument(type.getTypeArguments().size() >= index);
    return TypeName.get(type.getTypeArguments().get(index));
  }

  default AutoMatterProcessorException fail(final String msg, final Element element)
      throws AutoMatterProcessorException {
    throw new AutoMatterProcessorException(msg, element);
  }
}
