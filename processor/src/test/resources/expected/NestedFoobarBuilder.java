import io.norberg.automatter.AutoMatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
public final class NestedFoobarBuilder {

  public NestedFoobarBuilder() {
  }

  private NestedFoobarBuilder(Nested.NestedFoobar v) {
  }

  private NestedFoobarBuilder(NestedFoobarBuilder v) {
  }

  public Nested.NestedFoobar build() {
    return new Value();
  }

  public static NestedFoobarBuilder from(Nested.NestedFoobar v) {
    return new NestedFoobarBuilder(v);
  }

  public static NestedFoobarBuilder from(NestedFoobarBuilder v) {
    return new NestedFoobarBuilder(v);
  }

  private static final class Value
      implements Nested.NestedFoobar {

    private Value() {
    }

    public NestedFoobarBuilder builder() {
      return new NestedFoobarBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Nested.NestedFoobar)) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      return result;
    }

    @Override
    public String toString() {
      return "Nested.NestedFoobar{" +
             '}';
    }
  }
}