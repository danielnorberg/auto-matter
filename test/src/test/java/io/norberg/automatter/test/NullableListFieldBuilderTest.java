package io.norberg.automatter.test;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import io.norberg.automatter.AutoMatter;
import java.util.List;
import javax.annotation.Nullable;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class NullableListFieldBuilderTest {

  public @Rule ExpectedException expectedException = ExpectedException.none();

  @AutoMatter
  interface NullableLists {
    @Nullable
    List<String> apples();
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
    builder.addApple("red");
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
    builder.apples((List<String>) null);
    final NullableLists lists = builder.build();
    assertThat(lists.apples(), is(nullValue()));
  }

  @Test
  public void testAddingNull() {
    builder.addApple(null);
    final NullableLists lists = builder.build();
    List<String> apples = lists.apples();
    assertThat(apples, is(notNullValue()));
    assert apples != null;
    assertThat(apples.size(), is(1));
    assertThat(apples.get(0), is(nullValue()));
  }

  @Test
  public void testBuilderCopyingWithNullList() {
    final NullableListsBuilder copiedBuilder = new NullableListsBuilder().from(builder);
    final NullableLists listsFromCopied = copiedBuilder.build();
    assertThat(listsFromCopied.apples(), is(nullValue()));
  }
}
