package inheritance;

import io.norberg.automatter.AutoMatter;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
final class QuuxBuilder<T> {
  private T Quux;

  public QuuxBuilder() {
  }

  private QuuxBuilder(Quux<? extends T> v) {
    this.Quux = v.Quux();
  }

  private QuuxBuilder(QuuxBuilder<? extends T> v) {
    this.Quux = v.Quux();
  }

  public T Quux() {
    return Quux;
  }

  public QuuxBuilder<T> Quux(T Quux) {
    if (Quux == null) {
      throw new NullPointerException("Quux");
    }
    this.Quux = Quux;
    return this;
  }

  public Quux<T> build() {
    return new Value<T>(Quux);
  }

  public static <T> QuuxBuilder<T> from(Quux<? extends T> v) {
    return new QuuxBuilder<T>(v);
  }

  public static <T> QuuxBuilder<T> from(QuuxBuilder<? extends T> v) {
    return new QuuxBuilder<T>(v);
  }

  private static final class Value<T> implements Quux<T> {
    private final T Quux;

    private Value(@AutoMatter.Field("Quux") T Quux) {
      if (Quux == null) {
        throw new NullPointerException("Quux");
      }
      this.Quux = Quux;
    }

    @AutoMatter.Field
    @Override
    public T Quux() {
      return Quux;
    }

    public QuuxBuilder<T> builder() {
      return new QuuxBuilder<T>(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Quux)) {
        return false;
      }
      final Quux<?> that = (Quux<?>) o;
      if (Quux != null ? !Quux.equals(that.Quux()) : that.Quux() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      result = 31 * result + (this.Quux != null ? this.Quux.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "Quux{" +
          "Quux=" + Quux +
          '}';
    }
  }
}
