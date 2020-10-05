package foo;

import io.norberg.automatter.AutoMatter;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
public final class CustomCheckInvariantStaticBuilder {
  private String foo;

  public CustomCheckInvariantStaticBuilder() {
  }

  private CustomCheckInvariantStaticBuilder(CustomCheckInvariantStatic v) {
    this.foo = v.foo();
  }

  private CustomCheckInvariantStaticBuilder(CustomCheckInvariantStaticBuilder v) {
    this.foo = v.foo;
  }

  public String foo() {
    return foo;
  }

  public CustomCheckInvariantStaticBuilder foo(String foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    this.foo = foo;
    return this;
  }

  public CustomCheckInvariantStatic build() {
    return new Value(foo);
  }

  public static CustomCheckInvariantStaticBuilder from(CustomCheckInvariantStatic v) {
    return new CustomCheckInvariantStaticBuilder(v);
  }

  public static CustomCheckInvariantStaticBuilder from(CustomCheckInvariantStaticBuilder v) {
    return new CustomCheckInvariantStaticBuilder(v);
  }

  private static final class Value implements CustomCheckInvariantStatic {
    private final String foo;

    private Value(@AutoMatter.Field("foo") String foo) {
      if (foo == null) {
        throw new NullPointerException("foo");
      }
      this.foo = foo;
      CustomCheckInvariantStatic.checkInvariant(this);
    }

    @AutoMatter.Field
    @Override
    public String foo() {
      return foo;
    }

    public CustomCheckInvariantStaticBuilder builder() {
      return new CustomCheckInvariantStaticBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof CustomCheckInvariantStatic)) {
        return false;
      }
      final CustomCheckInvariantStatic that = (CustomCheckInvariantStatic) o;
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
      return "CustomCheckInvariantStatic{" +
      "foo=" + foo +
      '}';
    }
  }
}
