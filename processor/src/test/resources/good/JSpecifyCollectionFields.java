package foo;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jspecify.annotations.Nullable;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface JSpecifyCollectionFields {
  @Nullable List<String> strings();
  @Nullable Map<String, Integer> integers();
  @Nullable Set<Long> numbers();
}
