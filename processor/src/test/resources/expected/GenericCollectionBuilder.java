package generic_collection;

import io.norberg.automatter.AutoMatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
public final class GenericCollectionBuilder<T, K, V> {
  private List<T> foos;

  private Map<K, V> bars;

  public GenericCollectionBuilder() {
  }

  private GenericCollectionBuilder(GenericCollection<? extends T, ? extends K, ? extends V> v) {
    List<? extends T> _foos = v.foos();
    this.foos = (_foos == null) ? null : new ArrayList<T>(_foos);
    Map<? extends K, ? extends V> _bars = v.bars();
    this.bars = (_bars == null) ? null : new HashMap<K, V>(_bars);
  }

  private GenericCollectionBuilder(GenericCollectionBuilder<? extends T, ? extends K, ? extends V> v) {
    this.foos = (v.foos == null) ? null : new ArrayList<T>(v.foos);
    this.bars = (v.bars == null) ? null : new HashMap<K, V>(v.bars);
  }

  public List<T> foos() {
    if (this.foos == null) {
      this.foos = new ArrayList<T>();
    }
    return foos;
  }

  public GenericCollectionBuilder<T, K, V> foos(List<? extends T> foos) {
    return foos((Collection<? extends T>) foos);
  }

  public GenericCollectionBuilder<T, K, V> foos(Collection<? extends T> foos) {
    if (foos == null) {
      throw new NullPointerException("foos");
    }
    for (T item : foos) {
      if (item == null) {
        throw new NullPointerException("foos: null item");
      }
    }
    this.foos = new ArrayList<T>(foos);
    return this;
  }

  public GenericCollectionBuilder<T, K, V> foos(Iterable<? extends T> foos) {
    if (foos == null) {
      throw new NullPointerException("foos");
    }
    if (foos instanceof Collection) {
      return foos((Collection<? extends T>) foos);
    }
    return foos(foos.iterator());
  }

  public GenericCollectionBuilder<T, K, V> foos(Iterator<? extends T> foos) {
    if (foos == null) {
      throw new NullPointerException("foos");
    }
    this.foos = new ArrayList<T>();
    while (foos.hasNext()) {
      T item = foos.next();
      if (item == null) {
        throw new NullPointerException("foos: null item");
      }
      this.foos.add(item);
    }
    return this;
  }

  @SafeVarargs
  public final GenericCollectionBuilder<T, K, V> foos(T... foos) {
    if (foos == null) {
      throw new NullPointerException("foos");
    }
    return foos(Arrays.asList(foos));
  }

  public GenericCollectionBuilder<T, K, V> addFoo(T foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    if (this.foos == null) {
      this.foos = new ArrayList<T>();
    }
    foos.add(foo);
    return this;
  }

  public Map<K, V> bars() {
    if (this.bars == null) {
      this.bars = new HashMap<K, V>();
    }
    return bars;
  }

  public GenericCollectionBuilder<T, K, V> bars(Map<? extends K, ? extends V> bars) {
    if (bars == null) {
      throw new NullPointerException("bars");
    }
    for (Map.Entry<? extends K, ? extends V> entry : bars.entrySet()) {
      if (entry.getKey() == null) {
        throw new NullPointerException("bars: null key");
      }
      if (entry.getValue() == null) {
        throw new NullPointerException("bars: null value");
      }
    }
    this.bars = new HashMap<K, V>(bars);
    return this;
  }

  public GenericCollectionBuilder<T, K, V> bars(K k1, V v1) {
    if (k1 == null) {
      throw new NullPointerException("bars: k1");
    }
    if (v1 == null) {
      throw new NullPointerException("bars: v1");
    }
    bars = new HashMap<K, V>();
    bars.put(k1, v1);
    return this;
  }

  public GenericCollectionBuilder<T, K, V> bars(K k1, V v1, K k2, V v2) {
    bars(k1, v1);
    if (k2 == null) {
      throw new NullPointerException("bars: k2");
    }
    if (v2 == null) {
      throw new NullPointerException("bars: v2");
    }
    bars.put(k2, v2);
    return this;
  }

