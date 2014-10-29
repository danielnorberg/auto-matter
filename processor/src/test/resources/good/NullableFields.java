package foo;

import com.google.common.base.Optional;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface NullableFields {
  @javax.annotation.Nullable String nullableFoo();
  @Nullable(simple = "foo", complex = java.util.Date.class) String customNullableBar();
  String nonNullQuux();
  @Nullable(complex = String.class) int nonNullPrimitive();
}