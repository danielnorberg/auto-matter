package io.norberg.automatter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.annotation.Nullable;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

public class BuilderTest {

  public @Rule ExpectedException expectedException = ExpectedException.none();

  @AutoMatter
  interface Foobar {
    int foo();
    String bar();
    @Nullable String quux();

    FoobarBuilder builder();
  }

  FoobarBuilder builder;

  @Before
  public void setup() {
    builder = new FoobarBuilder();
  }

  @Test
  public void testDefaults() {
    builder.bar("bar");
    final Foobar foobar = builder.build();
    assertThat(foobar.foo(), is(0));
    assertThat(foobar.bar(), is("bar"));
    assertThat(foobar.quux(), is(nullValue()));

    // Ensure that builders are not instances of the interface, avoiding bugs
    assertThat(builder, is(not(instanceOf(Foobar.class))));
  }

  @Test
  public void verifyBuildingWithNullDefaultsNPEs() {
    expectedException.expect(NullPointerException.class);
    expectedException.expectMessage("bar");
    builder.build();
  }

  @Test
  public void verifySettingNullNPEs() {
    expectedException.expect(NullPointerException.class);
    expectedException.expectMessage("bar");
    builder.bar(null);
  }

  @Test
  public void testAccessors() {
    assertThat(builder.foo(), is(0));
    assertThat(builder.bar(), is(nullValue()));
    assertThat(builder.quux(), is(nullValue()));

    builder.foo(17);
    builder.bar("hello");
    builder.quux("world");

    assertThat(builder.foo(), is(17));
    assertThat(builder.bar(), is("hello"));
    assertThat(builder.quux(), is("world"));
  }

  @Test
  public void testBuild() {
    builder.foo(17);
    builder.bar("hello");
    builder.quux("world");
    final Foobar foobar = builder.build();
    assertThat(foobar.foo(), is(17));
    assertThat(foobar.bar(), is("hello"));
    assertThat(foobar.quux(), is("world"));
  }

  @Test
  public void testCopyBuilderWithDefaults() {
    FoobarBuilder.from(builder);
  }

  @Test
  public void testCopyBuilder() {
    builder.foo(17);
    builder.bar("hello");
    builder.quux("world");

    // Verify that a new builder can be created from an existing builder
    final FoobarBuilder copy = FoobarBuilder.from(builder);
    assertThat(copy.foo(), is(17));
    assertThat(copy.bar(), is("hello"));
    assertThat(copy.quux(), is("world"));

    // Verify that mutating the copy does not change the original
    copy.foo(18);
    copy.bar("hello2");
    copy.quux("world2");
    assertThat(builder.foo(), is(17));
    assertThat(builder.bar(), is("hello"));
    assertThat(builder.quux(), is("world"));

    // Verify that a value can be built using the copy
    final Foobar foobarCopy = copy.build();
    assertThat(foobarCopy.foo(), is(18));
    assertThat(foobarCopy.bar(), is("hello2"));
    assertThat(foobarCopy.quux(), is("world2"));
  }

  @Test
  public void testBuilderFromValue() {
    builder.foo(17);
    builder.bar("hello");
    builder.quux("world");
    final Foobar foobar = builder.build();

    // Verify that a new builder can be created from a value
    final FoobarBuilder copy = FoobarBuilder.from(foobar);
    assertThat(copy.foo(), is(17));
    assertThat(copy.bar(), is("hello"));
    assertThat(copy.quux(), is("world"));

    // Verify that mutating the builder does not change the value
    copy.foo(18);
    copy.bar("hello2");
    copy.quux("world2");
    assertThat(foobar.foo(), is(17));
    assertThat(foobar.bar(), is("hello"));
    assertThat(foobar.quux(), is("world"));

    // Verify that a new value can be built
    final Foobar foobarCopy = copy.build();
    assertThat(foobarCopy.foo(), is(18));
    assertThat(foobarCopy.bar(), is("hello2"));
    assertThat(foobarCopy.quux(), is("world2"));
  }


  @Test
  public void testValueToBuilder() {
    builder.foo(17);
    builder.bar("hello");
    builder.quux("world");
    final Foobar foobar = builder.build();

    // Verify that a new builder can be created from a value
    final FoobarBuilder copy = foobar.builder();
    assertThat(copy.foo(), is(17));
    assertThat(copy.bar(), is("hello"));

    // Verify that mutating the builder does not change the value
    copy.foo(18);
    copy.bar("hello2");
    copy.quux("world2");
    assertThat(foobar.foo(), is(17));
    assertThat(foobar.bar(), is("hello"));
    assertThat(foobar.quux(), is("world"));

    // Verify that a new value can be built
    final Foobar foobarCopy = copy.build();
    assertThat(foobarCopy.foo(), is(18));
    assertThat(foobarCopy.bar(), is("hello2"));
    assertThat(foobarCopy.quux(), is("world2"));
  }

  @Test
  public void testBuildingMultipleIdenticalValues() {
    builder.foo(17);
    builder.bar("hello");
    builder.quux("world");

    final Foobar foobar1 = builder.build();
    final Foobar foobar2 = builder.build();

    assertThat(foobar1, is(foobar2));
    assertThat(foobar1.hashCode(), is(foobar2.hashCode()));
    assertThat(foobar1.foo(), is(foobar2.foo()));
    assertThat(foobar1.bar(), is(foobar2.bar()));
  }

  @Test
  public void testBuildingMultipleDifferentValues() {
    builder.foo(17);
    builder.bar("hello");
    builder.quux("world");
    final Foobar foobar1 = builder.build();

    builder.foo(18);
    builder.bar("hello2");
    builder.quux("world2");
    final Foobar foobar2 = builder.build();

    assertThat(foobar1, is(not(foobar2)));
    assertThat(foobar2, is(not(foobar1)));

    assertThat(foobar1.hashCode(), is(not(foobar2.hashCode())));
    assertThat(foobar2.hashCode(), is(not(foobar1.hashCode())));

    assertThat(foobar1.foo(), is(17));
    assertThat(foobar1.bar(), is("hello"));
    assertThat(foobar1.quux(), is("world"));

    assertThat(foobar2.foo(), is(18));
    assertThat(foobar2.bar(), is("hello2"));
    assertThat(foobar2.quux(), is("world2"));
  }
}
