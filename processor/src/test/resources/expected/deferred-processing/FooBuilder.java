package foo;

import io.norberg.automatter.AutoMatter;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
public final class FooBuilder {
  private BarBuilder foo;

  public FooBuilder() {
  }

  private FooBuilder(Foo v) {
    this.foo = v.foo();
  }

  private FooBuilder(FooBuilder v) {
    this.foo = v.foo();
  }

  public BarBuilder foo() {
    return foo;
  }

  public FooBuilder foo(BarBuilder foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    this.foo = foo;
    return this;
  }

  public Foo build() {
    return new Value(foo);
  }

  public static FooBuilder from(Foo v) {
    return new FooBuilder(v);
  }

  public static FooBuilder from(FooBuilder v) {
    return new FooBuilder(v);
  }

  private static final class Value implements Foo {
    private final BarBuilder foo;

    private Value(@AutoMatter.Field("foo") BarBuilder foo) {
      if (foo == null) {
        throw new NullPointerException("foo");
      }
      this.foo = foo;
    }

    @AutoMatter.Field
    @Override
    public BarBuilder foo() {
      return foo;
    }

    public FooBuilder builder() {
      return new FooBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Foo)) {
        return false;
      }
      final Foo that = (Foo) o;
      if (foo != null ? !foo.equals(that.foo()) : that.foo() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      result = 31 * result + (this.foo != null ? this.foo.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "Foo{" +
          "foo=" + foo +
          '}';
    }
  }
}