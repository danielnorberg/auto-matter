package foo;

import io.norberg.automatter.AutoMatter;
import java.util.Arrays;
import javax.annotation.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
public final class OptionalFieldsBuilder {

  private com.google.common.base.Optional<String> optionalString = com.google.common.base.Optional.absent();

  public OptionalFieldsBuilder() {
  }

  private OptionalFieldsBuilder(OptionalFields v) {
    this.optionalString = v.optionalString();
  }

  private OptionalFieldsBuilder(OptionalFieldsBuilder v) {
    this.optionalString = v.optionalString;
  }

  public com.google.common.base.Optional<String> optionalString() {
    return optionalString;
  }

  public OptionalFieldsBuilder optionalString(com.google.common.base.Optional<String> optionalString) {
    if (optionalString == null) {
      throw new NullPointerException("optionalString");
    }
    this.optionalString = optionalString;
    return this;
  }

  public OptionalFieldsBuilder optionalString(String optionalString) {
    if (optionalString == null) {
      throw new NullPointerException("optionalString");
    }
    this.optionalString = com.google.common.base.Optional.of(optionalString);
    return this;
  }

  public OptionalFields build() {
    return new Value(optionalString);
  }

  public static OptionalFieldsBuilder from(OptionalFields v) {
    return new OptionalFieldsBuilder(v);
  }

  public static OptionalFieldsBuilder from(OptionalFieldsBuilder v) {
    return new OptionalFieldsBuilder(v);
  }

  private static final class Value
      implements OptionalFields {

    private final com.google.common.base.Optional<String> optionalString;

    private Value(@AutoMatter.Field("optionalString") com.google.common.base.Optional<String> optionalString) {
      this.optionalString = optionalString;
      if (this.optionalString == null) {
        throw new NullPointerException("optionalString");
      }
    }

    @AutoMatter.Field
    @Override
    public com.google.common.base.Optional<String> optionalString() {
      return optionalString;
    }

    public OptionalFieldsBuilder builder() {
      return new OptionalFieldsBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof OptionalFields)) {
        return false;
      }

      final OptionalFields that = (OptionalFields) o;

      if (optionalString != null ? !optionalString.equals(that.optionalString()) : that.optionalString() != null) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      result = 31 * result + (optionalString != null ? optionalString.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "OptionalFields{" +
          "optionalString=" + optionalString +
          '}';
    }
  }
}
