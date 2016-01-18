package io.norberg.automatter.example;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;

public class ComplexGenericExample {

  public static void main(final String... args) throws IOException {
    final ComplexGenericFoobar<String, Integer, List<Integer>, ComparableList<Integer>> foobar =
        new ComplexGenericFoobarBuilder<String, Integer, List<Integer>, ComparableList<Integer>>()
            .foo("foo")
            .bar(17)
            .baz(asList(13, 4711))
            .quux(ComparableList.of(1, 2, 3))
            .name("generics")
            .build();

    System.out.println(foobar);

    final ComplexGenericFoobarBuilder<CharSequence, Number, List<Integer>, ComparableList<Integer>> builder =
        ComplexGenericFoobarBuilder.<CharSequence, Number, List<Integer>, ComparableList<Integer>>from(foobar);

    final ComplexGenericFoobar<CharSequence, Number, List<Integer>, ComparableList<Integer>> foobar2 = builder.build();

    System.out.println(foobar2);
  }
}
