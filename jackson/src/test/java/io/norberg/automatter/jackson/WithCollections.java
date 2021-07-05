package io.norberg.automatter.jackson;

import io.norberg.automatter.AutoMatter;
import java.util.List;
import java.util.Map;
import java.util.Set;

@AutoMatter
public interface WithCollections {
  List<String> list();

  Set<String> set();

  Map<String, String> map();
}
