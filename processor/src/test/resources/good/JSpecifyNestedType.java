package foo;

import io.norberg.automatter.AutoMatter;
import org.jspecify.annotations.Nullable;

@AutoMatter
public interface JSpecifyNestedType {

  @Nullable Status status();

  enum Status {
    SUCCESS,
    FAILURE
  }
}
