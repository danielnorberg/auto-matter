package generic_multiple;

import io.norberg.automatter.AutoMatter;
import java.util.Collection;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
public final class GenericMultipleBuilder<T1, T2 extends Number, T3 extends Collection<T2>, T4 extends Iterable<T2> & Comparable<T4>> {
  private T1 field1;

  private T2 field2;

  private T3 field3;

  private T4 field4;

  private String plain;

  public GenericMultipleBuilder() {
  }

  private GenericMultipleBuilder(GenericMultiple<? extends T1, ? extends T2, ? extends T3, ? extends T4> v) {
    this.field1 = v.field1();
    this.field2 = v.field2();
    this.field3 = v.field3();
    this.field4 = v.field4();
    this.plain = v.plain();
  }

  private GenericMultipleBuilder(GenericMultipleBuilder<? extends T1, ? extends T2, ? extends T3, ? extends T4> v) {
    this.field1 = v.field1;
    this.field2 = v.field2;
    this.field3 = v.field3;
    this.field4 = v.field4;
    this.plain = v.plain;
  }

  public T1 field1() {
    return field1;
  }

  public GenericMultipleBuilder<T1, T2, T3, T4> field1(T1 field1) {
    if (field1 == null) {
      throw new NullPointerException("field1");
    }
    this.field1 = field1;
    return this;
  }

  public T2 field2() {
    return field2;
  }

  public GenericMultipleBuilder<T1, T2, T3, T4> field2(T2 field2) {
    if (field2 == null) {
      throw new NullPointerException("field2");
    }
    this.field2 = field2;
    return this;
  }

  public T3 field3() {
    return field3;
  }

  public GenericMultipleBuilder<T1, T2, T3, T4> field3(T3 field3) {
    if (field3 == null) {
      throw new NullPointerException("field3");
    }
    this.field3 = field3;
    return this;
  }

  public T4 field4() {
    return field4;
  }

  public GenericMultipleBuilder<T1, T2, T3, T4> field4(T4 field4) {
    if (field4 == null) {
      throw new NullPointerException("field4");
    }
    this.field4 = field4;
    return this;
  }

  public String plain() {
    return plain;
  }

  public GenericMultipleBuilder<T1, T2, T3, T4> plain(String plain) {
    if (plain == null) {
      throw new NullPointerException("plain");
    }
    this.plain = plain;
    return this;
  }

  public GenericMultipleBuilder<T1, T2, T3, T4> builder() {
    return new GenericMultipleBuilder<T1, T2, T3, T4>(this);
  }

  public GenericMultiple<T1, T2, T3, T4> build() {
    return new Value<T1, T2, T3, T4>(field1, field2, field3, field4, plain);
  }

  public static <T1, T2 extends Number, T3 extends Collection<T2>, T4 extends Iterable<T2> & Comparable<T4>> GenericMultipleBuilder<T1, T2, T3, T4> from(GenericMultiple<? extends T1, ? extends T2, ? extends T3, ? extends T4> v) {
    return new GenericMultipleBuilder<T1, T2, T3, T4>(v);
  }

  public static <T1, T2 extends Number, T3 extends Collection<T2>, T4 extends Iterable<T2> & Comparable<T4>> GenericMultipleBuilder<T1, T2, T3, T4> from(GenericMultipleBuilder<? extends T1, ? extends T2, ? extends T3, ? extends T4> v) {
    return new GenericMultipleBuilder<T1, T2, T3, T4>(v);
  }

  private static final class Value<T1, T2 extends Number, T3 extends Collection<T2>, T4 extends Iterable<T2> & Comparable<T4>> implements GenericMultiple<T1, T2, T3, T4> {
    private final T1 field1;

    private final T2 field2;

    private final T3 field3;

    private final T4 field4;

    private final String plain;

    private Value(@AutoMatter.Field("field1") T1 field1, @AutoMatter.Field("field2") T2 field2, @AutoMatter.Field("field3") T3 field3, @AutoMatter.Field("field4") T4 field4, @AutoMatter.Field("plain") String plain) {
      if (field1 == null) {
        throw new NullPointerException("field1");
      }
      if (field2 == null) {
        throw new NullPointerException("field2");
      }
      if (field3 == null) {
        throw new NullPointerException("field3");
      }
      if (field4 == null) {
        throw new NullPointerException("field4");
      }
      if (plain == null) {
        throw new NullPointerException("plain");
      }
      this.field1 = field1;
      this.field2 = field2;
      this.field3 = field3;
      this.field4 = field4;
      this.plain = plain;
    }

    @AutoMatter.Field
    @Override
    public T1 field1() {
      return field1;
    }

    @AutoMatter.Field
    @Override
    public T2 field2() {
      return field2;
    }

    @AutoMatter.Field
    @Override
    public T3 field3() {
      return field3;
    }

    @AutoMatter.Field
    @Override
    public T4 field4() {
      return field4;
    }

    @AutoMatter.Field
    @Override
    public String plain() {
      return plain;
    }

    @Override
    public GenericMultipleBuilder<T1, T2, T3, T4> builder() {
      return new GenericMultipleBuilder<T1, T2, T3, T4>(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof GenericMultiple)) {
        return false;
      }
      final GenericMultiple<?, ?, ?, ?> that = (GenericMultiple<?, ?, ?, ?>) o;
      if (field1 != null ? !field1.equals(that.field1()) : that.field1() != null) {
        return false;
      }
      if (field2 != null ? !field2.equals(that.field2()) : that.field2() != null) {
        return false;
      }
      if (field3 != null ? !field3.equals(that.field3()) : that.field3() != null) {
        return false;
      }
      if (field4 != null ? !field4.equals(that.field4()) : that.field4() != null) {
        return false;
      }
      if (plain != null ? !plain.equals(that.plain()) : that.plain() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      result = 31 * result + (this.field1 != null ? this.field1.hashCode() : 0);
      result = 31 * result + (this.field2 != null ? this.field2.hashCode() : 0);
      result = 31 * result + (this.field3 != null ? this.field3.hashCode() : 0);
      result = 31 * result + (this.field4 != null ? this.field4.hashCode() : 0);
      result = 31 * result + (this.plain != null ? this.plain.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "GenericMultiple{" +
             "field1=" + field1 +
             ", field2=" + field2 +
             ", field3=" + field3 +
             ", field4=" + field4 +
             ", plain=" + plain +
             '}';
    }
  }
}
