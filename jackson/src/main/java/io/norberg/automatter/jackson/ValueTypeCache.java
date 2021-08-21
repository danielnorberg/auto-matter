package io.norberg.automatter.jackson;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class ValueTypeCache {

  private static final String VALUE_SUFFIX = "Builder$Value";

  private final ConcurrentMap<Class<?>, JavaType> types = new ConcurrentHashMap<>();

  private final TypeFactory typeFactory;

  public ValueTypeCache(final TypeFactory typeFactory) {
    this.typeFactory = typeFactory;
  }

  JavaType resolveValueType(final Class<?> rawClass) {
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
      throw new IllegalArgumentException("No builder found for type: " + name, e);
    }
    final JavaType materialized = typeFactory.constructType(cls);

    // Cache the materialized type before returning
    final JavaType existing = types.putIfAbsent(rawClass, materialized);
    return (existing != null) ? existing : materialized;
  }
}
