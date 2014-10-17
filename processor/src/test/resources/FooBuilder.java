package foo;

import java.util.Arrays;

import javax.annotation.Generated;

@Generated("io.norberg.automatter.AutoMatterProcessor")
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

  public FooBuilder aBoolean(boolean aBoolean) {
    this.aBoolean = aBoolean;
    return this;
  }

  public FooBuilder aByte(byte aByte) {
    this.aByte = aByte;
    return this;
  }

  public FooBuilder aShort(short aShort) {
    this.aShort = aShort;
    return this;
  }

  public FooBuilder aInt(int aInt) {
    this.aInt = aInt;
    return this;
  }

  public FooBuilder aLong(long aLong) {
    this.aLong = aLong;
    return this;
  }

  public FooBuilder aChar(char aChar) {
    this.aChar = aChar;
    return this;
  }

  public FooBuilder aFloat(float aFloat) {
    this.aFloat = aFloat;
    return this;
  }

  public FooBuilder aDouble(double aDouble) {
    this.aDouble = aDouble;
    return this;
  }

  public FooBuilder object(Object object) {
    this.object = object;
    return this;
  }

  public FooBuilder array(Object[] array) {
    this.array = array;
    return this;
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
        boolean aBoolean,
        byte aByte,
        short aShort,
        int aInt,
        long aLong,
        char aChar,
        float aFloat,
        double aDouble,
        Object object,
        Object[] array
    ) {
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

    @Override
    public boolean aBoolean() {
      return aBoolean;
    }

    @Override
    public byte aByte() {
      return aByte;
    }

    @Override
    public short aShort() {
      return aShort;
    }

    @Override
    public int aInt() {
      return aInt;
    }

    @Override
    public long aLong() {
      return aLong;
    }

    @Override
    public char aChar() {
      return aChar;
    }

    @Override
    public float aFloat() {
      return aFloat;
    }

    @Override
    public double aDouble() {
      return aDouble;
    }

    @Override
    public Object object() {
      return object;
    }

    @Override
    public Object[] array() {
      return array;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      final Value value = (Value) o;

      if (aBoolean != value.aBoolean) {
        return false;
      }
      if (aByte != value.aByte) {
        return false;
      }
      if (aShort != value.aShort) {
        return false;
      }
      if (aInt != value.aInt) {
        return false;
      }
      if (aLong != value.aLong) {
        return false;
      }
      if (aChar != value.aChar) {
        return false;
      }
      if (Float.compare(value.aFloat, aFloat) != 0) {
        return false;
      }
      if (Double.compare(value.aDouble, aDouble) != 0) {
        return false;
      }
      if (object != null ? !object.equals(value.object) : value.object != null) {
        return false;
      }
      if (!Arrays.equals(array, value.array)) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int result = 0;
      long temp;
      result = 31 * result + (aBoolean ? 1 : 0);
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