package foo;

import io.norberg.automatter.AutoMatter;
import javax.annotation.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
public final class DefaultMethodsBuilder {

  private String foo;

  public DefaultMethodsBuilder() {
  }

  private DefaultMethodsBuilder(DefaultMethods v) {
    this.foo = v.foo();
  }

  private DefaultMethodsBuilder(DefaultMethodsBuilder v) {
    this.foo = v.foo;
  }

  public String foo() {
    return foo;
  }

  public DefaultMethodsBuilder foo(String foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    this.foo = foo;
    return this;
  }

  public DefaultMethods build() {
    return new Value(foo);
  }

  public static DefaultMethodsBuilder from(DefaultMethods v) {
    return new DefaultMethodsBuilder(v);
  }

  public static DefaultMethodsBuilder from(DefaultMethodsBuilder v) {
    return new DefaultMethodsBuilder(v);
  }

  private static final class Value implements DefaultMethods {

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

    public DefaultMethodsBuilder builder() {
      return new DefaultMethodsBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof DefaultMethods)) {
        return false;
      }

      final DefaultMethods that = (DefaultMethods) o;

      if (foo != null ? !foo.equals(that.foo()) : that.foo() != null) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;

      result = 31 * result + (foo != null ? foo.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "DefaultMethods{" +
             "foo=" + foo +
             '}';
    }
  }
}
