package io.norberg.automatter.gson;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface NestedGson {

  Nestee cutee();

  @AutoMatter
  interface Nestee {
    String floof();
  }
}
