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
public final class GuavaOptionalFieldsBuilder {

  private com.google.common.base.Optional<String> foo;
  private com.google.common.base.Optional<String> bar;

  public GuavaOptionalFieldsBuilder() {
    this.foo = com.google.common.base.Optional.absent();
  }

  private GuavaOptionalFieldsBuilder(GuavaOptionalFields v) {
    this.foo = v.foo();
    this.bar = v.bar();
  }

  private GuavaOptionalFieldsBuilder(GuavaOptionalFieldsBuilder v) {
    this.foo = v.foo;
    this.bar = v.bar;
  }

  public com.google.common.base.Optional<String> foo() {
    return foo;
  }

  public GuavaOptionalFieldsBuilder foo(String foo) {
    return foo(com.google.common.base.Optional.fromNullable(foo));
  }

  public GuavaOptionalFieldsBuilder foo(com.google.common.base.Optional<? extends String> foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    this.foo = (com.google.common.base.Optional<String>) foo;
    return this;
  }

  public com.google.common.base.Optional<String> bar() {
    return bar;
  }

  public GuavaOptionalFieldsBuilder bar(String bar) {
    return bar(com.google.common.base.Optional.fromNullable(bar));
  }

  public GuavaOptionalFieldsBuilder bar(com.google.common.base.Optional<? extends String> bar) {
    this.bar = (com.google.common.base.Optional<String>) bar;
    return this;
  }

  public GuavaOptionalFields build() {
    return new Value(foo, bar);
  }

  public static GuavaOptionalFieldsBuilder from(GuavaOptionalFields v) {
    return new GuavaOptionalFieldsBuilder(v);
  }

  public static GuavaOptionalFieldsBuilder from(GuavaOptionalFieldsBuilder v) {
    return new GuavaOptionalFieldsBuilder(v);
  }

  private static final class Value
      implements GuavaOptionalFields {

    private final com.google.common.base.Optional<String> foo;
    private final com.google.common.base.Optional<String> bar;

    private Value(@AutoMatter.Field("foo") com.google.common.base.Optional<String> foo,
                  @AutoMatter.Field("bar") com.google.common.base.Optional<String> bar) {
      if (foo == null) {
        throw new NullPointerException("foo");
      }
      this.foo = foo;
      this.bar = bar;
    }

    @AutoMatter.Field
    @Override
    public com.google.common.base.Optional<String> foo() {
      return foo;
    }

    @AutoMatter.Field
    @Override
    public com.google.common.base.Optional<String> bar() {
      return bar;
    }

    public GuavaOptionalFieldsBuilder builder() {
      return new GuavaOptionalFieldsBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof GuavaOptionalFields)) {
        return false;
      }

      final GuavaOptionalFields that = (GuavaOptionalFields) o;

      if (foo != null ? !foo.equals(that.foo()) : that.foo() != null) {
        return false;
      }
      if (bar != null ? !bar.equals(that.bar()) : that.bar() != null) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      result = 31 * result + (foo != null ? foo.hashCode() : 0);
      result = 31 * result + (bar != null ? bar.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "GuavaOptionalFields{" +
             "foo=" + foo +
             ", bar=" + bar +
             '}';
    }
  }
}