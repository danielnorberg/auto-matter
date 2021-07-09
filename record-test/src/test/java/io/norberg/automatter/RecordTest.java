package io.norberg.automatter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

public class RecordTest {

  private static final ObjectMapper mapper = new ObjectMapper();

  @AutoMatter
  public record RecordFoobar(int a, String b) {}

  @AutoMatter
  public record GenericRecordFoobar<A, B>(A a, B b) {}

  @Test
  public void testRecord() {
    var foobar = RecordFoobarBuilder.builder().a(1).b("2").build();
    assertThat(foobar.a(), is(1));
    assertThat(foobar.b(), is("2"));
    var rebuilt = RecordFoobarBuilder.from(foobar).build();
    assertThat(foobar, is(rebuilt));
  }

  @Test
  public void testGenericRecord() {
    var foobar = GenericRecordFoobarBuilder.builder().a(1).b("2").build();
    assertThat(foobar.a(), is(1));
    assertThat(foobar.b(), is("2"));
    var rebuilt = GenericRecordFoobarBuilder.from(foobar).build();
    assertThat(foobar, is(rebuilt));
  }

  @Test
  public void testJackson() throws JsonProcessingException {
    var foobar = RecordFoobarBuilder.builder().a(1).b("2").build();
    var json = mapper.writeValueAsString(foobar);
    var deserialized = mapper.readValue(json, RecordFoobar.class);
    assertThat(foobar, is(deserialized));
  }
}
