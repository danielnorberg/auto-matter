package io.norberg.automatter.example;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.asList;

public class ComparableList<T> extends ArrayList<T> implements Comparable<ComparableList<T>> {

  public ComparableList(final Collection<? extends T> values) {
    super(values);
  }

  public static <T> ComparableList<T> of(final Collection<? extends T> values) {
    return new ComparableList<>(values);
  }

  public static <T> ComparableList<T> of(final T... values) {
    return new ComparableList<>(asList(values));
  }

  @Override
  public int compareTo(final ComparableList<T> o) {
    return Integer.compare(size(), o.size());
  }
}
