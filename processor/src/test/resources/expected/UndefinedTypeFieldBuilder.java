package foo;

import generated.later.Foo;
import io.norberg.automatter.AutoMatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
public final class UndefinedTypeFieldBuilder {
  private Foo foo;

  private Foo.Bar bar;

  private Optional<Foo> maybeFoo;

  private List<Foo.Bar> bars;

  private Map<Foo, Foo.Bar> foobars;

  public UndefinedTypeFieldBuilder() {
    this.maybeFoo = Optional.empty();
  }

  private UndefinedTypeFieldBuilder(UndefinedTypeField v) {
    this.foo = v.foo();
    this.bar = v.bar();
    this.maybeFoo = v.maybeFoo();
    List<Foo.Bar> _bars = v.bars();
    this.bars = (_bars == null) ? null : new ArrayList<Foo.Bar>(_bars);
    Map<Foo, Foo.Bar> _foobars = v.foobars();
    this.foobars = (_foobars == null) ? null : new HashMap<Foo, Foo.Bar>(_foobars);
  }

  private UndefinedTypeFieldBuilder(UndefinedTypeFieldBuilder v) {
    this.foo = v.foo;
    this.bar = v.bar;
    this.maybeFoo = v.maybeFoo;
    this.bars = (v.bars == null) ? null : new ArrayList<Foo.Bar>(v.bars);
    this.foobars = (v.foobars == null) ? null : new HashMap<Foo, Foo.Bar>(v.foobars);
  }

  public Foo foo() {
    return foo;
  }

  public UndefinedTypeFieldBuilder foo(Foo foo) {
    if (foo == null) {
      throw new NullPointerException("foo");
    }
    this.foo = foo;
    return this;
  }

  public Foo.Bar bar() {
    return bar;
  }

  public UndefinedTypeFieldBuilder bar(Foo.Bar bar) {
    if (bar == null) {
      throw new NullPointerException("bar");
    }
    this.bar = bar;
    return this;
  }

  public Optional<Foo> maybeFoo() {
    return maybeFoo;
  }

  public UndefinedTypeFieldBuilder maybeFoo(Foo maybeFoo) {
    return maybeFoo(Optional.ofNullable(maybeFoo));
  }

  @SuppressWarnings("unchecked")
  public UndefinedTypeFieldBuilder maybeFoo(Optional<? extends Foo> maybeFoo) {
    if (maybeFoo == null) {
      throw new NullPointerException("maybeFoo");
    }
    this.maybeFoo = (Optional<Foo>)maybeFoo;
    return this;
  }

  public List<Foo.Bar> bars() {
    if (this.bars == null) {
      this.bars = new ArrayList<Foo.Bar>();
    }
    return bars;
  }

  public UndefinedTypeFieldBuilder bars(List<? extends Foo.Bar> bars) {
    return bars((Collection<? extends Foo.Bar>) bars);
  }

  public UndefinedTypeFieldBuilder bars(Collection<? extends Foo.Bar> bars) {
    if (bars == null) {
      throw new NullPointerException("bars");
    }
    for (Foo.Bar item : bars) {
      if (item == null) {
        throw new NullPointerException("bars: null item");
      }
    }
    this.bars = new ArrayList<Foo.Bar>(bars);
    return this;
  }

  public UndefinedTypeFieldBuilder bars(Iterable<? extends Foo.Bar> bars) {
    if (bars == null) {
      throw new NullPointerException("bars");
    }
    if (bars instanceof Collection) {
      return bars((Collection<? extends Foo.Bar>) bars);
    }
    return bars(bars.iterator());
  }

  public UndefinedTypeFieldBuilder bars(Iterator<? extends Foo.Bar> bars) {
    if (bars == null) {
      throw new NullPointerException("bars");
    }
    this.bars = new ArrayList<Foo.Bar>();
    while (bars.hasNext()) {
      Foo.Bar item = bars.next();
      if (item == null) {
        throw new NullPointerException("bars: null item");
      }
      this.bars.add(item);
    }
    return this;
  }

