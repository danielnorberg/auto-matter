package io.norberg.automatter.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import io.norberg.automatter.jackson.AutoMatterModule;

import static java.lang.System.out;

public class SimpleGenericJacksonExample {

  public static void main(final String... args) throws IOException {
    // Register the AutoMatterModule to handle deserialization
    ObjectMapper mapper = new ObjectMapper()
        .registerModule(new AutoMatterModule());

    GenericFoobar<Integer> foobar = new GenericFoobarBuilder<Integer>()
        .foo(17)
        .bar(1, 2, 3)
        .putBaz("hello world", 4711)
        .build();

    String json = mapper.writeValueAsString(foobar);
    out.println("json: " + json);

    GenericFoobar<Integer> parsed = mapper.readValue(json, new TypeReference<GenericFoobar<Integer>>() {});
    out.println("parsed: " + parsed);

    out.println("equals: " + foobar.equals(parsed));
  }
}
