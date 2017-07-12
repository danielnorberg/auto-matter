package io.norberg.automatter.processor;

import com.squareup.javapoet.TypeName;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

import static com.google.common.base.Preconditions.checkArgument;
import static io.norberg.automatter.processor.Common.fail;

class Fields {
  static TypeName fieldType(final Descriptor d, final ExecutableElement field) throws AutoMatterProcessorException {
    final TypeMirror returnType = field.getReturnType();
    if (returnType.getKind() == TypeKind.ERROR) {
      throw fail("Cannot resolve type, might be missing import: " + returnType, field);
    }
    final TypeMirror fieldType = d.fieldTypes().get(field);
    return TypeName.get(fieldType);
  }

  static String fieldName(final ExecutableElement field) {
    return field.getSimpleName().toString();
  }

  static boolean isPrimitive(final ExecutableElement field) {
    return field.getReturnType().getKind().isPrimitive();
  }

  static AnnotationMirror nullableAnnotation(final ExecutableElement field) {
    for (AnnotationMirror annotation : field.getAnnotationMirrors()) {
      if (annotation.getAnnotationType().asElement().getSimpleName().contentEquals("Nullable")) {
        return annotation;
      }
    }
    return null;
  }

  static boolean isNullableAnnotated(final ExecutableElement field) {
    return nullableAnnotation(field) != null;
  }

  static boolean shouldEnforceNonNull(final ExecutableElement field) {
    return !isPrimitive(field) && !isNullableAnnotated(field);
  }

  static TypeName genericArgument(final ExecutableElement field, int index) {
    final DeclaredType type = (DeclaredType) field.getReturnType();
    checkArgument(type.getTypeArguments().size() >= index);
    return TypeName.get(type.getTypeArguments().get(index));
  }
}
