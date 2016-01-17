package io.norberg.automatter.example;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

  Optional<FOO> maybeFoo();
  List<BAR> someBars();
  Map<FOO, BAR> foobars();

  String name();
}
