package foo;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface NullableCollectionFields {
  @Nullable List<String> strings();
  @Nullable Map<String, Integer> integers();
  @Nullable Set<Long> numbers();
}