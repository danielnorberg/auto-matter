package foo;

import io.norberg.automatter.AutoMatter;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
public final class CustomCheckInvariantDefaultBuilder {
  private String foo;

  public CustomCheckInvariantDefaultBuilder() {
  }

  private CustomCheckInvariantDefaultBuilder(CustomCheckInvariantDefault v) {
    this.foo = v.foo();
  }

  private CustomCheckInvariantDefaultBuilder(CustomCheckInvariantDefaultBuilder v) {
    this.foo = v.foo;
  }

  public String foo() {
    return foo;
  }

  public CustomCheckInvariantDefaultBuilder foo(String foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    this.foo = foo;
    return this;
  }

  public CustomCheckInvariantDefault build() {
    return new Value(foo);
  }

  public static CustomCheckInvariantDefaultBuilder from(CustomCheckInvariantDefault v) {
    return new CustomCheckInvariantDefaultBuilder(v);
  }

  public static CustomCheckInvariantDefaultBuilder from(CustomCheckInvariantDefaultBuilder v) {
    return new CustomCheckInvariantDefaultBuilder(v);
  }

  private static final class Value implements CustomCheckInvariantDefault {
    private final String foo;

    private Value(@AutoMatter.Field("foo") String foo) {
      if (foo == null) {
        throw new NullPointerException("foo");
      }
      this.foo = foo;
      checkInvariant();
    }

    @AutoMatter.Field
    @Override
    public String foo() {
      return foo;
    }

    public CustomCheckInvariantDefaultBuilder builder() {
      return new CustomCheckInvariantDefaultBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof CustomCheckInvariantDefault)) {
        return false;
      }
      final CustomCheckInvariantDefault that = (CustomCheckInvariantDefault) o;
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
      return "CustomCheckInvariantDefault{" +
      "foo=" + foo +
      '}';
    }
  }
}
