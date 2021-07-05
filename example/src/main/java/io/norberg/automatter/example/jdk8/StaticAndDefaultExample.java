package io.norberg.automatter.example.jdk8;

import static java.lang.System.out;

import io.norberg.automatter.AutoMatter;
import java.io.IOException;

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
    Baz baz = new BazBuilder().baz("hello").build();

    out.println("baz: " + baz.baz());
    out.println("quux: " + Baz.quux());
    out.println("bazquux: " + baz.bazquux());
  }
}
