package io.norberg.automatter.processor;

import static javax.lang.model.element.Modifier.DEFAULT;
import static javax.lang.model.element.Modifier.STATIC;

import io.norberg.automatter.AutoMatter;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;

class Fields {

  static boolean isCollection(final ExecutableElement field) {
    final String returnType = field.getReturnType().toString();
    return returnType.startsWith("java.util.List<") ||
           returnType.startsWith("java.util.Collection<") ||
           returnType.startsWith("java.util.Set<") ||
           returnType.startsWith("java.util.SortedSet<") ||
           returnType.startsWith("java.util.NavigableSet<");
  }

  static boolean isCollectionInterface(final ExecutableElement field) {
    final String returnType = field.getReturnType().toString();
    return returnType.startsWith("java.util.Collection<");
  }

  static boolean isMap(final ExecutableElement field) {
    final String returnType = field.getReturnType().toString();
    return returnType.startsWith("java.util.Map<") ||
           returnType.startsWith("java.util.SortedMap<") ||
           returnType.startsWith("java.util.NavigableMap<");
  }

  static boolean isPrimitive(final ExecutableElement field) {
    return field.getReturnType().getKind().isPrimitive();
  }

  static boolean isOptional(final ExecutableElement field) {
    final String returnType = field.getReturnType().toString();
    return returnType.startsWith("java.util.Optional<") ||
           returnType.startsWith("com.google.common.base.Optional<");
  }

  static boolean hasDefaultValue(ExecutableElement field) {
    return field.getAnnotation(AutoMatter.Field.class) != null;
  }

  static boolean isField(final Element member) {
    return !member.getModifiers().contains(STATIC)
        && (!member.getModifiers().contains(DEFAULT) || member.getAnnotation(AutoMatter.Field.class) != null);
  }

  static boolean isNullableAnnotated(final ExecutableElement field) {
    return nullableAnnotation(field) != null;
  }

  static AnnotationMirror nullableAnnotation(final ExecutableElement field) {
    for (AnnotationMirror annotation : field.getAnnotationMirrors()) {
      if (annotation.getAnnotationType().asElement().getSimpleName().contentEquals("Nullable")) {
        return annotation;
      }
    }
    return null;
  }
}
