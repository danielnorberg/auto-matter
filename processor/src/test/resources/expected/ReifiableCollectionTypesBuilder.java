package foo;

import io.norberg.automatter.AutoMatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
public final class ReifiableCollectionTypesBuilder<T> {
  private List<String> r1;

  private List<Foo<?>> r2;

  private List<Foo> r3;

  private List<Foo<?>.Bar> r4;

  private List<String[]> ar1;

  private List<Foo<?>[]> ar2;

  private List<Foo[]> ar3;

  private List<Foo<?>.Bar[]> ar4;

  private List<Foo<String>.Bar> nr1;

  private List<Foo<T>> nr2;

  private List<T> nr3;

  private List<Foo<String>.Bar[]> anr1;

  private List<Foo<String>[]> anr2;

  public ReifiableCollectionTypesBuilder() {
  }

  private ReifiableCollectionTypesBuilder(ReifiableCollectionTypes<? extends T> v) {
    List<String> _r1 = v.r1();
    this.r1 = (_r1 == null) ? null : new ArrayList<String>(_r1);
    List<Foo<?>> _r2 = v.r2();
    this.r2 = (_r2 == null) ? null : new ArrayList<Foo<?>>(_r2);
    List<Foo> _r3 = v.r3();
    this.r3 = (_r3 == null) ? null : new ArrayList<Foo>(_r3);
    List<Foo<?>.Bar> _r4 = v.r4();
    this.r4 = (_r4 == null) ? null : new ArrayList<Foo<?>.Bar>(_r4);
    List<String[]> _ar1 = v.ar1();
    this.ar1 = (_ar1 == null) ? null : new ArrayList<String[]>(_ar1);
    List<Foo<?>[]> _ar2 = v.ar2();
    this.ar2 = (_ar2 == null) ? null : new ArrayList<Foo<?>[]>(_ar2);
    List<Foo[]> _ar3 = v.ar3();
    this.ar3 = (_ar3 == null) ? null : new ArrayList<Foo[]>(_ar3);
    List<Foo<?>.Bar[]> _ar4 = v.ar4();
    this.ar4 = (_ar4 == null) ? null : new ArrayList<Foo<?>.Bar[]>(_ar4);
    List<Foo<String>.Bar> _nr1 = v.nr1();
    this.nr1 = (_nr1 == null) ? null : new ArrayList<Foo<String>.Bar>(_nr1);
    @SuppressWarnings("unchecked") List<? extends Foo<T>> _nr2 = (List<? extends Foo<T>>) (List<? extends Foo<? extends T>>) v.nr2();
    this.nr2 = (_nr2 == null) ? null : new ArrayList<Foo<T>>(_nr2);
    List<? extends T> _nr3 = v.nr3();
    this.nr3 = (_nr3 == null) ? null : new ArrayList<T>(_nr3);
    List<Foo<String>.Bar[]> _anr1 = v.anr1();
    this.anr1 = (_anr1 == null) ? null : new ArrayList<Foo<String>.Bar[]>(_anr1);
    List<Foo<String>[]> _anr2 = v.anr2();
    this.anr2 = (_anr2 == null) ? null : new ArrayList<Foo<String>[]>(_anr2);
  }

  private ReifiableCollectionTypesBuilder(
      ReifiableCollectionTypesBuilder<? extends T> v) {
    this.r1 = (v.r1 == null) ? null : new ArrayList<String>(v.r1);
    this.r2 = (v.r2 == null) ? null : new ArrayList<Foo<?>>(v.r2);
    this.r3 = (v.r3 == null) ? null : new ArrayList<Foo>(v.r3);
    this.r4 = (v.r4 == null) ? null : new ArrayList<Foo<?>.Bar>(v.r4);
    this.ar1 = (v.ar1 == null) ? null : new ArrayList<String[]>(v.ar1);
    this.ar2 = (v.ar2 == null) ? null : new ArrayList<Foo<?>[]>(v.ar2);
    this.ar3 = (v.ar3 == null) ? null : new ArrayList<Foo[]>(v.ar3);
    this.ar4 = (v.ar4 == null) ? null : new ArrayList<Foo<?>.Bar[]>(v.ar4);
    this.nr1 = (v.nr1 == null) ? null : new ArrayList<Foo<String>.Bar>(v.nr1);
    @SuppressWarnings("unchecked") List<? extends Foo<T>> _nr2 = (List<? extends Foo<T>>) (List<? extends Foo<? extends T>>) v.nr2;
    this.nr2 = (_nr2 == null) ? null : new ArrayList<Foo<T>>(_nr2);
    this.nr3 = (v.nr3 == null) ? null : new ArrayList<T>(v.nr3);
    this.anr1 = (v.anr1 == null) ? null : new ArrayList<Foo<String>.Bar[]>(v.anr1);
    this.anr2 = (v.anr2 == null) ? null : new ArrayList<Foo<String>[]>(v.anr2);
  }

