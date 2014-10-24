package io.norberg.automatter.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static com.fasterxml.jackson.databind.PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AutoMatterModuleTest {

  static final Foo FOO = new FooBuilder()
      .a(17)
      .b("foobar")
      .aCamelCaseField(true)
      .build();

  ObjectMapper mapper;

  @Before
  public void setUp() {
    mapper = new ObjectMapper()
        .registerModule(new AutoMatterModule());
  }

  @Test
  public void testDefault() throws IOException {
    final String json = mapper.writeValueAsString(FOO);
    final Foo parsed = mapper.readValue(json, Foo.class);
    assertThat(parsed, is(FOO));
  }

  @Test
  public void testUnderScoreNamingStrategy() throws IOException {
    mapper.setPropertyNamingStrategy(CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
    final String json = mapper.writeValueAsString(FOO);
    final JsonNode tree = mapper.readTree(json);
    assertThat(tree.has("a_camel_case_field"), is(true));
    final Foo parsed = mapper.readValue(json, Foo.class);
    assertThat(parsed, is(FOO));
  }

  @Test
  public void testExplicitJsonProperty() throws IOException {
    final String json = mapper.writeValueAsString(FOO);
    final JsonNode tree = mapper.readTree(json);
    assertThat(tree.has("foobar"), is(true));
    final Foo parsed = mapper.readValue(json, Foo.class);
    assertThat(parsed, is(FOO));
  }

}