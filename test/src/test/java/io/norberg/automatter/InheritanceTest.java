package io.norberg.automatter;

public class InheritanceTest {

  interface Foo {
    String foo();
  }

  interface Bar<T> {
    T bar();
  }

  interface Foobar extends Foo, Bar<Integer> {
    int baz();
  }

  static class FoobarValue implements Foobar {

    @Override
    public int baz() {
      return 1;
    }

    @Override
    public Integer bar() {
      return 2;
    }

    @Override
    public String foo() {
      return "hello";
    }
  }
}