  public List<String> r1() {
    if (this.r1 == null) {
      this.r1 = new ArrayList<String>();
    }
    return r1;
  }

  public ReifiableCollectionTypesBuilder<T> r1(List<? extends String> r1) {
    return r1((Collection<? extends String>) r1);
  }

  public ReifiableCollectionTypesBuilder<T> r1(Collection<? extends String> r1) {
    if (r1 == null) {
      throw new NullPointerException("r1");
    }
    for (String item : r1) {
      if (item == null) {
        throw new NullPointerException("r1: null item");
      }
    }
    this.r1 = new ArrayList<String>(r1);
    return this;
  }

  public ReifiableCollectionTypesBuilder<T> r1(Iterable<? extends String> r1) {
    if (r1 == null) {
      throw new NullPointerException("r1");
    }
    if (r1 instanceof Collection) {
      return r1((Collection<? extends String>) r1);
    }
    return r1(r1.iterator());
  }

  public ReifiableCollectionTypesBuilder<T> r1(Iterator<? extends String> r1) {
    if (r1 == null) {
      throw new NullPointerException("r1");
    }
    this.r1 = new ArrayList<String>();
    while (r1.hasNext()) {
      String item = r1.next();
      if (item == null) {
        throw new NullPointerException("r1: null item");
      }
      this.r1.add(item);
    }
    return this;
  }

  public ReifiableCollectionTypesBuilder<T> r1(String... r1) {
    if (r1 == null) {
      throw new NullPointerException("r1");
    }
    return r1(Arrays.asList(r1));
  }

  public List<Foo<?>> r2() {
    if (this.r2 == null) {
      this.r2 = new ArrayList<Foo<?>>();
    }
    return r2;
  }

  public ReifiableCollectionTypesBuilder<T> r2(List<? extends Foo<?>> r2) {
    return r2((Collection<? extends Foo<?>>) r2);
  }

  public ReifiableCollectionTypesBuilder<T> r2(Collection<? extends Foo<?>> r2) {
    if (r2 == null) {
      throw new NullPointerException("r2");
    }
    for (Foo<?> item : r2) {
      if (item == null) {
        throw new NullPointerException("r2: null item");
      }
    }
    this.r2 = new ArrayList<Foo<?>>(r2);
    return this;
  }

  public ReifiableCollectionTypesBuilder<T> r2(Iterable<? extends Foo<?>> r2) {
    if (r2 == null) {
      throw new NullPointerException("r2");
    }
    if (r2 instanceof Collection) {
      return r2((Collection<? extends Foo<?>>) r2);
    }
    return r2(r2.iterator());
  }

  public ReifiableCollectionTypesBuilder<T> r2(Iterator<? extends Foo<?>> r2) {
    if (r2 == null) {
      throw new NullPointerException("r2");
    }
    this.r2 = new ArrayList<Foo<?>>();
    while (r2.hasNext()) {
      Foo<?> item = r2.next();
      if (item == null) {
        throw new NullPointerException("r2: null item");
      }
      this.r2.add(item);
    }
    return this;
  }

  public ReifiableCollectionTypesBuilder<T> r2(Foo<?>... r2) {
    if (r2 == null) {
      throw new NullPointerException("r2");
    }
    return r2(Arrays.asList(r2));
  }

  public List<Foo> r3() {
    if (this.r3 == null) {
      this.r3 = new ArrayList<Foo>();
    }
    return r3;
  }

  public ReifiableCollectionTypesBuilder<T> r3(List<? extends Foo> r3) {
    return r3((Collection<? extends Foo>) r3);
  }

  public ReifiableCollectionTypesBuilder<T> r3(Collection<? extends Foo> r3) {
    if (r3 == null) {
      throw new NullPointerException("r3");
    }
    for (Foo item : r3) {
      if (item == null) {
        throw new NullPointerException("r3: null item");
      }
    }
    this.r3 = new ArrayList<Foo>(r3);
    return this;
  }

  public ReifiableCollectionTypesBuilder<T> r3(Iterable<? extends Foo> r3) {
    if (r3 == null) {
      throw new NullPointerException("r3");
    }
    if (r3 instanceof Collection) {
      return r3((Collection<? extends Foo>) r3);
    }
    return r3(r3.iterator());
  }

  public ReifiableCollectionTypesBuilder<T> r3(Iterator<? extends Foo> r3) {
    if (r3 == null) {
      throw new NullPointerException("r3");
    }
    this.r3 = new ArrayList<Foo>();
    while (r3.hasNext()) {
      Foo item = r3.next();
      if (item == null) {
        throw new NullPointerException("r3: null item");
      }
      this.r3.add(item);
    }
    return this;
  }

  public ReifiableCollectionTypesBuilder<T> r3(Foo... r3) {
    if (r3 == null) {
      throw new NullPointerException("r3");
    }
    return r3(Arrays.asList(r3));
  }

