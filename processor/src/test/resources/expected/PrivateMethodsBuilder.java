package foo;

import io.norberg.automatter.AutoMatter;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
public final class PrivateMethodsBuilder {

  private String foo;

  public PrivateMethodsBuilder() {
  }

  private PrivateMethodsBuilder(PrivateMethods v) {
    this.foo = v.foo();
  }

  private PrivateMethodsBuilder(PrivateMethodsBuilder v) {
    this.foo = v.foo();
  }

  public String foo() {
    return foo;
  }

  public PrivateMethodsBuilder foo(String foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    this.foo = foo;
    return this;
  }

  public PrivateMethods build() {
    return new Value(foo);
  }

  public static PrivateMethodsBuilder from(PrivateMethods v) {
    return new PrivateMethodsBuilder(v);
  }

  public static PrivateMethodsBuilder from(PrivateMethodsBuilder v) {
    return new PrivateMethodsBuilder(v);
  }

  private static final class Value implements PrivateMethods {

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

    public PrivateMethodsBuilder builder() {
      return new PrivateMethodsBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof PrivateMethods)) {
        return false;
      }

      final PrivateMethods that = (PrivateMethods) o;

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
      return "PrivateMethods{" +
          "foo=" + foo +
          '}';
    }
  }
}
