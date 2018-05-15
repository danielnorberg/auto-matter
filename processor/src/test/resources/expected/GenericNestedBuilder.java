package foo;

import io.norberg.automatter.AutoMatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
${GENERATED_IMPORT}


${GENERATED_ANNOTATION}
public final class GenericNestedBuilder<Q, W> {

  private GenericNested.Baz<GenericNested.Baz<Q>> baz2;
  private GenericNested.Baz<GenericNested.Baz<GenericNested.Baz<Q>>> baz3;
  private Map<Q, GenericNested.Baz<W>> mapBaz;
  private GenericNested.Quux<Q, GenericNested.Baz<W>> quuxBaz;
  private GenericNested.Quux<GenericNested.Quux<String, Integer>, GenericNested.Quux<W, GenericNested.Baz<Q>>> quuxQuuxBaz;
  private GenericNested.Baz<GenericNested.Baz<Q>> bazBaz;

  public GenericNestedBuilder() {
  }

  private GenericNestedBuilder(GenericNested<? extends Q, ? extends W> v) {
    @SuppressWarnings("unchecked") GenericNested.Baz<GenericNested.Baz<Q>> _baz2 = (GenericNested.Baz) v.baz2();
    this.baz2 = _baz2;
    @SuppressWarnings("unchecked") GenericNested.Baz<GenericNested.Baz<GenericNested.Baz<Q>>> _baz3 = (GenericNested.Baz) v.baz3();
    this.baz3 = _baz3;
    @SuppressWarnings("unchecked") Map<? extends Q, ? extends GenericNested.Baz<W>> _mapBaz = (Map) v.mapBaz();
    this.mapBaz = (_mapBaz == null) ? null : new HashMap<Q, GenericNested.Baz<W>>(_mapBaz);
    @SuppressWarnings("unchecked") GenericNested.Quux<Q, GenericNested.Baz<W>> _quuxBaz = (GenericNested.Quux) v.quuxBaz();
    this.quuxBaz = _quuxBaz;
    @SuppressWarnings("unchecked") GenericNested.Quux<GenericNested.Quux<String, Integer>, GenericNested.Quux<W, GenericNested.Baz<Q>>> _quuxQuuxBaz = (GenericNested.Quux) v.quuxQuuxBaz();
    this.quuxQuuxBaz = _quuxQuuxBaz;
    @SuppressWarnings("unchecked") GenericNested.Baz<GenericNested.Baz<Q>> _bazBaz = (GenericNested.Baz) v.bazBaz();
    this.bazBaz = _bazBaz;
  }

  private GenericNestedBuilder(GenericNestedBuilder<? extends Q, ? extends W> v) {
    @SuppressWarnings("unchecked") GenericNested.Baz<GenericNested.Baz<Q>> _baz2 = (GenericNested.Baz) v.baz2();
    this.baz2 = _baz2;
    @SuppressWarnings("unchecked") GenericNested.Baz<GenericNested.Baz<GenericNested.Baz<Q>>> _baz3 = (GenericNested.Baz) v.baz3();
    this.baz3 = _baz3;
    @SuppressWarnings("unchecked") Map<? extends Q, ? extends GenericNested.Baz<W>> _mapBaz = (Map) v.mapBaz;
    this.mapBaz = (_mapBaz == null) ? null : new HashMap<Q, GenericNested.Baz<W>>(_mapBaz);
    @SuppressWarnings("unchecked") GenericNested.Quux<Q, GenericNested.Baz<W>> _quuxBaz = (GenericNested.Quux) v.quuxBaz();
    this.quuxBaz = _quuxBaz;
    @SuppressWarnings("unchecked") GenericNested.Quux<GenericNested.Quux<String, Integer>, GenericNested.Quux<W, GenericNested.Baz<Q>>> _quuxQuuxBaz = (GenericNested.Quux) v.quuxQuuxBaz();
    this.quuxQuuxBaz = _quuxQuuxBaz;
    @SuppressWarnings("unchecked") GenericNested.Baz<GenericNested.Baz<Q>> _bazBaz = (GenericNested.Baz) v.bazBaz();
    this.bazBaz = _bazBaz;
  }

