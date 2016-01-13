package generic;

import io.norberg.automatter.AutoMatter;
import java.util.Arrays;
import javax.annotation.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
public final class GenericBuilder<T> {

  private T thing;

  public GenericBuilder() {
  }

  private GenericBuilder(Generic<T> v) {
    this.thing = v.thing();
  }

  private GenericBuilder(GenericBuilder<T> v) {
    this.thing = v.thing;
  }

  public T thing() {
    return thing;
  }

  public GenericBuilder thing(T thing) {
    if (thing == null) {
      throw new NullPointerException("thing");
    }
    this.thing = thing;
    return this;
  }

  public GenericBuilder builder() {
    return new GenericBuilder(this);
  }

  public Generic build() {
    return new Value(
        thing);
  }

  public static <T> GenericBuilder<T> from(Generic<T> v) {
    return new GenericBuilder<>(v);
  }

  public static <T> GenericBuilder<T> from(GenericBuilder<T> v) {
    return new GenericBuilder<>(v);
  }

  private static final class Value<T>
      implements Generic<T> {

    private final T thing;

    private Value(
        @AutoMatter.Field("thing") T thing
    ) {
      if (thing == null) {
        throw new NullPointerException("thing");
      }
    }

    @AutoMatter.Field
    @Override
    public T thing() {
      return thing;
    }

    @Override
    public GenericBuilder builder() {
      return new GenericBuilder(this);
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
