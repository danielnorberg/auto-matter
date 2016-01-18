package inheritance;

import io.norberg.automatter.AutoMatter;

@AutoMatter
interface Foobar extends Foo, Bar<Integer> {
  int baz();
}
