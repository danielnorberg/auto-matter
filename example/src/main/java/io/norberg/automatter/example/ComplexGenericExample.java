package io.norberg.automatter.example;

import static java.util.Arrays.asList;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import io.norberg.automatter.example.ComplexGenericFoobar.Fizz;
import java.io.IOException;
import java.util.List;

public class ComplexGenericExample {

  static <K, V, M> Fizz<K, V, M> fizz(K k, V v, M m) {
    return new FizzBuilder<K, V, M>().k(k).v(v).m(m).build();
  }

  public static void main(final String... args) throws IOException {
    final ComplexGenericFoobar<String, Integer, List<Integer>, ComparableList<Integer>> foobar =
        new ComplexGenericFoobarBuilder<String, Integer, List<Integer>, ComparableList<Integer>>()
            .foo("foo")
            .bar(17)
            .baz(asList(13, 4711))
            .quux(ComparableList.of(1, 2, 3))
            .name("generics")
            .maybeFoos(Optional.of(ImmutableList.of("foo1", "foo2")))
            .someBars(3, 1, 4)
            .foobars(
                "foo", fizz(34, ImmutableList.of(7, 4, 3), "a"),
                "bar", fizz(75, ImmutableList.of(93, 6, 23), "b"))
            .putFoobar("baz", fizz(853, ImmutableList.of(9, 46, 23), "c"))
            .build();

    System.out.println(foobar);

    final ComplexGenericFoobarBuilder<CharSequence, Number, List<Integer>, ComparableList<Integer>>
        builder =
            ComplexGenericFoobarBuilder
                .<CharSequence, Number, List<Integer>, ComparableList<Integer>>from(foobar);

    final ComplexGenericFoobar<CharSequence, Number, List<Integer>, ComparableList<Integer>>
        foobar2 = builder.build();

    System.out.println(foobar2);

    final InheritingComplexGenericFoobar<String> inheritingFoobar =
        InheritingComplexGenericFoobarBuilder.from(foobar).foot("foo").build();

    System.out.println(inheritingFoobar);
  }
}
