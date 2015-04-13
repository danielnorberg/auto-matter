package foo;

import com.google.common.base.Optional;
import io.norberg.automatter.AutoMatter;
import javax.annotation.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
public final class GuavaOptionalFieldsBuilder {

  private Optional<String> foo;
  private Optional<String> bar;

  public GuavaOptionalFieldsBuilder() {
    this.foo = Optional.absent();
  }

  private GuavaOptionalFieldsBuilder(GuavaOptionalFields v) {
    this.foo = v.foo();
    this.bar = v.bar();
  }

  private GuavaOptionalFieldsBuilder(GuavaOptionalFieldsBuilder v) {
    this.foo = v.foo;
    this.bar = v.bar;
  }

  public Optional<String> foo() {
    return foo;
  }

  public GuavaOptionalFieldsBuilder foo(String foo) {
    return foo(Optional.fromNullable(foo));
  }

  public GuavaOptionalFieldsBuilder foo(Optional<? extends String> foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    this.foo = (Optional<String>) foo;
    return this;
  }

  public Optional<String> bar() {
    return bar;
  }

  public GuavaOptionalFieldsBuilder bar(String bar) {
    return bar(Optional.fromNullable(bar));
  }

  public GuavaOptionalFieldsBuilder bar(Optional<? extends String> bar) {
    this.bar = (Optional<String>) bar;
    return this;
  }

  public GuavaOptionalFields build() {
    return new Value(foo, bar);
  }

  public static GuavaOptionalFieldsBuilder from(GuavaOptionalFields v) {
    return new GuavaOptionalFieldsBuilder(v);
  }

  public static GuavaOptionalFieldsBuilder from(GuavaOptionalFieldsBuilder v) {
    return new GuavaOptionalFieldsBuilder(v);
  }

  private static final class Value
      implements GuavaOptionalFields {

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

    public GuavaOptionalFieldsBuilder builder() {
      return new GuavaOptionalFieldsBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof GuavaOptionalFields)) {
        return false;
      }

      final GuavaOptionalFields that = (GuavaOptionalFields) o;

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
      long temp;
      result = 31 * result + (foo != null ? foo.hashCode() : 0);
      result = 31 * result + (bar != null ? bar.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "GuavaOptionalFields{" +
             "foo=" + foo +
             ", bar=" + bar +
             '}';
    }
  }
}
