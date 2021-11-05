package io.norberg.automatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.norberg.automatter.gson.AutoMatterTypeAdapterFactory;
import io.norberg.automatter.jackson.AutoMatterModule;
import java.util.List;
import javax.annotation.Nullable;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class RecordTest {

  private static final ObjectMapper jackson =
      new ObjectMapper().registerModule(new AutoMatterModule());

  private static final Gson gson =
      new GsonBuilder().registerTypeAdapterFactory(new AutoMatterTypeAdapterFactory()).create();

  @AutoMatter
  public record Foobar(int a, String b) {}

  @AutoMatter
  public record GenericFoobar<A, B>(A a, B b) {}

  @AutoMatter
  public record ComplexFoobar(
      String foo, String bar, String baz, String quux, String corge, List<String> strings) {}

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

  @Test
  public void testJackson() throws JsonProcessingException {
    var foobar = FoobarBuilder.builder().a(1).b("2").build();
    var json = jackson.writeValueAsString(foobar);
    var deserialized = jackson.readValue(json, Foobar.class);
    assertThat(deserialized, is(foobar));
  }

  @Ignore("Gson does not yet support records: https://github.com/google/gson/issues/1794")
  @Test
  public void testGson() {
    var foobar = FoobarBuilder.builder().a(1).b("2").build();
    var json = gson.toJson(foobar);
    var deserialized = gson.fromJson(json, Foobar.class);
    assertThat(deserialized, is(foobar));
  }
}
