package io.norberg.automatter.example;

import io.norberg.automatter.AutoMatter;

@AutoMatter
interface SuperType {

  String field();

  String overrideMe();
}

@AutoMatter
interface SubType extends SuperType {

  @Override
  default String overrideMe() {
    return "overridden hardcoded value";
  }
}
