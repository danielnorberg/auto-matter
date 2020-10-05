package foo;

import java.util.Optional;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface CustomCheckInvariantStatic {
  String foo();

  @AutoMatter.CheckInvariant
  static void checkInvariant(CustomCheckInvariantStatic v) {
    throw new IllegalArgumentException();
  }
}
