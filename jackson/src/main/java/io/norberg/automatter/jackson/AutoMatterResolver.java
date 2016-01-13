package io.norberg.automatter.jackson;

import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import io.norberg.automatter.AutoMatter;

class AutoMatterResolver extends AbstractTypeResolver {

  private static final String VALUE_SUFFIX = "Builder$Value";

  private final ConcurrentMap<JavaType, JavaType> types =
      new ConcurrentHashMap<JavaType, JavaType>();

  @Override
  public JavaType resolveAbstractType(final DeserializationConfig config, final JavaType type) {
    final Class<?> rawClass = type.getRawClass();
    final AutoMatter annotation = rawClass.getAnnotation(AutoMatter.class);
    if (annotation == null) {
      // This was not an @AutoMatter type.
      return super.resolveAbstractType(config, type);
    }

    // Return the cached type, if present.
    final JavaType cached = types.get(type);
    if (cached != null) {
      return cached;
    }

    // Look up and instantiate the value class
    final String packageName = rawClass.getPackage().getName();
    final String name = rawClass.getSimpleName();
    final String valueName = packageName + '.' + name + VALUE_SUFFIX;
    final Class<?> cls;
    try {
      cls = Class.forName(valueName);
    } catch (ClassNotFoundException e) {
      throw new IllegalArgumentException("No builder found for @AutoMatter type: " + name, e);
    }
    final JavaType materialized = config.getTypeFactory().constructType(cls);

    // Cache the materialized type before returning
    final JavaType existing = types.putIfAbsent(type, materialized);
    return (existing != null) ? existing : materialized;
  }
}