  public List<Foo<?>.Bar> r4() {
    if (this.r4 == null) {
      this.r4 = new ArrayList<Foo<?>.Bar>();
    }
    return r4;
  }

  public ReifiableCollectionTypesBuilder<T> r4(List<? extends Foo<?>.Bar> r4) {
    return r4((Collection<? extends Foo<?>.Bar>) r4);
  }

  public ReifiableCollectionTypesBuilder<T> r4(Collection<? extends Foo<?>.Bar> r4) {
    if (r4 == null) {
      throw new NullPointerException("r4");
    }
    for (Foo<?>.Bar item : r4) {
      if (item == null) {
        throw new NullPointerException("r4: null item");
      }
    }
    this.r4 = new ArrayList<Foo<?>.Bar>(r4);
    return this;
  }

  public ReifiableCollectionTypesBuilder<T> r4(Iterable<? extends Foo<?>.Bar> r4) {
    if (r4 == null) {
      throw new NullPointerException("r4");
    }
    if (r4 instanceof Collection) {
      return r4((Collection<? extends Foo<?>.Bar>) r4);
    }
    return r4(r4.iterator());
  }

  public ReifiableCollectionTypesBuilder<T> r4(Iterator<? extends Foo<?>.Bar> r4) {
    if (r4 == null) {
      throw new NullPointerException("r4");
    }
    this.r4 = new ArrayList<Foo<?>.Bar>();
    while (r4.hasNext()) {
      Foo<?>.Bar item = r4.next();
      if (item == null) {
        throw new NullPointerException("r4: null item");
      }
      this.r4.add(item);
    }
    return this;
  }

  public ReifiableCollectionTypesBuilder<T> r4(Foo<?>.Bar... r4) {
    if (r4 == null) {
      throw new NullPointerException("r4");
    }
    return r4(Arrays.asList(r4));
  }

  public List<String[]> ar1() {
    if (this.ar1 == null) {
      this.ar1 = new ArrayList<String[]>();
    }
    return ar1;
  }

  public ReifiableCollectionTypesBuilder<T> ar1(List<? extends String[]> ar1) {
    return ar1((Collection<? extends String[]>) ar1);
  }

  public ReifiableCollectionTypesBuilder<T> ar1(Collection<? extends String[]> ar1) {
    if (ar1 == null) {
      throw new NullPointerException("ar1");
    }
    for (String[] item : ar1) {
      if (item == null) {
        throw new NullPointerException("ar1: null item");
      }
    }
    this.ar1 = new ArrayList<String[]>(ar1);
    return this;
  }

  public ReifiableCollectionTypesBuilder<T> ar1(Iterable<? extends String[]> ar1) {
    if (ar1 == null) {
      throw new NullPointerException("ar1");
    }
    if (ar1 instanceof Collection) {
      return ar1((Collection<? extends String[]>) ar1);
    }
    return ar1(ar1.iterator());
  }

  public ReifiableCollectionTypesBuilder<T> ar1(Iterator<? extends String[]> ar1) {
    if (ar1 == null) {
      throw new NullPointerException("ar1");
    }
    this.ar1 = new ArrayList<String[]>();
    while (ar1.hasNext()) {
      String[] item = ar1.next();
      if (item == null) {
        throw new NullPointerException("ar1: null item");
      }
      this.ar1.add(item);
    }
    return this;
  }

  public ReifiableCollectionTypesBuilder<T> ar1(String[]... ar1) {
    if (ar1 == null) {
      throw new NullPointerException("ar1");
    }
    return ar1(Arrays.asList(ar1));
  }

  public List<Foo<?>[]> ar2() {
    if (this.ar2 == null) {
      this.ar2 = new ArrayList<Foo<?>[]>();
    }
    return ar2;
  }

  public ReifiableCollectionTypesBuilder<T> ar2(List<? extends Foo<?>[]> ar2) {
    return ar2((Collection<? extends Foo<?>[]>) ar2);
  }

  public ReifiableCollectionTypesBuilder<T> ar2(Collection<? extends Foo<?>[]> ar2) {
    if (ar2 == null) {
      throw new NullPointerException("ar2");
    }
    for (Foo<?>[] item : ar2) {
      if (item == null) {
        throw new NullPointerException("ar2: null item");
      }
    }
    this.ar2 = new ArrayList<Foo<?>[]>(ar2);
    return this;
  }

  public ReifiableCollectionTypesBuilder<T> ar2(Iterable<? extends Foo<?>[]> ar2) {
    if (ar2 == null) {
      throw new NullPointerException("ar2");
    }
    if (ar2 instanceof Collection) {
      return ar2((Collection<? extends Foo<?>[]>) ar2);
    }
    return ar2(ar2.iterator());
  }

