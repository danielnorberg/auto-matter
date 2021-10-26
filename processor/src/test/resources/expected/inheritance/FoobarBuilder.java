package inheritance;

import io.norberg.automatter.AutoMatter;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
@AutoMatter.Generated
final class FoobarBuilder {
  private String foo;

  private Integer bar;

  private Float Quux;

  private int baz;

  public FoobarBuilder() {
  }

  private FoobarBuilder(Foobar v) {
    this.foo = v.foo();
    this.bar = v.bar();
    this.Quux = v.Quux();
    this.baz = v.baz();
  }

  private FoobarBuilder(Quux<? extends Float> v) {
    this.Quux = v.Quux();
  }

  private FoobarBuilder(FoobarBuilder v) {
    this.foo = v.foo();
    this.bar = v.bar();
    this.Quux = v.Quux();
    this.baz = v.baz();
  }

  private FoobarBuilder(QuuxBuilder<? extends Float> v) {
    this.Quux = v.Quux();
  }

  public String foo() {
    return foo;
  }

  public FoobarBuilder foo(String foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    this.foo = foo;
    return this;
  }

  public Integer bar() {
    return bar;
  }

  public FoobarBuilder bar(Integer bar) {
    if (bar == null) {
      throw new NullPointerException("bar");
    }
    this.bar = bar;
    return this;
  }

  public Float Quux() {
    return Quux;
  }

  public FoobarBuilder Quux(Float Quux) {
    if (Quux == null) {
      throw new NullPointerException("Quux");
    }
    this.Quux = Quux;
    return this;
  }

  public int baz() {
    return baz;
  }

  public FoobarBuilder baz(int baz) {
    this.baz = baz;
    return this;
  }

  public Foobar build() {
    return new Value(foo, bar, Quux, baz);
  }

  public static FoobarBuilder from(Foobar v) {
    return new FoobarBuilder(v);
  }

  public static FoobarBuilder from(Quux<? extends Float> v) {
    return new FoobarBuilder(v);
  }

  public static FoobarBuilder from(FoobarBuilder v) {
    return new FoobarBuilder(v);
  }

  public static FoobarBuilder from(QuuxBuilder<? extends Float> v) {
    return new FoobarBuilder(v);
  }

  private static final class Value implements Foobar {
    private final String foo;

    private final Integer bar;

    private final Float Quux;

    private final int baz;

    private Value(@AutoMatter.Field("foo") String foo, @AutoMatter.Field("bar") Integer bar,
        @AutoMatter.Field("Quux") Float Quux, @AutoMatter.Field("baz") int baz) {
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
    public Integer bar() {
      return bar;
    }

    @AutoMatter.Field
    @Override
    public Float Quux() {
      return Quux;
    }

    @AutoMatter.Field
    @Override
    public int baz() {
      return baz;
    }

    public FoobarBuilder builder() {
      return new FoobarBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Foobar)) {
        return false;
      }
      final Foobar that = (Foobar) o;
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
      return "Foobar{" +
          "foo=" + foo +
          ", bar=" + bar +
          ", Quux=" + Quux +
          ", baz=" + baz +
          '}';
    }
  }
}
