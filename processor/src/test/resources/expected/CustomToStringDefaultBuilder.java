package foo;

import io.norberg.automatter.AutoMatter;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
public final class CustomToStringDefaultBuilder {
  private String foo;

  public CustomToStringDefaultBuilder() {
  }

  private CustomToStringDefaultBuilder(CustomToStringDefault v) {
    this.foo = v.foo();
  }

  private CustomToStringDefaultBuilder(CustomToStringDefaultBuilder v) {
    this.foo = v.foo();
  }

  public String foo() {
    return foo;
  }

  public CustomToStringDefaultBuilder foo(String foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    this.foo = foo;
    return this;
  }

  public CustomToStringDefault build() {
    return new Value(foo);
  }

  public static CustomToStringDefaultBuilder from(CustomToStringDefault v) {
    return new CustomToStringDefaultBuilder(v);
  }

  public static CustomToStringDefaultBuilder from(CustomToStringDefaultBuilder v) {
    return new CustomToStringDefaultBuilder(v);
  }

  private static final class Value implements CustomToStringDefault {
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

    public CustomToStringDefaultBuilder builder() {
      return new CustomToStringDefaultBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof CustomToStringDefault)) {
        return false;
      }
      final CustomToStringDefault that = (CustomToStringDefault) o;
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
      return overrideToString();
    }
  }
}