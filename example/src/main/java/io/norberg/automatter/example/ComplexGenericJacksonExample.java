package io.norberg.automatter.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

import io.norberg.automatter.jackson.AutoMatterModule;

import static java.lang.System.out;
import static java.util.Arrays.asList;

public class ComplexGenericJacksonExample {

  public static void main(final String... args) throws IOException {
    // Register the AutoMatterModule to handle deserialization
    ObjectMapper mapper = new ObjectMapper()
        .registerModule(new AutoMatterModule());

    ComplexGenericFoobar<String, Integer, List<Integer>, ComparableList<Integer>> foobar =
        new ComplexGenericFoobarBuilder<String, Integer, List<Integer>, ComparableList<Integer>>()
            .foo("foo")
            .bar(17)
            .baz(asList(13, 4711))
            .quux(ComparableList.of(1, 2, 3))
            .name("generics")
            .build();

    String json = mapper.writeValueAsString(foobar);
    out.println("json: " + json);

    ComplexGenericFoobar<String, Integer, List<Integer>, ComparableList<Integer>> parsed = mapper.readValue(
        json, new TypeReference<ComplexGenericFoobar<String, Integer, List<Integer>, ComparableList<Integer>>>() {});

    out.println("parsed: " + parsed);

    out.println("equals: " + foobar.equals(parsed));
  }
}
