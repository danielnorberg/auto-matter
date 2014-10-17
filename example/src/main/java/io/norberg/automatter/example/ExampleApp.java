package io.norberg.automatter.example;

public class ExampleApp {

  public static void main(final String... args) {
    Example example = new ExampleBuilder()
        .bar(17)
        .foo("hello world")
        .build();

    System.out.println(example);
  }
}
