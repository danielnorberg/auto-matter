package io.norberg.automatter;

import com.google.common.collect.ImmutableList;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GenericsTest {

  @AutoMatter
  interface GenericFoobar<
      T1,
      T2 extends Collection<? extends T1>> {

    T1 field1();

    T2 field2();
  }

  @Test
  public void test_T1_Number_T2_ListOfInteger() throws Exception {
    final Number value1 = 17;
    final List<Integer> value2 = ImmutableList.of(1, 2, 3);

    final GenericFoobarBuilder<Number, List<Integer>> builder = new GenericFoobarBuilder<>();

    builder.field1(value1);
    builder.field2(value2);

    // Verify builder getters
    final Number builderField1 = builder.field1();
    final Object builderField1b = builder.field1();
    final List<Integer> builderField2 = builder.field2();
    final List<? extends Number> builderField2b = builder.field2();
    assertThat(builderField1, is(value1));
    assertThat(builderField1b, Matchers.<Object>is(value1));
    assertThat(builderField2, is(value2));
    assertThat(builderField2b, Matchers.<List<? extends Number>>is(value2));

    // Verify value getters
    final GenericFoobar<Number, List<Integer>> foobar = builder.build();
    final Number field1 = foobar.field1();
    final Object field1b = foobar.field1();
    final List<? extends Integer> field2 = foobar.field2();
    final List<? extends Number> field2b = foobar.field2();
    assertThat(field1, is(value1));
    assertThat(field1b, is((Object) value1));
    assertThat(field2, Matchers.<List<? extends Integer>>is(value2));
    assertThat(field2b, Matchers.<List<? extends Number>>is(value2));

    // Verify copy constructors
    final GenericFoobarBuilder<Number, List<Integer>> copied1 = GenericFoobarBuilder.from(builder);
    final GenericFoobarBuilder<Number, List<Integer>> copied2 = GenericFoobarBuilder.from(foobar);
    final GenericFoobarBuilder<Number, List<? extends Number>> copied3 =
        GenericFoobarBuilder.<Number, List<? extends Number>>from(builder);
    final GenericFoobarBuilder<Number, List<? extends Number>> copied4 =
        GenericFoobarBuilder.<Number, List<? extends Number>>from(foobar);
    final GenericFoobarBuilder<Object, List<? extends Serializable>> copied5 =
        GenericFoobarBuilder.<Object, List<? extends Serializable>>from(builder);
    final GenericFoobarBuilder<Object, List<? extends Serializable>> copied6 =
        GenericFoobarBuilder.<Object, List<? extends Serializable>>from(foobar);
  }

  @Test
  public void test_T1_Number_T2_ListOfIntegerSubclass() throws Exception {
    final Number value1 = 17;
    final List<? extends Integer> value2 = ImmutableList.of(1, 2, 3);

    final GenericFoobarBuilder<Number, List<? extends Integer>> builder = new GenericFoobarBuilder<>();

    builder.field1(value1);
    builder.field2(value2);

    // Verify builder getters
    final Number builderField1 = builder.field1();
    final Object builderField1b = builder.field1();
    final List<? extends Integer> builderField2 = builder.field2();
    final List<? extends Number> builderField2b = builder.field2();
    assertThat(builderField1, is(value1));
    assertThat(builderField1b, Matchers.<Object>is(value1));
    assertThat(builderField2, Matchers.<List<? extends Integer>>is(value2));
    assertThat(builderField2b, Matchers.<List<? extends Number>>is(value2));

    // Verify value getters
    final GenericFoobar<Number, List<? extends Integer>> foobar = builder.build();
    final Number field1 = foobar.field1();
    final Object field1b = foobar.field1();
    final List<? extends Integer> field2 = foobar.field2();
    final List<? extends Number> field2b = foobar.field2();
    assertThat(field1, is(value1));
    assertThat(field1b, is((Object) value1));
    assertThat(field2, Matchers.<List<? extends Integer>>is(value2));
    assertThat(field2b, Matchers.<List<? extends Number>>is(value2));

    // Verify that assigning a concretely typed list works
    final List<Integer> value2b = ImmutableList.of(5, 6, 7);
    builder.field2(value2b);
    assertThat(builder.field2(), Matchers.<List<? extends Integer>>is(value2b));
    assertThat(builder.build().field2(), Matchers.<List<? extends Integer>>is(value2b));

    // Verify copy constructors
    final GenericFoobarBuilder<Number, List<? extends Integer>> copied1 = GenericFoobarBuilder.from(builder);
    final GenericFoobarBuilder<Number, List<? extends Integer>> copied2 = GenericFoobarBuilder.from(foobar);
    final GenericFoobarBuilder<Number, List<? extends Number>> copied3 =
        GenericFoobarBuilder.<Number, List<? extends Number>>from(builder);
    final GenericFoobarBuilder<Number, List<? extends Number>> copied4 =
        GenericFoobarBuilder.<Number, List<? extends Number>>from(foobar);
    final GenericFoobarBuilder<Object, List<? extends Serializable>> copied5 =
        GenericFoobarBuilder.<Object, List<? extends Serializable>>from(builder);
    final GenericFoobarBuilder<Object, List<? extends Serializable>> copied6 =
        GenericFoobarBuilder.<Object, List<? extends Serializable>>from(foobar);
  }

  @Test
  public void test_T1_Number_T2_ListOfNumber() throws Exception {
    final Number value1 = 17;
    final List<Number> value2 = ImmutableList.<Number>of(1, 2, 3);

    final GenericFoobarBuilder<Number, List<Number>> builder = new GenericFoobarBuilder<>();

    builder.field1(value1);
    builder.field2(value2);

    // Verify builder getters
    final Number builderField1 = builder.field1();
    final Object builderField1b = builder.field1();
    final List<Number> builderField2 = builder.field2();
    final List<? extends Serializable> builderField2b = builder.field2();
    assertThat(builderField1, is(value1));
    assertThat(builderField1b, Matchers.<Object>is(value1));
    assertThat(builderField2, is(value2));
    assertThat(builderField2b, Matchers.<List<? extends Serializable>>is(value2));

    // Verify value getters
    final GenericFoobar<Number, List<Number>> foobar = builder.build();
    final Number field1 = foobar.field1();
    final Object field1b = foobar.field1();
    final List<Number> field2 = foobar.field2();
    final List<? extends Serializable> field2b = foobar.field2();
    assertThat(field1, is(value1));
    assertThat(field1b, is((Object) value1));
    assertThat(field2, is(value2));
    assertThat(field2b, Matchers.<List<? extends Serializable>>is(value2));

    // Verify copy constructors
    final GenericFoobarBuilder<Number, List<Number>> copied1 = GenericFoobarBuilder.from(builder);
    final GenericFoobarBuilder<Number, List<Number>> copied2 = GenericFoobarBuilder.from(foobar);
    final GenericFoobarBuilder<Number, List<? extends Number>> copied3 =
        GenericFoobarBuilder.<Number, List<? extends Number>>from(builder);
    final GenericFoobarBuilder<Number, List<? extends Number>> copied4 =
        GenericFoobarBuilder.<Number, List<? extends Number>>from(foobar);
    final GenericFoobarBuilder<Object, List<? extends Serializable>> copied5 =
        GenericFoobarBuilder.<Object, List<? extends Serializable>>from(builder);
    final GenericFoobarBuilder<Object, List<? extends Serializable>> copied6 =
        GenericFoobarBuilder.<Object, List<? extends Serializable>>from(foobar);

  }

  @Test
  public void test_T1_Number_T2_ListOfNumberSubclass() throws Exception {
    final Number value1 = 17;
    final List<? extends Number> value2 = ImmutableList.<Number>of(1, 2, 3);

    final GenericFoobarBuilder<Number, List<? extends Number>> builder = new GenericFoobarBuilder<>();

    builder.field1(value1);
    builder.field2(value2);

    // Verify builder getters
    final Number builderField1 = builder.field1();
    final Object builderField1b = builder.field1();
    final List<? extends Number> builderField2 = builder.field2();
    final List<? extends Serializable> builderField2b = builder.field2();
    assertThat(builderField1, is(value1));
    assertThat(builderField1b, is((Object) value1));
    assertThat(builderField2, Matchers.<List<? extends Number>>is((value2)));
    assertThat(builderField2b, Matchers.<List<? extends Serializable>>is((value2)));

    // Verify value getters
    final GenericFoobar<Number, List<? extends Number>> foobar = builder.build();
    final Number field1 = foobar.field1();
    final Object field1b = foobar.field1();
    final List<? extends Number> field2 = foobar.field2();
    final List<? extends Serializable> field2b = foobar.field2();
    assertThat(field1, is(value1));
    assertThat(field1b, is((Object) value1));
    assertThat(field2, Matchers.<List<? extends Number>>is(value2));
    assertThat(field2b, Matchers.<List<? extends Serializable>>is(value2));

    // Verify that assigning a list of a more specific type works
    final List<Integer> value2b = ImmutableList.of(5, 6, 7);
    builder.field2(value2b);
    assertThat(builder.field2(), Matchers.<List<? extends Number>>is(value2b));
    assertThat(builder.build().field2(), Matchers.<List<? extends Number>>is(value2b));

    // Verify copy constructors
    final GenericFoobarBuilder<Number, List<? extends Number>> copied1 = GenericFoobarBuilder.from(builder);
    final GenericFoobarBuilder<Number, List<? extends Number>> copied2 = GenericFoobarBuilder.from(foobar);
    final GenericFoobarBuilder<Object, List<? extends Serializable>> copied3 =
        GenericFoobarBuilder.<Object, List<? extends Serializable>>from(builder);
    final GenericFoobarBuilder<Object, List<? extends Serializable>> copied4 =
        GenericFoobarBuilder.<Object, List<? extends Serializable>>from(foobar);
    final GenericFoobarBuilder<Object, List<?>> copied5 = GenericFoobarBuilder.<Object, List<?>>from(builder);
    final GenericFoobarBuilder<Object, List<?>> copied6 = GenericFoobarBuilder.<Object, List<?>>from(foobar);
  }


}
