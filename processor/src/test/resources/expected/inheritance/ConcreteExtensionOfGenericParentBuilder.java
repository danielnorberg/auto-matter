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
public final class ConcreteExtensionOfGenericParentBuilder {
  private List<Integer> foos;

  public ConcreteExtensionOfGenericParentBuilder() {
  }

  private ConcreteExtensionOfGenericParentBuilder(ConcreteExtensionOfGenericParent v) {
    List<Integer> _foos = v.foos();
    this.foos = (_foos == null) ? null : new ArrayList<Integer>(_foos);
  }

  private ConcreteExtensionOfGenericParentBuilder(ConcreteExtensionOfGenericParentBuilder v) {
    this.foos = (v.foos == null) ? null : new ArrayList<Integer>(v.foos);
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

  public ConcreteExtensionOfGenericParent build() {
    List<Integer> _foos = (foos != null) ? Collections.unmodifiableList(new ArrayList<Integer>(foos)) : Collections.<Integer>emptyList();
    return new Value(_foos);
  }

  public static ConcreteExtensionOfGenericParentBuilder from(ConcreteExtensionOfGenericParent v) {
    return new ConcreteExtensionOfGenericParentBuilder(v);
  }

  public static ConcreteExtensionOfGenericParentBuilder from(ConcreteExtensionOfGenericParentBuilder v) {
    return new ConcreteExtensionOfGenericParentBuilder(v);
  }

  private static final class Value implements ConcreteExtensionOfGenericParent {
    private final List<Integer> foos;

    private Value(@AutoMatter.Field("foos") List<Integer> foos) {
      this.foos = (foos != null) ? foos : Collections.<Integer>emptyList();
    }

    @AutoMatter.Field
    @Override
    public List<Integer> foos() {
      return foos;
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
      return "ConcreteExtensionOfGenericParent{" +
             "foos=" + foos +
             '}';
    }
  }
}
