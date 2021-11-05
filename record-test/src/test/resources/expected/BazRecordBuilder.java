package foo;

import io.norberg.automatter.AutoMatter;
import javax.annotation.processing.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
@AutoMatter.Generated
public final class BazRecordBuilder {
  private int a;

  private String b;

  public BazRecordBuilder() {
  }

  private BazRecordBuilder(BazRecordContainer.BazRecord v) {
    this.a = v.a();
    this.b = v.b();
  }

  private BazRecordBuilder(BazRecordBuilder v) {
    this.a = v.a();
    this.b = v.b();
  }

  public int a() {
    return a;
  }

  public BazRecordBuilder a(int a) {
    this.a = a;
    return this;
  }

  public String b() {
    return b;
  }

  public BazRecordBuilder b(String b) {
    if (b == null) {
      throw new NullPointerException("b");
    }
    this.b = b;
    return this;
  }

  public static BazRecordBuilder builder() {
    return new BazRecordBuilder();
  }

  public BazRecordContainer.BazRecord build() {
    if (b == null) {
      throw new NullPointerException("b");
    }
    return new BazRecordContainer.BazRecord(a, b);
  }

  public static BazRecordBuilder from(BazRecordContainer.BazRecord v) {
    return new BazRecordBuilder(v);
  }

  public static BazRecordBuilder from(BazRecordBuilder v) {
    return new BazRecordBuilder(v);
  }
}
