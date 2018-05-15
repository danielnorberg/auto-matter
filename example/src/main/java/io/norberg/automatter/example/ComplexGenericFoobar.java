package io.norberg.automatter.example;

import com.google.common.base.Optional;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import io.norberg.automatter.AutoMatter;

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
  Map<FOO, Fizz<BAR, BAZ>> foobars();

  String name();

  @AutoMatter
  interface Fizz<K, V> {
    K k();
    V v();
  }
}
