package foo;

import io.norberg.automatter.AutoMatter;
import java.util.Optional;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
public final class JUTOptionalFieldsBuilder {
  private Optional<String> foo;

  private Optional<String> bar;

  public JUTOptionalFieldsBuilder() {
    this.foo = Optional.empty();
  }

  private JUTOptionalFieldsBuilder(JUTOptionalFields v) {
    this.foo = v.foo();
    this.bar = v.bar();
  }

  private JUTOptionalFieldsBuilder(JUTOptionalFieldsBuilder v) {
    this.foo = v.foo();
    this.bar = v.bar();
  }

  public Optional<String> foo() {
    return foo;
  }

  public JUTOptionalFieldsBuilder foo(String foo) {
    return foo(Optional.ofNullable(foo));
  }

  @SuppressWarnings("unchecked")
  public JUTOptionalFieldsBuilder foo(Optional<? extends String> foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    this.foo = (Optional<String>)foo;
    return this;
  }

  public Optional<String> bar() {
    return bar;
  }

  public JUTOptionalFieldsBuilder bar(String bar) {
    return bar(Optional.ofNullable(bar));
  }

  @SuppressWarnings("unchecked")
  public JUTOptionalFieldsBuilder bar(Optional<? extends String> bar) {
    this.bar = (Optional<String>)bar;
    return this;
  }

  public JUTOptionalFields build() {
    return new Value(foo, bar);
  }

  public static JUTOptionalFieldsBuilder from(JUTOptionalFields v) {
    return new JUTOptionalFieldsBuilder(v);
  }

  public static JUTOptionalFieldsBuilder from(JUTOptionalFieldsBuilder v) {
    return new JUTOptionalFieldsBuilder(v);
  }

  private static final class Value implements JUTOptionalFields {
    private final Optional<String> foo;

    private final Optional<String> bar;

    private Value(@AutoMatter.Field("foo") Optional<String> foo,
        @AutoMatter.Field("bar") Optional<String> bar) {
      if (foo == null) {
        throw new NullPointerException("foo");
      }
      this.foo = foo;
      this.bar = bar;
    }

    @AutoMatter.Field
    @Override
    public Optional<String> foo() {
      return foo;
    }

    @AutoMatter.Field
    @Override
    public Optional<String> bar() {
      return bar;
    }

    public JUTOptionalFieldsBuilder builder() {
      return new JUTOptionalFieldsBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof JUTOptionalFields)) {
        return false;
      }
      final JUTOptionalFields that = (JUTOptionalFields) o;
      if (foo != null ? !foo.equals(that.foo()) : that.foo() != null) {
        return false;
      }
      if (bar != null ? !bar.equals(that.bar()) : that.bar() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (this.foo != null ? this.foo.hashCode() : 0);
      result = 31 * result + (this.bar != null ? this.bar.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "JUTOptionalFields{" +
          "foo=" + foo +
          ", bar=" + bar +
          '}';
    }
  }
}

