import java.util.Arrays;
import javax.annotation.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
public final class TopLevelBuilder {

  public TopLevelBuilder() {
  }

  private TopLevelBuilder(TopLevel v) {
  }

  private TopLevelBuilder(TopLevelBuilder v) {
  }

  public TopLevel build() {
    return new Value();
  }

  public static TopLevelBuilder from(TopLevel v) {
    return new TopLevelBuilder(v);
  }

  public static TopLevelBuilder from(TopLevelBuilder v) {
    return new TopLevelBuilder(v);
  }

  private static final class Value
      implements TopLevel {

    @com.fasterxml.jackson.annotation.JsonCreator
    private Value() {
    }

    public TopLevelBuilder builder() {
      return new TopLevelBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int result = 0;
      long temp;
      return result;
    }

    @Override
    public String toString() {
      return "TopLevel{" +
             '}';
    }
  }
}