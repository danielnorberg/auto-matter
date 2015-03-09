package io.norberg.automatter;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ListFieldBuilderTest {

  public @Rule ExpectedException expectedException = ExpectedException.none();

  @AutoMatter
  interface Lists {
    List<String> apples();
    List<Integer> oxen();
    List<Integer> serial();
    List<Map<String, Integer>> maps();
  }

  ListsBuilder builder;

  @Before
  public void setUp() {
    builder = new ListsBuilder();
  }

  @Test
  public void testDefaults() {
    assertThat(builder.apples(), is(emptyCollectionOf(String.class)));
    final Lists lists = builder.build();
    assertThat(lists.apples(), is(emptyCollectionOf(String.class)));
  }

  @Test
  public void verifyBuilderListIsMutable() {
    builder.appendApple("red");
    final List<String> apples = builder.apples();
    apples.remove("red");
    apples.add("green");
    assertThat(builder.apples(), is(asList("green")));
    final Lists lists = builder.build();
    assertThat(lists.apples(), is(asList("green")));
  }

  @Test
  public void verifyMutatingBuilderListDoesNotChangeValue() {
    final Lists lists1 = builder
        .apples("red")
        .build();
    builder.appendApple("green");
    final Lists lists2 = builder.build();
    assertThat(lists1.apples(), is(asList("red")));
    assertThat(lists2.apples(), is(asList("red", "green")));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void verifyValueListIsImmutable1() {
    final Lists lists = builder
        .appendApple("red").appendApple("green")
        .build();
    lists.apples().remove("red");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void verifyValueListIsImmutable2() {
    final Lists lists = builder
        .appendApple("red").appendApple("green")
        .build();
    lists.apples().add("blue");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void verifyValueListIsImmutable3() {
    final Lists lists = builder
        .appendApple("red").appendApple("green")
        .build();
    lists.apples().clear();
  }

  @Test
  public void testEnglishPlurals() {
    final Lists lists = builder.appendOx(17).appendOx(4711).build();
    assertThat(lists.oxen(), is(asList(17, 4711)));
  }

  @Test
  public void testSingular() {
    final Lists lists = builder.serial(1, 2, 3, 4).build();
    assertThat(lists.serial(), is(asList(1, 2, 3, 4)));
  }

  @Test
  public void verifyAddingNullThrowsNPE() {
    expectedException.expect(NullPointerException.class);
    expectedException.expectMessage("apple");
    builder.appendApple(null);
  }

  @Test
  public void verifySettingNullIterableThrowsNPE() {
    expectedException.expect(NullPointerException.class);
    expectedException.expectMessage("apples");
    builder.apples((Iterable<String>)null);
  }

  @Test
  public void verifySettingNullArrayThrowsNPE() {
    expectedException.expect(NullPointerException.class);
    expectedException.expectMessage("apples");
    builder.apples((String[])null);
  }

  @Test
  public void verifySettingExtendingValue() {
    builder.maps(ImmutableList.of(ImmutableMap.of("foo", 17)));
  }

  @Test
  public void testListMethodsReplaceOldValue() {
    final Lists list = builder
        .apples("red", "green")
        .apples("green")
        .build();

    assertThat(list.apples(), is(asList("green")));

    final Lists listWithRed = ListsBuilder.from(list)
        .apples("red")
        .build();
    assertThat(listWithRed.apples(), is(asList("red")));
  }
}
