package io.norberg.automatter.other;

import io.norberg.automatter.AutoMatter;

public interface InheritenceR1 {

  @AutoMatter
  interface InheritenceR2 {
    String callR2();

    @AutoMatter
    interface InheritenceR3 {
      String callR3();
    }
  }
}
