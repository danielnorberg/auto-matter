package io.norberg.automatter.example;

import com.google.common.base.Optional;
import io.norberg.automatter.AutoMatter;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@AutoMatter
interface ComplexGenericFoobar<
    FOO,
    BAR extends Number,
    BAZ extends Collection<? extends BAR>,
    QUUX extends Iterable<? extends BAR> & Comparable<QUUX>> {

  FOO foo();
  BAR bar();
  BAZ baz();
  QUUX quux();

  Optional<List<FOO>> maybeFoos();
  List<BAR> someBars();
  Map<FOO, Fizz<BAR, BAZ, FOO>> foobars();

  String name();

  @AutoMatter
  interface Fuzz<M> {
    M m();
  }

  @AutoMatter
  interface Fizz<K, V, M> extends Fuzz<M> {
    K k();
    V v();
  }
}

@AutoMatter
interface InheritingComplexGenericFoobar<FOO>
    extends ComplexGenericFoobar<FOO, Integer, List<Integer>, ComparableList<Integer>> {
  FOO foot();
}
