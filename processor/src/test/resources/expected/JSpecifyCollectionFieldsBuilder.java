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
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
@AutoMatter.Generated
public final class JSpecifyCollectionFieldsBuilder {
  private List<String> strings;

  private Map<String, Integer> integers;

  private Set<Long> numbers;

  public JSpecifyCollectionFieldsBuilder() {
  }

  private JSpecifyCollectionFieldsBuilder(JSpecifyCollectionFields v) {
    List<String> _strings = v.strings();
    this.strings = (_strings == null) ? null : new ArrayList<String>(_strings);
    Map<String, Integer> _integers = v.integers();
    this.integers = (_integers == null) ? null : new HashMap<String, Integer>(_integers);
    Set<Long> _numbers = v.numbers();
    this.numbers = (_numbers == null) ? null : new HashSet<Long>(_numbers);
  }

  private JSpecifyCollectionFieldsBuilder(JSpecifyCollectionFieldsBuilder v) {
    this.strings = (v.strings() == null) ? null : new ArrayList<String>(v.strings());
    this.integers = (v.integers() == null) ? null : new HashMap<String, Integer>(v.integers());
    this.numbers = (v.numbers() == null) ? null : new HashSet<Long>(v.numbers());
  }

  public List<String> strings() {
    return strings;
  }

  public JSpecifyCollectionFieldsBuilder strings(List<? extends String> strings) {
    return strings((Collection<? extends String>) strings);
  }

  public JSpecifyCollectionFieldsBuilder strings(Collection<? extends String> strings) {
    if (strings == null) {
      this.strings = null;
      return this;
    }
    this.strings = new ArrayList<String>(strings);
    return this;
  }

  public JSpecifyCollectionFieldsBuilder strings(Iterable<? extends String> strings) {
    if (strings == null) {
      this.strings = null;
      return this;
    }
    if (strings instanceof Collection) {
      return strings((Collection<? extends String>) strings);
    }
    return strings(strings.iterator());
  }

