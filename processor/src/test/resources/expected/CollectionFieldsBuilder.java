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
import java.util.Set;

import javax.annotation.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
public final class CollectionFieldsBuilder
    implements CollectionFields {

  private List<String> strings;
  private Map<String,Integer> integers;
  private Set<Long> numbers;

  public CollectionFieldsBuilder() {
  }

  private CollectionFieldsBuilder(CollectionFields v) {
    List<String> _strings = v.strings();
    this.strings = (_strings == null) ? null : new ArrayList<String>(_strings);
    Map<String,Integer> _integers = v.integers();
    this.integers = (_integers == null) ? null : new HashMap<String,Integer>(_integers);
    Set<Long> _numbers = v.numbers();
    this.numbers = (_numbers == null) ? null : new HashSet<Long>(_numbers);
  }

  private CollectionFieldsBuilder(CollectionFieldsBuilder v) {
    this.strings = (v.strings == null) ? null : new ArrayList<String>(v.strings);
    this.integers = (v.integers == null) ? null : new HashMap<String,Integer>(v.integers);
    this.numbers = (v.numbers == null) ? null : new HashSet<Long>(v.numbers);
  }

  @Override
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

  public CollectionFieldsBuilder strings(String... strings) {
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

  @Override
  public Map<String,Integer> integers() {
    if (integers == null) {
      integers = new HashMap<String,Integer>();
    }
    return integers;
  }

  public CollectionFieldsBuilder integers(Map<? extends String,? extends Integer> integers) {
    if (integers == null) {
      throw new NullPointerException("integers");
    }
    for (Map.Entry<? extends String,? extends Integer> entry : integers.entrySet()) {
      if (entry.getKey() == null) {
        throw new NullPointerException("integers: null key");
      }
      if (entry.getValue() == null) {
        throw new NullPointerException("integers: null value");
      }
    }
    this.integers = new HashMap<String,Integer>(integers);
    return this;
  }

  public CollectionFieldsBuilder integers(String k1, Integer v1) {
    if (k1 == null) {
      throw new NullPointerException("integers: k1");
    }
    if (v1 == null) {
      throw new NullPointerException("integers: v1");
    }
    integers = new HashMap<String,Integer>();
    integers.put(k1, v1);
    return this;
  }

  public CollectionFieldsBuilder integers(String k1, Integer v1,
                                          String k2, Integer v2) {
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

  public CollectionFieldsBuilder integers(String k1, Integer v1,
                                          String k2, Integer v2,
                                          String k3, Integer v3) {
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

  public CollectionFieldsBuilder integers(String k1, Integer v1,
                                          String k2, Integer v2,
                                          String k3, Integer v3,
                                          String k4, Integer v4) {
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

  public CollectionFieldsBuilder integers(String k1, Integer v1,
                                          String k2, Integer v2,
                                          String k3, Integer v3,
                                          String k4, Integer v4,
                                          String k5, Integer v5) {
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

  public CollectionFieldsBuilder addInteger(String key, Integer value) {
    if (key == null) {
      throw new NullPointerException("integer: key");
    }
    if (value == null) {
      throw new NullPointerException("integer: value");
    }
    if (integers == null) {
      integers = new HashMap<String,Integer>();
    }
    integers.put(key, value);
    return this;
  }

  @Override
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

  public CollectionFieldsBuilder numbers(Long... numbers) {
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

  public CollectionFields build() {
    return new Value((strings != null) ? Collections.unmodifiableList(new ArrayList<String>(strings)) : Collections.<String>emptyList(),
                     (integers != null) ? Collections.unmodifiableMap(new HashMap<String,Integer>(integers)) : Collections.<String,Integer>emptyMap(),
                     (numbers != null) ? Collections.unmodifiableSet(new HashSet<Long>(numbers)) : Collections.<Long>emptySet());
  }

  public static CollectionFieldsBuilder from(CollectionFields v) {
    return new CollectionFieldsBuilder(v);
  }

  public static CollectionFieldsBuilder from(CollectionFieldsBuilder v) {
    return new CollectionFieldsBuilder(v);
  }

  private static final class Value
      implements CollectionFields {

    private final List<String> strings;
    private final Map<String,Integer> integers;
    private final Set<Long> numbers;

    private Value(@AutoMatter.Field("strings") List<String> strings,
                  @AutoMatter.Field("integers") Map<String,Integer> integers,
                  @AutoMatter.Field("numbers") Set<Long> numbers) {
      this.strings = (strings != null) ? strings : Collections.<String>emptyList();
      this.integers = (integers != null) ? integers : Collections.<String,Integer>emptyMap();
      this.numbers = (numbers != null) ? numbers : Collections.<Long>emptySet();
    }

    @AutoMatter.Field
    @Override
    public List<String> strings() {
      return strings;
    }

    @AutoMatter.Field
    @Override
    public Map<String,Integer> integers() {
      return integers;
    }

    @AutoMatter.Field
    @Override
    public Set<Long> numbers() {
      return numbers;
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
      if (numbers != null ? !numbers.equals(that.numbers()) : that.numbers() != null) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      result = 31 * result + (strings != null ? strings.hashCode() : 0);
      result = 31 * result + (integers != null ? integers.hashCode() : 0);
      result = 31 * result + (numbers != null ? numbers.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "CollectionFields{" +
             "strings=" + strings +
             ", integers=" + integers +
             ", numbers=" + numbers +
             '}';
    }
  }
}
