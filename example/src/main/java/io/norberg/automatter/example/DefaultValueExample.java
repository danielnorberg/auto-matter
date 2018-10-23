package io.norberg.automatter.example;

import static java.lang.System.out;

import java.io.IOException;

public class DefaultValueExample {

  public static void main(final String... args) {
    DefaultValueFoobar foobarWithFooDefault = new DefaultValueFoobarBuilder()
        .baz("baz")
        .build();

    // prints: DefaultValueFoobar{baz=baz, foo=foo, bar=foo bar baz}
    out.println("default values only: " + foobarWithFooDefault);

    DefaultValueFoobar foobarWithFooValue = new DefaultValueFoobarBuilder()
        .baz("baz")
        .foo("hello")
        .build();

    // prints: DefaultValueFoobar{baz=baz, foo=hello, bar=hello bar baz}
    out.println("with foo value: " + foobarWithFooValue);
  }
}
