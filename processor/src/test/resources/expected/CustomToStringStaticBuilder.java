package foo;

import io.norberg.automatter.AutoMatter;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
public final class CustomToStringStaticBuilder {
  private String foo;

  public CustomToStringStaticBuilder() {
  }

  private CustomToStringStaticBuilder(CustomToStringStatic v) {
    this.foo = v.foo();
  }

  private CustomToStringStaticBuilder(CustomToStringStaticBuilder v) {
    this.foo = v.foo();
  }

  public String foo() {
    return foo;
  }

  public CustomToStringStaticBuilder foo(String foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    this.foo = foo;
    return this;
  }

  public CustomToStringStatic build() {
    return new Value(foo);
  }

  public static CustomToStringStaticBuilder from(CustomToStringStatic v) {
    return new CustomToStringStaticBuilder(v);
  }

  public static CustomToStringStaticBuilder from(CustomToStringStaticBuilder v) {
    return new CustomToStringStaticBuilder(v);
  }

  private static final class Value implements CustomToStringStatic {
    private final String foo;

    private Value(@AutoMatter.Field("foo") String foo) {
      if (foo == null) {
        throw new NullPointerException("foo");
      }
      this.foo = foo;
    }

    @AutoMatter.Field
    @Override
    public String foo() {
      return foo;
    }

    public CustomToStringStaticBuilder builder() {
      return new CustomToStringStaticBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof CustomToStringStatic)) {
        return false;
      }
      final CustomToStringStatic that = (CustomToStringStatic) o;
      if (foo != null ? !foo.equals(that.foo()) : that.foo() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (this.foo != null ? this.foo.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return CustomToStringStatic.toString(this);
    }
  }
}