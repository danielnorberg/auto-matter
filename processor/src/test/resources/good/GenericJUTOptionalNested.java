package foo;

import java.util.List;
import java.util.Optional;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface GenericJUTOptionalNested<T> {
  Optional<List<T>> foo();
}