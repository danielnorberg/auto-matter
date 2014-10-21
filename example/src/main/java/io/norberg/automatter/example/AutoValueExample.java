package io.norberg.automatter.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import io.norberg.automatter.gson.AutoMatterTypeAdapterFactory;
import io.norberg.automatter.jackson.AutoMatterModule;

import static java.lang.System.out;

public class AutoValueExample {

  public static void main(final String... args) throws IOException {
    AutoValueFoobar foobar = new AutoValueFoobarBuilder()
        .bar(17)
        .foo("hello world")
        .build();

    out.println("bar: " + foobar.bar());
    out.println("foo: " + foobar.foo());
    out.println("foobar: " + foobar);

    AutoValueFoobar modified = AutoValueFoobarBuilder.from(foobar)
        .bar(18)
        .build();

    out.println("modified: " + modified);

    // Jackson
    {
      ObjectMapper mapper = new ObjectMapper()
          .registerModule(new AutoMatterModule());

      String json = mapper.writeValueAsString(foobar);
      out.println("json: " + json);

      AutoValueFoobar parsed = mapper.readValue(json, AutoValueFoobar.class);
      out.println("parsed: " + parsed);

      out.println("equals: " + foobar.equals(parsed));
    }

    // Gson
    {
      Gson gson = new GsonBuilder()
          .registerTypeAdapterFactory(new AutoMatterTypeAdapterFactory())
          .create();

      String json = gson.toJson(foobar);
      out.println("json: " + json);

      AutoValueFoobar parsed = gson.fromJson(json, AutoValueFoobar.class);
      out.println("parsed: " + parsed);

      out.println("equals: " + foobar.equals(parsed));
    }
  }
}
