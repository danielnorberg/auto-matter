package foo;

import javax.annotation.processing.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
public final class FoobarRecordBuilder {
  private int a;

  private String b;

  public FoobarRecordBuilder() {
  }

  private FoobarRecordBuilder(FoobarRecord v) {
    this.a = v.a();
    this.b = v.b();
  }

  private FoobarRecordBuilder(FoobarRecordBuilder v) {
    this.a = v.a();
    this.b = v.b();
  }

  public int a() {
    return a;
  }

  public FoobarRecordBuilder a(int a) {
    this.a = a;
    return this;
  }

  public String b() {
    return b;
  }

  public FoobarRecordBuilder b(String b) {
    if (b == null) {
      throw new NullPointerException("b");
    }
    this.b = b;
    return this;
  }

  public static FoobarRecordBuilder builder() {
    return new FoobarRecordBuilder();
  }

  public FoobarRecord build() {
    if (b == null) {
      throw new NullPointerException("b");
    }
    return new FoobarRecord(a, b);
  }

  public static FoobarRecordBuilder from(FoobarRecord v) {
    return new FoobarRecordBuilder(v);
  }

  public static FoobarRecordBuilder from(FoobarRecordBuilder v) {
    return new FoobarRecordBuilder(v);
  }
}
