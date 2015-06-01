package foo;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface DefaultMethods {
  String foo();

  default String foobar() {
    return foo() + "bar";
  }
}