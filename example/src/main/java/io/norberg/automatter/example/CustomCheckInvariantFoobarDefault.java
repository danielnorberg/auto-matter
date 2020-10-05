package io.norberg.automatter.example;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface CustomCheckInvariantFoobarDefault {
  String foo();
  int bar();

  @AutoMatter.CheckInvariant
  default void checkInvariant() {
    if (foo().length() >= bar()) {
      throw new IllegalArgumentException("bar needs to be greater than lenth of foo");
    }
  }
}
