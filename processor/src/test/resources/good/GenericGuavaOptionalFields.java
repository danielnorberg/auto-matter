package foo;

import com.google.common.base.Optional;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface GenericGuavaOptionalFields<T> {
  Optional<T> foo();
  @Nullable Optional<T> bar();
}