  public GenericNested.Baz<GenericNested.Baz<Q>> baz2() {
    return baz2;
  }

  public GenericNestedBuilder<Q, W> baz2(GenericNested.Baz<GenericNested.Baz<Q>> baz2) {
    if (baz2 == null) {
      throw new NullPointerException("baz2");
    }
    this.baz2 = baz2;
    return this;
  }

  public GenericNested.Baz<GenericNested.Baz<GenericNested.Baz<Q>>> baz3() {
    return baz3;
  }

  public GenericNestedBuilder<Q, W> baz3(GenericNested.Baz<GenericNested.Baz<GenericNested.Baz<Q>>> baz3) {
    if (baz3 == null) {
      throw new NullPointerException("baz3");
    }
    this.baz3 = baz3;
    return this;
  }

  public Map<Q, GenericNested.Baz<W>> mapBaz() {
    if (this.mapBaz == null) {
      this.mapBaz = new HashMap<Q, GenericNested.Baz<W>>();
    }
    return mapBaz;
  }

  public GenericNestedBuilder<Q, W> mapBaz(Map<? extends Q, ? extends GenericNested.Baz<W>> mapBaz) {
    if (mapBaz == null) {
      throw new NullPointerException("mapBaz");
    }
    for (Map.Entry<? extends Q, ? extends GenericNested.Baz<W>> entry : mapBaz.entrySet()) {
      if (entry.getKey() == null) {
        throw new NullPointerException("mapBaz: null key");
      }
      if (entry.getValue() == null) {
        throw new NullPointerException("mapBaz: null value");
      }
    }
    this.mapBaz = new HashMap<Q, GenericNested.Baz<W>>(mapBaz);
    return this;
  }

  public GenericNestedBuilder<Q, W> mapBaz(Q k1, GenericNested.Baz<W> v1) {
    if (k1 == null) {
      throw new NullPointerException("mapBaz: k1");
    }
    if (v1 == null) {
      throw new NullPointerException("mapBaz: v1");
    }
    mapBaz = new HashMap<Q, GenericNested.Baz<W>>();
    mapBaz.put(k1, v1);
    return this;
  }

  public GenericNestedBuilder<Q, W> mapBaz(Q k1, GenericNested.Baz<W> v1, Q k2, GenericNested.Baz<W> v2) {
    mapBaz(k1, v1);
    if (k2 == null) {
      throw new NullPointerException("mapBaz: k2");
    }
    if (v2 == null) {
      throw new NullPointerException("mapBaz: v2");
    }
    mapBaz.put(k2, v2);
    return this;
  }

  public GenericNestedBuilder<Q, W> mapBaz(Q k1, GenericNested.Baz<W> v1, Q k2, GenericNested.Baz<W> v2, Q k3, GenericNested.Baz<W> v3) {
    mapBaz(k1, v1, k2, v2);
    if (k3 == null) {
      throw new NullPointerException("mapBaz: k3");
    }
    if (v3 == null) {
      throw new NullPointerException("mapBaz: v3");
    }
    mapBaz.put(k3, v3);
    return this;
  }

  public GenericNestedBuilder<Q, W> mapBaz(Q k1, GenericNested.Baz<W> v1, Q k2, GenericNested.Baz<W> v2, Q k3, GenericNested.Baz<W> v3, Q k4, GenericNested.Baz<W> v4) {
    mapBaz(k1, v1, k2, v2, k3, v3);
    if (k4 == null) {
      throw new NullPointerException("mapBaz: k4");
    }
    if (v4 == null) {
      throw new NullPointerException("mapBaz: v4");
    }
    mapBaz.put(k4, v4);
    return this;
  }

  public GenericNestedBuilder<Q, W> mapBaz(Q k1, GenericNested.Baz<W> v1, Q k2, GenericNested.Baz<W> v2, Q k3, GenericNested.Baz<W> v3, Q k4, GenericNested.Baz<W> v4, Q k5, GenericNested.Baz<W> v5) {
    mapBaz(k1, v1, k2, v2, k3, v3, k4, v4);
    if (k5 == null) {
      throw new NullPointerException("mapBaz: k5");
    }
    if (v5 == null) {
      throw new NullPointerException("mapBaz: v5");
    }
    mapBaz.put(k5, v5);
    return this;
  }

