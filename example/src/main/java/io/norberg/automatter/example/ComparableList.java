package io.norberg.automatter.example;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collection;

public class ComparableList<T> extends ArrayList<T> implements Comparable<ComparableList<T>> {

  private static final long serialVersionUID = 1L;

  public ComparableList(final Collection<? extends T> values) {
    super(values);
  }

  public static <T> ComparableList<T> of(final Collection<? extends T> values) {
    return new ComparableList<>(values);
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public static <T> ComparableList<T> of(final T... values) {
    return new ComparableList<>(asList(values));
  }

  @Override
  public int compareTo(final ComparableList<T> o) {
    return Integer.compare(size(), o.size());
  }
}
