package io.norberg.automatter.example;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface CustomToStringFoobarDefault {
  String foo();
  int bar();

  @AutoMatter.ToString
  default String overrideToString() {
    return foo() + " " + bar();
  }
}
