package io.norberg.automatter.example;

import io.norberg.automatter.AutoMatter;

public class InheritanceExample {

  @AutoMatter
  interface Foo<T> {
    String foo();

    T foot();
  }

  interface Bar<T> {
    T bar();
  }

  @AutoMatter
  interface Baz extends Foo<Integer>, Bar<Integer> {
    int baz();
  }

  @AutoMatter
  interface Quux<T> extends Foo<T> {
    int baz();
  }

  @AutoMatter
  interface Corge extends Quux<Integer> {
    String corge();
  }

  public static void main(final String... args) {
    // Create builder and set values of inherited fields
    Baz baz = new BazBuilder().foo("hello").foot(3).bar(17).baz(4711).build();
    System.out.println(baz);

    // Create builder and value from inheriting sub type
    Foo<Integer> foo = FooBuilder.from(baz).foo("world").foot(3).build();
    System.out.println(foo);

    // Create builder and value from inherited super type
    Baz bazFromFoo = BazBuilder.from(foo).bar(123).baz(456).foot(3).build();
    System.out.println(bazFromFoo);

    // Create generic builder and value from inherited direct super type
    Quux<Integer> quuxFromFoo = QuuxBuilder.from(foo).foo("hello world").foot(42).build();
    System.out.println(quuxFromFoo);

    // Create generic builder and value from inherited transitive super type
    Corge corgeFromFoo = CorgeBuilder.from(foo).baz(17).corge("hello world").build();
    System.out.println(corgeFromFoo);

    // Create generic builder and value from inheriting sub type
    Foo<Integer> fooFromQuux = FooBuilder.from(quuxFromFoo).build();
    System.out.println(fooFromQuux);
  }
}
