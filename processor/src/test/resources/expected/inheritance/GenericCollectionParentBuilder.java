package inheritance;

import io.norberg.automatter.AutoMatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
public final class GenericCollectionParentBuilder<T> {
  private List<T> foos;

  public GenericCollectionParentBuilder() {
  }

  private GenericCollectionParentBuilder(GenericCollectionParent<? extends T> v) {
    List<? extends T> _foos = v.foos();
    this.foos = (_foos == null) ? null : new ArrayList<T>(_foos);
  }

  private GenericCollectionParentBuilder(GenericCollectionParentBuilder<? extends T> v) {
    this.foos = (v.foos == null) ? null : new ArrayList<T>(v.foos);
  }

  public List<T> foos() {
    if (this.foos == null) {
      this.foos = new ArrayList<T>();
    }
    return foos;
  }

  public GenericCollectionParentBuilder<T> foos(List<? extends T> foos) {
    return foos((Collection<? extends T>) foos);
  }

  public GenericCollectionParentBuilder<T> foos(Collection<? extends T> foos) {
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

  public GenericCollectionParentBuilder<T> foos(Iterable<? extends T> foos) {
    if (foos == null) {
      throw new NullPointerException("foos");
    }
    if (foos instanceof Collection) {
      return foos((Collection<? extends T>) foos);
    }
    return foos(foos.iterator());
  }

  public GenericCollectionParentBuilder<T> foos(Iterator<? extends T> foos) {
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
  public final GenericCollectionParentBuilder<T> foos(T... foos) {
    if (foos == null) {
      throw new NullPointerException("foos");
    }
    return foos(Arrays.asList(foos));
  }

  public GenericCollectionParentBuilder<T> addFoo(T foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    if (this.foos == null) {
      this.foos = new ArrayList<T>();
    }
    foos.add(foo);
    return this;
  }

  public GenericCollectionParent<T> build() {
    List<T> _foos = (foos != null) ? Collections.unmodifiableList(new ArrayList<T>(foos)) : Collections.<T>emptyList();
    return new Value<T>(_foos);
  }

  public static <T> GenericCollectionParentBuilder<T> from(GenericCollectionParent<? extends T> v) {
    return new GenericCollectionParentBuilder<T>(v);
  }

  public static <T> GenericCollectionParentBuilder<T> from(GenericCollectionParentBuilder<? extends T> v) {
    return new GenericCollectionParentBuilder<T>(v);
  }

  private static final class Value<T> implements GenericCollectionParent<T> {
    private final List<T> foos;

    private Value(@AutoMatter.Field("foos") List<T> foos) {
      this.foos = (foos != null) ? foos : Collections.<T>emptyList();
    }

    @AutoMatter.Field
    @Override
    public List<T> foos() {
      return foos;
    }

    public GenericCollectionParentBuilder<T> builder() {
      return new GenericCollectionParentBuilder<T>(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof GenericCollectionParent)) {
        return false;
      }
      final GenericCollectionParent<?> that = (GenericCollectionParent<?>) o;
      if (foos != null ? !foos.equals(that.foos()) : that.foos() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      result = 31 * result + (this.foos != null ? this.foos.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "GenericCollectionParent{" +
             "foos=" + foos +
             '}';
    }
  }
}