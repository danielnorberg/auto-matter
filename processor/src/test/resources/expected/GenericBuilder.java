package generic;

import io.norberg.automatter.AutoMatter;
import javax.annotation.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
public final class GenericBuilder<T> {
  private T thing;

  public GenericBuilder() {
  }

  private GenericBuilder(Generic<? extends T> v) {
    this.thing = v.thing();
  }

  private GenericBuilder(GenericBuilder<? extends T> v) {
    this.thing = v.thing;
  }

  public T thing() {
    return thing;
  }

  public GenericBuilder<T> thing(T thing) {
    if (thing == null) {
      throw new NullPointerException("thing");
    }
    this.thing = thing;
    return this;
  }

  public GenericBuilder<T> builder() {
    return new GenericBuilder<T>(this);
  }

  public Generic<T> build() {
    return new Value<T>(thing);
  }

  public static <T> GenericBuilder<T> from(Generic<? extends T> v) {
    return new GenericBuilder<T>(v);
  }

  public static <T> GenericBuilder<T> from(GenericBuilder<? extends T> v) {
    return new GenericBuilder<T>(v);
  }

  private static final class Value<T> implements Generic<T> {

    private final T thing;

    private Value(@AutoMatter.Field("thing") T thing) {
      if (thing == null) {
        throw new NullPointerException("thing");
      }
      this.thing = thing;
    }

    @AutoMatter.Field
    @Override
    public T thing() {
      return thing;
    }

    @Override
    public GenericBuilder<T> builder() {
      return new GenericBuilder<T>(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Generic)) {
        return false;
      }
      final Generic<?> that = (Generic<?>) o;
      if (thing != null ? !thing.equals(that.thing()) : that.thing() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      result = 31 * result + (thing != null ? thing.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "Generic{" +
      "thing=" + thing +
      '}';
    }
  }
}
