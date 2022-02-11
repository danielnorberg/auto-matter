package inheritance;

import io.norberg.automatter.AutoMatter;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
@AutoMatter.Generated
final class DefaultMethodOverrideSuperTypeBuilder {
  private String field;

  private String overrideMe;

  public DefaultMethodOverrideSuperTypeBuilder() {
  }

  private DefaultMethodOverrideSuperTypeBuilder(DefaultMethodOverrideSuperType v) {
    this.field = v.field();
    this.overrideMe = v.overrideMe();
  }

  private DefaultMethodOverrideSuperTypeBuilder(DefaultMethodOverrideSuperTypeBuilder v) {
    this.field = v.field();
    this.overrideMe = v.overrideMe();
  }

  public String field() {
    return field;
  }

  public DefaultMethodOverrideSuperTypeBuilder field(String field) {
    if (field == null) {
      throw new NullPointerException("field");
    }
    this.field = field;
    return this;
  }

  public String overrideMe() {
    return overrideMe;
  }

  public DefaultMethodOverrideSuperTypeBuilder overrideMe(String overrideMe) {
    if (overrideMe == null) {
      throw new NullPointerException("overrideMe");
    }
    this.overrideMe = overrideMe;
    return this;
  }

  public DefaultMethodOverrideSuperType build() {
    return new Value(field, overrideMe);
  }

  public static DefaultMethodOverrideSuperTypeBuilder from(DefaultMethodOverrideSuperType v) {
    return new DefaultMethodOverrideSuperTypeBuilder(v);
  }

  public static DefaultMethodOverrideSuperTypeBuilder from(
      DefaultMethodOverrideSuperTypeBuilder v) {
    return new DefaultMethodOverrideSuperTypeBuilder(v);
  }

  @AutoMatter.Generated
  private static final class Value implements DefaultMethodOverrideSuperType {
    private final String field;

    private final String overrideMe;

    private Value(@AutoMatter.Field("field") String field,
        @AutoMatter.Field("overrideMe") String overrideMe) {
      if (field == null) {
        throw new NullPointerException("field");
      }
      if (overrideMe == null) {
        throw new NullPointerException("overrideMe");
      }
      this.field = field;
      this.overrideMe = overrideMe;
    }

    @AutoMatter.Field
    @Override
    public String field() {
      return field;
    }

    @AutoMatter.Field
    @Override
    public String overrideMe() {
      return overrideMe;
    }

    public DefaultMethodOverrideSuperTypeBuilder builder() {
      return new DefaultMethodOverrideSuperTypeBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof DefaultMethodOverrideSuperType)) {
        return false;
      }
      final DefaultMethodOverrideSuperType that = (DefaultMethodOverrideSuperType) o;
      if (field != null ? !field.equals(that.field()) : that.field() != null) {
        return false;
      }
      if (overrideMe != null ? !overrideMe.equals(that.overrideMe()) : that.overrideMe() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (this.field != null ? this.field.hashCode() : 0);
      result = 31 * result + (this.overrideMe != null ? this.overrideMe.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "DefaultMethodOverrideSuperType{" +
          "field=" + field +
          ", overrideMe=" + overrideMe +
          '}';
    }
  }
}
