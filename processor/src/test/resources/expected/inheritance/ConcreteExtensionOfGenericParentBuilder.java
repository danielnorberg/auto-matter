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
public final class ConcreteExtensionOfGenericParentBuilder {
  private List<Integer> foos;

  private Bar<Integer> oneParameterizedBar;

  private Bar<Integer> oneIntegerBar;

  private Bar<Quux<Integer>> oneParameterizedQuuxBar;

  private Bar<Quux<Integer>> oneIntegerQuuxBar;

  private Quux<Integer> oneParameterizedQuux;

  private Bar<Integer> secondParameterizedBar;

  private Quux<Integer> secondParameterizedQuux;

  private List<Integer> moreFoos;

  private Integer oneFoo;

  public ConcreteExtensionOfGenericParentBuilder() {
  }

  private ConcreteExtensionOfGenericParentBuilder(ConcreteExtensionOfGenericParent v) {
    List<Integer> _foos = v.foos();
    this.foos = (_foos == null) ? null : new ArrayList<Integer>(_foos);
    @SuppressWarnings("unchecked") Bar<Integer> _oneParameterizedBar = (Bar<Integer>) (Bar<? extends Integer>) v.oneParameterizedBar();
    this.oneParameterizedBar = _oneParameterizedBar;
    this.oneIntegerBar = v.oneIntegerBar();
    @SuppressWarnings("unchecked") Bar<Quux<Integer>> _oneParameterizedQuuxBar = (Bar<Quux<Integer>>) (Bar<? extends Quux<? extends Integer>>) v.oneParameterizedQuuxBar();
    this.oneParameterizedQuuxBar = _oneParameterizedQuuxBar;
    this.oneIntegerQuuxBar = v.oneIntegerQuuxBar();
    @SuppressWarnings("unchecked") Quux<Integer> _oneParameterizedQuux = (Quux<Integer>) (Quux<? extends Integer>) v.oneParameterizedQuux();
    this.oneParameterizedQuux = _oneParameterizedQuux;
    @SuppressWarnings("unchecked") Bar<Integer> _secondParameterizedBar = (Bar<Integer>) (Bar<? extends Integer>) v.secondParameterizedBar();
    this.secondParameterizedBar = _secondParameterizedBar;
    @SuppressWarnings("unchecked") Quux<Integer> _secondParameterizedQuux = (Quux<Integer>) (Quux<? extends Integer>) v.secondParameterizedQuux();
    this.secondParameterizedQuux = _secondParameterizedQuux;
    List<? extends Integer> _moreFoos = v.moreFoos();
    this.moreFoos = (_moreFoos == null) ? null : new ArrayList<Integer>(_moreFoos);
    this.oneFoo = v.oneFoo();
  }

  private ConcreteExtensionOfGenericParentBuilder(GenericSuperParent<? extends Integer> v) {
    List<? extends Integer> _foos = v.foos();
    this.foos = (_foos == null) ? null : new ArrayList<Integer>(_foos);
    @SuppressWarnings("unchecked") Bar<Integer> _oneParameterizedBar = (Bar<Integer>) (Bar<? extends Integer>) v.oneParameterizedBar();
    this.oneParameterizedBar = _oneParameterizedBar;
    this.oneIntegerBar = v.oneIntegerBar();
    @SuppressWarnings("unchecked") Bar<Quux<Integer>> _oneParameterizedQuuxBar = (Bar<Quux<Integer>>) (Bar<? extends Quux<? extends Integer>>) v.oneParameterizedQuuxBar();
    this.oneParameterizedQuuxBar = _oneParameterizedQuuxBar;
    this.oneIntegerQuuxBar = v.oneIntegerQuuxBar();
    @SuppressWarnings("unchecked") Quux<Integer> _oneParameterizedQuux = (Quux<Integer>) (Quux<? extends Integer>) v.oneParameterizedQuux();
    this.oneParameterizedQuux = _oneParameterizedQuux;
  }

