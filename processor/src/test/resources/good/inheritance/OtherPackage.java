package other;

import io.norberg.automatter.AutoMatter;

public interface OtherPackage {

  @AutoMatter
  interface OtherPackageNestedInterface {
    String foo();
  }
}
