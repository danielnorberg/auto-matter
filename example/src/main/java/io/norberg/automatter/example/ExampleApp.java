package io.norberg.automatter.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import io.norberg.automatter.jackson.AutoMatterModule;

import static java.lang.System.out;

public class ExampleApp {

  public static final ObjectMapper MAPPER = new ObjectMapper()
      .registerModule(new AutoMatterModule());

  public static void main(final String... args) throws IOException {
    Example example = new ExampleBuilder()
        .bar(17)
        .foo("hello world")
        .build();

    out.println("bar: " + example.bar());
    out.println("foo: " + example.foo());

    String json = MAPPER.writeValueAsString(example);
    out.println("json: " + json);

    Example parsed = MAPPER.readValue(json, Example.class);
    out.println("parsed: " + parsed);
  }
}
