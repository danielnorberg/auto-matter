package inheritance;

import java.util.List;
import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface GenericSuperParent<T> {
  List<T> foos();
  Bar<T> oneParameterizedBar();
  Bar<Integer> oneIntegerBar();
  Bar<Quux<T>> oneParameterizedQuuxBar();
  Bar<Quux<Integer>> oneIntegerQuuxBar();
  Quux<T> oneParameterizedQuux();
}
