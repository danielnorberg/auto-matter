package io.norberg.automatter.gson;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

class AutoMatterTypeAdapter<T> extends TypeAdapter<T> {

  private final TypeAdapter<JsonElement> elementAdapter;
  private final ImmutableMap<String, List<String>> serializedNameMethods;
  private final TypeAdapter<T> delegate;


  static <T> AutoMatterTypeAdapter<T> createForInterface(
      final Gson gson,
      final Class<T> cls,
      final ImmutableMap<String, List<String>> serializedNameMethods
  ) {
    return new AutoMatterTypeAdapter<>(gson, gson.getAdapter(cls), serializedNameMethods);
  }

  static <T> AutoMatterTypeAdapter<T> createForValue(
      final Gson gson,
      final TypeAdapterFactory skipFactory,
      final TypeToken<T> type,
      final ImmutableMap<String, List<String>> serializedNameMethods
  ) {
    return new AutoMatterTypeAdapter<>(gson,
                                       gson.getDelegateAdapter(skipFactory, type),
                                       serializedNameMethods);
  }

  private AutoMatterTypeAdapter(final Gson gson,
                                final TypeAdapter<T> delegate,
                                final ImmutableMap<String, List<String>> serializedNameMethods) {
    this.delegate = delegate;
    this.serializedNameMethods = serializedNameMethods;
    elementAdapter = gson.getAdapter(JsonElement.class);
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public void write(final JsonWriter out, final T value) throws IOException {
    final JsonElement tree = delegate.toJsonTree(value);
    if (tree.isJsonObject()) {
      for (Map.Entry<String, List<String>> entry : serializedNameMethods.entrySet()) {
        final String fieldName = entry.getKey();
        final List<String> alternatives = entry.getValue();
        final JsonObject asJsonObject = tree.getAsJsonObject();
        final JsonElement element = asJsonObject.get(fieldName);
        if (element != null) {
          asJsonObject.remove(fieldName);
          asJsonObject.add(alternatives.get(0), element);
        }
      }
    }
    elementAdapter.write(out, tree);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public T read(final JsonReader in) throws IOException {
    final JsonElement tree = elementAdapter.read(in);
    if (tree.isJsonObject()) {
      for (Map.Entry<String, List<String>> entry : serializedNameMethods.entrySet()) {
        final String fieldName = entry.getKey();
        final List<String> alternatives = entry.getValue();
        final JsonObject asJsonObject = tree.getAsJsonObject();
        for (String alternative : alternatives) {
          final JsonElement element = asJsonObject.get(alternative);
          if (element != null) {
            asJsonObject.remove(alternative);
            asJsonObject.add(fieldName, element);
            break;
          }
        }
      }
    }
    return delegate.fromJsonTree(tree);
  }
}