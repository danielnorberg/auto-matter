package foo;

import java.util.List;
import java.util.Map;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface CollectionFields {
  List<String> strings();
  List<String> stringList();

  Map<String, String> mappedStrings();
  Map<String, String> stringMap();
}