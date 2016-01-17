package io.norberg.automatter;

import com.google.common.collect.ImmutableMap;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class GenericCollectionsTest {

  @AutoMatter
  interface GenericCollectionFoobar<T, K, V> {

    List<T> foos();

    Map<K, V> bars();
  }

  @Test
  public void testList() throws Exception {
    final GenericCollectionFoobarBuilder<Number, CharSequence, CharSequence> builder =
        new GenericCollectionFoobarBuilder<>();

    final List<Integer> integers = Arrays.<Integer>asList(1, 2, 3);
    final List<Number> numbers = new ArrayList<Number>(integers);

    assertThat(builder.foos(integers).build().foos(), is(numbers));
    assertThat(builder.foos(numbers).build().foos(), is(numbers));
  }

  @Test
  public void testMap() throws Exception {
    final GenericCollectionFoobarBuilder<Number, CharSequence, CharSequence> builder =
        new GenericCollectionFoobarBuilder<>();

    final Map<String, String> stringToString = ImmutableMap.of("k1", "v1", "k2", "v2");
    final Map<CharSequence, CharSequence> charSequenceToCharSequence = ImmutableMap.<CharSequence, CharSequence>copyOf(stringToString);
    final Map<String, CharSequence> stringToCharSequence = ImmutableMap.<String, CharSequence>copyOf(stringToString);
    final Map<CharSequence, String> charSequenceToString = ImmutableMap.<CharSequence, String>copyOf(stringToString);

    assertThat(builder.bars(stringToString).build().bars(), is(charSequenceToCharSequence));
    assertThat(builder.bars(charSequenceToCharSequence).build().bars(), is(charSequenceToCharSequence));
    assertThat(builder.bars(stringToCharSequence).build().bars(), is(charSequenceToCharSequence));
    assertThat(builder.bars(charSequenceToString).build().bars(), is(charSequenceToCharSequence));
  }
}
