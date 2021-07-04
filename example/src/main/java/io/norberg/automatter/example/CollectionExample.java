package io.norberg.automatter.example;

import static java.lang.System.out;

import io.norberg.automatter.AutoMatter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CollectionExample {

  @AutoMatter
  interface CollectionsFoobar {
    List<String> oxen();

    List<String> cows();

    List<Integer> foo();

    Map<String, Integer> ages();
  }

  public static void main(final String... args) throws IOException {
    CollectionsFoobar foobar =
        new CollectionsFoobarBuilder()
            .addOx("moo!")
            .addOx("mooo!!")
            .addCow("moooo!!!")
            .foo(17, 18)
            .putAge("cassie", 5)
            .putAge("henrietta", 7)
            .build();

    out.println("oxen: " + foobar.oxen());
    out.println("cows: " + foobar.cows());
    out.println("foo: " + foobar.foo());
    out.println("ages: " + foobar.ages());

    out.println("foobar: " + foobar);
  }
}
