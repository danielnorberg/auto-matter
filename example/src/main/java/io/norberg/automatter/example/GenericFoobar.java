package io.norberg.automatter.example;

import io.norberg.automatter.AutoMatter;

@AutoMatter
interface GenericFoobar<T> {
  T foo();
  int bar();
}
