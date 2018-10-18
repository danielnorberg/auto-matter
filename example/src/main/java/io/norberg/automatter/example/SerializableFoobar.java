package io.norberg.automatter.example;

import io.norberg.automatter.AutoMatter;
import java.io.Serializable;

@AutoMatter
@AutoMatter.SerialVersionUID(1L)
public interface SerializableFoobar extends Serializable {

  default long serialVersionUID() { return 1L; }

  String foo();
  int bar();

  FoobarBuilder builder();
}
