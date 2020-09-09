package foo;

import io.norberg.automatter.AutoMatter;
import java.util.Objects;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
public final class DefaultValueFieldBuilder {
  private String bar;

  private String foo;

  public DefaultValueFieldBuilder() {
  }

  private DefaultValueFieldBuilder(DefaultValueField v) {
    this.bar = v.bar();
    this.foo = v.foo();
  }

  private DefaultValueFieldBuilder(DefaultValueFieldBuilder v) {
    this.bar = v.bar;
    this.foo = v.foo;
  }

  public String bar() {
    return bar;
  }

  public DefaultValueFieldBuilder bar(String bar) {
    if (bar == null) {
      throw new NullPointerException("bar");
    }
    this.bar = bar;
    return this;
  }

  public String foo() {
    return foo;
  }

  public DefaultValueFieldBuilder foo(String foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    this.foo = foo;
    return this;
  }

  public DefaultValueField build() {
    return new Value(bar, foo);
  }

  public static DefaultValueFieldBuilder from(DefaultValueField v) {
    return new DefaultValueFieldBuilder(v);
  }

  public static DefaultValueFieldBuilder from(DefaultValueFieldBuilder v) {
    return new DefaultValueFieldBuilder(v);
  }

  private static final class Value implements DefaultValueField {
    private final String bar;

    private final String foo;

    private Value(@AutoMatter.Field("bar") String bar, @AutoMatter.Field("foo") String foo) {
      this.bar = (bar != null) ? bar : Objects.requireNonNull(DefaultValueField.super.bar(), "bar");
      this.foo = (foo != null) ? foo : Objects.requireNonNull(DefaultValueField.super.foo(), "foo");
    }

    @AutoMatter.Field
    @Override
    public String bar() {
      return bar;
    }

    @AutoMatter.Field
    @Override
    public String foo() {
      return foo;
    }

    public DefaultValueFieldBuilder builder() {
      return new DefaultValueFieldBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof DefaultValueField)) {
        return false;
      }
      final DefaultValueField that = (DefaultValueField) o;
      if (bar != null ? !bar.equals(that.bar()) : that.bar() != null) {
        return false;
      }
      if (foo != null ? !foo.equals(that.foo()) : that.foo() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      result = 31 * result + (this.bar != null ? this.bar.hashCode() : 0);
      result = 31 * result + (this.foo != null ? this.foo.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "DefaultValueField{" +
          "bar=" + bar +
          ", foo=" + foo +
          '}';
    }
  }
}