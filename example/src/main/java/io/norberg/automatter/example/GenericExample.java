package io.norberg.automatter.example;

import java.io.IOException;

import io.norberg.automatter.AutoMatter;

import static java.lang.System.out;

public class GenericExample {

  @AutoMatter
  interface GenericFoobar<U, V extends Number> {
    U foo();
    V bar();

    GenericFoobarBuilder<U, V> builder();
//    <U, V extends Number> GenericFoobarBuilder<U, V> builder2();
  }

//  public static <U, V2> GenericFoobarBuilder<U, ? super V2> foo(GenericExample.GenericFoobar<? extends U, ? extends V2> v) {
//    return null;
//  }

  public static void main(final String... args) throws IOException {
    GenericFoobar<String, Integer> foobar = new GenericFoobarBuilder<String, Integer>()
        .foo("hello")
        .bar(17)
        .build();

    final GenericFoobarBuilder<Object, Number> builder = GenericFoobarBuilder.from(foobar);
//    final GenericFoobarBuilder<Object, Number> builder2 = foobar.builder();
//    final GenericFoobarBuilder<Object, Float> builder3 = foobar.builder();


//    GenericFoobarBuilder<Object, Number> f = foo(foobar);

//    GenericFoobar<String, Number> foobar2 = GenericFoobarBuilder.from(foobar);

    out.println("foo: " + foobar.foo());
//    out.println("bar: " + foobar.bar());

    out.println("foobar: " + foobar);
  }
}
