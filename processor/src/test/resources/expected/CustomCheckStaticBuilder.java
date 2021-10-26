package foo;

import io.norberg.automatter.AutoMatter;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
@AutoMatter.Generated
public final class CustomCheckStaticBuilder {
  private String foo;

  public CustomCheckStaticBuilder() {
  }

  private CustomCheckStaticBuilder(CustomCheckStatic v) {
    this.foo = v.foo();
  }

  private CustomCheckStaticBuilder(CustomCheckStaticBuilder v) {
    this.foo = v.foo();
  }

  public String foo() {
    return foo;
  }

  public CustomCheckStaticBuilder foo(String foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    this.foo = foo;
    return this;
  }

  public CustomCheckStatic build() {
    return new Value(foo);
  }

  public static CustomCheckStaticBuilder from(CustomCheckStatic v) {
    return new CustomCheckStaticBuilder(v);
  }

  public static CustomCheckStaticBuilder from(CustomCheckStaticBuilder v) {
    return new CustomCheckStaticBuilder(v);
  }

  @AutoMatter.Generated
  private static final class Value implements CustomCheckStatic {
    private final String foo;

    private Value(@AutoMatter.Field("foo") String foo) {
      if (foo == null) {
        throw new NullPointerException("foo");
      }
      this.foo = foo;
      CustomCheckStatic.check(this);
    }

    @AutoMatter.Field
    @Override
    public String foo() {
      return foo;
    }

    public CustomCheckStaticBuilder builder() {
      return new CustomCheckStaticBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof CustomCheckStatic)) {
        return false;
      }
      final CustomCheckStatic that = (CustomCheckStatic) o;
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
      return "CustomCheckStatic{" +
      "foo=" + foo +
      '}';
    }
  }
}
