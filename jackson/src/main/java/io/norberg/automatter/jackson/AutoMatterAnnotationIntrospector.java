package io.norberg.automatter.jackson;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;

import io.norberg.automatter.AutoMatter;

class AutoMatterAnnotationIntrospector extends NopAnnotationIntrospector {

  private static final long serialVersionUID = 1L;

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

  @Override
  public boolean hasCreatorAnnotation(final Annotated a) {
    if (!(a instanceof AnnotatedConstructor)) {
      return false;
    }
    final AnnotatedConstructor ctor = (AnnotatedConstructor) a;
    if (ctor.getParameterCount() == 0) {
      return true;
    }
    final AutoMatter.Field field = ctor.getParameter(0).getAnnotation(AutoMatter.Field.class);
    return field != null;
  }
}
