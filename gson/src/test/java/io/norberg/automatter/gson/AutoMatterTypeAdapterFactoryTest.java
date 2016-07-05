package io.norberg.automatter.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AutoMatterTypeAdapterFactoryTest {

  static final Foo FOO = new FooBuilder()
      .a(17)
      .b("foobar")
      .isPrivate(true)
      .build();

  Gson gson;

  @Before
  public void setUp() {
    gson = new GsonBuilder()
        .registerTypeAdapterFactory(new AutoMatterTypeAdapterFactory())
        .create();
  }

  @Test
  public void testJson() throws IOException {
    final String json = gson.toJson(FOO);
    final Foo parsed = gson.fromJson(json, Foo.class);
    assertThat(parsed, is(FOO));
  }

}
