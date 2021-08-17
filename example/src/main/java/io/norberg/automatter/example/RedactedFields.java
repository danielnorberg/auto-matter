package io.norberg.automatter.example;

import io.norberg.automatter.AutoMatter;
import io.norberg.automatter.AutoMatter.Redacted;

@AutoMatter
public interface RedactedFields {
  String userName();

  @Redacted
  String password();

  @Redacted(value = "....")
  String token();
}
