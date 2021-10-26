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
@AutoMatter.Generated
public final class GenericSuperParentBuilder<T> {
  private List<T> foos;

  private Bar<T> oneParameterizedBar;

  private Bar<Integer> oneIntegerBar;

  private Bar<Quux<T>> oneParameterizedQuuxBar;

  private Bar<Quux<Integer>> oneIntegerQuuxBar;

  private Quux<T> oneParameterizedQuux;

  public GenericSuperParentBuilder() {
  }

  private GenericSuperParentBuilder(GenericSuperParent<? extends T> v) {
    List<? extends T> _foos = v.foos();
    this.foos = (_foos == null) ? null : new ArrayList<T>(_foos);
    @SuppressWarnings("unchecked") Bar<T> _oneParameterizedBar = (Bar<T>) (Bar<? extends T>) v.oneParameterizedBar();
    this.oneParameterizedBar = _oneParameterizedBar;
    this.oneIntegerBar = v.oneIntegerBar();
    @SuppressWarnings("unchecked") Bar<Quux<T>> _oneParameterizedQuuxBar = (Bar<Quux<T>>) (Bar<? extends Quux<? extends T>>) v.oneParameterizedQuuxBar();
    this.oneParameterizedQuuxBar = _oneParameterizedQuuxBar;
    this.oneIntegerQuuxBar = v.oneIntegerQuuxBar();
    @SuppressWarnings("unchecked") Quux<T> _oneParameterizedQuux = (Quux<T>) (Quux<? extends T>) v.oneParameterizedQuux();
    this.oneParameterizedQuux = _oneParameterizedQuux;
  }

  private GenericSuperParentBuilder(GenericSuperParentBuilder<? extends T> v) {
    this.foos = (v.foos() == null) ? null : new ArrayList<T>(v.foos());
    @SuppressWarnings("unchecked") Bar<T> _oneParameterizedBar = (Bar<T>) (Bar<? extends T>) v.oneParameterizedBar();
    this.oneParameterizedBar = _oneParameterizedBar;
    this.oneIntegerBar = v.oneIntegerBar();
    @SuppressWarnings("unchecked") Bar<Quux<T>> _oneParameterizedQuuxBar = (Bar<Quux<T>>) (Bar<? extends Quux<? extends T>>) v.oneParameterizedQuuxBar();
    this.oneParameterizedQuuxBar = _oneParameterizedQuuxBar;
    this.oneIntegerQuuxBar = v.oneIntegerQuuxBar();
    @SuppressWarnings("unchecked") Quux<T> _oneParameterizedQuux = (Quux<T>) (Quux<? extends T>) v.oneParameterizedQuux();
    this.oneParameterizedQuux = _oneParameterizedQuux;
  }

  public List<T> foos() {
    if (this.foos == null) {
      this.foos = new ArrayList<T>();
    }
    return foos;
  }

  public GenericSuperParentBuilder<T> foos(List<? extends T> foos) {
    return foos((Collection<? extends T>) foos);
  }