  public JSpecifyCollectionFieldsBuilder strings(Iterator<? extends String> strings) {
    if (strings == null) {
      this.strings = null;
      return this;
    }
    this.strings = new ArrayList<String>();
    while (strings.hasNext()) {
      String item = strings.next();
      this.strings.add(item);
    }
    return this;
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public final JSpecifyCollectionFieldsBuilder strings(String... strings) {
    if (strings == null) {
      this.strings = null;
      return this;
    }
    return strings(Arrays.asList(strings));
  }

  public JSpecifyCollectionFieldsBuilder addString(String string) {
    if (this.strings == null) {
      this.strings = new ArrayList<String>();
    }
    strings.add(string);
    return this;
  }

  public Map<String, Integer> integers() {
    return integers;
  }

  public JSpecifyCollectionFieldsBuilder integers(
      Map<? extends String, ? extends Integer> integers) {
    if (integers == null) {
      this.integers = null;
      return this;
    }
    this.integers = new HashMap<String, Integer>(integers);
    return this;
  }

  public JSpecifyCollectionFieldsBuilder integers(String k1, Integer v1) {
    integers = new HashMap<String, Integer>();
    integers.put(k1, v1);
    return this;
  }

  public JSpecifyCollectionFieldsBuilder integers(String k1, Integer v1, String k2, Integer v2) {
    integers(k1, v1);
    integers.put(k2, v2);
    return this;
  }

  public JSpecifyCollectionFieldsBuilder integers(String k1, Integer v1, String k2, Integer v2,
      String k3, Integer v3) {
    integers(k1, v1, k2, v2);
    integers.put(k3, v3);
    return this;
  }

  public JSpecifyCollectionFieldsBuilder integers(String k1, Integer v1, String k2, Integer v2,
      String k3, Integer v3, String k4, Integer v4) {
    integers(k1, v1, k2, v2, k3, v3);
    integers.put(k4, v4);
    return this;
  }

  public JSpecifyCollectionFieldsBuilder integers(String k1, Integer v1, String k2, Integer v2,
      String k3, Integer v3, String k4, Integer v4, String k5, Integer v5) {
    integers(k1, v1, k2, v2, k3, v3, k4, v4);
    integers.put(k5, v5);
    return this;
  }

  public JSpecifyCollectionFieldsBuilder putInteger(String key, Integer value) {
    if (this.integers == null) {
      this.integers = new HashMap<String, Integer>();
    }
    integers.put(key, value);
    return this;
  }

  public Set<Long> numbers() {
    return numbers;
  }

  public JSpecifyCollectionFieldsBuilder numbers(Set<? extends Long> numbers) {
    return numbers((Collection<? extends Long>) numbers);
  }

  public JSpecifyCollectionFieldsBuilder numbers(Collection<? extends Long> numbers) {
    if (numbers == null) {
      this.numbers = null;
      return this;
    }
    this.numbers = new HashSet<Long>(numbers);
    return this;
  }

  public JSpecifyCollectionFieldsBuilder numbers(Iterable<? extends Long> numbers) {
    if (numbers == null) {
      this.numbers = null;
      return this;
    }
    if (numbers instanceof Collection) {
      return numbers((Collection<? extends Long>) numbers);
    }
    return numbers(numbers.iterator());
  }

  public JSpecifyCollectionFieldsBuilder numbers(Iterator<? extends Long> numbers) {
    if (numbers == null) {
      this.numbers = null;
      return this;
    }
    this.numbers = new HashSet<Long>();
    while (numbers.hasNext()) {
      Long item = numbers.next();
      this.numbers.add(item);
    }
    return this;
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public final JSpecifyCollectionFieldsBuilder numbers(Long... numbers) {
    if (numbers == null) {
      this.numbers = null;
      return this;
    }
    return numbers(Arrays.asList(numbers));
  }

  public JSpecifyCollectionFieldsBuilder addNumber(Long number) {
    if (this.numbers == null) {
      this.numbers = new HashSet<Long>();
    }
    numbers.add(number);
    return this;
  }

  public JSpecifyCollectionFields build() {
    List<String> _strings = (strings != null) ? Collections.unmodifiableList(new ArrayList<String>(strings)) : null;
    Map<String, Integer> _integers = (integers != null) ? Collections.unmodifiableMap(new HashMap<String, Integer>(integers)) : null;
    Set<Long> _numbers = (numbers != null) ? Collections.unmodifiableSet(new HashSet<Long>(numbers)) : null;
    return new Value(_strings, _integers, _numbers);
  }

  public static JSpecifyCollectionFieldsBuilder from(JSpecifyCollectionFields v) {
    return new JSpecifyCollectionFieldsBuilder(v);
  }

  public static JSpecifyCollectionFieldsBuilder from(JSpecifyCollectionFieldsBuilder v) {
    return new JSpecifyCollectionFieldsBuilder(v);
  }

  @AutoMatter.Generated
  private static final class Value
      implements JSpecifyCollectionFields {

    private final List<String> strings;

    private final Map<String, Integer> integers;

    private final Set<Long> numbers;

    private Value(@AutoMatter.Field("strings") List<String> strings,
        @AutoMatter.Field("integers") Map<String, Integer> integers,
        @AutoMatter.Field("numbers") Set<Long> numbers) {
      this.strings = strings;
      this.integers = integers;
      this.numbers = numbers;
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
    public Set<Long> numbers() {
      return numbers;
    }

    public JSpecifyCollectionFieldsBuilder builder() {
      return new JSpecifyCollectionFieldsBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof JSpecifyCollectionFields)) {
        return false;
      }
      final JSpecifyCollectionFields that = (JSpecifyCollectionFields) o;
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
      result = 31 * result + (this.strings != null ? this.strings.hashCode() : 0);
      result = 31 * result + (this.integers != null ? this.integers.hashCode() : 0);
      result = 31 * result + (this.numbers != null ? this.numbers.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "JSpecifyCollectionFields{" +
          "strings=" + strings +
          ", integers=" + integers +
          ", numbers=" + numbers +
          '}';
    }
  }
}
