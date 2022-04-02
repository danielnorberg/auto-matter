package foo;

import io.norberg.automatter.AutoMatter;
import javax.annotation.processing.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
@AutoMatter.Generated
public final class FoobarRecordWithMethodBuilder {
  private int a;

  private String b;

  public FoobarRecordWithMethodBuilder() {
  }

  private FoobarRecordWithMethodBuilder(FoobarRecordWithMethod v) {
    this.a = v.a();
    this.b = v.b();
  }

  private FoobarRecordWithMethodBuilder(FoobarRecordWithMethodBuilder v) {
    this.a = v.a();
    this.b = v.b();
  }

  public int a() {
    return a;
  }

  public FoobarRecordWithMethodBuilder a(int a) {
    this.a = a;
    return this;
  }

  public String b() {
    return b;
  }

  public FoobarRecordWithMethodBuilder b(String b) {
    if (b == null) {
      throw new NullPointerException("b");
    }
    this.b = b;
    return this;
  }

  public static FoobarRecordWithMethodBuilder builder() {
    return new FoobarRecordWithMethodBuilder();
  }

  public FoobarRecordWithMethod build() {
    if (b == null) {
      throw new NullPointerException("b");
    }
    return new FoobarRecordWithMethod(a, b);
  }

  public static FoobarRecordWithMethodBuilder from(FoobarRecordWithMethod v) {
    return new FoobarRecordWithMethodBuilder(v);
  }

  public static FoobarRecordWithMethodBuilder from(FoobarRecordWithMethodBuilder v) {
    return new FoobarRecordWithMethodBuilder(v);
  }
}