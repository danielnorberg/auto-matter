package foo;

import io.norberg.automatter.AutoMatter;
import java.util.List;

@AutoMatter
public interface ReifiableCollectionTypes<T> {
  // reifiable
  List<String> r1();
  List<Foo<?>> r2();
  List<Foo> r3();
  List<Foo<?>.Bar> r4();

  List<String[]> ar1();
  List<Foo<?>[]> ar2();
  List<Foo[]> ar3();
  List<Foo<?>.Bar[]> ar4();

  // non-reifiable
  List<Foo<String>.Bar> nr1();
  List<Foo<T>> nr2();
  List<T> nr3();

  List<Foo<String>.Bar[]> anr1();
  List<Foo<String>[]> anr2();

  // TODO: produces compilation errors in generated code
  //  List<Foo<T>[]> anr3();
  //  List<Foo<T>.Bar> nr4();
  //  List<Foo<T>.Bar[]> anr4();
}

class Foo<T> {
  class Bar {

  }
}

