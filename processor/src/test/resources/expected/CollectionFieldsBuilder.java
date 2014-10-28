package foo;

import io.norberg.automatter.AutoMatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Generated;

@Generated("io.norberg.automatter.processor.AutoMatterProcessor")
public final class CollectionFieldsBuilder {

  private List<String> strings;
  private List<String> stringList;

  public CollectionFieldsBuilder() {
  }

  private CollectionFieldsBuilder(CollectionFields v) {
    List<String> _strings = v.strings();
    this.strings = (_strings == null) ? null : new ArrayList(_strings);
    List<String> _stringList = v.stringList();
    this.stringList = (_stringList == null) ? null : new ArrayList(_stringList);
  }

  private CollectionFieldsBuilder(CollectionFieldsBuilder v) {
    this.strings = (v.strings == null) ? null : new ArrayList(v.strings);
    this.stringList = (v.stringList == null) ? null : new ArrayList(v.stringList);
  }

  public List<String> strings() {
    return strings;
  }

  public CollectionFieldsBuilder strings(Iterable<String> strings) {
    if (strings == null) {
      throw new NullPointerException("strings");
    } else if (strings instanceof Collection) {
      this.strings = new ArrayList((Collection<String>) strings);
    } else {
      this.strings = new ArrayList();
      Iterator<String> it = strings.iterator();
      while (it.hasNext()) {
        this.strings.add(it.next());
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

  public List<String> stringList() {
    return stringList;
  }

  public CollectionFieldsBuilder stringList(Iterable<String> stringList) {
    if (stringList == null) {
      throw new NullPointerException("stringList");
    } else if (stringList instanceof Collection) {
      this.stringList = new ArrayList((Collection<String>) stringList);
    } else {
      this.stringList = new ArrayList();
      Iterator<String> it = stringList.iterator();
      while (it.hasNext()) {
        this.stringList.add(it.next());
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

  public CollectionFields build() {
    return new Value((strings != null) ? new ArrayList<String>(strings) : Collections.<String>emptyList(),
                     (stringList != null) ? new ArrayList<String>(stringList) : Collections.<String>emptyList());
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

    private Value(@AutoMatter.Field("strings") List<String> strings,
                  @AutoMatter.Field("stringList") List<String> stringList) {
      if (strings == null) {
        throw new NullPointerException("strings");
      }
      if (stringList == null) {
        throw new NullPointerException("stringList");
      }
      this.strings = (strings != null) ? strings : Collections.<String>emptyList();
      this.stringList = (stringList != null) ? stringList : Collections.<String>emptyList();
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

      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      long temp;
      result = 31 * result + (strings != null ? strings.hashCode() : 0);
      result = 31 * result + (stringList != null ? stringList.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "CollectionFields{" +
             "strings=" + strings +
             ", stringList=" + stringList +
             '}';
    }
  }
}