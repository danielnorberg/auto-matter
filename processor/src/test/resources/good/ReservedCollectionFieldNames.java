package good;

import java.util.List;
import java.util.Map;
import java.util.Set;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface ReservedCollectionFieldNames {
  List<String> Strings();
  List<Integer> ints();
  List<Long> longs();
}