package io.norberg.automatter.example;

import java.util.Collection;

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

  String name();
}
