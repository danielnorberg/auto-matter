package inheritance;

import io.norberg.automatter.AutoMatter;

@AutoMatter
interface DefaultMethodOverrideSubType extends DefaultMethodOverrideSuperType {

  @Override
  default String overrideMe() {
    return "overridden hardcoded value";
  }
}
