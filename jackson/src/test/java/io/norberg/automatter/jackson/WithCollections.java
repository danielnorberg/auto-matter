package io.norberg.automatter.jackson;

import java.util.List;
import java.util.Map;
import java.util.Set;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface WithCollections {
  List<String> list();
  Set<String> set();
  Map<String, String> map();
}
