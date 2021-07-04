package io.norberg.automatter.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface Foo {
  int a();

  String b();

  boolean aCamelCaseField();

  @JsonProperty("foobar")
  boolean isReallyFoobar();
}
