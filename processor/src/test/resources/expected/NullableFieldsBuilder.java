package foo;

import io.norberg.automatter.AutoMatter;
import java.util.Date;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
public final class NullableFieldsBuilder {

  private String nullableFoo;
  private String customNullableBar;
  private String nonNullQuux;
  private int nonNullPrimitive;

  public NullableFieldsBuilder() {
  }

  private NullableFieldsBuilder(NullableFields v) {
    this.nullableFoo = v.nullableFoo();
    this.customNullableBar = v.customNullableBar();
    this.nonNullQuux = v.nonNullQuux();
    this.nonNullPrimitive = v.nonNullPrimitive();
  }

  private NullableFieldsBuilder(NullableFieldsBuilder v) {
    this.nullableFoo = v.nullableFoo();
    this.customNullableBar = v.customNullableBar();
    this.nonNullQuux = v.nonNullQuux();
    this.nonNullPrimitive = v.nonNullPrimitive();
  }

  public String nullableFoo() {
    return nullableFoo;
  }

  public NullableFieldsBuilder nullableFoo(@javax.annotation.Nullable String nullableFoo) {
    this.nullableFoo = nullableFoo;
    return this;
  }

  public String customNullableBar() {
    return customNullableBar;
  }

  public NullableFieldsBuilder customNullableBar(@Nullable(simple = "foo", complex = Date.class) String customNullableBar) {
    this.customNullableBar = customNullableBar;
    return this;
  }

  public String nonNullQuux() {
    return nonNullQuux;
  }

  public NullableFieldsBuilder nonNullQuux(String nonNullQuux) {
    if (nonNullQuux == null) {
      throw new NullPointerException("nonNullQuux");
    }
    this.nonNullQuux = nonNullQuux;
    return this;
  }

  public int nonNullPrimitive() {
    return nonNullPrimitive;
  }

  public NullableFieldsBuilder nonNullPrimitive(int nonNullPrimitive) {
    this.nonNullPrimitive = nonNullPrimitive;
    return this;
  }

  public NullableFields build() {
    return new Value(
        nullableFoo,
        customNullableBar,
        nonNullQuux,
        nonNullPrimitive);
  }

  public static NullableFieldsBuilder from(NullableFields v) {
    return new NullableFieldsBuilder(v);
  }

  public static NullableFieldsBuilder from(NullableFieldsBuilder v) {
    return new NullableFieldsBuilder(v);
  }

  private static final class Value
      implements NullableFields {

    private final String nullableFoo;
    private final String customNullableBar;
    private final String nonNullQuux;
    private final int nonNullPrimitive;

    private Value(
        @AutoMatter.Field("nullableFoo") String nullableFoo,
        @AutoMatter.Field("customNullableBar") String customNullableBar,
        @AutoMatter.Field("nonNullQuux") String nonNullQuux,
        @AutoMatter.Field("nonNullPrimitive") int nonNullPrimitive
    ) {
      if (nonNullQuux == null) {
        throw new NullPointerException("nonNullQuux");
      }
      this.nullableFoo = nullableFoo;
      this.customNullableBar = customNullableBar;
      this.nonNullQuux = nonNullQuux;
      this.nonNullPrimitive = nonNullPrimitive;
    }

    @AutoMatter.Field
    @Override
    public String nullableFoo() {
      return nullableFoo;
    }

    @AutoMatter.Field
    @Override
    public String customNullableBar() {
      return customNullableBar;
    }

    @AutoMatter.Field
    @Override
    public String nonNullQuux() {
      return nonNullQuux;
    }

    @AutoMatter.Field
    @Override
    public int nonNullPrimitive() {
      return nonNullPrimitive;
    }

    public NullableFieldsBuilder builder() {
      return new NullableFieldsBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof NullableFields)) {
        return false;
      }

      final NullableFields that = (NullableFields) o;

      if (nullableFoo != null ? !nullableFoo.equals(that.nullableFoo()) : that.nullableFoo() != null) {
        return false;
      }
      if (customNullableBar != null ? !customNullableBar.equals(that.customNullableBar()) : that.customNullableBar() != null) {
        return false;
      }
      if (nonNullQuux != null ? !nonNullQuux.equals(that.nonNullQuux()) : that.nonNullQuux() != null) {
        return false;
      }
      if (nonNullPrimitive != that.nonNullPrimitive()) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (this.nullableFoo != null ? this.nullableFoo.hashCode() : 0);
      result = 31 * result + (this.customNullableBar != null ? this.customNullableBar.hashCode() : 0);
      result = 31 * result + (this.nonNullQuux != null ? this.nonNullQuux.hashCode() : 0);
      result = 31 * result + this.nonNullPrimitive;
      return result;
    }

    @Override
    public String toString() {
      return "NullableFields{" +
             "nullableFoo=" + nullableFoo +
             ", customNullableBar=" + customNullableBar +
             ", nonNullQuux=" + nonNullQuux +
             ", nonNullPrimitive=" + nonNullPrimitive +
             '}';
    }
  }
}
