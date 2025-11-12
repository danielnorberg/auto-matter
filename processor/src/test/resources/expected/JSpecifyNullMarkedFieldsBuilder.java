package foo.jspecify;

import io.norberg.automatter.AutoMatter;
${GENERATED_IMPORT}
import org.jspecify.annotations.Nullable;

${GENERATED_ANNOTATION}
@AutoMatter.Generated
public final class JSpecifyNullMarkedFieldsBuilder {

  private String nullableFoo;
  private String nonNullBar;
  private int primitiveValue;

  public JSpecifyNullMarkedFieldsBuilder() {
  }

  private JSpecifyNullMarkedFieldsBuilder(JSpecifyNullMarkedFields v) {
    this.nullableFoo = v.nullableFoo();
    this.nonNullBar = v.nonNullBar();
    this.primitiveValue = v.primitiveValue();
  }

  private JSpecifyNullMarkedFieldsBuilder(JSpecifyNullMarkedFieldsBuilder v) {
    this.nullableFoo = v.nullableFoo();
    this.nonNullBar = v.nonNullBar();
    this.primitiveValue = v.primitiveValue();
  }

  public String nullableFoo() {
    return nullableFoo;
  }

  public JSpecifyNullMarkedFieldsBuilder nullableFoo(@Nullable String nullableFoo) {
    this.nullableFoo = nullableFoo;
    return this;
  }

  public String nonNullBar() {
    return nonNullBar;
  }

  public JSpecifyNullMarkedFieldsBuilder nonNullBar(String nonNullBar) {
    if (nonNullBar == null) {
      throw new NullPointerException("nonNullBar");
    }
    this.nonNullBar = nonNullBar;
    return this;
  }

  public int primitiveValue() {
    return primitiveValue;
  }

  public JSpecifyNullMarkedFieldsBuilder primitiveValue(int primitiveValue) {
    this.primitiveValue = primitiveValue;
    return this;
  }

  public JSpecifyNullMarkedFields build() {
    return new Value(
        nullableFoo,
        nonNullBar,
        primitiveValue);
  }

  public static JSpecifyNullMarkedFieldsBuilder from(JSpecifyNullMarkedFields v) {
    return new JSpecifyNullMarkedFieldsBuilder(v);
  }

  public static JSpecifyNullMarkedFieldsBuilder from(JSpecifyNullMarkedFieldsBuilder v) {
    return new JSpecifyNullMarkedFieldsBuilder(v);
  }

  @AutoMatter.Generated
  private static final class Value
      implements JSpecifyNullMarkedFields {

    private final String nullableFoo;
    private final String nonNullBar;
    private final int primitiveValue;

    private Value(
        @AutoMatter.Field("nullableFoo") String nullableFoo,
        @AutoMatter.Field("nonNullBar") String nonNullBar,
        @AutoMatter.Field("primitiveValue") int primitiveValue
    ) {
      if (nonNullBar == null) {
        throw new NullPointerException("nonNullBar");
      }
      this.nullableFoo = nullableFoo;
      this.nonNullBar = nonNullBar;
      this.primitiveValue = primitiveValue;
    }

    @AutoMatter.Field
    @Override
    public String nullableFoo() {
      return nullableFoo;
    }

    @AutoMatter.Field
    @Override
    public String nonNullBar() {
      return nonNullBar;
    }

    @AutoMatter.Field
    @Override
    public int primitiveValue() {
      return primitiveValue;
    }

    public JSpecifyNullMarkedFieldsBuilder builder() {
      return new JSpecifyNullMarkedFieldsBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof JSpecifyNullMarkedFields)) {
        return false;
      }

      final JSpecifyNullMarkedFields that = (JSpecifyNullMarkedFields) o;

      if (nullableFoo != null ? !nullableFoo.equals(that.nullableFoo()) : that.nullableFoo() != null) {
        return false;
      }
      if (nonNullBar != null ? !nonNullBar.equals(that.nonNullBar()) : that.nonNullBar() != null) {
        return false;
      }
      if (primitiveValue != that.primitiveValue()) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (this.nullableFoo != null ? this.nullableFoo.hashCode() : 0);
      result = 31 * result + (this.nonNullBar != null ? this.nonNullBar.hashCode() : 0);
      result = 31 * result + this.primitiveValue;
      return result;
    }

    @Override
    public String toString() {
      return "JSpecifyNullMarkedFields{" +
             "nullableFoo=" + nullableFoo +
             ", nonNullBar=" + nonNullBar +
             ", primitiveValue=" + primitiveValue +
             '}';
    }
  }
}
