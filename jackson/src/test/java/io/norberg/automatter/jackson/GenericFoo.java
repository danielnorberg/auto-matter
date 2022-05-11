package io.norberg.automatter.jackson;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface GenericFoo<T> {
  T a();
}
