package io.norberg.automatter.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import io.norberg.automatter.AutoMatter;

import static io.norberg.automatter.gson.AutoMatterTypeAdapter.createForInterface;
import static io.norberg.automatter.gson.AutoMatterTypeAdapter.createForValue;
import static java.util.Arrays.asList;

public class AutoMatterTypeAdapterFactory implements TypeAdapterFactory {

  private static final String VALUE_SUFFIX = "Builder$Value";

  private final ConcurrentMap<TypeToken, TypeAdapter> adapters = new ConcurrentHashMap<>();


  @SuppressWarnings("unchecked")
  @Override
  public <T> TypeAdapter<T> create(final Gson gson, final TypeToken<T> type) {
    final TypeAdapter<T> materialized;
    final AutoMatter annotation = type.getRawType().getAnnotation(AutoMatter.class);

    if (annotation != null) {
      // We are now the proud owners of an AutoMatter annotated interface.

      // Return the cached type, if present.
      final TypeAdapter cached = adapters.get(type);
      if (cached != null) {
        return cached;
      }

      // Look up and instantiate the value class
      final String name = type.getRawType().getName();
      final int lastDollar = name.lastIndexOf("$");

      final String valueName;
      if (lastDollar > -1) {
        final int lastDot = name.lastIndexOf(".");
        valueName =
            name.substring(0, lastDot + 1).concat(name.substring(lastDollar + 1)) + VALUE_SUFFIX;

      } else {
        valueName = name + VALUE_SUFFIX;
      }

      final Class<T> cls;

      try {
        cls = (Class<T>) Class.forName(valueName);
      } catch (ClassNotFoundException e) {
        throw new IllegalArgumentException("No builder found for @AutoMatter type: " + name, e);
      }

      // Find those magic remapping-of-name-annotations (SerializedName)
      final Map<String, List<String>>
          serializedNameMethods = getSerializedNameMethods(gson, type.getRawType(), cls);

      // If the interface passed to us didn't have any SerializedName annotations, go the fast path
      // and just pass it on the type adapter chain,
      // it will most likely end up in the ReflectiveTypeAdapterFactory, good riddance!
      // If it was annotated, we create a TypeAdapter that knows how to poke the json into
      // Java world submission.
      materialized = serializedNameMethods.isEmpty()
                     ? gson.getAdapter(cls)
                     : createForInterface(gson, cls, serializedNameMethods);
    } else {
      // Maybe a value class with SerializedName annotations?

      final Map<String, List<String>>
          serializedNameMethodsbuilder = new HashMap<>();

      // Since AutoMatter supports inheritance we need to walk through all of the interfaces with
      // AutoMatter annotations.
      final Class<? super T> valueClass = type.getRawType();
      for (Class<?> itf : valueClass.getInterfaces()) {
        if (itf.getAnnotation(AutoMatter.class) != null) {
          serializedNameMethodsbuilder.putAll(getSerializedNameMethods(gson, itf, valueClass));
        }
      }

      final Map<String, List<String>>
          serializedNameMethods = serializedNameMethodsbuilder;

      // Either what we were passed wasn't a value class or it was not annotated with SerializedName
      // either way, we don't have to care, pass it on to the chain of factories that might be
      // applicable. Bye, bye! This should be the fast path.
      if (serializedNameMethods.isEmpty()) {
        return null;
      }
      // We create a TypeAdapter that knows how to read between the lines (A.K.A is annotation aware)
      // and can translate the restricted Java world names to beautiful free form json fields. Nom!
      materialized = createForValue(gson, this, type, serializedNameMethods);
    }
    // Cache the materialized type before returning
    final TypeAdapter<T> existing = adapters.putIfAbsent(type, materialized);
    return (existing != null) ? existing : materialized;
  }

  private <T> Map<String, List<String>> getSerializedNameMethods(
      final Gson gson, final Class<T> c, final Class<?> valueClass) {
    final Map<String, List<String>>
        methodToAnnotation = new HashMap<>();

    for (Method method : c.getMethods()) {
      if (method.isAnnotationPresent(SerializedName.class)) {
        final SerializedName serializedName = method.getAnnotation(SerializedName.class);
        List<String> values = new ArrayList<>();
        values.add(serializedName.value());
        values.addAll(asList(serializedName.alternate()));
        methodToAnnotation.put(
            translateField(gson, method.getName(), valueClass),
            values
        );
      }

    }

    return methodToAnnotation;
  }

  private String translateField(final Gson gson, final String fieldName, final Class<?> valueClass) {
    final Field field;
    try {
      field = valueClass.getDeclaredField(fieldName);
    } catch (NoSuchFieldException e) {
      throw new RuntimeException(e);
    }
    return gson.fieldNamingStrategy().translateName(field);
  }

}
