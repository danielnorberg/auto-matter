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
public final class JUTOptionalFieldsBuilder
    implements JUTOptionalFields {

  private java.util.Optional<String> foo;
  private java.util.Optional<String> bar;

  public JUTOptionalFieldsBuilder() {
    this.foo = java.util.Optional.empty();
  }

  private JUTOptionalFieldsBuilder(JUTOptionalFields v) {
    this.foo = v.foo();
    this.bar = v.bar();
  }

  private JUTOptionalFieldsBuilder(JUTOptionalFieldsBuilder v) {
    this.foo = v.foo;
    this.bar = v.bar;
  }

  @Override
  public java.util.Optional<String> foo() {
    return foo;
  }

  public JUTOptionalFieldsBuilder foo(String foo) {
    return foo(java.util.Optional.ofNullable(foo));
  }

  public JUTOptionalFieldsBuilder foo(java.util.Optional<String> foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    this.foo = foo;
    return this;
  }

  @Override
  public java.util.Optional<String> bar() {
    return bar;
  }

  public JUTOptionalFieldsBuilder bar(String bar) {
    return bar(java.util.Optional.ofNullable(bar));
  }

  public JUTOptionalFieldsBuilder bar(java.util.Optional<String> bar) {
    this.bar = bar;
    return this;
  }

  public JUTOptionalFields build() {
    return new Value(foo, bar);
  }

  public static JUTOptionalFieldsBuilder from(JUTOptionalFields v) {
    return new JUTOptionalFieldsBuilder(v);
  }

  public static JUTOptionalFieldsBuilder from(JUTOptionalFieldsBuilder v) {
    return new JUTOptionalFieldsBuilder(v);
  }

  private static final class Value
      implements JUTOptionalFields {

    private final java.util.Optional<String> foo;
    private final java.util.Optional<String> bar;

    private Value(@AutoMatter.Field("foo") java.util.Optional<String> foo,
                  @AutoMatter.Field("bar") java.util.Optional<String> bar) {
      if (foo == null) {
        throw new NullPointerException("foo");
      }
      this.foo = foo;
      this.bar = bar;
    }

    @AutoMatter.Field
    @Override
    public java.util.Optional<String> foo() {
      return foo;
    }

    @AutoMatter.Field
    @Override
    public java.util.Optional<String> bar() {
      return bar;
    }

    public JUTOptionalFieldsBuilder builder() {
      return new JUTOptionalFieldsBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof JUTOptionalFields)) {
        return false;
      }

      final JUTOptionalFields that = (JUTOptionalFields) o;

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
      return "JUTOptionalFields{" +
             "foo=" + foo +
             ", bar=" + bar +
             '}';
    }
  }
}