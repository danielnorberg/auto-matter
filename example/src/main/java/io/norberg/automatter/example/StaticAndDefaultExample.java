/**
 * Copyright (C) 2015 Spotify AB
 */

package io.norberg.automatter.example;

import java.io.IOException;

import io.norberg.automatter.AutoMatter;

import static java.lang.System.out;

public class StaticAndDefaultExample {

  @AutoMatter
  public interface Baz {

    String baz();

    // Note: static and default methods require JDK 8+

    static String quux() {
      return "world";
    }

    default String bazquux() {
      return baz() + " " + quux();
    }
  }

  public static void main(final String... args) throws IOException {
    Baz baz = new BazBuilder()
        .baz("hello")
        .build();

    out.println("baz: " + baz.baz());
    out.println("quux: " + Baz.quux());
    out.println("bazquux: " + baz.bazquux());
  }
}
