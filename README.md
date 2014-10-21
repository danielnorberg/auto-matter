AutoMatter
==========

[![Build Status](https://travis-ci.org/danielnorberg/auto-matter.svg?branch=master)](https://travis-ci.org/danielnorberg/auto-matter)

A small library for materializing value classes and builders from value types defined as minimal
interfaces. Inspired by [AutoValue](https://github.com/google/auto/tree/master/value).

Why
---
* AutoMatter provides implementations of setters, getters, equals, hashCode, toString, etc for you,
  letting you quickly describe your data and move on to using it without spending time on error
  prone and repetitive scaffolding.

* AutoMatter also generates builders for your value types, as opposed to e.g. AutoValue.

* AutoMatter allows the value type definitions to be as minimal as possible. No need to write your
  own factory methods, use abstract modifiers or add json annotations, etc.
  
* Integrates with AutoValue for maximum flexibility and power.

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

### In `ExampleApp.java`

```java
Example example = new ExampleBuilder()
    .bar(17)
    .foo("hello world")
    .build();

System.out.println(example);
```

### In `pom.xml`

```xml
<dependency>
  <groupId>io.norberg</groupId>
  <artifactId>auto-matter</artifactId>
  <version>0.3</version>
  <scope>provided</scope>
</dependency>
```

### Jackson JSON Support

```xml
<dependency>
  <groupId>io.norberg</groupId>
  <artifactId>auto-matter-jackson</artifactId>
  <version>0.3</version>
</dependency>
```

```java
ObjectMapper mapper = new ObjectMapper()
    .registerModule(new AutoMatterModule());

Foobar foobar = new FoobarBuilder()
    .bar(17)
    .foo("hello world")
    .build();

String json = mapper.writeValueAsString(foobar);

Foobar parsed = mapper.readValue(json, Foobar.class);
```

### Gson Support

```xml
<dependency>
  <groupId>io.norberg</groupId>
  <artifactId>auto-matter-gson</artifactId>
  <version>0.3</version>
</dependency>
```

```java
Gson gson = new GsonBuilder()
    .registerTypeAdapterFactory(new AutoMatterTypeAdapterFactory())
    .create();

Foobar foobar = new FoobarBuilder()
    .bar(17)
    .foo("hello world")
    .build();

String json = gson.toJson(foobar);

Foobar parsed = gson.fromJson(json, Foobar.class);
```

### Copying

A value can be copied into a new builder in two ways.

* Using the `FoobarBuilder.from(Foobar)` method.
* Adding a `FoobarBuilder builder();` method to the type definition.

```java
@AutoMatter
interface Foobar {
    String foo();
    int bar();

    // Note: This method is an optional convenience.
    FoobarBuilder builder();
}

// ...

Foobar original = ... ;

// Using a static method on the builder
Foobar copy1 = FoobarBuilder.from(original);
    .foo("this is a copy")
    .build();

// Using a FoobarBuilder builder() method optionally defined on the value type
Foobar copy2 = original.builder();
    .foo("this is another copy")
    .build();
```


### AutoValue Support

```java
@AutoValue
@AutoMatter
public abstract class Foobar {
  @JsonProperty public abstract String foo();
  @JsonProperty public abstract int bar();

  @JsonCreator
  public static Foobar create(@JsonProperty("foo") String foo, 
                              @JsonProperty("bar") int bar) {
    return new AutoValue_Foobar(foo, bar);
  }
}

// ...

Foobar foobar = new FoobarBuilder()
    .bar(17)
    .foo("hello world")
    .build();

out.println("bar: " + foobar.bar());
out.println("foo: " + foobar.foo());
out.println("foobar: " + foobar);
```

TODO
----

* Propagate public modifier on target interface to builder. E.g. a package-local interface should
  generate a package-local builder.
* Null-checking, opt-outable using @Nullable
* Recursive builders, like protobuf.
* More tests
* Support nested interfaces
* Collection support. E.g. something like below:

```java
@AutoMatter
interface Foobar {
  List<String> foos();
  Map<String, String> bars();
}


// ...

Foobar foobar = new FoobarBuilder()
    .foo("1")
    .foo("2")
    .bar("k1", "v2")
    .bar("k2", "v2")
    .build();

assert foobar.foos().equals(asList("1", "2"));
assert foobar.bars().equals(ImmutableMap.of("k1", "v1",
                                            "k2", "v2"));
```
