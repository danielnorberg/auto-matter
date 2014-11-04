package foo;

import java.util.List;
import java.util.Map;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface CollectionFields {
  List<String> strings();
  Map<String, Integer> integers();
}