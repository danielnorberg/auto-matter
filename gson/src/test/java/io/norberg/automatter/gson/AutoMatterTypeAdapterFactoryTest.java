package io.norberg.automatter.gson;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;

public class AutoMatterTypeAdapterFactoryTest {

  static final Foo FOO = new FooBuilder().a(17).b("foobar").build();

  static final Bar BAR = new BarBuilder().a(17).b("foobar").isPrivate(true).build();

  private NestedGson nestedGson;

  Gson gson;

  @Before
  public void setUp() {
    gson =
        new GsonBuilder().registerTypeAdapterFactory(new AutoMatterTypeAdapterFactory()).create();
    nestedGson = new NestedGsonBuilder().cutee(new NesteeBuilder().floof("foobar").build()).build();
  }

  @Test
  public void testJson() {
    final String json = gson.toJson(FOO);
    final Foo parsed = gson.fromJson(json, Foo.class);
    assertThat(parsed, is(FOO));
  }

  @Test
  public void testSerializedName() {
    final String json = gson.toJson(BAR); // isPrivate -> private
    assertThat(json, is("{\"a\":17,\"b\":\"foobar\",\"private\":true}"));
  }

  @Test
  public void testSerializedNameWithUnderscorePolicy() {
    gson =
        new GsonBuilder()
            .registerTypeAdapterFactory(new AutoMatterTypeAdapterFactory())
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
    final String json = gson.toJson(BAR); // isPrivate -> private
    final Bar parsed = gson.fromJson(json, Bar.class); // private -> isPrivate
    assertThat(parsed, is(BAR));
    // Make sure that tranlation of under_scored fields still work.
    final String underscoredIsPrivate = "{\"a\":17,\"b\":\"foobar\",\"is_private\":true}";
    assertThat(gson.fromJson(underscoredIsPrivate, Bar.class), is(BAR)); // is_private -> isPrivate
  }

  @Test
  public void testNesting() {
    final String json = gson.toJson(nestedGson);
    assertThat(gson.fromJson(json, NestedGson.class), is(nestedGson));
  }
}
