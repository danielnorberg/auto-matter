package inheritance;

import io.norberg.automatter.AutoMatter;
import java.util.List;

@AutoMatter
public interface ConcreteExtensionOfGenericParent extends GenericCollectionParent<Integer> {

  List<Integer> foos();
}
