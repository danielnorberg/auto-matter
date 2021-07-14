package io.norberg.automatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class RecordTest {

  private static final ObjectMapper mapper = new ObjectMapper();

  @AutoMatter
  public record Foobar(int a, String b) {}

  @AutoMatter
  public record GenericFoobar<A, B>(A a, B b) {}

  @AutoMatter
  public record ComplexFoobar(String foo, String bar, String baz, String quux, String corge) {}

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
            .build();
    var rebuilt = ComplexFoobarBuilder.from(foobar).build();
    assertThat(foobar, is(rebuilt));
  }

  @Test
  public void testJackson() throws JsonProcessingException {
    var foobar = FoobarBuilder.builder().a(1).b("2").build();
    var json = mapper.writeValueAsString(foobar);
    var deserialized = mapper.readValue(json, Foobar.class);
    assertThat(foobar, is(deserialized));
  }
}