  public GenericNested.Quux<Q, GenericNested.Baz<W>> quuxBaz() {
    return quuxBaz;
  }

  public GenericNestedBuilder<Q, W> quuxBaz(GenericNested.Quux<Q, GenericNested.Baz<W>> quuxBaz) {
    if (quuxBaz == null) {
      throw new NullPointerException("quuxBaz");
    }
    this.quuxBaz = quuxBaz;
    return this;
  }

  public GenericNested.Quux<GenericNested.Quux<String, Integer>, GenericNested.Quux<W, GenericNested.Baz<Q>>> quuxQuuxBaz() {
    return quuxQuuxBaz;
  }

  public GenericNestedBuilder<Q, W> quuxQuuxBaz(GenericNested.Quux<GenericNested.Quux<String, Integer>, GenericNested.Quux<W, GenericNested.Baz<Q>>> quuxQuuxBaz) {
    if (quuxQuuxBaz == null) {
      throw new NullPointerException("quuxQuuxBaz");
    }
    this.quuxQuuxBaz = quuxQuuxBaz;
    return this;
  }

  public GenericNested.Baz<GenericNested.Baz<Q>> bazBaz() {
    return bazBaz;
  }

  public GenericNestedBuilder<Q, W> bazBaz(GenericNested.Baz<GenericNested.Baz<Q>> bazBaz) {
    if (bazBaz == null) {
      throw new NullPointerException("bazBaz");
    }
    this.bazBaz = bazBaz;
    return this;
  }

  public GenericNestedBuilder<Q, W> builder() {
    return new GenericNestedBuilder<Q, W>(this);
  }

  public GenericNested<Q, W> build() {
    Map<Q, GenericNested.Baz<W>> _mapBaz = (mapBaz != null) ? Collections.unmodifiableMap(new HashMap<Q, GenericNested.Baz<W>>(mapBaz)) : Collections.<Q, GenericNested.Baz<W>>emptyMap();
    return new Value<Q, W>(baz2, baz3, _mapBaz, quuxBaz, quuxQuuxBaz, bazBaz);
  }

  public static <Q, W> GenericNestedBuilder<Q, W> from(GenericNested<? extends Q, ? extends W> v) {
    return new GenericNestedBuilder<Q, W>(v);
  }

  public static <Q, W> GenericNestedBuilder<Q, W> from(GenericNestedBuilder<? extends Q, ? extends W> v) {
    return new GenericNestedBuilder<Q, W>(v);
  }

  private static final class Value<Q, W> implements GenericNested<Q, W> {
    private final GenericNested.Baz<GenericNested.Baz<Q>> baz2;

    private final GenericNested.Baz<GenericNested.Baz<GenericNested.Baz<Q>>> baz3;

    private final Map<Q, GenericNested.Baz<W>> mapBaz;

    private final GenericNested.Quux<Q, GenericNested.Baz<W>> quuxBaz;

    private final GenericNested.Quux<GenericNested.Quux<String, Integer>, GenericNested.Quux<W, GenericNested.Baz<Q>>> quuxQuuxBaz;

    private final GenericNested.Baz<GenericNested.Baz<Q>> bazBaz;

