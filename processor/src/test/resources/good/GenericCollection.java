package generic_collection;

import java.util.List;
import java.util.Map;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface GenericCollection<T, K, V> {
  List<T> foos();
  Map<K, V> bars();

  GenericCollectionBuilder<T, K, V> builder();
}
