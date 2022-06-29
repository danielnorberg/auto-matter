package io.norberg.automatter.example;

import static java.lang.System.out;

import io.norberg.automatter.AutoMatter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

public class NullableCollectionExample {

  @AutoMatter
  interface NullableCollectionFoobar {
    List<String> oxen();

    @Nullable
    List<String> cows();

    @Nullable
    Map<String, Integer> ages();
  }

  public static void main(final String... args) throws IOException {
    NullableCollectionFoobar foobar = new NullableCollectionFoobarBuilder().addOx("moo!").build();

    out.println("oxen: " + foobar.oxen());
    out.println("cows: " + foobar.cows());
    out.println("ages: " + foobar.ages());
    out.println("foobar: " + foobar);
  }
}