  @SafeVarargs
  public final UndefinedTypeFieldBuilder bars(Foo.Bar... bars) {
    if (bars == null) {
      throw new NullPointerException("bars");
    }
    return bars(Arrays.asList(bars));
  }

  public UndefinedTypeFieldBuilder addBar(Foo.Bar bar) {
    if (bar == null) {
      throw new NullPointerException("bar");
    }
    if (this.bars == null) {
      this.bars = new ArrayList<Foo.Bar>();
    }
    bars.add(bar);
    return this;
  }

  public Map<Foo, Foo.Bar> foobars() {
    if (this.foobars == null) {
      this.foobars = new HashMap<Foo, Foo.Bar>();
    }
    return foobars;
  }

  public UndefinedTypeFieldBuilder foobars(Map<? extends Foo, ? extends Foo.Bar> foobars) {
    if (foobars == null) {
      throw new NullPointerException("foobars");
    }
    for (Map.Entry<? extends Foo, ? extends Foo.Bar> entry : foobars.entrySet()) {
      if (entry.getKey() == null) {
        throw new NullPointerException("foobars: null key");
      }
      if (entry.getValue() == null) {
        throw new NullPointerException("foobars: null value");
      }
    }
    this.foobars = new HashMap<Foo, Foo.Bar>(foobars);
    return this;
  }

  public UndefinedTypeFieldBuilder foobars(Foo k1, Foo.Bar v1) {
    if (k1 == null) {
      throw new NullPointerException("foobars: k1");
    }
    if (v1 == null) {
      throw new NullPointerException("foobars: v1");
    }
    foobars = new HashMap<Foo, Foo.Bar>();
    foobars.put(k1, v1);
    return this;
  }

  public UndefinedTypeFieldBuilder foobars(Foo k1, Foo.Bar v1, Foo k2, Foo.Bar v2) {
    foobars(k1, v1);
    if (k2 == null) {
      throw new NullPointerException("foobars: k2");
    }
    if (v2 == null) {
      throw new NullPointerException("foobars: v2");
    }
    foobars.put(k2, v2);
    return this;
  }

  public UndefinedTypeFieldBuilder foobars(Foo k1, Foo.Bar v1, Foo k2, Foo.Bar v2, Foo k3,
      Foo.Bar v3) {
    foobars(k1, v1, k2, v2);
    if (k3 == null) {
      throw new NullPointerException("foobars: k3");
    }
    if (v3 == null) {
      throw new NullPointerException("foobars: v3");
    }
    foobars.put(k3, v3);
    return this;
  }

  public UndefinedTypeFieldBuilder foobars(Foo k1, Foo.Bar v1, Foo k2, Foo.Bar v2, Foo k3,
      Foo.Bar v3, Foo k4, Foo.Bar v4) {
    foobars(k1, v1, k2, v2, k3, v3);
    if (k4 == null) {
      throw new NullPointerException("foobars: k4");
    }
    if (v4 == null) {
      throw new NullPointerException("foobars: v4");
    }
    foobars.put(k4, v4);
    return this;
  }

  public UndefinedTypeFieldBuilder foobars(Foo k1, Foo.Bar v1, Foo k2, Foo.Bar v2, Foo k3,
      Foo.Bar v3, Foo k4, Foo.Bar v4, Foo k5, Foo.Bar v5) {
    foobars(k1, v1, k2, v2, k3, v3, k4, v4);
    if (k5 == null) {
      throw new NullPointerException("foobars: k5");
    }
    if (v5 == null) {
      throw new NullPointerException("foobars: v5");
    }
    foobars.put(k5, v5);
    return this;
  }

  public UndefinedTypeFieldBuilder putFoobar(Foo key, Foo.Bar value) {
    if (key == null) {
      throw new NullPointerException("foobar: key");
    }
    if (value == null) {
      throw new NullPointerException("foobar: value");
    }
    if (this.foobars == null) {
      this.foobars = new HashMap<Foo, Foo.Bar>();
    }
    foobars.put(key, value);
    return this;
  }

