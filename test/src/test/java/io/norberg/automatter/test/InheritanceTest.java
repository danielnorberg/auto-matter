package io.norberg.automatter.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import io.norberg.automatter.AutoMatter;
import io.norberg.automatter.other.InheritenceR1;
import java.util.List;
import org.junit.Test;

public class InheritanceTest {

  @AutoMatter
  interface InheritanceFoo {
    String foo();
  }

  @AutoMatter
  interface InheritanceBar<T> {
    T bar();
  }

  @AutoMatter
  interface InheritanceBaz<T> extends InheritanceFoo, InheritanceBar<T> {
    List<T> baz();
  }

  @AutoMatter
  interface InheritanceQuux<U> extends InheritanceBaz<U> {
    int quux();
  }

  @AutoMatter
  interface InheritanceCorge extends InheritanceQuux<Integer> {
    int corge();
  }

  @AutoMatter
  interface InheritenceR extends InheritenceR1.InheritenceR2 {
    String callR();
  }

  @Test
  public void builderFromSubValueTest() {
    final InheritanceCorge corge =
        new InheritanceCorgeBuilder().foo("1").bar(2).baz(3, 4, 5).quux(6).corge(7).build();

    final InheritanceQuux<Integer> quuxFromCorge = InheritanceQuuxBuilder.from(corge).build();
    assertThat(quuxFromCorge.quux(), is(corge.quux()));
    assertThat(quuxFromCorge.baz(), is(corge.baz()));
    assertThat(quuxFromCorge.bar(), is(corge.bar()));
    assertThat(quuxFromCorge.foo(), is(corge.foo()));

    final InheritanceBaz<Integer> bazFromCorge = InheritanceBazBuilder.from(corge).build();
    assertThat(bazFromCorge.baz(), is(corge.baz()));
    assertThat(bazFromCorge.bar(), is(corge.bar()));
    assertThat(bazFromCorge.foo(), is(corge.foo()));
    final InheritanceBaz<Integer> bazFromQuux = InheritanceBazBuilder.from(quuxFromCorge).build();
    assertThat(bazFromQuux.baz(), is(corge.baz()));
    assertThat(bazFromQuux.bar(), is(corge.bar()));
    assertThat(bazFromQuux.foo(), is(corge.foo()));

    final InheritanceBar<Integer> barFromBaz = InheritanceBarBuilder.from(bazFromQuux).build();
    assertThat(barFromBaz.bar(), is(corge.bar()));
    final InheritanceBar<Integer> barFromQuux = InheritanceBarBuilder.from(quuxFromCorge).build();
    assertThat(barFromQuux.bar(), is(corge.bar()));
    final InheritanceBar<Integer> barFromCorge = InheritanceBarBuilder.from(corge).build();
    assertThat(barFromCorge.bar(), is(corge.bar()));

    final InheritanceFoo fooFromBaz = InheritanceFooBuilder.from(bazFromQuux).build();
    assertThat(fooFromBaz.foo(), is(corge.foo()));
    final InheritanceFoo fooFromQuux = InheritanceFooBuilder.from(quuxFromCorge).build();
    assertThat(fooFromQuux.foo(), is(corge.foo()));
    final InheritanceFoo fooFromCorge = InheritanceFooBuilder.from(corge).build();
    assertThat(fooFromCorge.foo(), is(corge.foo()));
  }

  @Test
  public void builderFromSuperBuilderTest() {
    final InheritanceCorge corge =
        new InheritanceCorgeBuilder().foo("1").bar(2).baz(3, 4, 5).quux(6).corge(7).build();

    final InheritanceFooBuilder fooFromCorge = InheritanceFooBuilder.from(corge);
    final InheritanceBarBuilder<Integer> barFromCorge = InheritanceBarBuilder.from(corge);
    final InheritanceBazBuilder<Integer> bazFromCorge = InheritanceBazBuilder.from(corge);
    final InheritanceQuuxBuilder<Integer> quuxFromCorge = InheritanceQuuxBuilder.from(corge);

    final InheritanceCorgeBuilder corgeFromFoo = InheritanceCorgeBuilder.from(fooFromCorge);
    final InheritanceCorgeBuilder corgeFromBar = InheritanceCorgeBuilder.from(barFromCorge);
    final InheritanceCorgeBuilder corgeFromBaz = InheritanceCorgeBuilder.from(bazFromCorge);
    final InheritanceCorgeBuilder corgeFromQuux = InheritanceCorgeBuilder.from(quuxFromCorge);

    assertThat(corgeFromFoo.foo(), is(corge.foo()));
    assertThat(corgeFromFoo.bar(), is(nullValue()));
    assertThat(corgeFromFoo.baz(), is(empty()));
    assertThat(corgeFromFoo.quux(), is(0));

    assertThat(corgeFromBar.bar(), is(corge.bar()));
    assertThat(corgeFromBar.foo(), is(nullValue()));
    assertThat(corgeFromBar.baz(), is(empty()));
    assertThat(corgeFromBar.quux(), is(0));

    assertThat(corgeFromBaz.baz(), is(corge.baz()));
    assertThat(corgeFromBaz.foo(), is(corge.foo()));
    assertThat(corgeFromBaz.bar(), is(corge.bar()));
    assertThat(corgeFromBaz.quux(), is(0));

    assertThat(corgeFromQuux.foo(), is(corge.foo()));
    assertThat(corgeFromQuux.bar(), is(corge.bar()));
    assertThat(corgeFromQuux.baz(), is(corge.baz()));
    assertThat(corgeFromQuux.quux(), is(corge.quux()));

    final InheritanceQuuxBuilder<Integer> quuxFromBaz = InheritanceQuuxBuilder.from(bazFromCorge);
    final InheritanceQuuxBuilder<Integer> quuxFromBar = InheritanceQuuxBuilder.from(barFromCorge);
    final InheritanceQuuxBuilder<Integer> quuxFromFoo = InheritanceQuuxBuilder.from(fooFromCorge);

    assertThat(quuxFromBaz.foo(), is(corge.foo()));
    assertThat(quuxFromBaz.bar(), is(corge.bar()));
    assertThat(quuxFromBaz.baz(), is(corge.baz()));
    assertThat(quuxFromBaz.quux(), is(0));

    assertThat(quuxFromFoo.foo(), is(corge.foo()));
    assertThat(quuxFromFoo.bar(), is(nullValue()));
    assertThat(quuxFromFoo.baz(), is(empty()));
    assertThat(quuxFromFoo.quux(), is(0));

    assertThat(quuxFromBar.bar(), is(corge.bar()));
    assertThat(quuxFromBar.foo(), is(nullValue()));
    assertThat(quuxFromBar.baz(), is(empty()));
    assertThat(quuxFromBar.quux(), is(0));

    final InheritanceBazBuilder<Integer> bazFromFoo = InheritanceBazBuilder.from(fooFromCorge);
    final InheritanceBazBuilder<Integer> bazFromBar = InheritanceBazBuilder.from(barFromCorge);

    assertThat(bazFromFoo.foo(), is(corge.foo()));
    assertThat(bazFromFoo.bar(), is(nullValue()));
    assertThat(bazFromFoo.baz(), is(empty()));

    assertThat(bazFromBar.bar(), is(corge.bar()));
    assertThat(bazFromBar.foo(), is(nullValue()));
    assertThat(bazFromBar.baz(), is(empty()));
  }

