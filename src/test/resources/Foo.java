package test;

import io.norberg.autobuilder.AutoMatter;

@AutoMatter
public interface Foo {
  String foo();
  int bar();

  static FooBuilder builder() {
    return new FooBuilder();
  }
}