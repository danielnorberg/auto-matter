AutoMatter
==========

[![Build Status](https://travis-ci.org/danielnorberg/auto-matter.svg?branch=master)](https://travis-ci.org/danielnorberg/auto-matter)

A small library for materializing value classes and builders from value types defined as minimal
interfaces. Inspired by [AutoValue](https://github.com/google/auto/tree/master/value).


Usage
-----

### In `Foobar.java`

```java
@AutoMatter
public interface Foobar {
  String foo();
  int bar();
}
```

### In `Application.java`

```java
Foobar foobar = new FoobarBuilder()
    .foo("hello world")
    .bar(17)
    .build();

out.println("foo: " + foobar.foo());
out.println("bar: " + foobar.bar());
out.println("foobar: " + foobar);
```

### In `pom.xml`

```xml
<dependency>
  <groupId>io.norberg</groupId>
  <artifactId>auto-matter</artifactId>
  <version>0.8.1</version>
  <scope>provided</scope>
</dependency>
```


Why
---
* AutoMatter provides implementations of getters, equals, hashCode, toString, etc for you,
  letting you quickly describe your data and move on to using it without spending time on error
  prone and repetitive scaffolding.

* AutoMatter generates builders for your value types. No need to deal with long and error prone
  argument lists in factory methods and constructors.

* AutoMatter allows the value type definitions to be as minimal as possible. No need to write your
  own factory methods, use abstract modifiers or add json annotations, etc.

* The value and builder implementations are generated using standard Java annotation processing at
  build time. Thus all code is visible, navigable and debuggable using standard Java IDE's.

* AutoMatter enforces non-nullity for fields by default, moving those pesky NullPointerExceptions
  closer to the source. `@Nullable` can be used to opt out of the null checks.

* AutoMatter adds no runtime dependencies.

Why Not
-------
AutoMatter is designed to work well for pure data value type use cases by generating as much as
possible of the scaffolding needed in a straightforward manner. As such, it might not be flexible
enough for all use cases. For example, it is not possible to add your own methods to the generated
builders. For maximum flexibility, although at a higher cost, consider
[AutoValue](https://github.com/google/auto/tree/master/value).


Features
--------

### Jackson JSON Support

*Note: Requires Jackson 2.4.0+*

```xml
<dependency>
  <groupId>io.norberg</groupId>
  <artifactId>auto-matter-jackson</artifactId>
  <version>0.8.1</version>
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
  <version>0.8.1</version>
</dependency>
```

```java
Gson gson = new GsonBuilder()
    .registerTypeAdapterFactory(new AutoMatterTypeAdapterFactory())
    .create();

Foobar foobar = new FoobarBuilder()
    .foo("hello world")
    .bar(17)
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

### @Nullable

AutoMatter will omit null checks for fields annotated with `@Nullable`.

```java
@AutoMatter
interface Foobar {
    @Nullable String foo();
    int bar();
}

// ...

Foobar foobar = new FoobarBuilder()
    .foo(null)
    .bar(17)
    .build();

assert foobar.foo() == null;
```

The `@Nullable` annotation can be e.g. `javax.annotation.Nullable` from [jsr305](http://search.maven.org/#search%7Cga%7C1%7Cjsr305). A `@Nullable` annotation from any other package will also work.


### Collections

AutoMatter emits convenient adders for List, Set and Map fields.

```java
@AutoMatter
interface Foobar {
    List<String> oxen();
    List<String> cows();
    List<Integer> foo();

    Map<String, Integer> ages();
}

// ...

Foobar foobar = new FoobarBuilder()
    .appendOx("moo!")
    .appendOx("mooo!!")
    .appendCow("moooo!!!")
    .foo(17, 18)
    .age("cassie", 5)
    .age("henrietta", 7)
    .build();

assert foobar.oxen().equals(asList("moo!", "mooo!!"));
assert foobar.cows().equals(asList("moooo!!!"));
assert foobar.foo().equals(asList(17, 18));
assert foobar.ages().equals(ImmutableMap.of("cassie", 5,
                                            "henrietta", 7);
```

### Optional

AutoMatter also supports Guava and JDK8+ `Optional` fields, which can be a safer alternative to
`@Nullable`.

```java
@AutoMatter
interface Foobar {
    Optional<String> foo();
    Optional<String> bar();
    Optional<String> baz();
}

// ...

Foobar foobar = new FoobarBuilder()
    .foo("hello")
    .bar(null)
    .build();

assert foobar.foo().get().equals("hello");
assert !foobar.bar().isPresent();
assert !foobar.baz().isPresent();
```
