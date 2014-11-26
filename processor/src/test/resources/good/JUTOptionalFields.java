package foo;

import java.util.Optional;

import java.util.List;
import java.util.Map;
import java.util.Set;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface JUTOptionalFields {
  Optional<String> foo();
}