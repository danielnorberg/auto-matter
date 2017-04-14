package foo;

import io.norberg.automatter.AutoMatter;
import javax.annotation.Generated;
import javax.annotation.Resource;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
public final class AnnotatedFieldBuilder {
  private boolean isPrivate;

  public AnnotatedFieldBuilder() {
  }

  private AnnotatedFieldBuilder(AnnotatedField v) {
    this.isPrivate = v.isPrivate();
  }

  private AnnotatedFieldBuilder(AnnotatedFieldBuilder v) {
    this.isPrivate = v.isPrivate;
  }

  public boolean isPrivate() {
    return isPrivate;
  }

  public AnnotatedFieldBuilder isPrivate(boolean isPrivate) {
    this.isPrivate = isPrivate;
    return this;
  }

  public AnnotatedField build() {
    return new Value(isPrivate);
  }

  public static AnnotatedFieldBuilder from(AnnotatedField v) {
    return new AnnotatedFieldBuilder(v);
  }

  public static AnnotatedFieldBuilder from(AnnotatedFieldBuilder v) {
    return new AnnotatedFieldBuilder(v);
  }

  private static final class Value implements AnnotatedField {
    @Resource(
        name = "private"
    )
    private final boolean isPrivate;

    private Value(@AutoMatter.Field("isPrivate") boolean isPrivate) {
      this.isPrivate = isPrivate;
    }

    @AutoMatter.Field
    @Override
    public boolean isPrivate() {
      return isPrivate;
    }

    public AnnotatedFieldBuilder builder() {
      return new AnnotatedFieldBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof AnnotatedField)) {
        return false;
      }
      final AnnotatedField that = (AnnotatedField) o;
      if (isPrivate != that.isPrivate()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      result = 31 * result + (isPrivate ? 1231 : 1237);
      return result;
    }

    @Override
    public String toString() {
      return "AnnotatedField{" +
             "isPrivate=" + isPrivate +
             '}';
    }
  }
}