package io.norberg.autobuilder.example;

public class ExampleApp {

  public static void main(final String... args) {
    Example example = new ExampleBuilder()
        .bar(17)
        .foo("hello world")
        .build();

    System.out.println("foo: " + example.bar());
    System.out.println("bar: " + example.foo());
  }
}