  private ConcreteExtensionOfGenericParentBuilder(GenericCollectionParent<? extends Integer> v) {
    List<? extends Integer> _foos = v.foos();
    this.foos = (_foos == null) ? null : new ArrayList<Integer>(_foos);
    @SuppressWarnings("unchecked") Bar<Integer> _oneParameterizedBar = (Bar<Integer>) (Bar<? extends Integer>) v.oneParameterizedBar();
    this.oneParameterizedBar = _oneParameterizedBar;
    this.oneIntegerBar = v.oneIntegerBar();
    @SuppressWarnings("unchecked") Bar<Quux<Integer>> _oneParameterizedQuuxBar = (Bar<Quux<Integer>>) (Bar<? extends Quux<? extends Integer>>) v.oneParameterizedQuuxBar();
    this.oneParameterizedQuuxBar = _oneParameterizedQuuxBar;
    this.oneIntegerQuuxBar = v.oneIntegerQuuxBar();
    @SuppressWarnings("unchecked") Quux<Integer> _oneParameterizedQuux = (Quux<Integer>) (Quux<? extends Integer>) v.oneParameterizedQuux();
    this.oneParameterizedQuux = _oneParameterizedQuux;
    @SuppressWarnings("unchecked") Bar<Integer> _secondParameterizedBar = (Bar<Integer>) (Bar<? extends Integer>) v.secondParameterizedBar();
    this.secondParameterizedBar = _secondParameterizedBar;
    @SuppressWarnings("unchecked") Quux<Integer> _secondParameterizedQuux = (Quux<Integer>) (Quux<? extends Integer>) v.secondParameterizedQuux();
    this.secondParameterizedQuux = _secondParameterizedQuux;
    List<? extends Integer> _moreFoos = v.moreFoos();
    this.moreFoos = (_moreFoos == null) ? null : new ArrayList<Integer>(_moreFoos);
  }

  private ConcreteExtensionOfGenericParentBuilder(ConcreteExtensionOfGenericParentBuilder v) {
    this.foos = new ArrayList<Integer>(v.foos());
    @SuppressWarnings("unchecked") Bar<Integer> _oneParameterizedBar = (Bar<Integer>) (Bar<? extends Integer>) v.oneParameterizedBar();
    this.oneParameterizedBar = _oneParameterizedBar;
    this.oneIntegerBar = v.oneIntegerBar();
    @SuppressWarnings("unchecked") Bar<Quux<Integer>> _oneParameterizedQuuxBar = (Bar<Quux<Integer>>) (Bar<? extends Quux<? extends Integer>>) v.oneParameterizedQuuxBar();
    this.oneParameterizedQuuxBar = _oneParameterizedQuuxBar;
    this.oneIntegerQuuxBar = v.oneIntegerQuuxBar();
    @SuppressWarnings("unchecked") Quux<Integer> _oneParameterizedQuux = (Quux<Integer>) (Quux<? extends Integer>) v.oneParameterizedQuux();
    this.oneParameterizedQuux = _oneParameterizedQuux;
    @SuppressWarnings("unchecked") Bar<Integer> _secondParameterizedBar = (Bar<Integer>) (Bar<? extends Integer>) v.secondParameterizedBar();
    this.secondParameterizedBar = _secondParameterizedBar;
    @SuppressWarnings("unchecked") Quux<Integer> _secondParameterizedQuux = (Quux<Integer>) (Quux<? extends Integer>) v.secondParameterizedQuux();
    this.secondParameterizedQuux = _secondParameterizedQuux;
    this.moreFoos = new ArrayList<Integer>(v.moreFoos());
    this.oneFoo = v.oneFoo();
  }

  private ConcreteExtensionOfGenericParentBuilder(GenericSuperParentBuilder<? extends Integer> v) {
    this.foos = new ArrayList<Integer>(v.foos());
    @SuppressWarnings("unchecked") Bar<Integer> _oneParameterizedBar = (Bar<Integer>) (Bar<? extends Integer>) v.oneParameterizedBar();
    this.oneParameterizedBar = _oneParameterizedBar;
    this.oneIntegerBar = v.oneIntegerBar();
    @SuppressWarnings("unchecked") Bar<Quux<Integer>> _oneParameterizedQuuxBar = (Bar<Quux<Integer>>) (Bar<? extends Quux<? extends Integer>>) v.oneParameterizedQuuxBar();
    this.oneParameterizedQuuxBar = _oneParameterizedQuuxBar;
    this.oneIntegerQuuxBar = v.oneIntegerQuuxBar();
    @SuppressWarnings("unchecked") Quux<Integer> _oneParameterizedQuux = (Quux<Integer>) (Quux<? extends Integer>) v.oneParameterizedQuux();
    this.oneParameterizedQuux = _oneParameterizedQuux;
  }

