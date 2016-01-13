package io.norberg.automatter.example;

import java.io.IOException;

public class SimpleGenericExample {

  public static void main(final String... args) throws IOException {
    final GenericFoobar<String> foobar = new GenericFoobarBuilder<String>()
        .foo("foo")
        .bar(17)
        .build();

    System.out.println(foobar);
  }
}
