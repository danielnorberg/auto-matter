package io.norberg.automatter.example;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface CustomCheckFoobarStatic {
  String foo();
  int bar();

  @AutoMatter.Check
  static void check(CustomCheckFoobarStatic v) {
    assert v.foo().length() < v.bar() : "bar needs to be greater than length of foo";
  }
}
