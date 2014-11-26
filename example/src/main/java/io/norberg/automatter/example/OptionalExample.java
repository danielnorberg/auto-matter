package io.norberg.automatter.example;

import com.google.common.base.Optional;

import java.io.IOException;

import io.norberg.automatter.AutoMatter;

import static java.lang.System.out;

public class OptionalExample {

  @AutoMatter
  interface OptionalFoobar {
    Optional<String> foo();
    Optional<String> bar();
  }

  public static void main(final String... args) throws IOException {
    OptionalFoobar foobar = new OptionalFoobarBuilder()
        .foo("hello")
        .build();

    out.println("foo: " + foobar.foo());
    out.println("bar: " + foobar.bar());

    out.println("foobar: " + foobar);
  }
}
