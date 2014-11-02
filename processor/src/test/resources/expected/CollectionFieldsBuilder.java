package foo;

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
public final class CollectionFieldsBuilder {

  private List<String> strings;
  private List<String> stringList;
  private Map<String,String> mappedStrings;
  private Map<String,String> stringMap;

  public CollectionFieldsBuilder() {
  }

  private CollectionFieldsBuilder(CollectionFields v) {
    List<String> _strings = v.strings();
    this.strings = (_strings == null) ? null : new ArrayList<String>(_strings);
    List<String> _stringList = v.stringList();
    this.stringList = (_stringList == null) ? null : new ArrayList<String>(_stringList);
    Map<String,String> _mappedStrings = v.mappedStrings();
    this.mappedStrings = (_mappedStrings == null) ? null : new HashMap<String,String>(_mappedStrings);
    Map<String,String> _stringMap = v.stringMap();
    this.stringMap = (_stringMap == null) ? null : new HashMap<String,String>(_stringMap);
  }

  private CollectionFieldsBuilder(CollectionFieldsBuilder v) {
    this.strings = (v.strings == null) ? null : new ArrayList<String>(v.strings);
    this.stringList = (v.stringList == null) ? null : new ArrayList<String>(v.stringList);
    this.mappedStrings = (v.mappedStrings == null) ? null : new HashMap<String,String>(v.mappedStrings);
    this.stringMap = (v.stringMap == null) ? null : new HashMap<String,String>(v.stringMap);
  }

  public List<String> strings() {
    if (strings == null) {
      strings = new ArrayList<String>();
    }
    return strings;
  }

  public CollectionFieldsBuilder strings(Iterable<? extends String> strings) {
    if (strings == null) {
      throw new NullPointerException("strings");
    } else if (strings instanceof Collection) {
      Collection<? extends String> collection = (Collection<? extends String>) strings;
      for (String item : collection) {
        if (item == null) {
          throw new NullPointerException("strings: null item");
        }
      }
      this.strings = new ArrayList<String>(collection);
    } else {
      this.strings = new ArrayList<String>();
      Iterator<? extends String> iterator = strings.iterator();
      while (iterator.hasNext()) {
        String item = iterator.next();
        if (item == null) {
          throw new NullPointerException("strings: null item");
        }
        this.strings.add(item);
      }
    }
    return this;
  }

  public CollectionFieldsBuilder strings(String... strings) {
    if (strings == null) {
      throw new NullPointerException("strings");
    }
    return strings(Arrays.asList(strings));
  }

  public CollectionFieldsBuilder string(String string) {
    if (string == null) {
      throw new NullPointerException("string");
    }
    if (strings == null) {
      strings = new ArrayList<String>();
    }
    strings.add(string);
    return this;
  }

  public List<String> stringList() {
    if (stringList == null) {
      stringList = new ArrayList<String>();
    }
    return stringList;
  }

  public CollectionFieldsBuilder stringList(Iterable<? extends String> stringList) {
    if (stringList == null) {
      throw new NullPointerException("stringList");
    } else if (stringList instanceof Collection) {
      Collection<? extends String> collection = (Collection<? extends String>) stringList;
      for (String item : collection) {
        if (item == null) {
          throw new NullPointerException("stringList: null item");
        }
      }
      this.stringList = new ArrayList<String>(collection);
    } else {
      this.stringList = new ArrayList<String>();
      Iterator<? extends String> iterator = stringList.iterator();
      while (iterator.hasNext()) {
        String item = iterator.next();
        if (item == null) {
          throw new NullPointerException("stringList: null item");
        }
        this.stringList.add(item);
      }
    }
    return this;
  }

  public CollectionFieldsBuilder stringList(String... stringList) {
    if (stringList == null) {
      throw new NullPointerException("stringList");
    }
    return stringList(Arrays.asList(stringList));
  }

  public Map<String,String> mappedStrings() {
    if (mappedStrings == null) {
      mappedStrings = new HashMap<String,String>();
    }
    return mappedStrings;
  }

  public CollectionFieldsBuilder mappedStrings(Map<? extends String,? extends String> mappedStrings) {
    if (mappedStrings == null) {
      throw new NullPointerException("mappedStrings");
    }
    for (Map.Entry<? extends String,? extends String> entry : mappedStrings.entrySet()) {
      if (entry.getKey() == null) {
        throw new NullPointerException("mappedStrings: null key");
      }
      if (entry.getValue() == null) {
        throw new NullPointerException("mappedStrings: null value");
      }
    }
    this.mappedStrings = new HashMap<String,String>(mappedStrings);
    return this;
  }

