package inheritance;

import io.norberg.automatter.AutoMatter;
import javax.annotation.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
final class GenericFoobarBuilder<T> {

  private String foo;
  private T bar;
  private int baz;

  public GenericFoobarBuilder() {
  }

  private GenericFoobarBuilder(GenericFoobar<? extends T> v) {
    this.foo = v.foo();
    this.bar = v.bar();
    this.baz = v.baz();
  }

  private GenericFoobarBuilder(GenericFoobarBuilder<? extends T> v) {
    this.foo = v.foo;
    this.bar = v.bar;
    this.baz = v.baz;
  }

  public String foo() {
    return foo;
  }

  public GenericFoobarBuilder<T> foo(String foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    this.foo = foo;
    return this;
  }

  public T bar() {
    return bar;
  }

  public GenericFoobarBuilder<T> bar(T bar) {
    if (bar == null) {
      throw new NullPointerException("bar");
    }
    this.bar = bar;
    return this;
  }

  public int baz() {
    return baz;
  }

  public GenericFoobarBuilder<T> baz(int baz) {
    this.baz = baz;
    return this;
  }

  public GenericFoobar<T> build() {
    return new Value<T>(foo, bar, baz);
  }

  public static <T> GenericFoobarBuilder<T> from(GenericFoobar<? extends T> v) {
    return new GenericFoobarBuilder<T>(v);
  }

  public static <T> GenericFoobarBuilder<T> from(GenericFoobarBuilder<? extends T> v) {
    return new GenericFoobarBuilder<T>(v);
  }

  private static final class Value<T> implements GenericFoobar<T> {

    private final String foo;
    private final T bar;
    private final int baz;

    private Value(@AutoMatter.Field("foo") String foo, @AutoMatter.Field("bar") T bar, @AutoMatter.Field("baz") int baz) {
      if (foo == null) {
        throw new NullPointerException("foo");
      }
      if (bar == null) {
        throw new NullPointerException("bar");
      }
      this.foo = foo;
      this.bar = bar;
      this.baz = baz;
    }

    @AutoMatter.Field
    @Override
    public String foo() {
      return foo;
    }

    @AutoMatter.Field
    @Override
    public T bar() {
      return bar;
    }

    @AutoMatter.Field
    @Override
    public int baz() {
      return baz;
    }

    public GenericFoobarBuilder<T> builder() {
      return new GenericFoobarBuilder<T>(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof GenericFoobar)) {
        return false;
      }

      final GenericFoobar<?> that = (GenericFoobar<?>) o;

      if (foo != null ? !foo.equals(that.foo()) : that.foo() != null) {
        return false;
      }
      if (bar != null ? !bar.equals(that.bar()) : that.bar() != null) {
        return false;
      }
      if (baz != that.baz()) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;

      result = 31 * result + (this.foo != null ? this.foo.hashCode() : 0);
      result = 31 * result + (this.bar != null ? this.bar.hashCode() : 0);
      result = 31 * result + this.baz;
      return result;
    }

    @Override
    public String toString() {
      return "GenericFoobar{" +
             "foo=" + foo +
             ", bar=" + bar +
             ", baz=" + baz +
             '}';
    }
  }
}
