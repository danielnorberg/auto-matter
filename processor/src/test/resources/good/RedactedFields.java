package foo;

import io.norberg.automatter.AutoMatter.Redacted;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface RedactedFields {
  String userName();

  @Redacted
  String password();

  @Redacted(value = "....")
  String token();
}