  public CollectionFieldsBuilder mappedStrings(String k1, String v1) {
    if (k1 == null) {
      throw new NullPointerException("mappedStrings: k1");
    }
    if (v1 == null) {
      throw new NullPointerException("mappedStrings: v1");
    }
    mappedStrings = new HashMap<String,String>();
    mappedStrings.put(k1, v1);
    return this;
  }

  public CollectionFieldsBuilder mappedStrings(String k1, String v1,
                                               String k2, String v2) {
    mappedStrings(k1, v1);
    if (k2 == null) {
      throw new NullPointerException("mappedStrings: k2");
    }
    if (v2 == null) {
      throw new NullPointerException("mappedStrings: v2");
    }
    mappedStrings.put(k2, v2);
    return this;
  }

  public CollectionFieldsBuilder mappedStrings(String k1, String v1,
                                               String k2, String v2,
                                               String k3, String v3) {
    mappedStrings(k1, v1, k2, v2);
    if (k3 == null) {
      throw new NullPointerException("mappedStrings: k3");
    }
    if (v3 == null) {
      throw new NullPointerException("mappedStrings: v3");
    }
    mappedStrings.put(k3, v3);
    return this;
  }

  public CollectionFieldsBuilder mappedStrings(String k1, String v1,
                                               String k2, String v2,
                                               String k3, String v3,
                                               String k4, String v4) {
    mappedStrings(k1, v1, k2, v2, k3, v3);
    if (k4 == null) {
      throw new NullPointerException("mappedStrings: k4");
    }
    if (v4 == null) {
      throw new NullPointerException("mappedStrings: v4");
    }
    mappedStrings.put(k4, v4);
    return this;
  }

  public CollectionFieldsBuilder mappedStrings(String k1, String v1,
                                               String k2, String v2,
                                               String k3, String v3,
                                               String k4, String v4,
                                               String k5, String v5) {
    mappedStrings(k1, v1, k2, v2, k3, v3, k4, v4);
    if (k5 == null) {
      throw new NullPointerException("mappedStrings: k5");
    }
    if (v5 == null) {
      throw new NullPointerException("mappedStrings: v5");
    }
    mappedStrings.put(k5, v5);
    return this;
  }

  public CollectionFieldsBuilder mappedString(String key, String value) {
    if (key == null) {
      throw new NullPointerException("mappedString: key");
    }
    if (value == null) {
      throw new NullPointerException("mappedString: value");
    }
    if (mappedStrings == null) {
      mappedStrings = new HashMap<String,String>();
    }
    mappedStrings.put(key, value);
    return this;
  }

  public Map<String,String> stringMap() {
    if (stringMap == null) {
      stringMap = new HashMap<String,String>();
    }
    return stringMap;
  }

  public CollectionFieldsBuilder stringMap(Map<? extends String,? extends String> stringMap) {
    if (stringMap == null) {
      throw new NullPointerException("stringMap");
    }
    for (Map.Entry<? extends String,? extends String> entry : stringMap.entrySet()) {
      if (entry.getKey() == null) {
        throw new NullPointerException("stringMap: null key");
      }
      if (entry.getValue() == null) {
        throw new NullPointerException("stringMap: null value");
      }
    }
    this.stringMap = new HashMap<String,String>(stringMap);
    return this;
  }

  public CollectionFieldsBuilder stringMap(String k1, String v1) {
    if (k1 == null) {
      throw new NullPointerException("stringMap: k1");
    }
    if (v1 == null) {
      throw new NullPointerException("stringMap: v1");
    }
    stringMap = new HashMap<String,String>();
    stringMap.put(k1, v1);
    return this;
  }

  public CollectionFieldsBuilder stringMap(String k1, String v1,
                                               String k2, String v2) {
    stringMap(k1, v1);
    if (k2 == null) {
      throw new NullPointerException("stringMap: k2");
    }
    if (v2 == null) {
      throw new NullPointerException("stringMap: v2");
    }
    stringMap.put(k2, v2);
    return this;
  }

  public CollectionFieldsBuilder stringMap(String k1, String v1,
                                               String k2, String v2,
                                               String k3, String v3) {
    stringMap(k1, v1, k2, v2);
    if (k3 == null) {
      throw new NullPointerException("stringMap: k3");
    }
    if (v3 == null) {
      throw new NullPointerException("stringMap: v3");
    }
    stringMap.put(k3, v3);
    return this;
  }

