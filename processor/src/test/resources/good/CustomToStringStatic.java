package foo;

import java.util.Optional;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface CustomToStringStatic {
  String foo();

  @AutoMatter.ToString
  static String toString(CustomToStringStatic v) {
    return v.foo();
  }
}