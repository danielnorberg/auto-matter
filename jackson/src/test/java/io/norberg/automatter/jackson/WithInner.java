package io.norberg.automatter.jackson;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.norberg.automatter.AutoMatter;

public final class WithInner {
  @AutoMatter
  interface Bar {
    int a();

    String b();

    boolean aCamelCaseField();

    @JsonProperty("foobar")
    boolean isReallyFoobar();
  }

  @AutoMatter
  public interface PublicBar {
    int a();

    String b();

    boolean aCamelCaseField();

    @JsonProperty("foobar")
    boolean isReallyFoobar();
  }
}
