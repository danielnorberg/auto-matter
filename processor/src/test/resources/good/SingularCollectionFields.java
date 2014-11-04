package good;

import java.util.List;
import java.util.Map;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface SingularCollectionFields {
  List<String> stringList();
  Map<String, String> stringMap();
}