package io.norberg.automatter.jackson;

import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import io.norberg.automatter.AutoMatter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class AutoMatterResolver extends AbstractTypeResolver {

  private static final String VALUE_SUFFIX = "Builder$Value";

  private final ConcurrentMap<Class<?>, JavaType> types = new ConcurrentHashMap<>();

  @SuppressWarnings("deprecation")
  public JavaType resolveAbstractType(DeserializationConfig config, JavaType type) {
    return resolveAbstractType0(config, type.getRawClass());
  }

  public JavaType resolveAbstractType(DeserializationConfig config, BeanDescription typeDesc) {
    return resolveAbstractType0(config, typeDesc.getBeanClass());
  }

  private JavaType resolveAbstractType0(
      final DeserializationConfig config, final Class<?> rawClass) {
    final AutoMatter annotation = rawClass.getAnnotation(AutoMatter.class);
    if (annotation == null) {
      // This was not an @AutoMatter type.
      return null;
    }

    // Return the cached type, if present.
    final JavaType cached = types.get(rawClass);
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
    final JavaType existing = types.putIfAbsent(rawClass, materialized);
    return (existing != null) ? existing : materialized;
  }
}
