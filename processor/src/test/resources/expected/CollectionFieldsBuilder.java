package foo;

import io.norberg.automatter.AutoMatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
@AutoMatter.Generated
public final class CollectionFieldsBuilder {
  private List<String> strings;

  private Map<String, Integer> integers;

  private SortedMap<String, Integer> sortedIntegers;

  private NavigableMap<String, Integer> navigableIntegers;

  private Set<Long> numbers;

  private SortedSet<Long> sortedNumbers;

  private NavigableSet<Long> navigableNumbers;

  public CollectionFieldsBuilder() {
  }

  private CollectionFieldsBuilder(CollectionFields v) {
    List<String> _strings = v.strings();
    this.strings = (_strings == null) ? null : new ArrayList<String>(_strings);
    Map<String, Integer> _integers = v.integers();
    this.integers = (_integers == null) ? null : new HashMap<String, Integer>(_integers);
    SortedMap<String, Integer> _sortedIntegers = v.sortedIntegers();
    this.sortedIntegers = (_sortedIntegers == null) ? null : new TreeMap<String, Integer>(_sortedIntegers);
    NavigableMap<String, Integer> _navigableIntegers = v.navigableIntegers();
    this.navigableIntegers = (_navigableIntegers == null) ? null : new TreeMap<String, Integer>(_navigableIntegers);
    Set<Long> _numbers = v.numbers();
    this.numbers = (_numbers == null) ? null : new HashSet<Long>(_numbers);
    SortedSet<Long> _sortedNumbers = v.sortedNumbers();
    this.sortedNumbers = (_sortedNumbers == null) ? null : new TreeSet<Long>(_sortedNumbers);
    NavigableSet<Long> _navigableNumbers = v.navigableNumbers();
    this.navigableNumbers = (_navigableNumbers == null) ? null : new TreeSet<Long>(_navigableNumbers);
  }

  private CollectionFieldsBuilder(CollectionFieldsBuilder v) {
    this.strings = (v.strings() == null) ? null : new ArrayList<String>(v.strings());
    this.integers = (v.integers() == null) ? null : new HashMap<String, Integer>(v.integers());
    this.sortedIntegers = (v.sortedIntegers() == null) ? null : new TreeMap<String, Integer>(v.sortedIntegers());
    this.navigableIntegers = (v.navigableIntegers() == null) ? null : new TreeMap<String, Integer>(v.navigableIntegers());
    this.numbers = (v.numbers() == null) ? null : new HashSet<Long>(v.numbers());
    this.sortedNumbers = (v.sortedNumbers() == null) ? null : new TreeSet<Long>(v.sortedNumbers());
    this.navigableNumbers = (v.navigableNumbers() == null) ? null : new TreeSet<Long>(v.navigableNumbers());
  }

  public List<String> strings() {
    if (this.strings == null) {
      this.strings = new ArrayList<String>();
    }
    return strings;
  }

  public CollectionFieldsBuilder strings(List<? extends String> strings) {
    return strings((Collection<? extends String>) strings);
  }

  public CollectionFieldsBuilder strings(Collection<? extends String> strings) {
    if (strings == null) {
      throw new NullPointerException("strings");
    }
    for (String item : strings) {
      if (item == null) {
        throw new NullPointerException("strings: null item");
      }
    }
    this.strings = new ArrayList<String>(strings);
    return this;
  }

  public CollectionFieldsBuilder strings(Iterable<? extends String> strings) {
    if (strings == null) {
      throw new NullPointerException("strings");
    }
    if (strings instanceof Collection) {
      return strings((Collection<? extends String>) strings);
    }
    return strings(strings.iterator());
  }

