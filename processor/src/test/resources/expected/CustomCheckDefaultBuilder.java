package foo;

import io.norberg.automatter.AutoMatter;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
@AutoMatter.Generated
public final class CustomCheckDefaultBuilder {
  private String foo;

  public CustomCheckDefaultBuilder() {
  }

  private CustomCheckDefaultBuilder(CustomCheckDefault v) {
    this.foo = v.foo();
  }

  private CustomCheckDefaultBuilder(CustomCheckDefaultBuilder v) {
    this.foo = v.foo();
  }

  public String foo() {
    return foo;
  }

  public CustomCheckDefaultBuilder foo(String foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    this.foo = foo;
    return this;
  }

  public CustomCheckDefault build() {
    return new Value(foo);
  }

  public static CustomCheckDefaultBuilder from(CustomCheckDefault v) {
    return new CustomCheckDefaultBuilder(v);
  }

  public static CustomCheckDefaultBuilder from(CustomCheckDefaultBuilder v) {
    return new CustomCheckDefaultBuilder(v);
  }

  private static final class Value implements CustomCheckDefault {
    private final String foo;

    private Value(@AutoMatter.Field("foo") String foo) {
      if (foo == null) {
        throw new NullPointerException("foo");
      }
      this.foo = foo;
      check();
    }

    @AutoMatter.Field
    @Override
    public String foo() {
      return foo;
    }

    public CustomCheckDefaultBuilder builder() {
      return new CustomCheckDefaultBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof CustomCheckDefault)) {
        return false;
      }
      final CustomCheckDefault that = (CustomCheckDefault) o;
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
      return "CustomCheckDefault{" +
      "foo=" + foo +
      '}';
    }
  }
}
