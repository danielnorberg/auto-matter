package io.norberg.automatter.example;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface CustomCheckFoobarDefault {
  String foo();

  int bar();

  @AutoMatter.Check
  default void check() {
    if (foo().length() >= bar()) {
      throw new IllegalArgumentException("bar needs to be greater than length of foo");
    }
  }
}
