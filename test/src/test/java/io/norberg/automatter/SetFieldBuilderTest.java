package io.norberg.automatter;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.emptyCollectionOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SetFieldBuilderTest {

  public @Rule ExpectedException expectedException = ExpectedException.none();

  @AutoMatter
  interface Sets {
    Set<String> apples();

    Set<Integer> oxen();

    Set<Integer> serial();
  }

  SetsBuilder builder;

  @Before
  public void setUp() {
    builder = new SetsBuilder();
  }

  @Test
  public void testDefaults() {
    assertThat(builder.apples(), is(emptyCollectionOf(String.class)));
    final Sets lists = builder.build();
    assertThat(lists.apples(), is(emptyCollectionOf(String.class)));
  }

  @Test
  public void verifyBuilderSetIsMutable() {
    builder.addApple("red");
    final Set<String> apples = builder.apples();
    apples.remove("red");
    apples.add("green");
    assertThat(builder.apples(), is(set("green")));
    final Sets lists = builder.build();
    assertThat(lists.apples(), is(set("green")));
  }

  @Test
  public void verifyMutatingBuilderSetDoesNotChangeValue() {
    final Sets lists1 = builder.apples("red").build();
    builder.addApple("green");
    final Sets lists2 = builder.build();
    assertThat(lists1.apples(), is(set("red")));
    assertThat(lists2.apples(), is(set("red", "green")));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void verifyValueSetIsImmutable1() {
    final Sets lists = builder.addApple("red").addApple("green").build();
    lists.apples().remove("red");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void verifyValueSetIsImmutable2() {
    final Sets lists = builder.addApple("red").addApple("green").build();
    lists.apples().add("blue");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void verifyValueSetIsImmutable3() {
    final Sets lists = builder.addApple("red").addApple("green").build();
    lists.apples().clear();
  }

  @Test
  public void testEnglishPlurals() {
    final Sets lists = builder.addOx(17).addOx(4711).build();
    assertThat(lists.oxen(), is(set(17, 4711)));
  }

  @Test
  public void testSingular() {
    final Sets lists = builder.serial(1, 2, 3, 4).build();
    assertThat(lists.serial(), is(set(1, 2, 3, 4)));
  }

  @Test
  public void verifyAddingNullThrowsNPE() {
    expectedException.expect(NullPointerException.class);
    expectedException.expectMessage("apple");
    builder.addApple(null);
  }

  @Test
  public void verifySettingNullIterableThrowsNPE() {
    expectedException.expect(NullPointerException.class);
    expectedException.expectMessage("apples");
    builder.apples((Iterable<String>) null);
  }

  @Test
  public void verifySettingNullArrayThrowsNPE() {
    expectedException.expect(NullPointerException.class);
    expectedException.expectMessage("apples");
    builder.apples((String[]) null);
  }

  @SafeVarargs
  @SuppressWarnings("varargs")
  private final <T> Set<T> set(final T... items) {
    return new HashSet<>(asList(items));
  }
}