  public CollectionFieldsBuilder strings(Iterator<? extends String> strings) {
    if (strings == null) {
      throw new NullPointerException("strings");
    }
    this.strings = new ArrayList<String>();
    while (strings.hasNext()) {
      String item = strings.next();
      if (item == null) {
        throw new NullPointerException("strings: null item");
      }
      this.strings.add(item);
    }
    return this;
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public final CollectionFieldsBuilder strings(String... strings) {
    if (strings == null) {
      throw new NullPointerException("strings");
    }
    return strings(Arrays.asList(strings));
  }

  public CollectionFieldsBuilder addString(String string) {
    if (string == null) {
      throw new NullPointerException("string");
    }
    if (this.strings == null) {
      this.strings = new ArrayList<String>();
    }
    strings.add(string);
    return this;
  }

  public Map<String, Integer> integers() {
    if (this.integers == null) {
      this.integers = new HashMap<String, Integer>();
    }
    return integers;
  }

  public CollectionFieldsBuilder integers(Map<? extends String, ? extends Integer> integers) {
    if (integers == null) {
      throw new NullPointerException("integers");
    }
    for (Map.Entry<? extends String, ? extends Integer> entry : integers.entrySet()) {
      if (entry.getKey() == null) {
        throw new NullPointerException("integers: null key");
      }
      if (entry.getValue() == null) {
        throw new NullPointerException("integers: null value");
      }
    }
    this.integers = new HashMap<String, Integer>(integers);
    return this;
  }

  public CollectionFieldsBuilder integers(String k1, Integer v1) {
    if (k1 == null) {
      throw new NullPointerException("integers: k1");
    }
    if (v1 == null) {
      throw new NullPointerException("integers: v1");
    }
    integers = new HashMap<String, Integer>();
    integers.put(k1, v1);
    return this;
  }

  public CollectionFieldsBuilder integers(String k1, Integer v1, String k2, Integer v2) {
    integers(k1, v1);
    if (k2 == null) {
      throw new NullPointerException("integers: k2");
    }
    if (v2 == null) {
      throw new NullPointerException("integers: v2");
    }
    integers.put(k2, v2);
    return this;
  }

  public CollectionFieldsBuilder integers(String k1, Integer v1, String k2, Integer v2, String k3,
      Integer v3) {
    integers(k1, v1, k2, v2);
    if (k3 == null) {
      throw new NullPointerException("integers: k3");
    }
    if (v3 == null) {
      throw new NullPointerException("integers: v3");
    }
    integers.put(k3, v3);
    return this;
  }

  public CollectionFieldsBuilder integers(String k1, Integer v1, String k2, Integer v2, String k3,
      Integer v3, String k4, Integer v4) {
    integers(k1, v1, k2, v2, k3, v3);
    if (k4 == null) {
      throw new NullPointerException("integers: k4");
    }
    if (v4 == null) {
      throw new NullPointerException("integers: v4");
    }
    integers.put(k4, v4);
    return this;
  }

  public CollectionFieldsBuilder integers(String k1, Integer v1, String k2, Integer v2, String k3,
      Integer v3, String k4, Integer v4, String k5, Integer v5) {
    integers(k1, v1, k2, v2, k3, v3, k4, v4);
    if (k5 == null) {
      throw new NullPointerException("integers: k5");
    }
    if (v5 == null) {
      throw new NullPointerException("integers: v5");
    }
    integers.put(k5, v5);
    return this;
  }

  public CollectionFieldsBuilder putInteger(String key, Integer value) {
    if (key == null) {
      throw new NullPointerException("integer: key");
    }
    if (value == null) {
      throw new NullPointerException("integer: value");
    }
    if (this.integers == null) {
      this.integers = new HashMap<String, Integer>();
    }
    integers.put(key, value);
    return this;
  }

  public SortedMap<String, Integer> sortedIntegers() {
    if (this.sortedIntegers == null) {
      this.sortedIntegers = new TreeMap<String, Integer>();
    }
    return sortedIntegers;
  }

  public CollectionFieldsBuilder sortedIntegers(
      Map<? extends String, ? extends Integer> sortedIntegers) {
    if (sortedIntegers == null) {
      throw new NullPointerException("sortedIntegers");
    }
    for (Map.Entry<? extends String, ? extends Integer> entry : sortedIntegers.entrySet()) {
      if (entry.getKey() == null) {
        throw new NullPointerException("sortedIntegers: null key");
      }
      if (entry.getValue() == null) {
        throw new NullPointerException("sortedIntegers: null value");
      }
    }
    this.sortedIntegers = new TreeMap<String, Integer>(sortedIntegers);
    return this;
  }

  public CollectionFieldsBuilder sortedIntegers(String k1, Integer v1) {
    if (k1 == null) {
      throw new NullPointerException("sortedIntegers: k1");
    }
    if (v1 == null) {
      throw new NullPointerException("sortedIntegers: v1");
    }
    sortedIntegers = new TreeMap<String, Integer>();
    sortedIntegers.put(k1, v1);
    return this;
  }

  public CollectionFieldsBuilder sortedIntegers(String k1, Integer v1, String k2, Integer v2) {
    sortedIntegers(k1, v1);
    if (k2 == null) {
      throw new NullPointerException("sortedIntegers: k2");
    }
    if (v2 == null) {
      throw new NullPointerException("sortedIntegers: v2");
    }
    sortedIntegers.put(k2, v2);
    return this;
  }

  public CollectionFieldsBuilder sortedIntegers(String k1, Integer v1, String k2, Integer v2,
      String k3, Integer v3) {
    sortedIntegers(k1, v1, k2, v2);
    if (k3 == null) {
      throw new NullPointerException("sortedIntegers: k3");
    }
    if (v3 == null) {
      throw new NullPointerException("sortedIntegers: v3");
    }
    sortedIntegers.put(k3, v3);
    return this;
  }

  public CollectionFieldsBuilder sortedIntegers(String k1, Integer v1, String k2, Integer v2,
      String k3, Integer v3, String k4, Integer v4) {
    sortedIntegers(k1, v1, k2, v2, k3, v3);
    if (k4 == null) {
      throw new NullPointerException("sortedIntegers: k4");
    }
    if (v4 == null) {
      throw new NullPointerException("sortedIntegers: v4");
    }
    sortedIntegers.put(k4, v4);
    return this;
  }

  public CollectionFieldsBuilder sortedIntegers(String k1, Integer v1, String k2, Integer v2,
      String k3, Integer v3, String k4, Integer v4, String k5, Integer v5) {
    sortedIntegers(k1, v1, k2, v2, k3, v3, k4, v4);
    if (k5 == null) {
      throw new NullPointerException("sortedIntegers: k5");
    }
    if (v5 == null) {
      throw new NullPointerException("sortedIntegers: v5");
    }
    sortedIntegers.put(k5, v5);
    return this;
  }

  public CollectionFieldsBuilder putSortedInteger(String key, Integer value) {
    if (key == null) {
      throw new NullPointerException("sortedInteger: key");
    }
    if (value == null) {
      throw new NullPointerException("sortedInteger: value");
    }
    if (this.sortedIntegers == null) {
      this.sortedIntegers = new TreeMap<String, Integer>();
    }
    sortedIntegers.put(key, value);
    return this;
  }

  public NavigableMap<String, Integer> navigableIntegers() {
    if (this.navigableIntegers == null) {
      this.navigableIntegers = new TreeMap<String, Integer>();
    }
    return navigableIntegers;
  }

  public CollectionFieldsBuilder navigableIntegers(
      Map<? extends String, ? extends Integer> navigableIntegers) {
    if (navigableIntegers == null) {
      throw new NullPointerException("navigableIntegers");
    }
    for (Map.Entry<? extends String, ? extends Integer> entry : navigableIntegers.entrySet()) {
      if (entry.getKey() == null) {
        throw new NullPointerException("navigableIntegers: null key");
      }
      if (entry.getValue() == null) {
        throw new NullPointerException("navigableIntegers: null value");
      }
    }
    this.navigableIntegers = new TreeMap<String, Integer>(navigableIntegers);
    return this;
  }

  public CollectionFieldsBuilder navigableIntegers(String k1, Integer v1) {
    if (k1 == null) {
      throw new NullPointerException("navigableIntegers: k1");
    }
    if (v1 == null) {
      throw new NullPointerException("navigableIntegers: v1");
    }
    navigableIntegers = new TreeMap<String, Integer>();
    navigableIntegers.put(k1, v1);
    return this;
  }

  public CollectionFieldsBuilder navigableIntegers(String k1, Integer v1, String k2, Integer v2) {
    navigableIntegers(k1, v1);
    if (k2 == null) {
      throw new NullPointerException("navigableIntegers: k2");
    }
    if (v2 == null) {
      throw new NullPointerException("navigableIntegers: v2");
    }
    navigableIntegers.put(k2, v2);
    return this;
  }

  public CollectionFieldsBuilder navigableIntegers(String k1, Integer v1, String k2, Integer v2,
      String k3, Integer v3) {
    navigableIntegers(k1, v1, k2, v2);
    if (k3 == null) {
      throw new NullPointerException("navigableIntegers: k3");
    }
    if (v3 == null) {
      throw new NullPointerException("navigableIntegers: v3");
    }
    navigableIntegers.put(k3, v3);
    return this;
  }

  public CollectionFieldsBuilder navigableIntegers(String k1, Integer v1, String k2, Integer v2,
      String k3, Integer v3, String k4, Integer v4) {
    navigableIntegers(k1, v1, k2, v2, k3, v3);
    if (k4 == null) {
      throw new NullPointerException("navigableIntegers: k4");
    }
    if (v4 == null) {
      throw new NullPointerException("navigableIntegers: v4");
    }
    navigableIntegers.put(k4, v4);
    return this;
  }

  public CollectionFieldsBuilder navigableIntegers(String k1, Integer v1, String k2, Integer v2,
      String k3, Integer v3, String k4, Integer v4, String k5, Integer v5) {
    navigableIntegers(k1, v1, k2, v2, k3, v3, k4, v4);
    if (k5 == null) {
      throw new NullPointerException("navigableIntegers: k5");
    }
    if (v5 == null) {
      throw new NullPointerException("navigableIntegers: v5");
    }
    navigableIntegers.put(k5, v5);
    return this;
  }

  public CollectionFieldsBuilder putNavigableInteger(String key, Integer value) {
    if (key == null) {
      throw new NullPointerException("navigableInteger: key");
    }
    if (value == null) {
      throw new NullPointerException("navigableInteger: value");
    }
    if (this.navigableIntegers == null) {
      this.navigableIntegers = new TreeMap<String, Integer>();
    }
    navigableIntegers.put(key, value);
    return this;
  }

  public Set<Long> numbers() {
    if (this.numbers == null) {
      this.numbers = new HashSet<Long>();
    }
    return numbers;
  }

  public CollectionFieldsBuilder numbers(Set<? extends Long> numbers) {
    return numbers((Collection<? extends Long>) numbers);
  }

  public CollectionFieldsBuilder numbers(Collection<? extends Long> numbers) {
    if (numbers == null) {
      throw new NullPointerException("numbers");
    }
    for (Long item : numbers) {
      if (item == null) {
        throw new NullPointerException("numbers: null item");
      }
    }
    this.numbers = new HashSet<Long>(numbers);
    return this;
  }

  public CollectionFieldsBuilder numbers(Iterable<? extends Long> numbers) {
    if (numbers == null) {
      throw new NullPointerException("numbers");
    }
    if (numbers instanceof Collection) {
      return numbers((Collection<? extends Long>) numbers);
    }
    return numbers(numbers.iterator());
  }

  public CollectionFieldsBuilder numbers(Iterator<? extends Long> numbers) {
    if (numbers == null) {
      throw new NullPointerException("numbers");
    }
    this.numbers = new HashSet<Long>();
    while (numbers.hasNext()) {
      Long item = numbers.next();
      if (item == null) {
        throw new NullPointerException("numbers: null item");
      }
      this.numbers.add(item);
    }
    return this;
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public final CollectionFieldsBuilder numbers(Long... numbers) {
    if (numbers == null) {
      throw new NullPointerException("numbers");
    }
    return numbers(Arrays.asList(numbers));
  }

  public CollectionFieldsBuilder addNumber(Long number) {
    if (number == null) {
      throw new NullPointerException("number");
    }
    if (this.numbers == null) {
      this.numbers = new HashSet<Long>();
    }
    numbers.add(number);
    return this;
  }

  public SortedSet<Long> sortedNumbers() {
    if (this.sortedNumbers == null) {
      this.sortedNumbers = new TreeSet<Long>();
    }
    return sortedNumbers;
  }

  public CollectionFieldsBuilder sortedNumbers(SortedSet<? extends Long> sortedNumbers) {
    return sortedNumbers((Collection<? extends Long>) sortedNumbers);
  }

  public CollectionFieldsBuilder sortedNumbers(Collection<? extends Long> sortedNumbers) {
    if (sortedNumbers == null) {
      throw new NullPointerException("sortedNumbers");
    }
    for (Long item : sortedNumbers) {
      if (item == null) {
        throw new NullPointerException("sortedNumbers: null item");
      }
    }
    this.sortedNumbers = new TreeSet<Long>(sortedNumbers);
    return this;
  }

  public CollectionFieldsBuilder sortedNumbers(Iterable<? extends Long> sortedNumbers) {
    if (sortedNumbers == null) {
      throw new NullPointerException("sortedNumbers");
    }
    if (sortedNumbers instanceof Collection) {
      return sortedNumbers((Collection<? extends Long>) sortedNumbers);
    }
    return sortedNumbers(sortedNumbers.iterator());
  }

  public CollectionFieldsBuilder sortedNumbers(Iterator<? extends Long> sortedNumbers) {
    if (sortedNumbers == null) {
      throw new NullPointerException("sortedNumbers");
    }
    this.sortedNumbers = new TreeSet<Long>();
    while (sortedNumbers.hasNext()) {
      Long item = sortedNumbers.next();
      if (item == null) {
        throw new NullPointerException("sortedNumbers: null item");
      }
      this.sortedNumbers.add(item);
    }
    return this;
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public final CollectionFieldsBuilder sortedNumbers(Long... sortedNumbers) {
    if (sortedNumbers == null) {
      throw new NullPointerException("sortedNumbers");
    }
    return sortedNumbers(Arrays.asList(sortedNumbers));
  }

  public CollectionFieldsBuilder addSortedNumber(Long sortedNumber) {
    if (sortedNumber == null) {
      throw new NullPointerException("sortedNumber");
    }
    if (this.sortedNumbers == null) {
      this.sortedNumbers = new TreeSet<Long>();
    }
    sortedNumbers.add(sortedNumber);
    return this;
  }

  public NavigableSet<Long> navigableNumbers() {
    if (this.navigableNumbers == null) {
      this.navigableNumbers = new TreeSet<Long>();
    }
    return navigableNumbers;
  }

  public CollectionFieldsBuilder navigableNumbers(NavigableSet<? extends Long> navigableNumbers) {
    return navigableNumbers((Collection<? extends Long>) navigableNumbers);
  }

  public CollectionFieldsBuilder navigableNumbers(Collection<? extends Long> navigableNumbers) {
    if (navigableNumbers == null) {
      throw new NullPointerException("navigableNumbers");
    }
    for (Long item : navigableNumbers) {
      if (item == null) {
        throw new NullPointerException("navigableNumbers: null item");
      }
    }
    this.navigableNumbers = new TreeSet<Long>(navigableNumbers);
    return this;
  }

  public CollectionFieldsBuilder navigableNumbers(Iterable<? extends Long> navigableNumbers) {
    if (navigableNumbers == null) {
      throw new NullPointerException("navigableNumbers");
    }
    if (navigableNumbers instanceof Collection) {
      return navigableNumbers((Collection<? extends Long>) navigableNumbers);
    }
    return navigableNumbers(navigableNumbers.iterator());
  }

  public CollectionFieldsBuilder navigableNumbers(Iterator<? extends Long> navigableNumbers) {
    if (navigableNumbers == null) {
      throw new NullPointerException("navigableNumbers");
    }
    this.navigableNumbers = new TreeSet<Long>();
    while (navigableNumbers.hasNext()) {
      Long item = navigableNumbers.next();
      if (item == null) {
        throw new NullPointerException("navigableNumbers: null item");
      }
      this.navigableNumbers.add(item);
    }
    return this;
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public final CollectionFieldsBuilder navigableNumbers(Long... navigableNumbers) {
    if (navigableNumbers == null) {
      throw new NullPointerException("navigableNumbers");
    }
    return navigableNumbers(Arrays.asList(navigableNumbers));
  }

  public CollectionFieldsBuilder addNavigableNumber(Long navigableNumber) {
    if (navigableNumber == null) {
      throw new NullPointerException("navigableNumber");
    }
    if (this.navigableNumbers == null) {
      this.navigableNumbers = new TreeSet<Long>();
    }
    navigableNumbers.add(navigableNumber);
    return this;
  }

  public CollectionFields build() {
    List<String> _strings = (strings != null) ? Collections.unmodifiableList(new ArrayList<String>(strings)) : Collections.<String>emptyList();
    Map<String, Integer> _integers = (integers != null) ? Collections.unmodifiableMap(new HashMap<String, Integer>(integers)) : Collections.<String, Integer>emptyMap();
    SortedMap<String, Integer> _sortedIntegers = (sortedIntegers != null) ? Collections.unmodifiableSortedMap(new TreeMap<String, Integer>(sortedIntegers)) : Collections.<String, Integer>emptySortedMap();
    NavigableMap<String, Integer> _navigableIntegers = (navigableIntegers != null) ? Collections.unmodifiableNavigableMap(new TreeMap<String, Integer>(navigableIntegers)) : Collections.<String, Integer>emptyNavigableMap();
    Set<Long> _numbers = (numbers != null) ? Collections.unmodifiableSet(new HashSet<Long>(numbers)) : Collections.<Long>emptySet();
    SortedSet<Long> _sortedNumbers = (sortedNumbers != null) ? Collections.unmodifiableSortedSet(new TreeSet<Long>(sortedNumbers)) : Collections.<Long>emptySortedSet();
    NavigableSet<Long> _navigableNumbers = (navigableNumbers != null) ? Collections.unmodifiableNavigableSet(new TreeSet<Long>(navigableNumbers)) : Collections.<Long>emptyNavigableSet();
    return new Value(_strings, _integers, _sortedIntegers, _navigableIntegers, _numbers, _sortedNumbers, _navigableNumbers);
  }

  public static CollectionFieldsBuilder from(CollectionFields v) {
    return new CollectionFieldsBuilder(v);
  }

  public static CollectionFieldsBuilder from(CollectionFieldsBuilder v) {
    return new CollectionFieldsBuilder(v);
  }

  @AutoMatter.Generated
  private static final class Value implements CollectionFields {
    private final List<String> strings;

    private final Map<String, Integer> integers;

    private final SortedMap<String, Integer> sortedIntegers;

    private final NavigableMap<String, Integer> navigableIntegers;

    private final Set<Long> numbers;

    private final SortedSet<Long> sortedNumbers;

    private final NavigableSet<Long> navigableNumbers;

    private Value(@AutoMatter.Field("strings") List<String> strings,
        @AutoMatter.Field("integers") Map<String, Integer> integers,
        @AutoMatter.Field("sortedIntegers") SortedMap<String, Integer> sortedIntegers,
        @AutoMatter.Field("navigableIntegers") NavigableMap<String, Integer> navigableIntegers,
        @AutoMatter.Field("numbers") Set<Long> numbers,
        @AutoMatter.Field("sortedNumbers") SortedSet<Long> sortedNumbers,
        @AutoMatter.Field("navigableNumbers") NavigableSet<Long> navigableNumbers) {
      this.strings = (strings != null) ? strings : Collections.<String>emptyList();
      this.integers = (integers != null) ? integers : Collections.<String, Integer>emptyMap();
      this.sortedIntegers = (sortedIntegers != null) ? sortedIntegers : Collections.<String, Integer>emptySortedMap();
      this.navigableIntegers = (navigableIntegers != null) ? navigableIntegers : Collections.<String, Integer>emptyNavigableMap();
      this.numbers = (numbers != null) ? numbers : Collections.<Long>emptySet();
      this.sortedNumbers = (sortedNumbers != null) ? sortedNumbers : Collections.<Long>emptySortedSet();
      this.navigableNumbers = (navigableNumbers != null) ? navigableNumbers : Collections.<Long>emptyNavigableSet();
    }

    @AutoMatter.Field
    @Override
    public List<String> strings() {
      return strings;
    }

    @AutoMatter.Field
    @Override
    public Map<String, Integer> integers() {
      return integers;
    }

    @AutoMatter.Field
    @Override
    public SortedMap<String, Integer> sortedIntegers() {
      return sortedIntegers;
    }

    @AutoMatter.Field
    @Override
    public NavigableMap<String, Integer> navigableIntegers() {
      return navigableIntegers;
    }

    @AutoMatter.Field
    @Override
    public Set<Long> numbers() {
      return numbers;
    }

    @AutoMatter.Field
    @Override
    public SortedSet<Long> sortedNumbers() {
      return sortedNumbers;
    }

    @AutoMatter.Field
    @Override
    public NavigableSet<Long> navigableNumbers() {
      return navigableNumbers;
    }

    public CollectionFieldsBuilder builder() {
      return new CollectionFieldsBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof CollectionFields)) {
        return false;
      }
      final CollectionFields that = (CollectionFields) o;
      if (strings != null ? !strings.equals(that.strings()) : that.strings() != null) {
        return false;
      }
      if (integers != null ? !integers.equals(that.integers()) : that.integers() != null) {
        return false;
      }
      if (sortedIntegers != null ? !sortedIntegers.equals(that.sortedIntegers()) : that.sortedIntegers() != null) {
        return false;
      }
      if (navigableIntegers != null ? !navigableIntegers.equals(that.navigableIntegers()) : that.navigableIntegers() != null) {
        return false;
      }
      if (numbers != null ? !numbers.equals(that.numbers()) : that.numbers() != null) {
        return false;
      }
      if (sortedNumbers != null ? !sortedNumbers.equals(that.sortedNumbers()) : that.sortedNumbers() != null) {
        return false;
      }
      if (navigableNumbers != null ? !navigableNumbers.equals(that.navigableNumbers()) : that.navigableNumbers() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (this.strings != null ? this.strings.hashCode() : 0);
      result = 31 * result + (this.integers != null ? this.integers.hashCode() : 0);
      result = 31 * result + (this.sortedIntegers != null ? this.sortedIntegers.hashCode() : 0);
      result = 31 * result + (this.navigableIntegers != null ? this.navigableIntegers.hashCode() : 0);
      result = 31 * result + (this.numbers != null ? this.numbers.hashCode() : 0);
      result = 31 * result + (this.sortedNumbers != null ? this.sortedNumbers.hashCode() : 0);
      result = 31 * result + (this.navigableNumbers != null ? this.navigableNumbers.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "CollectionFields{" +
          "strings=" + strings +
          ", integers=" + integers +
          ", sortedIntegers=" + sortedIntegers +
          ", navigableIntegers=" + navigableIntegers +
          ", numbers=" + numbers +
          ", sortedNumbers=" + sortedNumbers +
          ", navigableNumbers=" + navigableNumbers +
          '}';
    }
  }
}
