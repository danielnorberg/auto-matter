package foo.jspecify;

import io.norberg.automatter.AutoMatter;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
@AutoMatter
public interface JSpecifyNullMarkedFields {
  @Nullable String nullableFoo();
  String nonNullBar();
  int primitiveValue();
}
