package io.norberg.automatter.example;

import java.io.IOException;
import java.util.List;

import io.norberg.automatter.AutoMatter;

import static java.lang.System.out;

public class CollectionExample {

  @AutoMatter
  interface CollectionsFoobar {
    List<String> oxen();
    List<String> cows();
    List<Integer> foo();
  }

  public static void main(final String... args) throws IOException {
    CollectionsFoobar foobar = new CollectionsFoobarBuilder()
        .ox("moo!")
        .ox("mooo!!")
        .cow("moooo!!!")
        .foo(17, 18)
        .build();

    out.println("oxen: " + foobar.oxen());
    out.println("cows: " + foobar.cows());
    out.println("foo: " + foobar.foo());
    out.println("foobar: " + foobar);
  }
}
