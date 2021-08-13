package io.norberg.automatter.jackson;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import io.norberg.automatter.AutoMatter;

class AutoMatterAnnotationIntrospector extends NopAnnotationIntrospector {

  private static final long serialVersionUID = 1L;
  private final ValueTypeCache typeCache;

  AutoMatterAnnotationIntrospector(final ValueTypeCache typeCache) {
    this.typeCache = typeCache;
  }

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

  @Override
  public JavaType refineSerializationType(
      final MapperConfig<?> config, final Annotated a, final JavaType baseType)
      throws JsonMappingException {
    final Class<?> rawClass = baseType.getRawClass();

    // Refine only if baseType is explicitly annotated with @AutoMatter
    if (rawClass.isAnnotationPresent(AutoMatter.class)) {
      return typeCache.resolveValueType(rawClass);
    }
    return super.refineSerializationType(config, a, baseType);
  }
}
