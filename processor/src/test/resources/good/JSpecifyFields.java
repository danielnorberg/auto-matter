package foo;

import io.norberg.automatter.AutoMatter;
import org.jspecify.annotations.Nullable;

@AutoMatter
public interface JSpecifyFields {
  @Nullable String nullableFoo();
  String nonNullBar();
  int primitiveValue();
}
