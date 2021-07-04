package inheritance;

import io.norberg.automatter.AutoMatter;

@AutoMatter
interface GenericFoobar<T> extends Foo, Bar<T>, Quux<T> {
  int baz();
}
