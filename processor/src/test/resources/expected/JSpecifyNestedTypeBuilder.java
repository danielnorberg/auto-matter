package foo;

import io.norberg.automatter.AutoMatter;
${GENERATED_IMPORT}
import org.jspecify.annotations.Nullable;

${GENERATED_ANNOTATION}
@AutoMatter.Generated
public final class JSpecifyNestedTypeBuilder {

  private JSpecifyNestedType.Status status;

  public JSpecifyNestedTypeBuilder() {
  }

  private JSpecifyNestedTypeBuilder(JSpecifyNestedType v) {
    this.status = v.status();
  }

  private JSpecifyNestedTypeBuilder(JSpecifyNestedTypeBuilder v) {
    this.status = v.status();
  }

  public JSpecifyNestedType.Status status() {
    return status;
  }

  public JSpecifyNestedTypeBuilder status(JSpecifyNestedType.@Nullable Status status) {
    this.status = status;
    return this;
  }

  public JSpecifyNestedType build() {
    return new Value(
        status);
  }

  public static JSpecifyNestedTypeBuilder from(JSpecifyNestedType v) {
    return new JSpecifyNestedTypeBuilder(v);
  }

  public static JSpecifyNestedTypeBuilder from(JSpecifyNestedTypeBuilder v) {
    return new JSpecifyNestedTypeBuilder(v);
  }

  @AutoMatter.Generated
  private static final class Value
      implements JSpecifyNestedType {

    private final JSpecifyNestedType.Status status;

    private Value(
        @AutoMatter.Field("status") JSpecifyNestedType.Status status
    ) {
      this.status = status;
    }

    @AutoMatter.Field
    @Override
    public JSpecifyNestedType.Status status() {
      return status;
    }

    public JSpecifyNestedTypeBuilder builder() {
      return new JSpecifyNestedTypeBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof JSpecifyNestedType)) {
        return false;
      }

      final JSpecifyNestedType that = (JSpecifyNestedType) o;

      if (status != null ? !status.equals(that.status()) : that.status() != null) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (this.status != null ? this.status.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "JSpecifyNestedType{" +
             "status=" + status +
             '}';
    }
  }
}
