package foo;

import io.norberg.automatter.AutoMatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
final class PackageLocalBuilder implements PackageLocal {

  public PackageLocalBuilder() {
  }

  private PackageLocalBuilder(PackageLocal v) {
  }

  private PackageLocalBuilder(PackageLocalBuilder v) {
  }

  public PackageLocal build() {
    return new Value();
  }

  public static PackageLocalBuilder from(PackageLocal v) {
    return new PackageLocalBuilder(v);
  }

  public static PackageLocalBuilder from(PackageLocalBuilder v) {
    return new PackageLocalBuilder(v);
  }

  private static final class Value
      implements PackageLocal {

    private Value() {
    }

    public PackageLocalBuilder builder() {
      return new PackageLocalBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof PackageLocal)) {
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
      return "PackageLocal{" +
             '}';
    }
  }
}