    private Value(@AutoMatter.Field("baz2") GenericNested.Baz<GenericNested.Baz<Q>> baz2, @AutoMatter.Field("baz3") GenericNested.Baz<GenericNested.Baz<GenericNested.Baz<Q>>> baz3, @AutoMatter.Field("mapBaz") Map<Q, GenericNested.Baz<W>> mapBaz, @AutoMatter.Field("quuxBaz") GenericNested.Quux<Q, GenericNested.Baz<W>> quuxBaz, @AutoMatter.Field("quuxQuuxBaz") GenericNested.Quux<GenericNested.Quux<String, Integer>, GenericNested.Quux<W, GenericNested.Baz<Q>>> quuxQuuxBaz, @AutoMatter.Field("bazBaz") GenericNested.Baz<GenericNested.Baz<Q>> bazBaz) {
      if (baz2 == null) {
        throw new NullPointerException("baz2");
      }
      if (baz3 == null) {
        throw new NullPointerException("baz3");
      }
      if (quuxBaz == null) {
        throw new NullPointerException("quuxBaz");
      }
      if (quuxQuuxBaz == null) {
        throw new NullPointerException("quuxQuuxBaz");
      }
      if (bazBaz == null) {
        throw new NullPointerException("bazBaz");
      }
      this.baz2 = baz2;
      this.baz3 = baz3;
      this.mapBaz = (mapBaz != null) ? mapBaz : Collections.<Q, GenericNested.Baz<W>>emptyMap();
      this.quuxBaz = quuxBaz;
      this.quuxQuuxBaz = quuxQuuxBaz;
      this.bazBaz = bazBaz;
    }

    @AutoMatter.Field
    @Override
    public GenericNested.Baz<GenericNested.Baz<Q>> baz2() {
      return baz2;
    }

    @AutoMatter.Field
    @Override
    public GenericNested.Baz<GenericNested.Baz<GenericNested.Baz<Q>>> baz3() {
      return baz3;
    }

    @AutoMatter.Field
    @Override
    public Map<Q, GenericNested.Baz<W>> mapBaz() {
      return mapBaz;
    }

    @AutoMatter.Field
    @Override
    public GenericNested.Quux<Q, GenericNested.Baz<W>> quuxBaz() {
      return quuxBaz;
    }

    @AutoMatter.Field
    @Override
    public GenericNested.Quux<GenericNested.Quux<String, Integer>, GenericNested.Quux<W, GenericNested.Baz<Q>>> quuxQuuxBaz() {
      return quuxQuuxBaz;
    }

    @AutoMatter.Field
    @Override
    public GenericNested.Baz<GenericNested.Baz<Q>> bazBaz() {
      return bazBaz;
    }

    @Override
    public GenericNestedBuilder<Q, W> builder() {
      return new GenericNestedBuilder<Q, W>(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof GenericNested)) {
        return false;
      }
      final GenericNested<?, ?> that = (GenericNested<?, ?>) o;
      if (baz2 != null ? !baz2.equals(that.baz2()) : that.baz2() != null) {
        return false;
      }
      if (baz3 != null ? !baz3.equals(that.baz3()) : that.baz3() != null) {
        return false;
      }
      if (mapBaz != null ? !mapBaz.equals(that.mapBaz()) : that.mapBaz() != null) {
        return false;
      }
      if (quuxBaz != null ? !quuxBaz.equals(that.quuxBaz()) : that.quuxBaz() != null) {
        return false;
      }
      if (quuxQuuxBaz != null ? !quuxQuuxBaz.equals(that.quuxQuuxBaz()) : that.quuxQuuxBaz() != null) {
        return false;
      }
      if (bazBaz != null ? !bazBaz.equals(that.bazBaz()) : that.bazBaz() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      result = 31 * result + (this.baz2 != null ? this.baz2.hashCode() : 0);
      result = 31 * result + (this.baz3 != null ? this.baz3.hashCode() : 0);
      result = 31 * result + (this.mapBaz != null ? this.mapBaz.hashCode() : 0);
      result = 31 * result + (this.quuxBaz != null ? this.quuxBaz.hashCode() : 0);
      result = 31 * result + (this.quuxQuuxBaz != null ? this.quuxQuuxBaz.hashCode() : 0);
      result = 31 * result + (this.bazBaz != null ? this.bazBaz.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "GenericNested{" +
          "baz2=" + baz2 +
          ", baz3=" + baz3 +
          ", mapBaz=" + mapBaz +
          ", quuxBaz=" + quuxBaz +
          ", quuxQuuxBaz=" + quuxQuuxBaz +
          ", bazBaz=" + bazBaz +
          '}';
    }
  }
