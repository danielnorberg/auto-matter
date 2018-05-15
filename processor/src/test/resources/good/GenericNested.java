package foo;

import io.norberg.automatter.AutoMatter;
import java.util.Map;

@AutoMatter
public interface GenericNested<Q, W> {

  Baz<Baz<Q>> baz2();

  Baz<Baz<Baz<Q>>> baz3();

  Map<Q, Baz<W>> mapBaz();

  Quux<Q, Baz<W>> quuxBaz();

  Quux<Quux<String, Integer>, Quux<W, Baz<Q>>> quuxQuuxBaz();

  Baz<Baz<Q>> bazBaz();

  interface Baz<T> {
    T t();
  }

  interface Quux<T, Y> {
    T t();
    Y y();
  }

  GenericNestedBuilder<Q, W> builder();
}
