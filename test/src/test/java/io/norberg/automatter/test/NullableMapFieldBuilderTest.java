package io.norberg.automatter.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsMapContaining.hasEntry;

import com.google.common.collect.ImmutableMap;
import io.norberg.automatter.AutoMatter;
import java.util.Map;
import javax.annotation.Nullable;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class NullableMapFieldBuilderTest {

  public @Rule ExpectedException expectedException = ExpectedException.none();

  @AutoMatter
  interface NullableMap {
    @Nullable
    Map<String, Integer> prices();
  }

  NullableMapBuilder builder;

  @Before
  public void setUp() {
    builder = new NullableMapBuilder();
  }

  @Test
  public void testDefaults() {
    final NullableMap map = builder.build();
    assertThat(map.prices(), is(nullValue()));
  }

  @Test
  public void testAddingEntryInstantiatesMap() {
    builder.putPrice("red", 17);
    final NullableMap map = builder.build();
    assertThat(map.prices(), is((Map<String, Integer>) ImmutableMap.of("red", 17)));
  }

  @Test
  public void testAddingEntriesInstantiatesMap() {
    builder.prices(
        "red", 17,
        "green", 18);
    final NullableMap map = builder.build();
    assertThat(
        map.prices(),
        is(
            (Map<String, Integer>)
                ImmutableMap.of(
                    "red", 17,
                    "green", 18)));
  }

  @Test
  public void testAddingNullEntries() {
    builder.prices("red", 17, null, 1, null, 2, "blue", null, "green", 18);
    final NullableMap map = builder.build();
    assertThat(map.prices(), is(notNullValue()));
    Map<String, Integer> prices = map.prices();
    assert prices != null;
    assertThat(prices.size(), is(4));
    assertThat(prices, hasEntry("red", 17));
    assertThat(prices, hasEntry(null, 2));
    assertThat(prices, hasEntry("blue", null));
    assertThat(prices, hasEntry("green", 18));
  }

  @Test
  public void testSettingNull() {
    builder.prices("foo", 1, "bar", 2);
    builder.prices(null);
    final NullableMap map = builder.build();
    assertThat(map.prices(), is(nullValue()));
  }
}
