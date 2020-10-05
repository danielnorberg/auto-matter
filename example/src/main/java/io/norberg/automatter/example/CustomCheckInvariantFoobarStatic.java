package io.norberg.automatter.example;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface CustomCheckInvariantFoobarStatic {
  String foo();
  int bar();

  @AutoMatter.CheckInvariant
  static void toString(CustomCheckInvariantFoobarStatic v) {
    assert v.foo().length() < v.bar() : "bar needs to be greater than length of foo";
  }
}
