package foo;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface Foo {
  boolean aBoolean();
  byte aByte();
  short aShort();
  int aInt();
  long aLong();
  char aChar();
  float aFloat();
  double aDouble();
  Object object();
  Object[] array();

  FooBuilder builder();
}