  public ReifiableCollectionTypesBuilder<T> ar2(Iterator<? extends Foo<?>[]> ar2) {
    if (ar2 == null) {
      throw new NullPointerException("ar2");
    }
    this.ar2 = new ArrayList<Foo<?>[]>();
    while (ar2.hasNext()) {
      Foo<?>[] item = ar2.next();
      if (item == null) {
        throw new NullPointerException("ar2: null item");
      }
      this.ar2.add(item);
    }
    return this;
  }

  public ReifiableCollectionTypesBuilder<T> ar2(Foo<?>[]... ar2) {
    if (ar2 == null) {
      throw new NullPointerException("ar2");
    }
    return ar2(Arrays.asList(ar2));
  }

  public List<Foo[]> ar3() {
    if (this.ar3 == null) {
      this.ar3 = new ArrayList<Foo[]>();
    }
    return ar3;
  }

  public ReifiableCollectionTypesBuilder<T> ar3(List<? extends Foo[]> ar3) {
    return ar3((Collection<? extends Foo[]>) ar3);
  }

  public ReifiableCollectionTypesBuilder<T> ar3(Collection<? extends Foo[]> ar3) {
    if (ar3 == null) {
      throw new NullPointerException("ar3");
    }
    for (Foo[] item : ar3) {
      if (item == null) {
        throw new NullPointerException("ar3: null item");
      }
    }
    this.ar3 = new ArrayList<Foo[]>(ar3);
    return this;
  }

  public ReifiableCollectionTypesBuilder<T> ar3(Iterable<? extends Foo[]> ar3) {
    if (ar3 == null) {
      throw new NullPointerException("ar3");
    }
    if (ar3 instanceof Collection) {
      return ar3((Collection<? extends Foo[]>) ar3);
    }
    return ar3(ar3.iterator());
  }

  public ReifiableCollectionTypesBuilder<T> ar3(Iterator<? extends Foo[]> ar3) {
    if (ar3 == null) {
      throw new NullPointerException("ar3");
    }
    this.ar3 = new ArrayList<Foo[]>();
    while (ar3.hasNext()) {
      Foo[] item = ar3.next();
      if (item == null) {
        throw new NullPointerException("ar3: null item");
      }
      this.ar3.add(item);
    }
    return this;
  }

  public ReifiableCollectionTypesBuilder<T> ar3(Foo[]... ar3) {
    if (ar3 == null) {
      throw new NullPointerException("ar3");
    }
    return ar3(Arrays.asList(ar3));
  }

  public List<Foo<?>.Bar[]> ar4() {
    if (this.ar4 == null) {
      this.ar4 = new ArrayList<Foo<?>.Bar[]>();
    }
    return ar4;
  }

  public ReifiableCollectionTypesBuilder<T> ar4(List<? extends Foo<?>.Bar[]> ar4) {
    return ar4((Collection<? extends Foo<?>.Bar[]>) ar4);
  }

  public ReifiableCollectionTypesBuilder<T> ar4(Collection<? extends Foo<?>.Bar[]> ar4) {
    if (ar4 == null) {
      throw new NullPointerException("ar4");
    }
    for (Foo<?>.Bar[] item : ar4) {
      if (item == null) {
        throw new NullPointerException("ar4: null item");
      }
    }
    this.ar4 = new ArrayList<Foo<?>.Bar[]>(ar4);
    return this;
  }

  public ReifiableCollectionTypesBuilder<T> ar4(Iterable<? extends Foo<?>.Bar[]> ar4) {
    if (ar4 == null) {
      throw new NullPointerException("ar4");
    }
    if (ar4 instanceof Collection) {
      return ar4((Collection<? extends Foo<?>.Bar[]>) ar4);
    }
    return ar4(ar4.iterator());
  }

  public ReifiableCollectionTypesBuilder<T> ar4(Iterator<? extends Foo<?>.Bar[]> ar4) {
    if (ar4 == null) {
      throw new NullPointerException("ar4");
    }
    this.ar4 = new ArrayList<Foo<?>.Bar[]>();
    while (ar4.hasNext()) {
      Foo<?>.Bar[] item = ar4.next();
      if (item == null) {
        throw new NullPointerException("ar4: null item");
      }
      this.ar4.add(item);
    }
    return this;
  }

  public ReifiableCollectionTypesBuilder<T> ar4(Foo<?>.Bar[]... ar4) {
    if (ar4 == null) {
      throw new NullPointerException("ar4");
    }
    return ar4(Arrays.asList(ar4));
  }

  public List<Foo<String>.Bar> nr1() {
    if (this.nr1 == null) {
      this.nr1 = new ArrayList<Foo<String>.Bar>();
    }
    return nr1;
  }

  public ReifiableCollectionTypesBuilder<T> nr1(List<? extends Foo<String>.Bar> nr1) {
    return nr1((Collection<? extends Foo<String>.Bar>) nr1);
  }

