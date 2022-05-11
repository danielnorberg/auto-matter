package io.norberg.automatter.jackson;

import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import io.norberg.automatter.AutoMatter;

class AutoMatterResolver extends AbstractTypeResolver {

  private final ValueTypeCache typeCache;

  AutoMatterResolver(final ValueTypeCache typeCache) {
    this.typeCache = typeCache;
  }

  @SuppressWarnings("deprecation")
  public JavaType resolveAbstractType(DeserializationConfig config, JavaType type) {
    return resolveAbstractType0(config, type);
  }

  public JavaType resolveAbstractType(DeserializationConfig config, BeanDescription typeDesc) {
    return resolveAbstractType0(config, typeDesc.getType());
  }

  private JavaType resolveAbstractType0(final DeserializationConfig config, JavaType type) {
    if (!type.isInterface()) {
      // Only resolve interface style @AutoMatter types.
      return null;
    }

    final AutoMatter annotation = type.getRawClass().getAnnotation(AutoMatter.class);
    if (annotation == null) {
      // This was not an @AutoMatter type.
      return null;
    }

    // Resolve from cache.
    return typeCache.resolveValueType(type);
  }
}
