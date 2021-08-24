package io.norberg.automatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.util.List;
import javax.annotation.Nullable;
import org.junit.Assert;
import org.junit.Test;

public class RecordTest {

  @AutoMatter
  public record Foobar(int a, String b) {}

  @AutoMatter
  public record GenericFoobar<A, B>(A a, B b) {}

  @AutoMatter
  public record ComplexFoobar(
      String foo,
      String bar,
      String baz,
      String quux,
      String corge,
      List<String> strings) {}

  @AutoMatter
  public record NullableRecord(@Nullable String foo, String bar) {}

  @Test
  public void testRecord() {
    var foobar = FoobarBuilder.builder().a(1).b("2").build();
    assertThat(foobar.a(), is(1));
    assertThat(foobar.b(), is("2"));
    var rebuilt = FoobarBuilder.from(foobar).build();
    assertThat(foobar, is(rebuilt));
  }

  @Test
  public void testGenericRecord() {
    var foobar = GenericFoobarBuilder.builder().a(1).b("2").build();
    assertThat(foobar.a(), is(1));
    assertThat(foobar.b(), is("2"));
    var rebuilt = GenericFoobarBuilder.from(foobar).build();
    assertThat(foobar, is(rebuilt));
  }

  @Test
  public void testComplexRecord() {
    var foobar =
        ComplexFoobarBuilder.builder()
            .foo("foo")
            .bar("bar")
            .baz("baz")
            .quux("quux")
            .corge("corge")
            .strings("a", "b")
            .build();
    var rebuilt = ComplexFoobarBuilder.from(foobar).build();
    assertThat(foobar, is(rebuilt));
  }

  @Test
  public void testRecordNullCheck() {
    var b = FoobarBuilder.builder();
    var t = Assert.assertThrows(NullPointerException.class, b::build);
    assertThat(t.getMessage(), is("b"));
  }

  @Test
  public void testNullableField() {
    var b = NullableRecordBuilder.builder().bar("bar").build();
    assertThat(b.bar(), is("bar"));
    assertThat(b.foo(), is(nullValue()));
  }
}
