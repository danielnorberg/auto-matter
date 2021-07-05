package io.norberg.automatter.gson;

import com.google.gson.annotations.SerializedName;
import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface Bar {
  int a();

  String b();

  @SerializedName("private")
  boolean isPrivate();
}
