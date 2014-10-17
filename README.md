AutoMatter
==========

A small library for materializing value classes and builders from value types defined as minimal
interfaces. Inspired by [auto-value](https://github.com/google/auto/tree/master/value).

Usage
-----

### In `Example.java`

```java
package io.norberg.automatter.example;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface Example {
  String foo();
  int bar();
}
```

### Using the generated builder and class

```java
Example example = new ExampleBuilder()
    .bar(17)
    .foo("hello world")
    .build();

System.out.println(example);
```

### Generated `ExampleBuilder.java`

```java
package io.norberg.automatter.example;

import java.util.Arrays;
import javax.annotation.Generated;

@Generated("io.norberg.automatter.AutoMatterProcessor")
public final class ExampleBuilder {

  private String foo;
  private int bar;

  public ExampleBuilder foo(String foo) {
    this.foo = foo;
    return this;
  }

  public ExampleBuilder bar(int bar) {
    this.bar = bar;
    return this;
  }

  public Example build() {
    return new Value(foo, bar);
  }

  private static final class Value
      implements Example {

    private final String foo;
    private final int bar;

    private Value(String foo, int bar) {
      this.foo = foo;
      this.bar = bar;
    }

    @Override
    public String foo() {
      return foo;
    }

    @Override
    public int bar() {
      return bar;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      final Value value = (Value) o;

      if (foo != null ? !foo.equals(value.foo) : value.foo != null) {
        return false;
      }
      if (bar != value.bar) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int result = 0;
      long temp;
      result = 31 * result + (foo != null ? foo.hashCode() : 0);
      result = 31 * result + bar;
      return result;
    }

    @Override
    public String toString() {
      return "Example{" + 
          "foo=" + foo +
          ", bar=" + bar +
          '}';
    }
  }
}
```

### In `pom.xml`

```xml
<dependency>
  <groupId>io.norberg</groupId>
  <artifactId>auto-matter</artifactId>
  <version>1.0-SNAPSHOT</version>
  <scope>provided</scope>
</dependency>
```

TODO
----

* Null-checking, opt-outable using @Nullable 
* JSON support. E.g. emitting Jackson @JsonProperty annotations, etc.
* Recursive builders, like protobuf.
* More tests
