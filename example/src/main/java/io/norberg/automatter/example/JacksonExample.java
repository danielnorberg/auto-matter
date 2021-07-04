package io.norberg.automatter.example;

import static java.lang.System.out;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.norberg.automatter.jackson.AutoMatterModule;
import java.io.IOException;

public class JacksonExample {

  public static void main(final String... args) throws IOException {
    // Register the AutoMatterModule to handle deserialization
    ObjectMapper mapper = new ObjectMapper().registerModule(new AutoMatterModule());

    Foobar foobar = new FoobarBuilder().bar(17).foo("hello world").build();

    String json = mapper.writeValueAsString(foobar);
    out.println("json: " + json);

    Foobar parsed = mapper.readValue(json, Foobar.class);
    out.println("parsed: " + parsed);

    out.println("equals: " + foobar.equals(parsed));
  }
}
