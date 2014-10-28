package io.norberg.automatter.example;

import java.io.IOException;
import java.util.List;

import io.norberg.automatter.AutoMatter;

import static java.lang.System.out;

public class CollectionExample {

  @AutoMatter
  interface CollectionsFoobar {
    List<String> foos();
    List<Integer> bars();
  }


  public static void main(final String... args) throws IOException {
    CollectionsFoobar foobar = new CollectionsFoobarBuilder()
        .foos("hello world")
        .bars(17)
        .build();

    out.println("bars: " + foobar.bars());
    out.println("foos: " + foobar.foos());
    out.println("foobar: " + foobar);
  }
}
