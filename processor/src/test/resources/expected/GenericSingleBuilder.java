package generic_single;

import io.norberg.automatter.AutoMatter;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
@AutoMatter.Generated
public final class GenericSingleBuilder<T> {
  private T thing;

  public GenericSingleBuilder() {
  }

  private GenericSingleBuilder(GenericSingle<? extends T> v) {
    this.thing = v.thing();
  }

  private GenericSingleBuilder(GenericSingleBuilder<? extends T> v) {
    this.thing = v.thing();
  }

  public T thing() {
    return thing;
  }

  public GenericSingleBuilder<T> thing(T thing) {
    if (thing == null) {
      throw new NullPointerException("thing");
    }
    this.thing = thing;
    return this;
  }

  public GenericSingleBuilder<T> builder() {
    return new GenericSingleBuilder<T>(this);
  }

  public GenericSingle<T> build() {
    return new Value<T>(thing);
  }

  public static <T> GenericSingleBuilder<T> from(GenericSingle<? extends T> v) {
    return new GenericSingleBuilder<T>(v);
  }

  public static <T> GenericSingleBuilder<T> from(GenericSingleBuilder<? extends T> v) {
    return new GenericSingleBuilder<T>(v);
  }

  private static final class Value<T> implements GenericSingle<T> {
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
    public GenericSingleBuilder<T> builder() {
      return new GenericSingleBuilder<T>(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof GenericSingle)) {
        return false;
      }
      final GenericSingle<?> that = (GenericSingle<?>) o;
      if (thing != null ? !thing.equals(that.thing()) : that.thing() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (this.thing != null ? this.thing.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "GenericSingle{" +
          "thing=" + thing +
          '}';
    }
  }
}
