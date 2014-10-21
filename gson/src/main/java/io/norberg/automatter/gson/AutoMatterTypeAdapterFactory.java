package io.norberg.automatter.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import io.norberg.automatter.AutoMatter;

public class AutoMatterTypeAdapterFactory implements TypeAdapterFactory {

  private final ConcurrentMap<TypeToken, TypeAdapter> adapters =
      new ConcurrentHashMap<TypeToken, TypeAdapter>();

  @SuppressWarnings("unchecked")
  @Override
  public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
    final AutoMatter annotation = type.getRawType().getAnnotation(AutoMatter.class);
    if (annotation == null) {
      // This was not an @AutoMatter type.
      return null;
    }

    // Return the cached type, if present.
    final TypeAdapter cached = adapters.get(type);
    if (cached != null) {
      return cached;
    }

    // Look up and instantiate the value class
    final Class<T> cls = resolve(type);

    final TypeAdapter<T> materialized = gson.getAdapter(cls);

    // Cache the materialized type before returning
    final TypeAdapter<T> existing = adapters.putIfAbsent(type, materialized);
    return (existing != null) ? existing : materialized;
  }

  private <T> Class<T> resolve(final TypeToken<T> type) {
    final String name = type.getRawType().getName();
    final String valueName = name + "Builder$Value";
    try {
      return (Class<T>) Class.forName(valueName);
    } catch (ClassNotFoundException e) {
      return resolveAutoValue(type);
    }
  }

  private <T> Class<T> resolveAutoValue(final TypeToken<T> type) {
    final String name = type.getRawType().getName();
    final String valueName = namespace(name) + ".AutoValue_" + simple(name);
    try {
      return (Class<T>) Class.forName(valueName);
    } catch (ClassNotFoundException e) {
      throw new IllegalArgumentException("No builder found for @AutoMatter type: " + name);
    }
  }

  private String namespace(final String name) {
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
