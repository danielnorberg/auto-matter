package foo;

import io.norberg.automatter.AutoMatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
final class NestedPackageLocalFoobarBuilder {

  public NestedPackageLocalFoobarBuilder() {
  }

  private NestedPackageLocalFoobarBuilder(NestedPackageLocal.NestedPackageLocalFoobar v) {
  }

  private NestedPackageLocalFoobarBuilder(NestedPackageLocalFoobarBuilder v) {
  }

  public NestedPackageLocal.NestedPackageLocalFoobar build() {
    return new Value();
  }

  public static NestedPackageLocalFoobarBuilder from(NestedPackageLocal.NestedPackageLocalFoobar v) {
    return new NestedPackageLocalFoobarBuilder(v);
  }

  public static NestedPackageLocalFoobarBuilder from(NestedPackageLocalFoobarBuilder v) {
    return new NestedPackageLocalFoobarBuilder(v);
  }

  private static final class Value
      implements NestedPackageLocal.NestedPackageLocalFoobar {

    private Value() {
    }

    public NestedPackageLocalFoobarBuilder builder() {
      return new NestedPackageLocalFoobarBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof NestedPackageLocal.NestedPackageLocalFoobar)) {
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
      return "NestedPackageLocal.NestedPackageLocalFoobar{" +
             '}';
    }
  }
}