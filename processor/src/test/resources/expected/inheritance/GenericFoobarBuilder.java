package inheritance;

import io.norberg.automatter.AutoMatter;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
@AutoMatter.Generated
final class GenericFoobarBuilder<T> {
  private String foo;

  private T bar;

  private T Quux;

  private int baz;

  public GenericFoobarBuilder() {
  }

  private GenericFoobarBuilder(GenericFoobar<? extends T> v) {
    this.foo = v.foo();
    this.bar = v.bar();
    this.Quux = v.Quux();
    this.baz = v.baz();
  }

  private GenericFoobarBuilder(Quux<? extends T> v) {
    this.Quux = v.Quux();
  }

  private GenericFoobarBuilder(GenericFoobarBuilder<? extends T> v) {
    this.foo = v.foo();
    this.bar = v.bar();
    this.Quux = v.Quux();
    this.baz = v.baz();
  }

  private GenericFoobarBuilder(QuuxBuilder<? extends T> v) {
    this.Quux = v.Quux();
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

  public T Quux() {
    return Quux;
  }

  public GenericFoobarBuilder<T> Quux(T Quux) {
    if (Quux == null) {
      throw new NullPointerException("Quux");
    }
    this.Quux = Quux;
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
    return new Value<T>(foo, bar, Quux, baz);
  }

  public static <T> GenericFoobarBuilder<T> from(GenericFoobar<? extends T> v) {
    return new GenericFoobarBuilder<T>(v);
  }

  public static <T> GenericFoobarBuilder<T> from(Quux<? extends T> v) {
    return new GenericFoobarBuilder<T>(v);
  }

  public static <T> GenericFoobarBuilder<T> from(GenericFoobarBuilder<? extends T> v) {
    return new GenericFoobarBuilder<T>(v);
  }

  public static <T> GenericFoobarBuilder<T> from(QuuxBuilder<? extends T> v) {
    return new GenericFoobarBuilder<T>(v);
  }

  @AutoMatter.Generated
  private static final class Value<T> implements GenericFoobar<T> {
    private final String foo;

    private final T bar;

    private final T Quux;

    private final int baz;

    private Value(@AutoMatter.Field("foo") String foo, @AutoMatter.Field("bar") T bar,
        @AutoMatter.Field("Quux") T Quux, @AutoMatter.Field("baz") int baz) {
      if (foo == null) {
        throw new NullPointerException("foo");
      }
      if (bar == null) {
        throw new NullPointerException("bar");
      }
      if (Quux == null) {
        throw new NullPointerException("Quux");
      }
      this.foo = foo;
      this.bar = bar;
      this.Quux = Quux;
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
    public T Quux() {
      return Quux;
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
      if (Quux != null ? !Quux.equals(that.Quux()) : that.Quux() != null) {
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
      result = 31 * result + (this.foo != null ? this.foo.hashCode() : 0);
      result = 31 * result + (this.bar != null ? this.bar.hashCode() : 0);
      result = 31 * result + (this.Quux != null ? this.Quux.hashCode() : 0);
      result = 31 * result + this.baz;
      return result;
    }

    @Override
    public String toString() {
      return "GenericFoobar{" +
          "foo=" + foo +
          ", bar=" + bar +
          ", Quux=" + Quux +
          ", baz=" + baz +
          '}';
    }
  }
}
