package inheritance;

import io.norberg.automatter.AutoMatter;

@AutoMatter
interface InheritingPackage extends other.OtherPackage.OtherPackageNestedInterface {
  String bar();
}
