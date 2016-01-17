package io.norberg.automatter.example;

import java.io.IOException;

public class SimpleGenericExample {

  public static void main(final String... args) throws IOException {
    GenericFoobar<Integer> foobar = new GenericFoobarBuilder<Integer>()
        .foo(17)
        .bar(1, 2, 3)
        .putBaz("hello world", 4711)
        .build();

    System.out.println(foobar);
  }
}
