package io.norberg.automatter;

import com.google.common.base.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GuavaOptionalTest {

  public @Rule ExpectedException expectedException = ExpectedException.none();

  @AutoMatter
  interface GuavaOptionals {

    Optional<String> foo();
    Optional<String> bar();
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
    assertThat(foobar.bar(), is(Optional.<String>absent()));
  }

  @Test
  public void verifySettingNullGivesAbsent() {
    builder.foo((String)null);
    final GuavaOptionals foobar = builder.build();
    assertThat(foobar.foo(), is(Optional.<String>absent()));
  }

  @Test
  public void verifySettingValueGivesPresent() {
    builder.foo("bar");
    final GuavaOptionals foobar = builder.build();
    assertThat(foobar.foo(), is(Optional.of("bar")));
  }
}