  private ConcreteExtensionOfGenericParentBuilder(
      GenericCollectionParentBuilder<? extends Integer> v) {
    this.foos = new ArrayList<Integer>(v.foos());
    @SuppressWarnings("unchecked") Bar<Integer> _oneParameterizedBar = (Bar<Integer>) (Bar<? extends Integer>) v.oneParameterizedBar();
    this.oneParameterizedBar = _oneParameterizedBar;
    this.oneIntegerBar = v.oneIntegerBar();
    @SuppressWarnings("unchecked") Bar<Quux<Integer>> _oneParameterizedQuuxBar = (Bar<Quux<Integer>>) (Bar<? extends Quux<? extends Integer>>) v.oneParameterizedQuuxBar();
    this.oneParameterizedQuuxBar = _oneParameterizedQuuxBar;
    this.oneIntegerQuuxBar = v.oneIntegerQuuxBar();
    @SuppressWarnings("unchecked") Quux<Integer> _oneParameterizedQuux = (Quux<Integer>) (Quux<? extends Integer>) v.oneParameterizedQuux();
    this.oneParameterizedQuux = _oneParameterizedQuux;
    @SuppressWarnings("unchecked") Bar<Integer> _secondParameterizedBar = (Bar<Integer>) (Bar<? extends Integer>) v.secondParameterizedBar();
    this.secondParameterizedBar = _secondParameterizedBar;
    @SuppressWarnings("unchecked") Quux<Integer> _secondParameterizedQuux = (Quux<Integer>) (Quux<? extends Integer>) v.secondParameterizedQuux();
    this.secondParameterizedQuux = _secondParameterizedQuux;
    this.moreFoos = new ArrayList<Integer>(v.moreFoos());
  }

  public List<Integer> foos() {
    if (this.foos == null) {
      this.foos = new ArrayList<Integer>();
    }
    return foos;
  }

  public ConcreteExtensionOfGenericParentBuilder foos(List<? extends Integer> foos) {
    return foos((Collection<? extends Integer>) foos);
  }

  public ConcreteExtensionOfGenericParentBuilder foos(Collection<? extends Integer> foos) {
    if (foos == null) {
      throw new NullPointerException("foos");
    }
    for (Integer item : foos) {
      if (item == null) {
        throw new NullPointerException("foos: null item");
      }
    }
    this.foos = new ArrayList<Integer>(foos);
    return this;
  }

  public ConcreteExtensionOfGenericParentBuilder foos(Iterable<? extends Integer> foos) {
    if (foos == null) {
      throw new NullPointerException("foos");
    }
    if (foos instanceof Collection) {
      return foos((Collection<? extends Integer>) foos);
    }
    return foos(foos.iterator());
  }

