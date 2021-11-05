package io.norberg.automatter.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.google.common.base.Optional;
import io.norberg.automatter.AutoMatter;
import org.junit.Test;

public class GenericGuavaOptionalTest {

  @AutoMatter
  interface GenericGuavaOptionalFoobar<T> {

    Optional<T> foo();

    GenericGuavaOptionalFoobarBuilder<T> builder();
  }

  @Test
  public void testExactClass() throws Exception {
    final GenericGuavaOptionalFoobarBuilder<String> builder =
        new GenericGuavaOptionalFoobarBuilder<>();
    assertThat(builder.build().foo(), is(Optional.<String>absent()));
    assertThat(builder.foo(Optional.of("hello")).build().foo(), is(Optional.of("hello")));
    assertThat(builder.foo("hello").build().foo(), is(Optional.of("hello")));
  }

  @Test
  public void testSuperClass() throws Exception {
    final GenericGuavaOptionalFoobarBuilder<CharSequence> builder =
        new GenericGuavaOptionalFoobarBuilder<>();
    assertThat(builder.build().foo(), is(Optional.<CharSequence>absent()));
    assertThat(
        builder.foo(Optional.of("hello")).build().foo(), is(Optional.<CharSequence>of("hello")));
    assertThat(builder.foo("hello").build().foo(), is(Optional.<CharSequence>of("hello")));
  }

  @Test
  public void testCopyPresent() throws Exception {
    final GenericGuavaOptionalFoobarBuilder<CharSequence> builder =
        new GenericGuavaOptionalFoobarBuilder<>();
    builder.foo("hello");
    assertThat(builder.builder().build(), is(builder.build()));
    assertThat(GenericGuavaOptionalFoobarBuilder.from(builder).build(), is(builder.build()));
    assertThat(
        GenericGuavaOptionalFoobarBuilder.from(builder.build()).build(), is(builder.build()));
  }

  @Test
  public void testCopyAbsent() throws Exception {
    final GenericGuavaOptionalFoobarBuilder<CharSequence> builder =
        new GenericGuavaOptionalFoobarBuilder<>();
    assertThat(builder.builder().build(), is(builder.build()));
    assertThat(GenericGuavaOptionalFoobarBuilder.from(builder).build(), is(builder.build()));
    assertThat(
        GenericGuavaOptionalFoobarBuilder.from(builder.build()).build(), is(builder.build()));
  }
}