  public GenericSuperParentBuilder<T> foos(Collection<? extends T> foos) {
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

  public GenericSuperParentBuilder<T> foos(Iterable<? extends T> foos) {
    if (foos == null) {
      throw new NullPointerException("foos");
    }
    if (foos instanceof Collection) {
      return foos((Collection<? extends T>) foos);
    }
    return foos(foos.iterator());
  }

  public GenericSuperParentBuilder<T> foos(Iterator<? extends T> foos) {
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
  @SuppressWarnings("varargs")
  public final GenericSuperParentBuilder<T> foos(T... foos) {
    if (foos == null) {
      throw new NullPointerException("foos");
    }
    return foos(Arrays.asList(foos));
  }

  public GenericSuperParentBuilder<T> addFoo(T foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    if (this.foos == null) {
      this.foos = new ArrayList<T>();
    }
    foos.add(foo);
    return this;
  }

  public Bar<T> oneParameterizedBar() {
    return oneParameterizedBar;
  }

  public GenericSuperParentBuilder<T> oneParameterizedBar(Bar<T> oneParameterizedBar) {
    if (oneParameterizedBar == null) {
      throw new NullPointerException("oneParameterizedBar");
    }
    this.oneParameterizedBar = oneParameterizedBar;
    return this;
  }

  public Bar<Integer> oneIntegerBar() {
    return oneIntegerBar;
  }

  public GenericSuperParentBuilder<T> oneIntegerBar(Bar<Integer> oneIntegerBar) {
    if (oneIntegerBar == null) {
      throw new NullPointerException("oneIntegerBar");
    }
    this.oneIntegerBar = oneIntegerBar;
    return this;
  }

  public Bar<Quux<T>> oneParameterizedQuuxBar() {
    return oneParameterizedQuuxBar;
  }

  public GenericSuperParentBuilder<T> oneParameterizedQuuxBar(
      Bar<Quux<T>> oneParameterizedQuuxBar) {
    if (oneParameterizedQuuxBar == null) {
      throw new NullPointerException("oneParameterizedQuuxBar");
    }
    this.oneParameterizedQuuxBar = oneParameterizedQuuxBar;
    return this;
  }

  public Bar<Quux<Integer>> oneIntegerQuuxBar() {
    return oneIntegerQuuxBar;
  }

  public GenericSuperParentBuilder<T> oneIntegerQuuxBar(Bar<Quux<Integer>> oneIntegerQuuxBar) {
    if (oneIntegerQuuxBar == null) {
      throw new NullPointerException("oneIntegerQuuxBar");
    }
    this.oneIntegerQuuxBar = oneIntegerQuuxBar;
    return this;
  }

  public Quux<T> oneParameterizedQuux() {
    return oneParameterizedQuux;
  }

  public GenericSuperParentBuilder<T> oneParameterizedQuux(Quux<T> oneParameterizedQuux) {
    if (oneParameterizedQuux == null) {
      throw new NullPointerException("oneParameterizedQuux");
    }
    this.oneParameterizedQuux = oneParameterizedQuux;
    return this;
  }

  public GenericSuperParent<T> build() {
    List<T> _foos = (foos != null) ? Collections.unmodifiableList(new ArrayList<T>(foos)) : Collections.<T>emptyList();
    return new Value<T>(_foos, oneParameterizedBar, oneIntegerBar, oneParameterizedQuuxBar, oneIntegerQuuxBar, oneParameterizedQuux);
  }

  public static <T> GenericSuperParentBuilder<T> from(GenericSuperParent<? extends T> v) {
    return new GenericSuperParentBuilder<T>(v);
  }

  public static <T> GenericSuperParentBuilder<T> from(GenericSuperParentBuilder<? extends T> v) {
    return new GenericSuperParentBuilder<T>(v);
  }

  @AutoMatter.Generated
  private static final class Value<T> implements GenericSuperParent<T> {
    private final List<T> foos;

    private final Bar<T> oneParameterizedBar;

    private final Bar<Integer> oneIntegerBar;

    private final Bar<Quux<T>> oneParameterizedQuuxBar;

    private final Bar<Quux<Integer>> oneIntegerQuuxBar;

    private final Quux<T> oneParameterizedQuux;

    private Value(@AutoMatter.Field("foos") List<T> foos,
        @AutoMatter.Field("oneParameterizedBar") Bar<T> oneParameterizedBar,
        @AutoMatter.Field("oneIntegerBar") Bar<Integer> oneIntegerBar,
        @AutoMatter.Field("oneParameterizedQuuxBar") Bar<Quux<T>> oneParameterizedQuuxBar,
        @AutoMatter.Field("oneIntegerQuuxBar") Bar<Quux<Integer>> oneIntegerQuuxBar,
        @AutoMatter.Field("oneParameterizedQuux") Quux<T> oneParameterizedQuux) {
      if (oneParameterizedBar == null) {
        throw new NullPointerException("oneParameterizedBar");
      }
      if (oneIntegerBar == null) {
        throw new NullPointerException("oneIntegerBar");
      }
      if (oneParameterizedQuuxBar == null) {
        throw new NullPointerException("oneParameterizedQuuxBar");
      }
      if (oneIntegerQuuxBar == null) {
        throw new NullPointerException("oneIntegerQuuxBar");
      }
      if (oneParameterizedQuux == null) {
        throw new NullPointerException("oneParameterizedQuux");
      }
      this.foos = (foos != null) ? foos : Collections.<T>emptyList();
      this.oneParameterizedBar = oneParameterizedBar;
      this.oneIntegerBar = oneIntegerBar;
      this.oneParameterizedQuuxBar = oneParameterizedQuuxBar;
      this.oneIntegerQuuxBar = oneIntegerQuuxBar;
      this.oneParameterizedQuux = oneParameterizedQuux;
    }

    @AutoMatter.Field
    @Override
    public List<T> foos() {
      return foos;
    }

    @AutoMatter.Field
    @Override
    public Bar<T> oneParameterizedBar() {
      return oneParameterizedBar;
    }

    @AutoMatter.Field
    @Override
    public Bar<Integer> oneIntegerBar() {
      return oneIntegerBar;
    }

    @AutoMatter.Field
    @Override
    public Bar<Quux<T>> oneParameterizedQuuxBar() {
      return oneParameterizedQuuxBar;
    }

    @AutoMatter.Field
    @Override
    public Bar<Quux<Integer>> oneIntegerQuuxBar() {
      return oneIntegerQuuxBar;
    }

    @AutoMatter.Field
    @Override
    public Quux<T> oneParameterizedQuux() {
      return oneParameterizedQuux;
    }

    public GenericSuperParentBuilder<T> builder() {
      return new GenericSuperParentBuilder<T>(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof GenericSuperParent)) {
        return false;
      }
      final GenericSuperParent<?> that = (GenericSuperParent<?>) o;
      if (foos != null ? !foos.equals(that.foos()) : that.foos() != null) {
        return false;
      }
      if (oneParameterizedBar != null ? !oneParameterizedBar.equals(that.oneParameterizedBar()) : that.oneParameterizedBar() != null) {
        return false;
      }
      if (oneIntegerBar != null ? !oneIntegerBar.equals(that.oneIntegerBar()) : that.oneIntegerBar() != null) {
        return false;
      }
      if (oneParameterizedQuuxBar != null ? !oneParameterizedQuuxBar.equals(that.oneParameterizedQuuxBar()) : that.oneParameterizedQuuxBar() != null) {
        return false;
      }
      if (oneIntegerQuuxBar != null ? !oneIntegerQuuxBar.equals(that.oneIntegerQuuxBar()) : that.oneIntegerQuuxBar() != null) {
        return false;
      }
      if (oneParameterizedQuux != null ? !oneParameterizedQuux.equals(that.oneParameterizedQuux()) : that.oneParameterizedQuux() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (this.foos != null ? this.foos.hashCode() : 0);
      result = 31 * result + (this.oneParameterizedBar != null ? this.oneParameterizedBar.hashCode() : 0);
      result = 31 * result + (this.oneIntegerBar != null ? this.oneIntegerBar.hashCode() : 0);
      result = 31 * result + (this.oneParameterizedQuuxBar != null ? this.oneParameterizedQuuxBar.hashCode() : 0);
      result = 31 * result + (this.oneIntegerQuuxBar != null ? this.oneIntegerQuuxBar.hashCode() : 0);
      result = 31 * result + (this.oneParameterizedQuux != null ? this.oneParameterizedQuux.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "GenericSuperParent{" +
          "foos=" + foos +
          ", oneParameterizedBar=" + oneParameterizedBar +
          ", oneIntegerBar=" + oneIntegerBar +
          ", oneParameterizedQuuxBar=" + oneParameterizedQuuxBar +
          ", oneIntegerQuuxBar=" + oneIntegerQuuxBar +
          ", oneParameterizedQuux=" + oneParameterizedQuux +
          '}';
    }
  }
}
