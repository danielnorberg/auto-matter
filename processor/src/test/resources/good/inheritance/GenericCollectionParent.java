package inheritance;

import java.util.List;
import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface GenericCollectionParent<T> {

  List<T> foos();
}
