package foo;

import io.norberg.automatter.AutoMatter;
import java.util.Optional;

${GENERATED_IMPORT}


${GENERATED_ANNOTATION}
public final class GenericJUTOptionalFieldsBuilder<T> {

  private Optional<T> foo;
  private Optional<T> bar;

  public GenericJUTOptionalFieldsBuilder() {
    this.foo = Optional.empty();
  }

  private GenericJUTOptionalFieldsBuilder(GenericJUTOptionalFields<? extends T> v) {
    @SuppressWarnings("unchecked") Optional<T> _foo = (Optional<T>) (Optional<? extends T>) v.foo();
    this.foo = _foo;
    @SuppressWarnings("unchecked") Optional<T> _bar = (Optional<T>) (Optional<? extends T>) v.bar();
    this.bar = _bar;
  }

  private GenericJUTOptionalFieldsBuilder(GenericJUTOptionalFieldsBuilder<? extends T> v) {
    @SuppressWarnings("unchecked") Optional<T> _foo = (Optional<T>) (Optional<? extends T>) v.foo();
    this.foo = _foo;
    @SuppressWarnings("unchecked") Optional<T> _bar = (Optional<T>) (Optional<? extends T>) v.bar();
    this.bar = _bar;
  }

  public Optional<T> foo() {
    return foo;
  }

  public GenericJUTOptionalFieldsBuilder<T> foo(T foo) {
    return foo(Optional.ofNullable(foo));
  }

  @SuppressWarnings("unchecked")
  public GenericJUTOptionalFieldsBuilder<T> foo(Optional<? extends T> foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    this.foo = (Optional<T>) foo;
    return this;
  }

  public Optional<T> bar() {
    return bar;
  }

  public GenericJUTOptionalFieldsBuilder<T> bar(T bar) {
    return bar(Optional.ofNullable(bar));
  }

  @SuppressWarnings("unchecked")
  public GenericJUTOptionalFieldsBuilder<T> bar(Optional<? extends T> bar) {
    this.bar = (Optional<T>) bar;
    return this;
  }

  public GenericJUTOptionalFields<T> build() {
    return new Value<T>(foo, bar);
  }

  public static <T> GenericJUTOptionalFieldsBuilder<T> from(GenericJUTOptionalFields<? extends T> v) {
    return new GenericJUTOptionalFieldsBuilder<T>(v);
  }

  public static <T> GenericJUTOptionalFieldsBuilder<T> from(GenericJUTOptionalFieldsBuilder<? extends T> v) {
    return new GenericJUTOptionalFieldsBuilder<T>(v);
  }

  private static final class Value<T>
      implements GenericJUTOptionalFields<T> {

    private final Optional<T> foo;
    private final Optional<T> bar;

    private Value(@AutoMatter.Field("foo") Optional<T> foo, @AutoMatter.Field("bar") Optional<T> bar) {
      if (foo == null) {
        throw new NullPointerException("foo");
      }
      this.foo = foo;
      this.bar = bar;
    }

    @AutoMatter.Field
    @Override
    public Optional<T> foo() {
      return foo;
    }

    @AutoMatter.Field
    @Override
    public Optional<T> bar() {
      return bar;
    }

    public GenericJUTOptionalFieldsBuilder<T> builder() {
      return new GenericJUTOptionalFieldsBuilder<T>(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof GenericJUTOptionalFields)) {
        return false;
      }

      final GenericJUTOptionalFields<?> that = (GenericJUTOptionalFields<?>) o;

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
      return "GenericJUTOptionalFields{" +
             "foo=" + foo +
             ", bar=" + bar +
             '}';
    }
  }
}
