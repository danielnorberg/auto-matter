package foo;

import java.util.List;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface CollectionFields {
  List<String> strings();
  List<String> stringList();
}