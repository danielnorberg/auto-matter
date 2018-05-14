package foo;

import io.norberg.automatter.AutoMatter;
import java.util.List;
import java.util.Optional;

${GENERATED_IMPORT}


${GENERATED_ANNOTATION}
public final class GenericJUTOptionalNestedBuilder<T> {

  private Optional<List<T>> foo;

  public GenericJUTOptionalNestedBuilder() {
    this.foo = Optional.empty();
  }

  private GenericJUTOptionalNestedBuilder(GenericJUTOptionalNested<? extends T> v) {
    @SuppressWarnings("unchecked") Optional<List<T>> _foo = (Optional) v.foo();
    this.foo = _foo;
  }

  private GenericJUTOptionalNestedBuilder(GenericJUTOptionalNestedBuilder<? extends T> v) {
    @SuppressWarnings("unchecked") Optional<List<T>> _foo = (Optional) v.foo();
    this.foo = _foo;
  }

  public Optional<List<T>> foo() {
    return foo;
  }

  public GenericJUTOptionalNestedBuilder<T> foo(List<T> foo) {
    return foo(Optional.ofNullable(foo));
  }

  @SuppressWarnings("unchecked")
  public GenericJUTOptionalNestedBuilder<T> foo(Optional<? extends List<T>> foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    this.foo = (Optional<List<T>>) foo;
    return this;
  }

  public GenericJUTOptionalNested<T> build() {
    return new Value<T>(foo);
  }

  public static <T> GenericJUTOptionalNestedBuilder<T> from(GenericJUTOptionalNested<? extends T> v) {
    return new GenericJUTOptionalNestedBuilder<T>(v);
  }

  public static <T> GenericJUTOptionalNestedBuilder<T> from(GenericJUTOptionalNestedBuilder<? extends T> v) {
    return new GenericJUTOptionalNestedBuilder<T>(v);
  }

  private static final class Value<T>
      implements GenericJUTOptionalNested<T> {

    private final Optional<List<T>> foo;

    private Value(@AutoMatter.Field("foo") Optional<List<T>> foo) {
      if (foo == null) {
        throw new NullPointerException("foo");
      }
      this.foo = foo;
    }

    @AutoMatter.Field
    @Override
    public Optional<List<T>> foo() {
      return foo;
    }

    public GenericJUTOptionalNestedBuilder<T> builder() {
      return new GenericJUTOptionalNestedBuilder<T>(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof GenericJUTOptionalNested)) {
        return false;
      }

      final GenericJUTOptionalNested<?> that = (GenericJUTOptionalNested<?>) o;

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
      return "GenericJUTOptionalNested{" +
             "foo=" + foo +
             '}';
    }
  }
}
