package io.norberg.automatter.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import io.norberg.automatter.AutoMatter;
import io.norberg.automatter.other.InheritenceR1;
import org.junit.Test;

public class OtherPackageInheritanceTest {

  @AutoMatter
  interface InheritenceR
      extends InheritenceR1.InheritenceR2, InheritenceR1.InheritenceR2.InheritenceR3 {
    String callR();
  }

  @Test
  public void test() {
    final InheritenceR r = new InheritenceRBuilder().callR("r").callR2("r2").callR3("r3").build();
    assertThat(r.callR(), is("r"));
    assertThat(r.callR2(), is("r2"));
    assertThat(r.callR3(), is("r3"));
  }
}
