package foo;

import java.util.Optional;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface DefaultValueField {
  @AutoMatter.Field
  default String bar() {
    return "bar";
  }

  @AutoMatter.Field
  default String foo() {
    return bar();
  }
}