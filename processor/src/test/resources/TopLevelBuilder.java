import java.util.Arrays;
import javax.annotation.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
public final class TopLevelBuilder {

  public TopLevel build() {
    return new Value();
  }

  private static final class Value
      implements TopLevel {

    @com.fasterxml.jackson.annotation.JsonCreator
    private Value() {
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