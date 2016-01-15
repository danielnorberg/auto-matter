package foo;

import io.norberg.automatter.AutoMatter;
import java.util.Arrays;
import javax.annotation.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
public final class FooBuilder {

  private boolean aBoolean;
  private byte aByte;
  private short aShort;
  private int aInt;
  private long aLong;
  private char aChar;
  private float aFloat;
  private double aDouble;
  private Object object;
  private Object[] array;

  public FooBuilder() {
  }

  private FooBuilder(Foo v) {
    this.aBoolean = v.aBoolean();
    this.aByte = v.aByte();
    this.aShort = v.aShort();
    this.aInt = v.aInt();
    this.aLong = v.aLong();
    this.aChar = v.aChar();
    this.aFloat = v.aFloat();
    this.aDouble = v.aDouble();
    this.object = v.object();
    this.array = v.array();
  }

  private FooBuilder(FooBuilder v) {
    this.aBoolean = v.aBoolean;
    this.aByte = v.aByte;
    this.aShort = v.aShort;
    this.aInt = v.aInt;
    this.aLong = v.aLong;
    this.aChar = v.aChar;
    this.aFloat = v.aFloat;
    this.aDouble = v.aDouble;
    this.object = v.object;
    this.array = v.array;
  }

  public boolean aBoolean() {
    return aBoolean;
  }

  public FooBuilder aBoolean(boolean aBoolean) {
    this.aBoolean = aBoolean;
    return this;
  }

  public byte aByte() {
    return aByte;
  }

  public FooBuilder aByte(byte aByte) {
    this.aByte = aByte;
    return this;
  }

  public short aShort() {
    return aShort;
  }

  public FooBuilder aShort(short aShort) {
    this.aShort = aShort;
    return this;
  }

  public int aInt() {
    return aInt;
  }

  public FooBuilder aInt(int aInt) {
    this.aInt = aInt;
    return this;
  }

  public long aLong() {
    return aLong;
  }

  public FooBuilder aLong(long aLong) {
    this.aLong = aLong;
    return this;
  }

  public char aChar() {
    return aChar;
  }

  public FooBuilder aChar(char aChar) {
    this.aChar = aChar;
    return this;
  }

  public float aFloat() {
    return aFloat;
  }

  public FooBuilder aFloat(float aFloat) {
    this.aFloat = aFloat;
    return this;
  }

  public double aDouble() {
    return aDouble;
  }

  public FooBuilder aDouble(double aDouble) {
    this.aDouble = aDouble;
    return this;
  }

  public Object object() {
    return object;
  }

  public FooBuilder object(Object object) {
    if (object == null) {
      throw new NullPointerException("object");
    }
    this.object = object;
    return this;
  }

  public Object[] array() {
    return array;
  }

  public FooBuilder array(Object[] array) {
    if (array == null) {
      throw new NullPointerException("array");
    }
    this.array = array;
    return this;
  }

  public FooBuilder builder() {
    return new FooBuilder(this);
  }

  public Foo build() {
    return new Value(
        aBoolean,
        aByte,
        aShort,
        aInt,
        aLong,
        aChar,
        aFloat,
        aDouble,
        object,
        array);
  }

  public static FooBuilder from(Foo v) {
    return new FooBuilder(v);
  }

  public static FooBuilder from(FooBuilder v) {
    return new FooBuilder(v);
  }

  /**
   * This only works with non primitive types as we don't know if it was set intentionally or by default.
   */
  public FooBuilder merge(FooBuilder other) {
    if (other.object() != null) {
      this.object(other.object());
    }
    if (other.array() != null) {
      this.array(other.array());
    }
    return this;
  }

