package io.norberg.automatter.example;

import static java.lang.System.out;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.norberg.automatter.gson.AutoMatterTypeAdapterFactory;
import java.io.IOException;

public class GsonExample {

  public static void main(final String... args) throws IOException {
    // Register the AutoMatterTypeAdapterFactory to handle deserialization
    Gson gson =
        new GsonBuilder().registerTypeAdapterFactory(new AutoMatterTypeAdapterFactory()).create();

    Foobar foobar = new FoobarBuilder().bar(17).foo("hello world").build();

    String json = gson.toJson(foobar);
    out.println("json: " + json);

    Foobar parsed = gson.fromJson(json, Foobar.class);
    out.println("parsed: " + parsed);

    out.println("equals: " + foobar.equals(parsed));
  }
}