  public ReifiableCollectionTypesBuilder<T> nr1(Collection<? extends Foo<String>.Bar> nr1) {
    if (nr1 == null) {
      throw new NullPointerException("nr1");
    }
    for (Foo<String>.Bar item : nr1) {
      if (item == null) {
        throw new NullPointerException("nr1: null item");
      }
    }
    this.nr1 = new ArrayList<Foo<String>.Bar>(nr1);
    return this;
  }

  public ReifiableCollectionTypesBuilder<T> nr1(Iterable<? extends Foo<String>.Bar> nr1) {
    if (nr1 == null) {
      throw new NullPointerException("nr1");
    }
    if (nr1 instanceof Collection) {
      return nr1((Collection<? extends Foo<String>.Bar>) nr1);
    }
    return nr1(nr1.iterator());
  }

  public ReifiableCollectionTypesBuilder<T> nr1(Iterator<? extends Foo<String>.Bar> nr1) {
    if (nr1 == null) {
      throw new NullPointerException("nr1");
    }
    this.nr1 = new ArrayList<Foo<String>.Bar>();
    while (nr1.hasNext()) {
      Foo<String>.Bar item = nr1.next();
      if (item == null) {
        throw new NullPointerException("nr1: null item");
      }
      this.nr1.add(item);
    }
    return this;
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public final ReifiableCollectionTypesBuilder<T> nr1(Foo<String>.Bar... nr1) {
    if (nr1 == null) {
      throw new NullPointerException("nr1");
    }
    return nr1(Arrays.asList(nr1));
  }

  public List<Foo<T>> nr2() {
    if (this.nr2 == null) {
      this.nr2 = new ArrayList<Foo<T>>();
    }
    return nr2;
  }

  public ReifiableCollectionTypesBuilder<T> nr2(List<? extends Foo<T>> nr2) {
    return nr2((Collection<? extends Foo<T>>) nr2);
  }

  public ReifiableCollectionTypesBuilder<T> nr2(Collection<? extends Foo<T>> nr2) {
    if (nr2 == null) {
      throw new NullPointerException("nr2");
    }
    for (Foo<T> item : nr2) {
      if (item == null) {
        throw new NullPointerException("nr2: null item");
      }
    }
    this.nr2 = new ArrayList<Foo<T>>(nr2);
    return this;
  }

  public ReifiableCollectionTypesBuilder<T> nr2(Iterable<? extends Foo<T>> nr2) {
    if (nr2 == null) {
      throw new NullPointerException("nr2");
    }
    if (nr2 instanceof Collection) {
      return nr2((Collection<? extends Foo<T>>) nr2);
    }
    return nr2(nr2.iterator());
  }

  public ReifiableCollectionTypesBuilder<T> nr2(Iterator<? extends Foo<T>> nr2) {
    if (nr2 == null) {
      throw new NullPointerException("nr2");
    }
    this.nr2 = new ArrayList<Foo<T>>();
    while (nr2.hasNext()) {
      Foo<T> item = nr2.next();
      if (item == null) {
        throw new NullPointerException("nr2: null item");
      }
      this.nr2.add(item);
    }
    return this;
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public final ReifiableCollectionTypesBuilder<T> nr2(Foo<T>... nr2) {
    if (nr2 == null) {
      throw new NullPointerException("nr2");
    }
    return nr2(Arrays.asList(nr2));
  }

  public List<T> nr3() {
    if (this.nr3 == null) {
      this.nr3 = new ArrayList<T>();
    }
    return nr3;
  }

  public ReifiableCollectionTypesBuilder<T> nr3(List<? extends T> nr3) {
    return nr3((Collection<? extends T>) nr3);
  }

  public ReifiableCollectionTypesBuilder<T> nr3(Collection<? extends T> nr3) {
    if (nr3 == null) {
      throw new NullPointerException("nr3");
    }
    for (T item : nr3) {
      if (item == null) {
        throw new NullPointerException("nr3: null item");
      }
    }
    this.nr3 = new ArrayList<T>(nr3);
    return this;
  }

  public ReifiableCollectionTypesBuilder<T> nr3(Iterable<? extends T> nr3) {
    if (nr3 == null) {
      throw new NullPointerException("nr3");
    }
    if (nr3 instanceof Collection) {
      return nr3((Collection<? extends T>) nr3);
    }
    return nr3(nr3.iterator());
  }

  public ReifiableCollectionTypesBuilder<T> nr3(Iterator<? extends T> nr3) {
    if (nr3 == null) {
      throw new NullPointerException("nr3");
    }
    this.nr3 = new ArrayList<T>();
    while (nr3.hasNext()) {
      T item = nr3.next();
      if (item == null) {
        throw new NullPointerException("nr3: null item");
      }
      this.nr3.add(item);
    }
    return this;
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public final ReifiableCollectionTypesBuilder<T> nr3(T... nr3) {
    if (nr3 == null) {
      throw new NullPointerException("nr3");
    }
    return nr3(Arrays.asList(nr3));
  }

  public List<Foo<String>.Bar[]> anr1() {
    if (this.anr1 == null) {
      this.anr1 = new ArrayList<Foo<String>.Bar[]>();
    }
    return anr1;
  }

  public ReifiableCollectionTypesBuilder<T> anr1(List<? extends Foo<String>.Bar[]> anr1) {
    return anr1((Collection<? extends Foo<String>.Bar[]>) anr1);
  }

  public ReifiableCollectionTypesBuilder<T> anr1(
      Collection<? extends Foo<String>.Bar[]> anr1) {
    if (anr1 == null) {
      throw new NullPointerException("anr1");
    }
    for (Foo<String>.Bar[] item : anr1) {
      if (item == null) {
        throw new NullPointerException("anr1: null item");
      }
    }
    this.anr1 = new ArrayList<Foo<String>.Bar[]>(anr1);
    return this;
  }

  public ReifiableCollectionTypesBuilder<T> anr1(Iterable<? extends Foo<String>.Bar[]> anr1) {
    if (anr1 == null) {
      throw new NullPointerException("anr1");
    }
    if (anr1 instanceof Collection) {
      return anr1((Collection<? extends Foo<String>.Bar[]>) anr1);
    }
    return anr1(anr1.iterator());
  }

  public ReifiableCollectionTypesBuilder<T> anr1(Iterator<? extends Foo<String>.Bar[]> anr1) {
    if (anr1 == null) {
      throw new NullPointerException("anr1");
    }
    this.anr1 = new ArrayList<Foo<String>.Bar[]>();
    while (anr1.hasNext()) {
      Foo<String>.Bar[] item = anr1.next();
      if (item == null) {
        throw new NullPointerException("anr1: null item");
      }
      this.anr1.add(item);
    }
    return this;
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public final ReifiableCollectionTypesBuilder<T> anr1(Foo<String>.Bar[]... anr1) {
    if (anr1 == null) {
      throw new NullPointerException("anr1");
    }
    return anr1(Arrays.asList(anr1));
  }

  public List<Foo<String>[]> anr2() {
    if (this.anr2 == null) {
      this.anr2 = new ArrayList<Foo<String>[]>();
    }
    return anr2;
  }

  public ReifiableCollectionTypesBuilder<T> anr2(List<? extends Foo<String>[]> anr2) {
    return anr2((Collection<? extends Foo<String>[]>) anr2);
  }

  public ReifiableCollectionTypesBuilder<T> anr2(Collection<? extends Foo<String>[]> anr2) {
    if (anr2 == null) {
      throw new NullPointerException("anr2");
    }
    for (Foo<String>[] item : anr2) {
      if (item == null) {
        throw new NullPointerException("anr2: null item");
      }
    }
    this.anr2 = new ArrayList<Foo<String>[]>(anr2);
    return this;
  }

  public ReifiableCollectionTypesBuilder<T> anr2(Iterable<? extends Foo<String>[]> anr2) {
    if (anr2 == null) {
      throw new NullPointerException("anr2");
    }
    if (anr2 instanceof Collection) {
      return anr2((Collection<? extends Foo<String>[]>) anr2);
    }
    return anr2(anr2.iterator());
  }

  public ReifiableCollectionTypesBuilder<T> anr2(Iterator<? extends Foo<String>[]> anr2) {
    if (anr2 == null) {
      throw new NullPointerException("anr2");
    }
    this.anr2 = new ArrayList<Foo<String>[]>();
    while (anr2.hasNext()) {
      Foo<String>[] item = anr2.next();
      if (item == null) {
        throw new NullPointerException("anr2: null item");
      }
      this.anr2.add(item);
    }
    return this;
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public final ReifiableCollectionTypesBuilder<T> anr2(Foo<String>[]... anr2) {
    if (anr2 == null) {
      throw new NullPointerException("anr2");
    }
    return anr2(Arrays.asList(anr2));
  }

  public ReifiableCollectionTypes<T> build() {
    List<String> _r1 = (r1 != null) ? Collections.unmodifiableList(new ArrayList<String>(r1)) : Collections.<String>emptyList();
    List<Foo<?>> _r2 = (r2 != null) ? Collections.unmodifiableList(new ArrayList<Foo<?>>(r2)) : Collections.<Foo<?>>emptyList();
    List<Foo> _r3 = (r3 != null) ? Collections.unmodifiableList(new ArrayList<Foo>(r3)) : Collections.<Foo>emptyList();
    List<Foo<?>.Bar> _r4 = (r4 != null) ? Collections.unmodifiableList(new ArrayList<Foo<?>.Bar>(r4)) : Collections.<Foo<?>.Bar>emptyList();
    List<String[]> _ar1 = (ar1 != null) ? Collections.unmodifiableList(new ArrayList<String[]>(ar1)) : Collections.<String[]>emptyList();
    List<Foo<?>[]> _ar2 = (ar2 != null) ? Collections.unmodifiableList(new ArrayList<Foo<?>[]>(ar2)) : Collections.<Foo<?>[]>emptyList();
    List<Foo[]> _ar3 = (ar3 != null) ? Collections.unmodifiableList(new ArrayList<Foo[]>(ar3)) : Collections.<Foo[]>emptyList();
    List<Foo<?>.Bar[]> _ar4 = (ar4 != null) ? Collections.unmodifiableList(new ArrayList<Foo<?>.Bar[]>(ar4)) : Collections.<Foo<?>.Bar[]>emptyList();
    List<Foo<String>.Bar> _nr1 = (nr1 != null) ? Collections.unmodifiableList(new ArrayList<Foo<String>.Bar>(nr1)) : Collections.<Foo<String>.Bar>emptyList();
    List<Foo<T>> _nr2 = (nr2 != null) ? Collections.unmodifiableList(new ArrayList<Foo<T>>(nr2)) : Collections.<Foo<T>>emptyList();
    List<T> _nr3 = (nr3 != null) ? Collections.unmodifiableList(new ArrayList<T>(nr3)) : Collections.<T>emptyList();
    List<Foo<String>.Bar[]> _anr1 = (anr1 != null) ? Collections.unmodifiableList(new ArrayList<Foo<String>.Bar[]>(anr1)) : Collections.<Foo<String>.Bar[]>emptyList();
    List<Foo<String>[]> _anr2 = (anr2 != null) ? Collections.unmodifiableList(new ArrayList<Foo<String>[]>(anr2)) : Collections.<Foo<String>[]>emptyList();
    return new Value<T>(_r1, _r2, _r3, _r4, _ar1, _ar2, _ar3, _ar4, _nr1, _nr2, _nr3, _anr1, _anr2);
  }

  public static <T> ReifiableCollectionTypesBuilder<T> from(
      ReifiableCollectionTypes<? extends T> v) {
    return new ReifiableCollectionTypesBuilder<T>(v);
  }

  public static <T> ReifiableCollectionTypesBuilder<T> from(
      ReifiableCollectionTypesBuilder<? extends T> v) {
    return new ReifiableCollectionTypesBuilder<T>(v);
  }

  private static final class Value<T> implements ReifiableCollectionTypes<T> {
    private final List<String> r1;

    private final List<Foo<?>> r2;

    private final List<Foo> r3;

    private final List<Foo<?>.Bar> r4;

    private final List<String[]> ar1;

    private final List<Foo<?>[]> ar2;

    private final List<Foo[]> ar3;

    private final List<Foo<?>.Bar[]> ar4;

    private final List<Foo<String>.Bar> nr1;

    private final List<Foo<T>> nr2;

    private final List<T> nr3;

    private final List<Foo<String>.Bar[]> anr1;

    private final List<Foo<String>[]> anr2;

    private Value(@AutoMatter.Field("r1") List<String> r1, @AutoMatter.Field("r2") List<Foo<?>> r2,
        @AutoMatter.Field("r3") List<Foo> r3, @AutoMatter.Field("r4") List<Foo<?>.Bar> r4,
        @AutoMatter.Field("ar1") List<String[]> ar1, @AutoMatter.Field("ar2") List<Foo<?>[]> ar2,
        @AutoMatter.Field("ar3") List<Foo[]> ar3, @AutoMatter.Field("ar4") List<Foo<?>.Bar[]> ar4,
        @AutoMatter.Field("nr1") List<Foo<String>.Bar> nr1,
        @AutoMatter.Field("nr2") List<Foo<T>> nr2, @AutoMatter.Field("nr3") List<T> nr3,
        @AutoMatter.Field("anr1") List<Foo<String>.Bar[]> anr1,
        @AutoMatter.Field("anr2") List<Foo<String>[]> anr2) {
      this.r1 = (r1 != null) ? r1 : Collections.<String>emptyList();
      this.r2 = (r2 != null) ? r2 : Collections.<Foo<?>>emptyList();
      this.r3 = (r3 != null) ? r3 : Collections.<Foo>emptyList();
      this.r4 = (r4 != null) ? r4 : Collections.<Foo<?>.Bar>emptyList();
      this.ar1 = (ar1 != null) ? ar1 : Collections.<String[]>emptyList();
      this.ar2 = (ar2 != null) ? ar2 : Collections.<Foo<?>[]>emptyList();
      this.ar3 = (ar3 != null) ? ar3 : Collections.<Foo[]>emptyList();
      this.ar4 = (ar4 != null) ? ar4 : Collections.<Foo<?>.Bar[]>emptyList();
      this.nr1 = (nr1 != null) ? nr1 : Collections.<Foo<String>.Bar>emptyList();
      this.nr2 = (nr2 != null) ? nr2 : Collections.<Foo<T>>emptyList();
      this.nr3 = (nr3 != null) ? nr3 : Collections.<T>emptyList();
      this.anr1 = (anr1 != null) ? anr1 : Collections.<Foo<String>.Bar[]>emptyList();
      this.anr2 = (anr2 != null) ? anr2 : Collections.<Foo<String>[]>emptyList();
    }

    @AutoMatter.Field
    @Override
    public List<String> r1() {
      return r1;
    }

    @AutoMatter.Field
    @Override
    public List<Foo<?>> r2() {
      return r2;
    }

    @AutoMatter.Field
    @Override
    public List<Foo> r3() {
      return r3;
    }

    @AutoMatter.Field
    @Override
    public List<Foo<?>.Bar> r4() {
      return r4;
    }

    @AutoMatter.Field
    @Override
    public List<String[]> ar1() {
      return ar1;
    }

    @AutoMatter.Field
    @Override
    public List<Foo<?>[]> ar2() {
      return ar2;
    }

    @AutoMatter.Field
    @Override
    public List<Foo[]> ar3() {
      return ar3;
    }

    @AutoMatter.Field
    @Override
    public List<Foo<?>.Bar[]> ar4() {
      return ar4;
    }

    @AutoMatter.Field
    @Override
    public List<Foo<String>.Bar> nr1() {
      return nr1;
    }

    @AutoMatter.Field
    @Override
    public List<Foo<T>> nr2() {
      return nr2;
    }

    @AutoMatter.Field
    @Override
    public List<T> nr3() {
      return nr3;
    }

    @AutoMatter.Field
    @Override
    public List<Foo<String>.Bar[]> anr1() {
      return anr1;
    }

    @AutoMatter.Field
    @Override
    public List<Foo<String>[]> anr2() {
      return anr2;
    }

    public ReifiableCollectionTypesBuilder<T> builder() {
      return new ReifiableCollectionTypesBuilder<T>(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof ReifiableCollectionTypes)) {
        return false;
      }
      final ReifiableCollectionTypes<?> that = (ReifiableCollectionTypes<?>) o;
      if (r1 != null ? !r1.equals(that.r1()) : that.r1() != null) {
        return false;
      }
      if (r2 != null ? !r2.equals(that.r2()) : that.r2() != null) {
        return false;
      }
      if (r3 != null ? !r3.equals(that.r3()) : that.r3() != null) {
        return false;
      }
      if (r4 != null ? !r4.equals(that.r4()) : that.r4() != null) {
        return false;
      }
      if (ar1 != null ? !ar1.equals(that.ar1()) : that.ar1() != null) {
        return false;
      }
      if (ar2 != null ? !ar2.equals(that.ar2()) : that.ar2() != null) {
        return false;
      }
      if (ar3 != null ? !ar3.equals(that.ar3()) : that.ar3() != null) {
        return false;
      }
      if (ar4 != null ? !ar4.equals(that.ar4()) : that.ar4() != null) {
        return false;
      }
      if (nr1 != null ? !nr1.equals(that.nr1()) : that.nr1() != null) {
        return false;
      }
      if (nr2 != null ? !nr2.equals(that.nr2()) : that.nr2() != null) {
        return false;
      }
      if (nr3 != null ? !nr3.equals(that.nr3()) : that.nr3() != null) {
        return false;
      }
      if (anr1 != null ? !anr1.equals(that.anr1()) : that.anr1() != null) {
        return false;
      }
      if (anr2 != null ? !anr2.equals(that.anr2()) : that.anr2() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      result = 31 * result + (this.r1 != null ? this.r1.hashCode() : 0);
      result = 31 * result + (this.r2 != null ? this.r2.hashCode() : 0);
      result = 31 * result + (this.r3 != null ? this.r3.hashCode() : 0);
      result = 31 * result + (this.r4 != null ? this.r4.hashCode() : 0);
      result = 31 * result + (this.ar1 != null ? this.ar1.hashCode() : 0);
      result = 31 * result + (this.ar2 != null ? this.ar2.hashCode() : 0);
      result = 31 * result + (this.ar3 != null ? this.ar3.hashCode() : 0);
      result = 31 * result + (this.ar4 != null ? this.ar4.hashCode() : 0);
      result = 31 * result + (this.nr1 != null ? this.nr1.hashCode() : 0);
      result = 31 * result + (this.nr2 != null ? this.nr2.hashCode() : 0);
      result = 31 * result + (this.nr3 != null ? this.nr3.hashCode() : 0);
      result = 31 * result + (this.anr1 != null ? this.anr1.hashCode() : 0);
      result = 31 * result + (this.anr2 != null ? this.anr2.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "ReifiableCollectionTypes{" +
          "r1=" + r1 +
          ", r2=" + r2 +
          ", r3=" + r3 +
          ", r4=" + r4 +
          ", ar1=" + ar1 +
          ", ar2=" + ar2 +
          ", ar3=" + ar3 +
          ", ar4=" + ar4 +
          ", nr1=" + nr1 +
          ", nr2=" + nr2 +
          ", nr3=" + nr3 +
          ", anr1=" + anr1 +
          ", anr2=" + anr2 +
          '}';
    }
  }
}