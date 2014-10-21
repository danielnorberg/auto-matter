package io.norberg.automatter.example;

import com.google.auto.value.AutoValue;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.norberg.automatter.AutoMatter;

@AutoValue
@AutoMatter
public abstract class AutoValueFoobar {
  @JsonProperty public abstract String foo();
  @JsonProperty public abstract int bar();

  @JsonCreator
  static AutoValueFoobar create(@JsonProperty("foo") String foo, @JsonProperty("bar") int bar) {
    return new AutoValue_AutoValueFoobar(foo, bar);
  }
}