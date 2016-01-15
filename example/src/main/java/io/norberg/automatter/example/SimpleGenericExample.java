package io.norberg.automatter.example;

import java.io.IOException;

import io.norberg.automatter.AutoMatter;

public class SimpleGenericExample {

  @AutoMatter
  interface GenericFoobar<T> {
    T foo();
    int bar();
  }

  public static void main(final String... args) throws IOException {
    final GenericFoobar<String> foobar = new GenericFoobarBuilder<String>()
        .foo("foo")
        .bar(17)
        .build();

    System.out.println(foobar);
  }
}
