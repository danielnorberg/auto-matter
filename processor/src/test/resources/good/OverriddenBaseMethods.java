package foo;

import io.norberg.automatter.AutoMatter;

interface Base {
  String foo();
}

@AutoMatter
public interface OverriddenBaseMethods extends Base {
  default String foo() {
    return "";
  }

  String baz();
}