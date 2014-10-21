package io.norberg.automatter.example;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface Foobar {
  String foo();
  int bar();

  FoobarBuilder builder();
}
