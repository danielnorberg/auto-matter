package foo;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface PrivateMethods {
  String foo();

  private String bar() {
    return "bar";
  }

  default String foobar() {
    return foo() + bar();
  }
}
