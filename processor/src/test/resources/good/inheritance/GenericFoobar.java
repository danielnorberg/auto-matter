package inheritance;

import io.norberg.automatter.AutoMatter;

@AutoMatter
interface GenericFoobar<T> extends Foo, Bar<T> {
  int baz();
}
