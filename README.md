AutoMatter
==========

[![Build Status](https://travis-ci.org/danielnorberg/auto-matter.svg?branch=master)](https://travis-ci.org/danielnorberg/auto-matter)

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
  <version>0.2</version>
  <scope>provided</scope>
</dependency>
```

### Jackson JSON Support

```xml
<dependency>
  <groupId>io.norberg</groupId>
  <artifactId>auto-matter-jackson</artifactId>
  <version>0.2</version>
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
  <version>0.3-SNAPSHOT</version>
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


TODO
----

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