  public ConcreteExtensionOfGenericParentBuilder foos(Iterator<? extends Integer> foos) {
    if (foos == null) {
      throw new NullPointerException("foos");
    }
    this.foos = new ArrayList<Integer>();
    while (foos.hasNext()) {
      Integer item = foos.next();
      if (item == null) {
        throw new NullPointerException("foos: null item");
      }
      this.foos.add(item);
    }
    return this;
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public final ConcreteExtensionOfGenericParentBuilder foos(Integer... foos) {
    if (foos == null) {
      throw new NullPointerException("foos");
    }
    return foos(Arrays.asList(foos));
  }

  public ConcreteExtensionOfGenericParentBuilder addFoo(Integer foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    if (this.foos == null) {
      this.foos = new ArrayList<Integer>();
    }
    foos.add(foo);
    return this;
  }

  public Bar<Integer> oneParameterizedBar() {
    return oneParameterizedBar;
  }

  public ConcreteExtensionOfGenericParentBuilder oneParameterizedBar(
      Bar<Integer> oneParameterizedBar) {
    if (oneParameterizedBar == null) {
      throw new NullPointerException("oneParameterizedBar");
    }
    this.oneParameterizedBar = oneParameterizedBar;
    return this;
  }

  public Bar<Integer> oneIntegerBar() {
    return oneIntegerBar;
  }

  public ConcreteExtensionOfGenericParentBuilder oneIntegerBar(Bar<Integer> oneIntegerBar) {
    if (oneIntegerBar == null) {
      throw new NullPointerException("oneIntegerBar");
    }
    this.oneIntegerBar = oneIntegerBar;
    return this;
  }

  public Bar<Quux<Integer>> oneParameterizedQuuxBar() {
    return oneParameterizedQuuxBar;
  }

  public ConcreteExtensionOfGenericParentBuilder oneParameterizedQuuxBar(
      Bar<Quux<Integer>> oneParameterizedQuuxBar) {
    if (oneParameterizedQuuxBar == null) {
      throw new NullPointerException("oneParameterizedQuuxBar");
    }
    this.oneParameterizedQuuxBar = oneParameterizedQuuxBar;
    return this;
  }

  public Bar<Quux<Integer>> oneIntegerQuuxBar() {
    return oneIntegerQuuxBar;
  }

  public ConcreteExtensionOfGenericParentBuilder oneIntegerQuuxBar(
      Bar<Quux<Integer>> oneIntegerQuuxBar) {
    if (oneIntegerQuuxBar == null) {
      throw new NullPointerException("oneIntegerQuuxBar");
    }
    this.oneIntegerQuuxBar = oneIntegerQuuxBar;
    return this;
  }

  public Quux<Integer> oneParameterizedQuux() {
    return oneParameterizedQuux;
  }

  public ConcreteExtensionOfGenericParentBuilder oneParameterizedQuux(
      Quux<Integer> oneParameterizedQuux) {
    if (oneParameterizedQuux == null) {
      throw new NullPointerException("oneParameterizedQuux");
    }
    this.oneParameterizedQuux = oneParameterizedQuux;
    return this;
  }

  public Bar<Integer> secondParameterizedBar() {
    return secondParameterizedBar;
  }

  public ConcreteExtensionOfGenericParentBuilder secondParameterizedBar(
      Bar<Integer> secondParameterizedBar) {
    if (secondParameterizedBar == null) {
      throw new NullPointerException("secondParameterizedBar");
    }
    this.secondParameterizedBar = secondParameterizedBar;
    return this;
  }

  public Quux<Integer> secondParameterizedQuux() {
    return secondParameterizedQuux;
  }

  public ConcreteExtensionOfGenericParentBuilder secondParameterizedQuux(
      Quux<Integer> secondParameterizedQuux) {
    if (secondParameterizedQuux == null) {
      throw new NullPointerException("secondParameterizedQuux");
    }
    this.secondParameterizedQuux = secondParameterizedQuux;
    return this;
  }

  public List<Integer> moreFoos() {
    if (this.moreFoos == null) {
      this.moreFoos = new ArrayList<Integer>();
    }
    return moreFoos;
  }

  public ConcreteExtensionOfGenericParentBuilder moreFoos(List<? extends Integer> moreFoos) {
    return moreFoos((Collection<? extends Integer>) moreFoos);
  }

  public ConcreteExtensionOfGenericParentBuilder moreFoos(Collection<? extends Integer> moreFoos) {
    if (moreFoos == null) {
      throw new NullPointerException("moreFoos");
    }
    for (Integer item : moreFoos) {
      if (item == null) {
        throw new NullPointerException("moreFoos: null item");
      }
    }
    this.moreFoos = new ArrayList<Integer>(moreFoos);
    return this;
  }

  public ConcreteExtensionOfGenericParentBuilder moreFoos(Iterable<? extends Integer> moreFoos) {
    if (moreFoos == null) {
      throw new NullPointerException("moreFoos");
    }
    if (moreFoos instanceof Collection) {
      return moreFoos((Collection<? extends Integer>) moreFoos);
    }
    return moreFoos(moreFoos.iterator());
  }

  public ConcreteExtensionOfGenericParentBuilder moreFoos(Iterator<? extends Integer> moreFoos) {
    if (moreFoos == null) {
      throw new NullPointerException("moreFoos");
    }
    this.moreFoos = new ArrayList<Integer>();
    while (moreFoos.hasNext()) {
      Integer item = moreFoos.next();
      if (item == null) {
        throw new NullPointerException("moreFoos: null item");
      }
      this.moreFoos.add(item);
    }
    return this;
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public final ConcreteExtensionOfGenericParentBuilder moreFoos(Integer... moreFoos) {
    if (moreFoos == null) {
      throw new NullPointerException("moreFoos");
    }
    return moreFoos(Arrays.asList(moreFoos));
  }

  public ConcreteExtensionOfGenericParentBuilder addMoreFoo(Integer moreFoo) {
    if (moreFoo == null) {
      throw new NullPointerException("moreFoo");
    }
    if (this.moreFoos == null) {
      this.moreFoos = new ArrayList<Integer>();
    }
    moreFoos.add(moreFoo);
    return this;
  }

  public Integer oneFoo() {
    return oneFoo;
  }

  public ConcreteExtensionOfGenericParentBuilder oneFoo(Integer oneFoo) {
    if (oneFoo == null) {
      throw new NullPointerException("oneFoo");
    }
    this.oneFoo = oneFoo;
    return this;
  }

  public ConcreteExtensionOfGenericParent build() {
    List<Integer> _foos = (foos != null) ? Collections.unmodifiableList(new ArrayList<Integer>(foos)) : Collections.<Integer>emptyList();
    List<Integer> _moreFoos = (moreFoos != null) ? Collections.unmodifiableList(new ArrayList<Integer>(moreFoos)) : Collections.<Integer>emptyList();
    return new Value(_foos, oneParameterizedBar, oneIntegerBar, oneParameterizedQuuxBar, oneIntegerQuuxBar, oneParameterizedQuux, secondParameterizedBar, secondParameterizedQuux, _moreFoos, oneFoo);
  }

  public static ConcreteExtensionOfGenericParentBuilder from(ConcreteExtensionOfGenericParent v) {
    return new ConcreteExtensionOfGenericParentBuilder(v);
  }

  public static ConcreteExtensionOfGenericParentBuilder from(
      GenericSuperParent<? extends Integer> v) {
    return new ConcreteExtensionOfGenericParentBuilder(v);
  }

  public static ConcreteExtensionOfGenericParentBuilder from(
      GenericCollectionParent<? extends Integer> v) {
    return new ConcreteExtensionOfGenericParentBuilder(v);
  }

  public static ConcreteExtensionOfGenericParentBuilder from(
      ConcreteExtensionOfGenericParentBuilder v) {
    return new ConcreteExtensionOfGenericParentBuilder(v);
  }

  public static ConcreteExtensionOfGenericParentBuilder from(
      GenericSuperParentBuilder<? extends Integer> v) {
    return new ConcreteExtensionOfGenericParentBuilder(v);
  }

  public static ConcreteExtensionOfGenericParentBuilder from(
      GenericCollectionParentBuilder<? extends Integer> v) {
    return new ConcreteExtensionOfGenericParentBuilder(v);
  }

  @AutoMatter.Generated
  private static final class Value implements ConcreteExtensionOfGenericParent {
    private final List<Integer> foos;

    private final Bar<Integer> oneParameterizedBar;

    private final Bar<Integer> oneIntegerBar;

    private final Bar<Quux<Integer>> oneParameterizedQuuxBar;

    private final Bar<Quux<Integer>> oneIntegerQuuxBar;

    private final Quux<Integer> oneParameterizedQuux;

    private final Bar<Integer> secondParameterizedBar;

    private final Quux<Integer> secondParameterizedQuux;

    private final List<Integer> moreFoos;

    private final Integer oneFoo;

    private Value(@AutoMatter.Field("foos") List<Integer> foos,
        @AutoMatter.Field("oneParameterizedBar") Bar<Integer> oneParameterizedBar,
        @AutoMatter.Field("oneIntegerBar") Bar<Integer> oneIntegerBar,
        @AutoMatter.Field("oneParameterizedQuuxBar") Bar<Quux<Integer>> oneParameterizedQuuxBar,
        @AutoMatter.Field("oneIntegerQuuxBar") Bar<Quux<Integer>> oneIntegerQuuxBar,
        @AutoMatter.Field("oneParameterizedQuux") Quux<Integer> oneParameterizedQuux,
        @AutoMatter.Field("secondParameterizedBar") Bar<Integer> secondParameterizedBar,
        @AutoMatter.Field("secondParameterizedQuux") Quux<Integer> secondParameterizedQuux,
        @AutoMatter.Field("moreFoos") List<Integer> moreFoos,
        @AutoMatter.Field("oneFoo") Integer oneFoo) {
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
      if (oneFoo == null) {
        throw new NullPointerException("oneFoo");
      }
      this.foos = (foos != null) ? foos : Collections.<Integer>emptyList();
      this.oneParameterizedBar = oneParameterizedBar;
      this.oneIntegerBar = oneIntegerBar;
      this.oneParameterizedQuuxBar = oneParameterizedQuuxBar;
      this.oneIntegerQuuxBar = oneIntegerQuuxBar;
      this.oneParameterizedQuux = oneParameterizedQuux;
      this.secondParameterizedBar = secondParameterizedBar;
      this.secondParameterizedQuux = secondParameterizedQuux;
      this.moreFoos = (moreFoos != null) ? moreFoos : Collections.<Integer>emptyList();
      this.oneFoo = oneFoo;
    }

    @AutoMatter.Field
    @Override
    public List<Integer> foos() {
      return foos;
    }

    @AutoMatter.Field
    @Override
    public Bar<Integer> oneParameterizedBar() {
      return oneParameterizedBar;
    }

    @AutoMatter.Field
    @Override
    public Bar<Integer> oneIntegerBar() {
      return oneIntegerBar;
    }

    @AutoMatter.Field
    @Override
    public Bar<Quux<Integer>> oneParameterizedQuuxBar() {
      return oneParameterizedQuuxBar;
    }

    @AutoMatter.Field
    @Override
    public Bar<Quux<Integer>> oneIntegerQuuxBar() {
      return oneIntegerQuuxBar;
    }

    @AutoMatter.Field
    @Override
    public Quux<Integer> oneParameterizedQuux() {
      return oneParameterizedQuux;
    }

    @AutoMatter.Field
    @Override
    public Bar<Integer> secondParameterizedBar() {
      return secondParameterizedBar;
    }

    @AutoMatter.Field
    @Override
    public Quux<Integer> secondParameterizedQuux() {
      return secondParameterizedQuux;
    }

    @AutoMatter.Field
    @Override
    public List<Integer> moreFoos() {
      return moreFoos;
    }

    @AutoMatter.Field
    @Override
    public Integer oneFoo() {
      return oneFoo;
    }

    public ConcreteExtensionOfGenericParentBuilder builder() {
      return new ConcreteExtensionOfGenericParentBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ConcreteExtensionOfGenericParent)) {
        return false;
      }
      final ConcreteExtensionOfGenericParent that = (ConcreteExtensionOfGenericParent) o;
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
      if (oneFoo != null ? !oneFoo.equals(that.oneFoo()) : that.oneFoo() != null) {
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
      result = 31 * result + (this.oneFoo != null ? this.oneFoo.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "ConcreteExtensionOfGenericParent{" +
          "foos=" + foos +
          ", oneParameterizedBar=" + oneParameterizedBar +
          ", oneIntegerBar=" + oneIntegerBar +
          ", oneParameterizedQuuxBar=" + oneParameterizedQuuxBar +
          ", oneIntegerQuuxBar=" + oneIntegerQuuxBar +
          ", oneParameterizedQuux=" + oneParameterizedQuux +
          ", secondParameterizedBar=" + secondParameterizedBar +
          ", secondParameterizedQuux=" + secondParameterizedQuux +
          ", moreFoos=" + moreFoos +
          ", oneFoo=" + oneFoo +
          '}';
    }
  }
}
