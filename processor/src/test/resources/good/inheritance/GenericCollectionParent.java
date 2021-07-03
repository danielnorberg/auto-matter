package inheritance;

import java.util.List;
import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface GenericCollectionParent<T> extends GenericSuperParent<T> {
  Bar<T> secondParameterizedBar();
  Quux<T> secondParameterizedQuux();
  List<T> moreFoos();
}
