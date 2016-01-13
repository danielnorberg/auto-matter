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

    GenericFoobar<String> foobar = new GenericFoobarBuilder<String>()
        .bar(17)
        .foo("hello world")
        .build();

    String json = mapper.writeValueAsString(foobar);
    out.println("json: " + json);

    GenericFoobar<String> parsed = mapper.readValue(json, new TypeReference<GenericFoobar<String>>() {});
    out.println("parsed: " + parsed);

    out.println("equals: " + foobar.equals(parsed));
  }
}
