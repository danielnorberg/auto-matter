AutoMatter
==========

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.norberg/auto-matter/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.norberg/auto-matter) [![Build Status](https://circleci.com/gh/danielnorberg/auto-matter.svg?style=shield)](https://circleci.com/gh/danielnorberg/auto-matter)

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
  <version>0.17.0</version>
  <scope>provided</scope>
</dependency>
```

*Note*: Use `<scope>provided</scope>` to avoid pulling in the runtime dependencies of the annotation processor itself. The generated code does not have any runtime dependencies on *auto-matter*.

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
builders. For greater flexibility and more features, consider using [Immutables](https://immutables.github.io/) or [AutoValue](https://github.com/google/auto/tree/master/value).

Features
--------

### Jackson JSON Support

*Note: Requires Jackson 2.4.0+*

```xml
<dependency>
  <groupId>io.norberg</groupId>
  <artifactId>auto-matter-jackson</artifactId>
  <version>0.17.0</version>
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
  <version>0.17.0</version>
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
Foobar copy2 = original.builder()
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

AutoMatter emits convenient setters and adders for List, Set and Map fields.

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
    .oxen("mooie", "looie")
    .addOx("moo!")
    .addOx("mooo!!")
    .addCow("moooo!!!")
    .foo(17, 18)
    .ages("junior", 1,
          "spotty", 3)
    .putAge("cassie", 5)
    .putAge("henrietta", 7)
    .build();

assert foobar.oxen().equals(Arrays.asList("mooie", "looie", "moo!", "mooo!!"));
assert foobar.cows().equals(Arrays.asList("moooo!!!"));
assert foobar.foo().equals(Arrays.asList(17, 18));
assert foobar.ages().equals(ImmutableMap.of("junior", 1,
                                            "spotty", 3,
                                            "cassie", 5,
                                            "henrietta", 7));
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

### `static` & `default` methods (JDK 8+)

AutoMatter ignores `static` and `default` methods, which can be useful for
adding behavior to a value type. Note that `static` and `default` methods in
interfaces require JDK 8+.

```java
@AutoMatter
interface Baz {

    String baz();

    static String quux() {
        return "world";
    }

    default String bazquux() {
        return baz() + " " + quux();
    }
}

// ...

Baz baz = new BazBuilder()
        .baz("hello")
        .build();

assert baz.baz().equals("hello");
assert Baz.quux().equals("world");
assert baz.bazquux().equals("hello world");
```

### Generics

AutoMatter value types can be generic.

```java
@AutoMatter
interface Foobar<T> {
    T foo();
    List<T> moreFoos();
    Map<String, T> mappedFoos();
    Optional<T> maybeFoo();
}

// ...

Foobar<String> foobar = new FoobarBuilder<String>()
    .foo("hello")
    .moreFoos("foo", "bar")
    .putMappedFoo("the", "baz")
    .maybeFoo("quux")
    .build();
```

### Inheritance

AutoMatter value types can inherit fields from interfaces.

```java
@AutoMatter
interface Foo {
    String foo();
}

interface Bar<T> {
    T bar();
}

@AutoMatter
interface Baz extends Foo, Bar<Integer> {
    int baz();
}

@AutoMatter
interface Quux extends Baz {
    String quux();
}

// ...

Baz baz = new BazBuilder()
    .foo("hello")
    .bar(17)
    .baz(4711)
    .build();

```

The `from` method can create builders by copying values from both inherited (super) and inheriting (sub) value types. The super types must be annotated with `@AutoMatter`.

```java
// Copy from subtype. Will have all fields populated
// as `Baz` extends `Foo`.
Foo fooFromBaz = FooBuilder.from(baz)
    .build(); 

// Copy from supertype. Will only have some fields
// populated, the remaining fields must be explicitly set.        
Baz bazFromFoo = BazBuilder.from(fooFromBaz)
    .bar(17)
    .baz(4711)
    .build();
        
// Copy from transitive supertype (parent of parent).
Quux quux = QuuxBuilder.from(fooFromBaz)
    .bar(17)
    .baz(4711)
    .quux("world")
    .build();
```

A builder can also be created by copying values from the builder of an inherited (super) type:

```java
FooBuilder fooBuilder = new FooBuilder()
    .foo("hello");

// Copy from supertype builder. Will only have some fields
// populated, the remaining fields must be explicitly set.        
Baz bazFromFooBuilder = BazBuilder.from(fooBuilder)
    .bar(17)
    .baz(4711)
    .build();
```

### Customizing `toString`

The AutoMatter-generated `toString` method can be overriden by annotating a method with `@AutoMatter.ToString`.

This method can be either a `default` method:

```java
@AutoMatter
interface Foobar {
  String foo();
  int bar();

  @AutoMatter.ToString
  default String overrideToString() {
    return foo() + " " + bar();
  }
}
```

Or a `static` method:

```
@AutoMatter
interface Foobar {
  String foo();
  int bar();

  @AutoMatter.ToString
  static String toString(Foobar v) {
    return v.foo() + " " + v.bar();
  }
}
```

Note that in the case of a default method, the method name cannot be `toString` as default methods are not
allowed to override methods from `java.lang.Object`.

### Redact a field's value in `toString`

There are cases that a field's value is too sensitive to reveal in `toString`, for example a password or
token. To redact the actual value in `toString`, annotate a method with `@AutoMatter.Redacted` optionally
with a customized value.

Given:

```java
@AutoMatter
public interface RedactedFields {
  String userName();

  @AutoMatter.Redacted
  String password();

  @AutoMatter.Redacted(value = "....")
  String token();
}
```

The generated `toString` method will be like:

```java
@Override
public String toString() {
  return "RedactedFields{" +
         "userName=" + userName +
         ", password=****" +
         ", token=...." +
         '}';
}
```

### Verify complex type constraints

Sometimes it is desirable to only allow certain combinations of the values of
the fields, or some fields should only be allowed to contain a subset of the
values of the field type. This validation is something that would normally take
place in the constructor or the builder for hand-written types.

As the constructor and builder is generated, we need some way of adding these
checks. To verify these more complex type constraints, annotating a method with
`@AutoMatter.Check` allows asserting on complex type constraints.

The method annotated with `@AutoMatter.Check` will be called from the
constructor when all fields have been assigned and all null checks have been
performed.

This method can be either a `default` method:

```java
@AutoMatter
interface Foobar {
  String foo();
  int bar();

  @AutoMatter.Check
  default void check() {
    assert foo().length() < bar() : "bar needs to be greater than length of foo";
  }
}
```

Or a `static` method:

```java
@AutoMatter
interface Foobar {
  String foo();
  int bar();

  @AutoMatter.Check
  static void check(Foobar v) {
    if (v.foo().length() >= v.bar()) {
      throw new IllegalArgumentException("bar needs to be greater than length of foo");
    }
  }
}
```

These kinds of checks is sometimes called invariant checks for mutable objects. But as
these objects are immutable, it is sufficient to have it as a precondition check.

### Known Issues

There's an issue with maven-compiler-plugin 3.x and annotation processors that causes
recompilation without a preceding `mvn clean` to fail.

https://github.com/danielnorberg/auto-matter/issues/17

Known workarounds:

* Clean when building. E.g. `mvn clean test`
* Use maven-compiler-plugin 2.x (e.g. 2.5.1)
* Disable the maven-compiler-plugin `useIncrementalCompilation` configuration option
