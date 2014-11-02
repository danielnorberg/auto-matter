package io.norberg.automatter;

import com.google.common.collect.ImmutableMap;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class MapFieldBuilderTest {

  public @Rule ExpectedException expectedException = ExpectedException.none();

  @AutoMatter
  interface Maps {
    Map<String, Integer> prices();
    Map<Integer, String> oxen();
    Map<Integer, String> serial();
  }

  MapsBuilder builder;

  @Before
  public void setUp() {
    builder = new MapsBuilder();
  }

  @Test
  public void testDefaults() {
    assertThat(builder.prices().isEmpty(), is(true));
    final Maps maps = builder.build();
    assertThat(maps.prices().isEmpty(), is(true));
  }

  @Test
  public void verifyBuilderMapIsMutable() {
    builder.price("apple", 17);
    final Map<String, Integer> prices = builder.prices();
    prices.remove("apple");
    prices.put("orange", 18);
    assertThat(builder.prices(), is(singletonMap("orange", 18)));
    final Maps maps = builder.build();
    assertThat(maps.prices(), is(singletonMap("orange", 18)));
  }

  @Test
  public void verifyMutatingBuilderMapDoesNotChangeValue() {
    final Maps maps1 = builder
        .prices("apple", 17)
        .build();
    builder.price("orange", 18);
    final Maps maps2 = builder.build();
    assertThat(maps1.prices(), is(singletonMap("apple", 17)));
    assertEquals(ImmutableMap.of("apple", 17, "orange", 18), maps2.prices());
  }

  @Test
  public void testPuttingMultipleEntries() {
    builder.prices("a", 1);
    assertEquals(ImmutableMap.of("a", 1), builder.prices());
    assertEquals(ImmutableMap.of("a", 1), builder.build().prices());

    builder.prices("a", 1, "b", 2);
    assertEquals(ImmutableMap.of("a", 1, "b", 2), builder.prices());
    assertEquals(ImmutableMap.of("a", 1, "b", 2), builder.build().prices());

    builder.prices("a", 1, "b", 2, "c", 3);
    assertEquals(ImmutableMap.of("a", 1, "b", 2, "c", 3), builder.prices());
    assertEquals(ImmutableMap.of("a", 1, "b", 2, "c", 3), builder.build().prices());

    builder.prices("a", 1, "b", 2, "c", 3, "d", 4);
    assertEquals(ImmutableMap.of("a", 1, "b", 2, "c", 3, "d", 4), builder.prices());
    assertEquals(ImmutableMap.of("a", 1, "b", 2, "c", 3, "d", 4), builder.build().prices());

    builder.prices("a", 1, "b", 2, "c", 3, "d", 4, "e", 5);
    assertEquals(ImmutableMap.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5), builder.prices());
    assertEquals(ImmutableMap.of("a", 1, "b", 2, "c", 3, "d", 4, "e", 5), builder.build().prices());
  }

  @Test
  public void verifyPuttingMultipleEntriesWithNullKeyThrowsNPE() {
    expectedException.expect(NullPointerException.class);
    expectedException.expectMessage("prices: k5");
    builder.prices("a", 1, "b", 2, "c", 3, "d", 4, null, 5);
  }

  @Test
  public void verifyPuttingMultipleEntriesWithNullValueThrowsNPE() {
    expectedException.expect(NullPointerException.class);
    expectedException.expectMessage("prices: v5");
    builder.prices("a", 1, "b", 2, "c", 3, "d", 4, "f", null);
  }

  @Test
  public void testPuttingAdditionalEntries() {
    builder.price("a", 1);
    assertEquals(ImmutableMap.of("a", 1), builder.prices());
    assertEquals(ImmutableMap.of("a", 1), builder.build().prices());

    builder.price("b", 2);
    assertEquals(ImmutableMap.of("a", 1, "b", 2), builder.prices());
    assertEquals(ImmutableMap.of("a", 1, "b", 2), builder.build().prices());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void verifyValueMapIsImmutable1() {
    final Maps maps = builder
        .price("apple", 17)
        .price("orange", 18)
        .build();
    maps.prices().remove("apple");
  }

  @Test(expected = UnsupportedOperationException.class)
  public void verifyValueListIsImmutable2() {
    final Maps maps = builder
        .price("apple", 17)
        .build();
    maps.prices().put("orange", 18);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void verifyValueListIsImmutable3() {
    final Maps maps = builder
        .price("apple", 17)
        .build();
    maps.prices().clear();
  }

  @Test
  public void testEnglishPlurals() {
    final Maps maps = builder.ox(17, "foo").ox(4711, "bar").build();
    assertEquals(ImmutableMap.of(17, "foo", 4711, "bar"), maps.oxen());
  }

  @Test
  public void testSingular() {
    final Maps maps = builder.serial(1, "a", 3, "b").build();
    assertEquals(ImmutableMap.of(1, "a", 3, "b"), maps.serial());
  }

  @Test
  public void verifyPuttingNullKeyThrowsNPE() {
    expectedException.expect(NullPointerException.class);
    expectedException.expectMessage("price: key");
    builder.price(null, 17);
  }

  @Test
  public void verifyPuttingNullValueThrowsNPE() {
    expectedException.expect(NullPointerException.class);
    expectedException.expectMessage("price: value");
    builder.price("apple", null);
  }

  @Test
  public void verifySettingNullMapThrowsNPE() {
    expectedException.expect(NullPointerException.class);
    expectedException.expectMessage("prices");
    builder.prices(null);
  }
}