  public GenericCollectionBuilder<T, K, V> bars(K k1, V v1, K k2, V v2, K k3, V v3) {
    bars(k1, v1, k2, v2);
    if (k3 == null) {
      throw new NullPointerException("bars: k3");
    }
    if (v3 == null) {
      throw new NullPointerException("bars: v3");
    }
    bars.put(k3, v3);
    return this;
  }

  public GenericCollectionBuilder<T, K, V> bars(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
    bars(k1, v1, k2, v2, k3, v3);
    if (k4 == null) {
      throw new NullPointerException("bars: k4");
    }
    if (v4 == null) {
      throw new NullPointerException("bars: v4");
    }
    bars.put(k4, v4);
    return this;
  }

  public GenericCollectionBuilder<T, K, V> bars(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
    bars(k1, v1, k2, v2, k3, v3, k4, v4);
    if (k5 == null) {
      throw new NullPointerException("bars: k5");
    }
    if (v5 == null) {
      throw new NullPointerException("bars: v5");
    }
    bars.put(k5, v5);
    return this;
  }

  public GenericCollectionBuilder<T, K, V> putBar(K key, V value) {
    if (key == null) {
      throw new NullPointerException("bar: key");
    }
    if (value == null) {
      throw new NullPointerException("bar: value");
    }
    if (this.bars == null) {
      this.bars = new HashMap<K, V>();
    }
    bars.put(key, value);
    return this;
  }

  public GenericCollectionBuilder<T, K, V> builder() {
    return new GenericCollectionBuilder<T, K, V>(this);
  }

  public GenericCollection<T, K, V> build() {
    List<T> _foos = (foos != null) ? Collections.unmodifiableList(new ArrayList<T>(foos)) : Collections.<T>emptyList();
    Map<K, V> _bars = (bars != null) ? Collections.unmodifiableMap(new HashMap<K, V>(bars)) : Collections.<K, V>emptyMap();
    return new Value<T, K, V>(_foos, _bars);
  }

  public static <T, K, V> GenericCollectionBuilder<T, K, V> from(GenericCollection<? extends T, ? extends K, ? extends V> v) {
    return new GenericCollectionBuilder<T, K, V>(v);
  }

  public static <T, K, V> GenericCollectionBuilder<T, K, V> from(GenericCollectionBuilder<? extends T, ? extends K, ? extends V> v) {
    return new GenericCollectionBuilder<T, K, V>(v);
  }

  private static final class Value<T, K, V> implements GenericCollection<T, K, V> {
    private final List<T> foos;

    private final Map<K, V> bars;

    private Value(@AutoMatter.Field("foos") List<T> foos, @AutoMatter.Field("bars") Map<K, V> bars) {
      this.foos = (foos != null) ? foos : Collections.<T>emptyList();
      this.bars = (bars != null) ? bars : Collections.<K, V>emptyMap();
    }

    @AutoMatter.Field
    @Override
    public List<T> foos() {
      return foos;
    }

    @AutoMatter.Field
    @Override
    public Map<K, V> bars() {
      return bars;
    }

    @Override
    public GenericCollectionBuilder<T, K, V> builder() {
      return new GenericCollectionBuilder<T, K, V>(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof GenericCollection)) {
        return false;
      }
      final GenericCollection<?, ?, ?> that = (GenericCollection<?, ?, ?>) o;
      if (foos != null ? !foos.equals(that.foos()) : that.foos() != null) {
        return false;
      }
      if (bars != null ? !bars.equals(that.bars()) : that.bars() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      result = 31 * result + (foos != null ? foos.hashCode() : 0);
      result = 31 * result + (bars != null ? bars.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "GenericCollection{" +
             "foos=" + foos +
             ", bars=" + bars +
             '}';
    }
  }
}
