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
public final class GenericCollectionParentBuilder<T> {
  private List<T> foos;

  private Bar<T> oneParameterizedBar;

  private Bar<Integer> oneIntegerBar;

  private Bar<Quux<T>> oneParameterizedQuuxBar;

  private Bar<Quux<Integer>> oneIntegerQuuxBar;

  private Quux<T> oneParameterizedQuux;

  private Bar<T> secondParameterizedBar;

  private Quux<T> secondParameterizedQuux;

  private List<T> moreFoos;

  public GenericCollectionParentBuilder() {
  }

  private GenericCollectionParentBuilder(GenericCollectionParent<? extends T> v) {
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
    @SuppressWarnings("unchecked") Bar<T> _secondParameterizedBar = (Bar<T>) (Bar<? extends T>) v.secondParameterizedBar();
    this.secondParameterizedBar = _secondParameterizedBar;
    @SuppressWarnings("unchecked") Quux<T> _secondParameterizedQuux = (Quux<T>) (Quux<? extends T>) v.secondParameterizedQuux();
    this.secondParameterizedQuux = _secondParameterizedQuux;
    List<? extends T> _moreFoos = v.moreFoos();
    this.moreFoos = (_moreFoos == null) ? null : new ArrayList<T>(_moreFoos);
  }

  private GenericCollectionParentBuilder(GenericSuperParent<? extends T> v) {
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

  private GenericCollectionParentBuilder(GenericCollectionParentBuilder<? extends T> v) {
    this.foos = (v.foos() == null) ? null : new ArrayList<T>(v.foos());
    @SuppressWarnings("unchecked") Bar<T> _oneParameterizedBar = (Bar<T>) (Bar<? extends T>) v.oneParameterizedBar();
    this.oneParameterizedBar = _oneParameterizedBar;
    this.oneIntegerBar = v.oneIntegerBar();
    @SuppressWarnings("unchecked") Bar<Quux<T>> _oneParameterizedQuuxBar = (Bar<Quux<T>>) (Bar<? extends Quux<? extends T>>) v.oneParameterizedQuuxBar();
    this.oneParameterizedQuuxBar = _oneParameterizedQuuxBar;
    this.oneIntegerQuuxBar = v.oneIntegerQuuxBar();
    @SuppressWarnings("unchecked") Quux<T> _oneParameterizedQuux = (Quux<T>) (Quux<? extends T>) v.oneParameterizedQuux();
    this.oneParameterizedQuux = _oneParameterizedQuux;
    @SuppressWarnings("unchecked") Bar<T> _secondParameterizedBar = (Bar<T>) (Bar<? extends T>) v.secondParameterizedBar();
    this.secondParameterizedBar = _secondParameterizedBar;
    @SuppressWarnings("unchecked") Quux<T> _secondParameterizedQuux = (Quux<T>) (Quux<? extends T>) v.secondParameterizedQuux();
    this.secondParameterizedQuux = _secondParameterizedQuux;
    this.moreFoos = (v.moreFoos() == null) ? null : new ArrayList<T>(v.moreFoos());
  }

  private GenericCollectionParentBuilder(GenericSuperParentBuilder<? extends T> v) {
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
  @SuppressWarnings("varargs")
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

  public Bar<T> oneParameterizedBar() {
    return oneParameterizedBar;
  }

  public GenericCollectionParentBuilder<T> oneParameterizedBar(Bar<T> oneParameterizedBar) {
    if (oneParameterizedBar == null) {
      throw new NullPointerException("oneParameterizedBar");
    }
    this.oneParameterizedBar = oneParameterizedBar;
    return this;
  }

  public Bar<Integer> oneIntegerBar() {
    return oneIntegerBar;
  }

  public GenericCollectionParentBuilder<T> oneIntegerBar(Bar<Integer> oneIntegerBar) {
    if (oneIntegerBar == null) {
      throw new NullPointerException("oneIntegerBar");
    }
    this.oneIntegerBar = oneIntegerBar;
    return this;
  }

  public Bar<Quux<T>> oneParameterizedQuuxBar() {
    return oneParameterizedQuuxBar;
  }

  public GenericCollectionParentBuilder<T> oneParameterizedQuuxBar(
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

  public GenericCollectionParentBuilder<T> oneIntegerQuuxBar(Bar<Quux<Integer>> oneIntegerQuuxBar) {
    if (oneIntegerQuuxBar == null) {
      throw new NullPointerException("oneIntegerQuuxBar");
    }
    this.oneIntegerQuuxBar = oneIntegerQuuxBar;
    return this;
  }

  public Quux<T> oneParameterizedQuux() {
    return oneParameterizedQuux;
  }

  public GenericCollectionParentBuilder<T> oneParameterizedQuux(Quux<T> oneParameterizedQuux) {
    if (oneParameterizedQuux == null) {
      throw new NullPointerException("oneParameterizedQuux");
    }
    this.oneParameterizedQuux = oneParameterizedQuux;
    return this;
  }

  public Bar<T> secondParameterizedBar() {
    return secondParameterizedBar;
  }

  public GenericCollectionParentBuilder<T> secondParameterizedBar(Bar<T> secondParameterizedBar) {
    if (secondParameterizedBar == null) {
      throw new NullPointerException("secondParameterizedBar");
    }
    this.secondParameterizedBar = secondParameterizedBar;
    return this;
  }

  public Quux<T> secondParameterizedQuux() {
    return secondParameterizedQuux;
  }

  public GenericCollectionParentBuilder<T> secondParameterizedQuux(
      Quux<T> secondParameterizedQuux) {
    if (secondParameterizedQuux == null) {
      throw new NullPointerException("secondParameterizedQuux");
    }
    this.secondParameterizedQuux = secondParameterizedQuux;
    return this;
  }

  public List<T> moreFoos() {
    if (this.moreFoos == null) {
      this.moreFoos = new ArrayList<T>();
    }
    return moreFoos;
  }

  public GenericCollectionParentBuilder<T> moreFoos(List<? extends T> moreFoos) {
    return moreFoos((Collection<? extends T>) moreFoos);
  }

  public GenericCollectionParentBuilder<T> moreFoos(Collection<? extends T> moreFoos) {
    if (moreFoos == null) {
      throw new NullPointerException("moreFoos");
    }
    for (T item : moreFoos) {
      if (item == null) {
        throw new NullPointerException("moreFoos: null item");
      }
    }
    this.moreFoos = new ArrayList<T>(moreFoos);
    return this;
  }

  public GenericCollectionParentBuilder<T> moreFoos(Iterable<? extends T> moreFoos) {
    if (moreFoos == null) {
      throw new NullPointerException("moreFoos");
    }
    if (moreFoos instanceof Collection) {
      return moreFoos((Collection<? extends T>) moreFoos);
    }
    return moreFoos(moreFoos.iterator());
  }

  public GenericCollectionParentBuilder<T> moreFoos(Iterator<? extends T> moreFoos) {
    if (moreFoos == null) {
      throw new NullPointerException("moreFoos");
    }
    this.moreFoos = new ArrayList<T>();
    while (moreFoos.hasNext()) {
      T item = moreFoos.next();
      if (item == null) {
        throw new NullPointerException("moreFoos: null item");
      }
      this.moreFoos.add(item);
    }
    return this;
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public final GenericCollectionParentBuilder<T> moreFoos(T... moreFoos) {
    if (moreFoos == null) {
      throw new NullPointerException("moreFoos");
    }
    return moreFoos(Arrays.asList(moreFoos));
  }

  public GenericCollectionParentBuilder<T> addMoreFoo(T moreFoo) {
    if (moreFoo == null) {
      throw new NullPointerException("moreFoo");
    }
    if (this.moreFoos == null) {
      this.moreFoos = new ArrayList<T>();
    }
    moreFoos.add(moreFoo);
    return this;
  }

  public GenericCollectionParent<T> build() {
    List<T> _foos = (foos != null) ? Collections.unmodifiableList(new ArrayList<T>(foos)) : Collections.<T>emptyList();
    List<T> _moreFoos = (moreFoos != null) ? Collections.unmodifiableList(new ArrayList<T>(moreFoos)) : Collections.<T>emptyList();
    return new Value<T>(_foos, oneParameterizedBar, oneIntegerBar, oneParameterizedQuuxBar, oneIntegerQuuxBar, oneParameterizedQuux, secondParameterizedBar, secondParameterizedQuux, _moreFoos);
  }

  public static <T> GenericCollectionParentBuilder<T> from(GenericCollectionParent<? extends T> v) {
    return new GenericCollectionParentBuilder<T>(v);
  }

  public static <T> GenericCollectionParentBuilder<T> from(GenericSuperParent<? extends T> v) {
    return new GenericCollectionParentBuilder<T>(v);
  }

  public static <T> GenericCollectionParentBuilder<T> from(
      GenericCollectionParentBuilder<? extends T> v) {
    return new GenericCollectionParentBuilder<T>(v);
  }

  public static <T> GenericCollectionParentBuilder<T> from(
      GenericSuperParentBuilder<? extends T> v) {
    return new GenericCollectionParentBuilder<T>(v);
  }

  private static final class Value<T> implements GenericCollectionParent<T> {
    private final List<T> foos;

    private final Bar<T> oneParameterizedBar;

    private final Bar<Integer> oneIntegerBar;

    private final Bar<Quux<T>> oneParameterizedQuuxBar;

    private final Bar<Quux<Integer>> oneIntegerQuuxBar;

    private final Quux<T> oneParameterizedQuux;

    private final Bar<T> secondParameterizedBar;

    private final Quux<T> secondParameterizedQuux;

    private final List<T> moreFoos;

    private Value(@AutoMatter.Field("foos") List<T> foos,
        @AutoMatter.Field("oneParameterizedBar") Bar<T> oneParameterizedBar,
        @AutoMatter.Field("oneIntegerBar") Bar<Integer> oneIntegerBar,
        @AutoMatter.Field("oneParameterizedQuuxBar") Bar<Quux<T>> oneParameterizedQuuxBar,
        @AutoMatter.Field("oneIntegerQuuxBar") Bar<Quux<Integer>> oneIntegerQuuxBar,
        @AutoMatter.Field("oneParameterizedQuux") Quux<T> oneParameterizedQuux,
        @AutoMatter.Field("secondParameterizedBar") Bar<T> secondParameterizedBar,
        @AutoMatter.Field("secondParameterizedQuux") Quux<T> secondParameterizedQuux,
        @AutoMatter.Field("moreFoos") List<T> moreFoos) {
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
      if (secondParameterizedBar == null) {
        throw new NullPointerException("secondParameterizedBar");
      }
      if (secondParameterizedQuux == null) {
        throw new NullPointerException("secondParameterizedQuux");
      }
      this.foos = (foos != null) ? foos : Collections.<T>emptyList();
      this.oneParameterizedBar = oneParameterizedBar;
      this.oneIntegerBar = oneIntegerBar;
      this.oneParameterizedQuuxBar = oneParameterizedQuuxBar;
      this.oneIntegerQuuxBar = oneIntegerQuuxBar;
      this.oneParameterizedQuux = oneParameterizedQuux;
      this.secondParameterizedBar = secondParameterizedBar;
      this.secondParameterizedQuux = secondParameterizedQuux;
      this.moreFoos = (moreFoos != null) ? moreFoos : Collections.<T>emptyList();
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

    @AutoMatter.Field
    @Override
    public Bar<T> secondParameterizedBar() {
      return secondParameterizedBar;
    }

    @AutoMatter.Field
    @Override
    public Quux<T> secondParameterizedQuux() {
      return secondParameterizedQuux;
    }

    @AutoMatter.Field
    @Override
    public List<T> moreFoos() {
      return moreFoos;
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
      if (secondParameterizedBar != null ? !secondParameterizedBar.equals(that.secondParameterizedBar()) : that.secondParameterizedBar() != null) {
        return false;
      }
      if (secondParameterizedQuux != null ? !secondParameterizedQuux.equals(that.secondParameterizedQuux()) : that.secondParameterizedQuux() != null) {
        return false;
      }
      if (moreFoos != null ? !moreFoos.equals(that.moreFoos()) : that.moreFoos() != null) {
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
      result = 31 * result + (this.secondParameterizedBar != null ? this.secondParameterizedBar.hashCode() : 0);
      result = 31 * result + (this.secondParameterizedQuux != null ? this.secondParameterizedQuux.hashCode() : 0);
      result = 31 * result + (this.moreFoos != null ? this.moreFoos.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "GenericCollectionParent{" +
          "foos=" + foos +
          ", oneParameterizedBar=" + oneParameterizedBar +
          ", oneIntegerBar=" + oneIntegerBar +
          ", oneParameterizedQuuxBar=" + oneParameterizedQuuxBar +
          ", oneIntegerQuuxBar=" + oneIntegerQuuxBar +
          ", oneParameterizedQuux=" + oneParameterizedQuux +
          ", secondParameterizedBar=" + secondParameterizedBar +
          ", secondParameterizedQuux=" + secondParameterizedQuux +
          ", moreFoos=" + moreFoos +
          '}';
    }
  }
}