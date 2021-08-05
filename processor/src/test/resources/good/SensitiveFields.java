package foo;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.NavigableMap;
import java.util.Set;
import java.util.SortedSet;
import java.util.NavigableSet;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface SensitiveFields {
  String userName();

  @AutoMatter.Sensitive
  String password();

  @AutoMatter.Sensitive(value = "....")
  String token();
}
