package generic_multiple;

import java.util.ArrayList;
import java.util.Collection;

import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface GenericMultiple<
    T1,
    T2 extends Number,
    T3 extends Collection<T2>,
    T4 extends Iterable<T2> & Comparable<T4>> {

  T1 field1();
  T2 field2();
  T3 field3();
  T4 field4();

  String plain();

  GenericMultipleBuilder<T1, T2, T3, T4> builder();
}