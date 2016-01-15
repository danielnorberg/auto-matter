package io.norberg.automatter.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import io.norberg.automatter.gson.AutoMatterTypeAdapterFactory;

import static java.lang.System.out;

public class SimpleGenericGsonExample {

  public static void main(final String... args) throws IOException {
    // Register the AutoMatterTypeAdapterFactory to handle deserialization
    Gson gson = new GsonBuilder()
        .registerTypeAdapterFactory(new AutoMatterTypeAdapterFactory())
        .create();

    GenericFoobar<String> foobar = new GenericFoobarBuilder<String>()
        .foo("hello world")
        .bar(17)
        .build();

    String json = gson.toJson(foobar);
    out.println("json: " + json);

    GenericFoobar<String> parsed = gson.fromJson(json, new TypeToken<GenericFoobar<String>>() {}.getType());
    out.println("parsed: " + parsed);

    out.println("equals: " + foobar.equals(parsed));

  }
}
