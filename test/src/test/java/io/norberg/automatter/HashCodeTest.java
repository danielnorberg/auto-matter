package io.norberg.automatter;

import static java.lang.Math.E;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

public class HashCodeTest {

  private static final float F_PI = (float) Math.PI;
  private static final String O = "foobar";
  private static final List<String> LIST = asList("a", "b", "c");

  @Test
  public void verifyHashCodeMatchesArraysHashCode() {

    // Note: This expectation breaks if any of the fields is an array. But then, arrays are mutable
    //       so all bets are off anyway.

    HashCodeFoobar foobar =
        new HashCodeFoobarBuilder()
            .f0_boolean(true)
            .f1_byte((byte) 17)
            .f2_short((short) 4711)
            .f3_int(17 * 4711)
            .f4_long(4711 * 4711)
            .f5_char('Y')
            .f6_float(F_PI)
            .f7_double(E)
            .f8_object(O)
            .f9_list(LIST)
            .build();

    final int expected =
        Arrays.hashCode(
            new Object[] {
              foobar.f0_boolean(),
              foobar.f1_byte(),
              foobar.f2_short(),
              foobar.f3_int(),
              foobar.f4_long(),
              foobar.f5_char(),
              foobar.f6_float(),
              foobar.f7_double(),
              foobar.f8_object(),
              foobar.f9_list()
            });

    int hashCode = foobar.hashCode();

    assertThat(hashCode, is(expected));
  }
}
