package io.norberg.automatter.example;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface CustomToStringFoobarStatic {
  String foo();
  int bar();

  @AutoMatter.ToString
  static String toString(CustomToStringFoobarStatic v) {
    return v.foo() + " " + v.bar();
  }
}