  public UndefinedTypeField build() {
    List<Foo.Bar> _bars = (bars != null) ? Collections.unmodifiableList(new ArrayList<Foo.Bar>(bars)) : Collections.<Foo.Bar>emptyList();
    Map<Foo, Foo.Bar> _foobars = (foobars != null) ? Collections.unmodifiableMap(new HashMap<Foo, Foo.Bar>(foobars)) : Collections.<Foo, Foo.Bar>emptyMap();
    return new Value(foo, bar, maybeFoo, _bars, _foobars);
  }

  public static UndefinedTypeFieldBuilder from(UndefinedTypeField v) {
    return new UndefinedTypeFieldBuilder(v);
  }

  public static UndefinedTypeFieldBuilder from(UndefinedTypeFieldBuilder v) {
    return new UndefinedTypeFieldBuilder(v);
  }

  private static final class Value implements UndefinedTypeField {
    private final Foo foo;

    private final Foo.Bar bar;

    private final Optional<Foo> maybeFoo;

    private final List<Foo.Bar> bars;

    private final Map<Foo, Foo.Bar> foobars;

    private Value(@AutoMatter.Field("foo") Foo foo, @AutoMatter.Field("bar") Foo.Bar bar,
        @AutoMatter.Field("maybeFoo") Optional<Foo> maybeFoo,
        @AutoMatter.Field("bars") List<Foo.Bar> bars,
        @AutoMatter.Field("foobars") Map<Foo, Foo.Bar> foobars) {
      if (foo == null) {
        throw new NullPointerException("foo");
      }
      if (bar == null) {
        throw new NullPointerException("bar");
      }
      if (maybeFoo == null) {
        throw new NullPointerException("maybeFoo");
      }
      this.foo = foo;
      this.bar = bar;
      this.maybeFoo = maybeFoo;
      this.bars = (bars != null) ? bars : Collections.<Foo.Bar>emptyList();
      this.foobars = (foobars != null) ? foobars : Collections.<Foo, Foo.Bar>emptyMap();
    }

    @AutoMatter.Field
    @Override
    public Foo foo() {
      return foo;
    }

    @AutoMatter.Field
    @Override
    public Foo.Bar bar() {
      return bar;
    }

    @AutoMatter.Field
    @Override
    public Optional<Foo> maybeFoo() {
      return maybeFoo;
    }

    @AutoMatter.Field
    @Override
    public List<Foo.Bar> bars() {
      return bars;
    }

    @AutoMatter.Field
    @Override
    public Map<Foo, Foo.Bar> foobars() {
      return foobars;
    }

    public UndefinedTypeFieldBuilder builder() {
      return new UndefinedTypeFieldBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof UndefinedTypeField)) {
        return false;
      }
      final UndefinedTypeField that = (UndefinedTypeField) o;
      if (foo != null ? !foo.equals(that.foo()) : that.foo() != null) {
        return false;
      }
      if (bar != null ? !bar.equals(that.bar()) : that.bar() != null) {
        return false;
      }
      if (maybeFoo != null ? !maybeFoo.equals(that.maybeFoo()) : that.maybeFoo() != null) {
        return false;
      }
      if (bars != null ? !bars.equals(that.bars()) : that.bars() != null) {
        return false;
      }
      if (foobars != null ? !foobars.equals(that.foobars()) : that.foobars() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      result = 31 * result + (this.foo != null ? this.foo.hashCode() : 0);
      result = 31 * result + (this.bar != null ? this.bar.hashCode() : 0);
      result = 31 * result + (this.maybeFoo != null ? this.maybeFoo.hashCode() : 0);
      result = 31 * result + (this.bars != null ? this.bars.hashCode() : 0);
      result = 31 * result + (this.foobars != null ? this.foobars.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "UndefinedTypeField{" +
          "foo=" + foo +
          ", bar=" + bar +
          ", maybeFoo=" + maybeFoo +
          ", bars=" + bars +
          ", foobars=" + foobars +
          '}';
    }
  }
}
