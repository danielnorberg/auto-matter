package io.norberg.automatter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import javax.annotation.Nullable;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class NullableListFieldBuilderTest {

  public @Rule ExpectedException expectedException = ExpectedException.none();

  @AutoMatter
  interface NullableLists {
    @Nullable List<String> apples();
  }

  NullableListsBuilder builder;

  @Before
  public void setUp() {
    builder = new NullableListsBuilder();
  }

  @Test
  public void testDefaults() {
    final NullableLists lists = builder.build();
    assertThat(lists.apples(), is(nullValue()));
  }

  @Test
  public void testAddingItemInstantiatesList() {
    builder.apple("red");
    final NullableLists lists = builder.build();
    assertThat(lists.apples(), is(asList("red")));
  }

  @Test
  public void testAddingItemsInstantiatesList() {
    builder.apples("red", "green");
    final NullableLists lists = builder.build();
    assertThat(lists.apples(), is(asList("red", "green")));
  }

  @Test
  public void testAddingNullItems() {
    builder.apples("red", null, "green", null);
    final NullableLists lists = builder.build();
    assertThat(lists.apples(), is(asList("red", null, "green", null)));
  }

  @Test
  public void testSettingNull() {
    builder.apples("foo", "bar");
    builder.apples((List<String>)null);
    final NullableLists lists = builder.build();
    assertThat(lists.apples(), is(nullValue()));
  }

  @Test
  public void testAddingNull() {
    builder.apple(null);
    final NullableLists lists = builder.build();
    List<String> apples = lists.apples();
    assertThat(apples, is(notNullValue()));
    assert apples != null;
    assertThat(apples.size(), is(1));
    assertThat(apples.get(0), is(nullValue()));
  }
}
