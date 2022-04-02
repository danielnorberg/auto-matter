package foo;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public record FoobarRecordWithMethod(int a, String b) {

  String ab() {
    return a + b;
  }
}
