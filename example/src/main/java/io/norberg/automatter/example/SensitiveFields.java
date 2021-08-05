package io.norberg.automatter.example;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface SensitiveFields {
  String userName();

  @AutoMatter.Sensitive
  String password();

  @AutoMatter.Sensitive(value = "....")
  String token();
}
