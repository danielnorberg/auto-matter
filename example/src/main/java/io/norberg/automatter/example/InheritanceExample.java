package io.norberg.automatter.example;

import io.norberg.automatter.AutoMatter;

public class InheritanceExample {

  interface Foo {
    String foo();
  }

  interface Bar<T> {
    T bar();
  }

  @AutoMatter
  interface Baz extends Foo, Bar<Integer> {
    int baz();
  }

  public static void main(final String... args) {
    Baz baz = new BazBuilder()
        .foo("hello")
        .bar(17)
        .baz(4711)
        .build();

    System.out.println(baz);
  }
}
