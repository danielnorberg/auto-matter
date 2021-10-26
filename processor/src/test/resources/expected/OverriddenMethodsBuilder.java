package foo;

import io.norberg.automatter.AutoMatter;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
@AutoMatter.Generated
public final class OverriddenBaseMethodsBuilder {

  private String baz;

  public OverriddenBaseMethodsBuilder() {
  }

  private OverriddenBaseMethodsBuilder(OverriddenBaseMethods v) {
    this.baz = v.baz();
  }

  private OverriddenBaseMethodsBuilder(OverriddenBaseMethodsBuilder v) {
    this.baz = v.baz();
  }

  public String baz() {
    return baz;
  }

  public OverriddenBaseMethodsBuilder baz(String baz) {
    if (baz == null) {
      throw new NullPointerException("baz");
    }
    this.baz = baz;
    return this;
  }

  public OverriddenBaseMethods build() {
    return new Value(baz);
  }

  public static OverriddenBaseMethodsBuilder from(OverriddenBaseMethods v) {
    return new OverriddenBaseMethodsBuilder(v);
  }

  public static OverriddenBaseMethodsBuilder from(OverriddenBaseMethodsBuilder v) {
    return new OverriddenBaseMethodsBuilder(v);
  }

  @AutoMatter.Generated
  private static final class Value implements OverriddenBaseMethods {

    private final String baz;

    private Value(@AutoMatter.Field("baz") String baz) {
      if (baz == null) {
        throw new NullPointerException("baz");
      }
      this.baz = baz;
    }

    @AutoMatter.Field
    @Override
    public String baz() {
      return baz;
    }

    public OverriddenBaseMethodsBuilder builder() {
      return new OverriddenBaseMethodsBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof OverriddenBaseMethods)) {
        return false;
      }

      final OverriddenBaseMethods that = (OverriddenBaseMethods) o;

      if (baz != null ? !baz.equals(that.baz()) : that.baz() != null) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (this.baz != null ? this.baz.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "OverriddenBaseMethods{" +
             "baz=" + baz +
             '}';
    }
  }
}
