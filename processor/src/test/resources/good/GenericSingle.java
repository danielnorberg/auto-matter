package generic_single;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface GenericSingle<T> {
  T thing();

  GenericSingleBuilder<T> builder();
}