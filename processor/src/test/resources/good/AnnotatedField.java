package foo;

import javax.annotation.Resource;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface AnnotatedField {

  @Resource(name="private")
  boolean isPrivate();
}