  @Test
  public void builderFromSuperValueTest() {
    final InheritanceCorge corge =
        new InheritanceCorgeBuilder().foo("1").bar(2).baz(3, 4, 5).quux(6).corge(7).build();

    final InheritanceFoo fooFromCorge = InheritanceFooBuilder.from(corge).build();
    final InheritanceBar<Integer> barFromCorge = InheritanceBarBuilder.from(corge).build();
    final InheritanceBaz<Integer> bazFromCorge = InheritanceBazBuilder.from(corge).build();
    final InheritanceQuux<Integer> quuxFromCorge = InheritanceQuuxBuilder.from(corge).build();

    final InheritanceCorgeBuilder corgeFromFoo = InheritanceCorgeBuilder.from(fooFromCorge);
    final InheritanceCorgeBuilder corgeFromBar = InheritanceCorgeBuilder.from(barFromCorge);
    final InheritanceCorgeBuilder corgeFromBaz = InheritanceCorgeBuilder.from(bazFromCorge);
    final InheritanceCorgeBuilder corgeFromQuux = InheritanceCorgeBuilder.from(quuxFromCorge);

    assertThat(corgeFromFoo.foo(), is(corge.foo()));
    assertThat(corgeFromFoo.bar(), is(nullValue()));
    assertThat(corgeFromFoo.baz(), is(empty()));
    assertThat(corgeFromFoo.quux(), is(0));

    assertThat(corgeFromBar.bar(), is(corge.bar()));
    assertThat(corgeFromBar.foo(), is(nullValue()));
    assertThat(corgeFromBar.baz(), is(empty()));
    assertThat(corgeFromBar.quux(), is(0));

    assertThat(corgeFromBaz.baz(), is(corge.baz()));
    assertThat(corgeFromBaz.foo(), is(corge.foo()));
    assertThat(corgeFromBaz.bar(), is(corge.bar()));
    assertThat(corgeFromBaz.quux(), is(0));

    assertThat(corgeFromQuux.foo(), is(corge.foo()));
    assertThat(corgeFromQuux.bar(), is(corge.bar()));
    assertThat(corgeFromQuux.baz(), is(corge.baz()));
    assertThat(corgeFromQuux.quux(), is(corge.quux()));

    final InheritanceQuuxBuilder<Integer> quuxFromBaz = InheritanceQuuxBuilder.from(bazFromCorge);
    final InheritanceQuuxBuilder<Integer> quuxFromBar = InheritanceQuuxBuilder.from(barFromCorge);
    final InheritanceQuuxBuilder<Integer> quuxFromFoo = InheritanceQuuxBuilder.from(fooFromCorge);

    assertThat(quuxFromBaz.foo(), is(corge.foo()));
    assertThat(quuxFromBaz.bar(), is(corge.bar()));
    assertThat(quuxFromBaz.baz(), is(corge.baz()));
    assertThat(quuxFromBaz.quux(), is(0));

    assertThat(quuxFromFoo.foo(), is(corge.foo()));
    assertThat(quuxFromFoo.bar(), is(nullValue()));
    assertThat(quuxFromFoo.baz(), is(empty()));
    assertThat(quuxFromFoo.quux(), is(0));

    assertThat(quuxFromBar.bar(), is(corge.bar()));
    assertThat(quuxFromBar.foo(), is(nullValue()));
    assertThat(quuxFromBar.baz(), is(empty()));
    assertThat(quuxFromBar.quux(), is(0));

    final InheritanceBazBuilder<Integer> bazFromFoo = InheritanceBazBuilder.from(fooFromCorge);
    final InheritanceBazBuilder<Integer> bazFromBar = InheritanceBazBuilder.from(barFromCorge);

    assertThat(bazFromFoo.foo(), is(corge.foo()));
    assertThat(bazFromFoo.bar(), is(nullValue()));
    assertThat(bazFromFoo.baz(), is(empty()));

    assertThat(bazFromBar.bar(), is(corge.bar()));
    assertThat(bazFromBar.foo(), is(nullValue()));
    assertThat(bazFromBar.baz(), is(empty()));
  }
}
