package io.norberg.automatter.example;

import io.norberg.automatter.AutoMatter;
import java.util.List;
import java.util.Map;

@AutoMatter
interface GenericFoobar<T> {
  T foo();

  List<T> bar();

  Map<String, T> bazs();
}