  private static final class Value
      implements Foo {

    private final boolean aBoolean;
    private final byte aByte;
    private final short aShort;
    private final int aInt;
    private final long aLong;
    private final char aChar;
    private final float aFloat;
    private final double aDouble;
    private final Object object;
    private final Object[] array;

    private Value(
        @AutoMatter.Field("aBoolean") boolean aBoolean,
        @AutoMatter.Field("aByte") byte aByte,
        @AutoMatter.Field("aShort") short aShort,
        @AutoMatter.Field("aInt") int aInt,
        @AutoMatter.Field("aLong") long aLong,
        @AutoMatter.Field("aChar") char aChar,
        @AutoMatter.Field("aFloat") float aFloat,
        @AutoMatter.Field("aDouble") double aDouble,
        @AutoMatter.Field("object") Object object,
        @AutoMatter.Field("array") Object[] array
    ) {
      if (object == null) {
        throw new NullPointerException("object");
      }
      if (array == null) {
        throw new NullPointerException("array");
      }
      this.aBoolean = aBoolean;
      this.aByte = aByte;
      this.aShort = aShort;
      this.aInt = aInt;
      this.aLong = aLong;
      this.aChar = aChar;
      this.aFloat = aFloat;
      this.aDouble = aDouble;
      this.object = object;
      this.array = array;
    }

    @AutoMatter.Field
    @Override
    public boolean aBoolean() {
      return aBoolean;
    }

    @AutoMatter.Field
    @Override
    public byte aByte() {
      return aByte;
    }

    @AutoMatter.Field
    @Override
    public short aShort() {
      return aShort;
    }

    @AutoMatter.Field
    @Override
    public int aInt() {
      return aInt;
    }

    @AutoMatter.Field
    @Override
    public long aLong() {
      return aLong;
    }

    @AutoMatter.Field
    @Override
    public char aChar() {
      return aChar;
    }

    @AutoMatter.Field
    @Override
    public float aFloat() {
      return aFloat;
    }

    @AutoMatter.Field
    @Override
    public double aDouble() {
      return aDouble;
    }

    @AutoMatter.Field
    @Override
    public Object object() {
      return object;
    }

    @AutoMatter.Field
    @Override
    public Object[] array() {
      return array;
    }

    @Override
    public FooBuilder builder() {
      return new FooBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Foo)) {
        return false;
      }

      final Foo that = (Foo) o;

      if (aBoolean != that.aBoolean()) {
        return false;
      }
      if (aByte != that.aByte()) {
        return false;
      }
      if (aShort != that.aShort()) {
        return false;
      }
      if (aInt != that.aInt()) {
        return false;
      }
      if (aLong != that.aLong()) {
        return false;
      }
      if (aChar != that.aChar()) {
        return false;
      }
      if (Float.compare(aFloat, that.aFloat()) != 0) {
        return false;
      }
      if (Double.compare(aDouble, that.aDouble()) != 0) {
        return false;
      }
      if (object != null ? !object.equals(that.object()) : that.object() != null) {
        return false;
      }
      if (!Arrays.equals(array, that.array())) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;

      result = 31 * result + (aBoolean ? 1231 : 1237);
      result = 31 * result + (int) aByte;
      result = 31 * result + (int) aShort;
      result = 31 * result + aInt;
      result = 31 * result + (int) (aLong ^ (aLong >>> 32));
      result = 31 * result + (int) aChar;
      result = 31 * result + (aFloat != +0.0f ? Float.floatToIntBits(aFloat) : 0);
      temp = Double.doubleToLongBits(aDouble);
      result = 31 * result + (int) (temp ^ (temp >>> 32));
      result = 31 * result + (object != null ? object.hashCode() : 0);
      result = 31 * result + (array != null ? Arrays.hashCode(array) : 0);
      return result;
    }

    @Override
    public String toString() {
      return "Foo{" +
             "aBoolean=" + aBoolean +
             ", aByte=" + aByte +
             ", aShort=" + aShort +
             ", aInt=" + aInt +
             ", aLong=" + aLong +
             ", aChar=" + aChar +
             ", aFloat=" + aFloat +
             ", aDouble=" + aDouble +
             ", object=" + object +
             ", array=" + Arrays.toString(array) +
             '}';
    }
  }
}
