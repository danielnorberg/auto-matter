package foo;

import io.norberg.automatter.AutoMatter;
import com.google.common.base.Optional;

@AutoMatter
public interface OptionalFields {
  Optional<String> optionalString();
}