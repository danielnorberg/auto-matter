package io.norberg.automatter.example;

import static java.lang.System.out;

import com.google.common.base.Optional;
import io.norberg.automatter.AutoMatter;
import java.io.IOException;

public class OptionalExample {

  @AutoMatter
  interface OptionalFoobar {
    Optional<String> foo();

    Optional<String> bar();
  }

  public static void main(final String... args) throws IOException {
    OptionalFoobar foobar = new OptionalFoobarBuilder().foo("hello").build();

    out.println("foo: " + foobar.foo());
    out.println("bar: " + foobar.bar());

    out.println("foobar: " + foobar);
  }
}
