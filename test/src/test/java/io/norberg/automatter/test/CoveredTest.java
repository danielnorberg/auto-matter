package io.norberg.automatter.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

import org.hamcrest.Matchers;
import org.junit.Test;

public class CoveredTest {

  @Test
  public void touchCovered() {
    final Covered c = new CoveredBuilder().bar("bar").build();
    assertThat(c.toString(), not(Matchers.isEmptyOrNullString()));
  }
}
