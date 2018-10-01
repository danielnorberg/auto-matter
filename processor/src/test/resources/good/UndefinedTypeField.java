package foo;

import io.norberg.automatter.AutoMatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import generated.later.Foo;

@AutoMatter
public interface UndefinedTypeField {
  Foo foo();
  Foo.Bar bar();
  Optional<Foo> maybeFoo();
  List<Foo.Bar> bars();
  Map<Foo, Foo.Bar> foobars();
}