  public CollectionFieldsBuilder stringMap(String k1, String v1,
                                               String k2, String v2,
                                               String k3, String v3,
                                               String k4, String v4) {
    stringMap(k1, v1, k2, v2, k3, v3);
    if (k4 == null) {
      throw new NullPointerException("stringMap: k4");
    }
    if (v4 == null) {
      throw new NullPointerException("stringMap: v4");
    }
    stringMap.put(k4, v4);
    return this;
  }

  public CollectionFieldsBuilder stringMap(String k1, String v1,
                                               String k2, String v2,
                                               String k3, String v3,
                                               String k4, String v4,
                                               String k5, String v5) {
    stringMap(k1, v1, k2, v2, k3, v3, k4, v4);
    if (k5 == null) {
      throw new NullPointerException("stringMap: k5");
    }
    if (v5 == null) {
      throw new NullPointerException("stringMap: v5");
    }
    stringMap.put(k5, v5);
    return this;
  }

  public CollectionFields build() {
    return new Value((strings != null) ? new ArrayList<String>(strings) : Collections.<String>emptyList(),
                     (stringList != null) ? new ArrayList<String>(stringList) : Collections.<String>emptyList(),
                     (mappedStrings != null) ? new HashMap<String,String>(mappedStrings) : Collections.<String,String>emptyMap(),
                     (stringMap != null) ? new HashMap<String,String>(stringMap) : Collections.<String,String>emptyMap());
  }

  public static CollectionFieldsBuilder from(CollectionFields v) {
    return new CollectionFieldsBuilder(v);
  }

  public static CollectionFieldsBuilder from(CollectionFieldsBuilder v) {
    return new CollectionFieldsBuilder(v);
  }

  private static final class Value
      implements CollectionFields {

    private final List<String> strings;
    private final List<String> stringList;
    private final Map<String,String> mappedStrings;
    private final Map<String,String> stringMap;

    private Value(@AutoMatter.Field("strings") List<String> strings,
                  @AutoMatter.Field("stringList") List<String> stringList,
                  @AutoMatter.Field("mappedStrings") Map<String,String> mappedStrings,
                  @AutoMatter.Field("stringMap") Map<String,String> stringMap) {
      if (strings == null) {
        throw new NullPointerException("strings");
      }
      if (stringList == null) {
        throw new NullPointerException("stringList");
      }
      if (mappedStrings == null) {
        throw new NullPointerException("mappedStrings");
      }
      if (stringMap == null) {
        throw new NullPointerException("stringMap");
      }
      this.strings = (strings != null) ? strings : Collections.<String>emptyList();
      this.stringList = (stringList != null) ? stringList : Collections.<String>emptyList();
      this.mappedStrings = (mappedStrings != null) ? mappedStrings : Collections.<String,String>emptyMap();
      this.stringMap = (stringMap != null) ? stringMap : Collections.<String,String>emptyMap();
    }

    @AutoMatter.Field
    @Override
    public List<String> strings() {
      return Collections.unmodifiableList(strings);
    }

    @AutoMatter.Field
    @Override
    public List<String> stringList() {
      return Collections.unmodifiableList(stringList);
    }

    @AutoMatter.Field
    @Override
    public Map<String,String> mappedStrings() {
      return Collections.unmodifiableMap(mappedStrings);
    }

    @AutoMatter.Field
    @Override
    public Map<String,String> stringMap() {
      return Collections.unmodifiableMap(stringMap);
    }

    public CollectionFieldsBuilder builder() {
      return new CollectionFieldsBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof CollectionFields)) {
        return false;
      }

      final CollectionFields that = (CollectionFields) o;

      if (strings != null ? !strings.equals(that.strings()) : that.strings() != null) {
        return false;
      }
      if (stringList != null ? !stringList.equals(that.stringList()) : that.stringList() != null) {
        return false;
      }
      if (mappedStrings != null ? !mappedStrings.equals(that.mappedStrings()) : that.mappedStrings() != null) {
        return false;
      }
      if (stringMap != null ? !stringMap.equals(that.stringMap()) : that.stringMap() != null) {
        return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      result = 31 * result + (strings != null ? strings.hashCode() : 0);
      result = 31 * result + (stringList != null ? stringList.hashCode() : 0);
      result = 31 * result + (mappedStrings != null ? mappedStrings.hashCode() : 0);
      result = 31 * result + (stringMap != null ? stringMap.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "CollectionFields{" +
             "strings=" + strings +
             ", stringList=" + stringList +
             ", mappedStrings=" + mappedStrings +
             ", stringMap=" + stringMap +
             '}';
    }
  }
}