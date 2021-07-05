package io.norberg.automatter.example;

import static java.lang.System.out;

import java.io.IOException;

public class SimpleExample {

  public static void main(final String... args) throws IOException {
    Foobar foobar = new FoobarBuilder().foo("hello world").bar(17).build();

    out.println("bar: " + foobar.bar());
    out.println("foo: " + foobar.foo());
    out.println("foobar: " + foobar);

    Foobar modified = foobar.builder().bar(18).build();

    out.println("modified: " + modified);
  }
}
