AutoMatter
==========

A small library for materializing value classes and builders from value types defined as minimal
interfaces. Inspired by [auto-value](https://github.com/google/auto/tree/master/value).

Usage
-----

### In `Foo.java`

```java
package foo;

import io.norberg.autobuilder.AutoMatter;

@AutoMatter
public interface Foo {
  String foo();
  int bar();
}
```

### Using the generated builder

```java
Foo foo = new FooBuilder()
    .foo("foo")
    .bar(17)
    .build();
```

### Generated `FooBuilder.java`

```java
package foo;

import javax.annotation.Generated;

@Generated("io.norberg.autobuilder.AutoMatterProcessor")
public final class FooBuilder {
  private String foo;
  private int bar;

  public FooBuilder foo(String foo) {
    return this;
  }

  public FooBuilder bar(int bar) {
    return this;
  }

  public Foo build() {
    return new Value(foo, bar);
  }

  private static final class Value implements Foo {
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

* Generate equals, hashCode, toString, etc...
* Recursive builders, like protobuf.
* JSON support. E.g. emitting Jackson @JsonProperty annotations, etc.
* Tests
