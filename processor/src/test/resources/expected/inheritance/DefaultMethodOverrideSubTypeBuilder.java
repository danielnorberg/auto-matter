package inheritance;

import io.norberg.automatter.AutoMatter;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
@AutoMatter.Generated
final class DefaultMethodOverrideSubTypeBuilder {
  private String field;

  public DefaultMethodOverrideSubTypeBuilder() {
  }

  private DefaultMethodOverrideSubTypeBuilder(DefaultMethodOverrideSubType v) {
    this.field = v.field();
  }

  private DefaultMethodOverrideSubTypeBuilder(DefaultMethodOverrideSuperType v) {
    this.field = v.field();
  }

  private DefaultMethodOverrideSubTypeBuilder(DefaultMethodOverrideSubTypeBuilder v) {
    this.field = v.field();
  }

  private DefaultMethodOverrideSubTypeBuilder(DefaultMethodOverrideSuperTypeBuilder v) {
    this.field = v.field();
  }

  public String field() {
    return field;
  }

  public DefaultMethodOverrideSubTypeBuilder field(String field) {
    if (field == null) {
      throw new NullPointerException("field");
    }
    this.field = field;
    return this;
  }

  public DefaultMethodOverrideSubType build() {
    return new Value(field);
  }

  public static DefaultMethodOverrideSubTypeBuilder from(DefaultMethodOverrideSubType v) {
    return new DefaultMethodOverrideSubTypeBuilder(v);
  }

  public static DefaultMethodOverrideSubTypeBuilder from(DefaultMethodOverrideSuperType v) {
    return new DefaultMethodOverrideSubTypeBuilder(v);
  }

  public static DefaultMethodOverrideSubTypeBuilder from(DefaultMethodOverrideSubTypeBuilder v) {
    return new DefaultMethodOverrideSubTypeBuilder(v);
  }

  public static DefaultMethodOverrideSubTypeBuilder from(DefaultMethodOverrideSuperTypeBuilder v) {
    return new DefaultMethodOverrideSubTypeBuilder(v);
  }

  @AutoMatter.Generated
  private static final class Value implements DefaultMethodOverrideSubType {
    private final String field;

    private Value(@AutoMatter.Field("field") String field) {
      if (field == null) {
        throw new NullPointerException("field");
      }
      this.field = field;
    }

    @AutoMatter.Field
    @Override
    public String field() {
      return field;
    }

    public DefaultMethodOverrideSubTypeBuilder builder() {
      return new DefaultMethodOverrideSubTypeBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof DefaultMethodOverrideSubType)) {
        return false;
      }
      final DefaultMethodOverrideSubType that = (DefaultMethodOverrideSubType) o;
      if (field != null ? !field.equals(that.field()) : that.field() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (this.field != null ? this.field.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "DefaultMethodOverrideSubType{" +
          "field=" + field +
          '}';
    }
  }
}
