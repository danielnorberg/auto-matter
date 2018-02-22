package foo;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.NavigableMap;
import java.util.Set;
import java.util.SortedSet;
import java.util.NavigableSet;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface CollectionFields {
  List<String> strings();
  Map<String, Integer> integers();
  SortedMap<String, Integer> sortedIntegers();
  NavigableMap<String, Integer> navigableIntegers();
  Set<Long> numbers();
  SortedSet<Long> sortedNumbers();
  NavigableSet<Long> navigableNumbers();
}
