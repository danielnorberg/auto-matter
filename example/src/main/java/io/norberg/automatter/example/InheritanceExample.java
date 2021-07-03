package io.norberg.automatter.example;

import io.norberg.automatter.AutoMatter;

public class InheritanceExample {

  @AutoMatter
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
    // Create builder and set values of inherited fields
    Baz baz = new BazBuilder()
        .foo("hello")
        .bar(17)
        .baz(4711)
        .build();

    Foo foo = new FooBuilder()
        .foo("world")
        .build();

    // Create builder from inherited value type
    BazBuilder.from(foo)
        .bar(123)
        .baz(456)
        .build();

    System.out.println(baz);
  }
}
