package generic;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface Generic<T> {
  T thing();

//  GenericBuilder<T> builder();
}