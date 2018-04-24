package foo;

import io.norberg.automatter.AutoMatter;
import java.util.Collection;

@AutoMatter
public interface CollectionInterfaceField {
  Collection<String> strings();
}