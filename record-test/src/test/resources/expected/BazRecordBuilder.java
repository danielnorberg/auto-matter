package foo;

import io.norberg.automatter.AutoMatter;
import javax.annotation.processing.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
@AutoMatter.Generated
public final class BazRecordBuilder {
  private String baz1;

  private int a;

  private String b;

  private boolean c;

  private long d;

  private String baz2;

  public BazRecordBuilder() {
  }

  private BazRecordBuilder(BazRecordContainer.BazRecord v) {
    this.baz1 = v.baz1();
    this.a = v.a();
    this.b = v.b();
    this.c = v.c();
    this.d = v.d();
    this.baz2 = v.baz2();
  }

  private BazRecordBuilder(BazRecordContainer.Foobar1 v) {
    this.a = v.a();
    this.b = v.b();
  }

  private BazRecordBuilder(BazRecordContainer.Foobar2 v) {
    this.c = v.c();
    this.d = v.d();
  }

  private BazRecordBuilder(BazRecordBuilder v) {
    this.baz1 = v.baz1();
    this.a = v.a();
    this.b = v.b();
    this.c = v.c();
    this.d = v.d();
    this.baz2 = v.baz2();
  }

  private BazRecordBuilder(Foobar1Builder v) {
    this.a = v.a();
    this.b = v.b();
  }

  private BazRecordBuilder(Foobar2Builder v) {
    this.c = v.c();
    this.d = v.d();
  }

  public String baz1() {
    return baz1;
  }

  public BazRecordBuilder baz1(String baz1) {
    if (baz1 == null) {
      throw new NullPointerException("baz1");
    }
    this.baz1 = baz1;
    return this;
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

  public boolean c() {
    return c;
  }

  public BazRecordBuilder c(boolean c) {
    this.c = c;
    return this;
  }

  public long d() {
    return d;
  }

  public BazRecordBuilder d(long d) {
    this.d = d;
    return this;
  }

  public String baz2() {
    return baz2;
  }

  public BazRecordBuilder baz2(String baz2) {
    if (baz2 == null) {
      throw new NullPointerException("baz2");
    }
    this.baz2 = baz2;
    return this;
  }

  public static BazRecordBuilder builder() {
    return new BazRecordBuilder();
  }

  public BazRecordContainer.BazRecord build() {
    if (baz1 == null) {
      throw new NullPointerException("baz1");
    }
    if (b == null) {
      throw new NullPointerException("b");
    }
    if (baz2 == null) {
      throw new NullPointerException("baz2");
    }
    return new BazRecordContainer.BazRecord(baz1, a, b, c, d, baz2);
  }

  public static BazRecordBuilder from(BazRecordContainer.BazRecord v) {
    return new BazRecordBuilder(v);
  }

  public static BazRecordBuilder from(BazRecordContainer.Foobar1 v) {
    return new BazRecordBuilder(v);
  }

  public static BazRecordBuilder from(BazRecordContainer.Foobar2 v) {
    return new BazRecordBuilder(v);
  }

  public static BazRecordBuilder from(BazRecordBuilder v) {
    return new BazRecordBuilder(v);
  }

  public static BazRecordBuilder from(Foobar1Builder v) {
    return new BazRecordBuilder(v);
  }

  public static BazRecordBuilder from(Foobar2Builder v) {
    return new BazRecordBuilder(v);
  }
}
