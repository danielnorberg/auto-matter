package io.norberg.automatter.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AutoMatterModuleTest {

  static final Foo FOO = new FooBuilder()
      .a(17)
      .b("foobar")
      .build();

  ObjectMapper mapper;

  @Before
  public void setUp() {
    mapper = new ObjectMapper()
        .registerModule(new AutoMatterModule());
  }

  @Test
  public void testJson() throws IOException {
    final String json = mapper.writeValueAsString(FOO);
    final Foo parsed = mapper.readValue(json, Foo.class);
    assertThat(parsed, is(FOO));
  }

}