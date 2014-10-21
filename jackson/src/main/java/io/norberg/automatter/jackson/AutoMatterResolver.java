package io.norberg.automatter.jackson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.SimpleType;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import io.norberg.automatter.AutoMatter;

class AutoMatterResolver extends AbstractTypeResolver {

  private final ConcurrentMap<JavaType, JavaType> types =
      new ConcurrentHashMap<JavaType, JavaType>();

  @Override
  public JavaType resolveAbstractType(final DeserializationConfig config, final JavaType type) {
    final AutoMatter annotation = type.getRawClass().getAnnotation(AutoMatter.class);
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
    final Class<?> cls = resolve(type);
    final JavaType materialized = SimpleType.construct(cls);

    // Cache the materialized type before returning
    final JavaType existing = types.putIfAbsent(type, materialized);
    return (existing != null) ? existing : materialized;
  }

  private Class<?> resolve(final JavaType type) {
    // Look for @JsonCreator annotations
    for (Method method : type.getRawClass().getDeclaredMethods()) {
      if (method.isAnnotationPresent(JsonCreator.class)) {
        return type.getRawClass();
      }
    }

    // Look for an AutoMatter generated value class
    final String name = type.getRawClass().getName();
    try {
      return Class.forName(autoMatterName(name));
    } catch (ClassNotFoundException e) {
      throw new IllegalArgumentException(
          "No implementation found for @AutoMatter type: " + name, e);
    }
  }

  private String autoMatterName(final String name) {
    return name + "Builder$Value";
  }

  private String pkg(final String name) {
    final int lastDot = name.lastIndexOf('.');
    if (lastDot == -1) {
      return "";
    }
    return name.substring(0, lastDot);
  }

  private String simple(final String name) {
    final int lastDot = name.lastIndexOf('.');
    return name.substring(lastDot + 1);
  }
}
