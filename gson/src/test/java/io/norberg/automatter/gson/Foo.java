package io.norberg.automatter.gson;

import com.google.gson.annotations.SerializedName;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface Foo {
  int a();
  String b();
  @SerializedName("private") Boolean isPrivate();
}
