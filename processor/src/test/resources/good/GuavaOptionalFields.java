package foo;

import com.google.common.base.Optional;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface GuavaOptionalFields {
  Optional<String> foo();
  @Nullable Optional<String> bar();
}