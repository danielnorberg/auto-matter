package io.norberg.automatter.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import io.norberg.automatter.AutoMatter;
import java.util.List;
import javax.annotation.Nullable;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class GuavaOptionalTest {

  public @Rule ExpectedException expectedException = ExpectedException.none();

  @AutoMatter
  interface GuavaOptionals {

    Optional<String> foo();

    Optional<List<String>> bar();

    @Nullable
    Optional<String> baz();
  }

  GuavaOptionalsBuilder builder;

  @Before
  public void setup() {
    builder = new GuavaOptionalsBuilder();
  }

  @Test
  public void testDefaults() {
    final GuavaOptionals foobar = builder.build();
    assertThat(foobar.foo(), is(Optional.<String>absent()));
    assertThat(foobar.bar(), is(Optional.<List<String>>absent()));
    assertThat(foobar.baz(), is(nullValue()));
  }

  @Test
  public void verifySettingNullGivesAbsent() {
    builder.foo((String) null);
    final GuavaOptionals foobar = builder.build();
    assertThat(foobar.foo(), is(Optional.<String>absent()));
  }

  @Test
  public void verifySettingValueGivesPresent() {
    builder.foo("bar");
    final GuavaOptionals foobar = builder.build();
    assertThat(foobar.foo(), is(Optional.of("bar")));
  }

  @Test
  public void verifySettingNonNullableNullNPEs() {
    expectedException.expect(NullPointerException.class);
    expectedException.expectMessage("foo");
    builder.foo((Optional<String>) null);
  }

  @Test
  public void verifySettingNullableNull() {
    builder.baz("hello");
    builder.baz((Optional<String>) null);
    final GuavaOptionals foobar = builder.build();
    assertThat(foobar.baz(), is(nullValue()));
  }

  @Test
  public void verifySettingExtendingValue() {
    builder.bar(Optional.fromNullable(ImmutableList.of("foo", "bar")));
  }
}
