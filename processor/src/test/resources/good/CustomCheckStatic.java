package foo;

import java.util.Optional;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface CustomCheckStatic {
  String foo();

  @AutoMatter.Check
  static void check(CustomCheckStatic v) {
    throw new IllegalArgumentException();
  }
}
