package test;

import javax.annotation.Generated;

@Generated("io.norberg.autobuilder.AutoMatterProcessor")
public final class FooBuilder {
  private String foo;
  private int bar;

  public FooBuilder foo(String foo) {
    return this;
  }

  public FooBuilder bar(int bar) {
    return this;
  }

  public Foo build() {
    return new Value(foo, bar);
  }

  private static final class Value implements Foo {
    private final String foo;
    private final int bar;

    private Value(String foo, int bar) {
      this.foo = foo;
      this.bar = bar;
    }

    @Override
    public String foo() {
      return foo;
    }

    @Override
    public int bar() {
      return bar;
    }
  }
}