package io.norberg.automatter.example;

import java.util.List;
import java.util.Map;

import io.norberg.automatter.AutoMatter;

@AutoMatter
interface GenericFoobar<T> {
  T foo();
  List<T> bar();
  Map<String, T> bazs();
}
