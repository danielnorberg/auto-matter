package io.norberg.automatter.example;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface DefaultValueFoobar {

  String baz();

  @AutoMatter.Field
  default String foo() {
    return "foo";
  }

  @AutoMatter.Field
  default String bar() {
    // Default valued fields can refer to other fields
    // Note: The fields referred to must be _above_ the referring field.
    return foo() + " bar " + baz();
  }
}
