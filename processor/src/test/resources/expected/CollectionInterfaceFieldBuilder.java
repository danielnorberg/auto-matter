package foo;

import io.norberg.automatter.AutoMatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
${GENERATED_IMPORT}

${GENERATED_ANNOTATION}
@AutoMatter.Generated
public final class CollectionInterfaceFieldBuilder {
  private Collection<String> strings;

  public CollectionInterfaceFieldBuilder() {
  }

  private CollectionInterfaceFieldBuilder(CollectionInterfaceField v) {
    Collection<String> _strings = v.strings();
    this.strings = (_strings == null) ? null : new ArrayList<String>(_strings);
  }

  private CollectionInterfaceFieldBuilder(CollectionInterfaceFieldBuilder v) {
    this.strings = (v.strings() == null) ? null : new ArrayList<String>(v.strings());
  }

  public Collection<String> strings() {
    if (this.strings == null) {
      this.strings = new ArrayList<String>();
    }
    return strings;
  }

  public CollectionInterfaceFieldBuilder strings(Collection<? extends String> strings) {
    if (strings == null) {
      throw new NullPointerException("strings");
    }
    for (String item : strings) {
      if (item == null) {
        throw new NullPointerException("strings: null item");
      }
    }
    this.strings = new ArrayList<String>(strings);
    return this;
  }

  public CollectionInterfaceFieldBuilder strings(Iterable<? extends String> strings) {
    if (strings == null) {
      throw new NullPointerException("strings");
    }
    if (strings instanceof Collection) {
      return strings((Collection<? extends String>) strings);
    }
    return strings(strings.iterator());
  }

  public CollectionInterfaceFieldBuilder strings(Iterator<? extends String> strings) {
    if (strings == null) {
      throw new NullPointerException("strings");
    }
    this.strings = new ArrayList<String>();
    while (strings.hasNext()) {
      String item = strings.next();
      if (item == null) {
        throw new NullPointerException("strings: null item");
      }
      this.strings.add(item);
    }
    return this;
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  public final CollectionInterfaceFieldBuilder strings(String... strings) {
    if (strings == null) {
      throw new NullPointerException("strings");
    }
    return strings(Arrays.asList(strings));
  }

  public CollectionInterfaceFieldBuilder addString(String string) {
    if (string == null) {
      throw new NullPointerException("string");
    }
    if (this.strings == null) {
      this.strings = new ArrayList<String>();
    }
    strings.add(string);
    return this;
  }

  public CollectionInterfaceField build() {
    Collection<String> _strings = (strings != null) ? Collections.unmodifiableList(new ArrayList<String>(strings)) : Collections.<String>emptyList();
    return new Value(_strings);
  }

  public static CollectionInterfaceFieldBuilder from(CollectionInterfaceField v) {
    return new CollectionInterfaceFieldBuilder(v);
  }

  public static CollectionInterfaceFieldBuilder from(CollectionInterfaceFieldBuilder v) {
    return new CollectionInterfaceFieldBuilder(v);
  }

  @AutoMatter.Generated
  private static final class Value implements CollectionInterfaceField {
    private final Collection<String> strings;

    private Value(@AutoMatter.Field("strings") Collection<String> strings) {
      this.strings = (strings != null) ? strings : Collections.<String>emptyList();
    }

    @AutoMatter.Field
    @Override
    public Collection<String> strings() {
      return strings;
    }

    public CollectionInterfaceFieldBuilder builder() {
      return new CollectionInterfaceFieldBuilder(this);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof CollectionInterfaceField)) {
        return false;
      }
      final CollectionInterfaceField that = (CollectionInterfaceField) o;
      if (strings != null ? !strings.equals(that.strings()) : that.strings() != null) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + (this.strings != null ? this.strings.hashCode() : 0);
      return result;
    }

    @Override
    public String toString() {
      return "CollectionInterfaceField{" +
          "strings=" + strings +
          '}';
    }
  }
}
