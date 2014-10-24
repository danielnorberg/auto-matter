package io.norberg.automatter.jackson;

import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;

import io.norberg.automatter.AutoMatter;

class AutoMatterAnnotationIntrospector extends NopAnnotationIntrospector {

  @Override
  public String findImplicitPropertyName(final AnnotatedMember member) {
    final AutoMatter.Field field = member.getAnnotation(AutoMatter.Field.class);
    if (field == null) {
      return null;
    }
    if (member instanceof AnnotatedParameter) {
      return field.value();
    }
    if (member instanceof AnnotatedMethod) {
      return member.getName();
    }
    return null;
  }
}
