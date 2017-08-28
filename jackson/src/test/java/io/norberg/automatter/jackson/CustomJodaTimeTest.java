package io.norberg.automatter.jackson;

import static org.junit.Assert.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.norberg.automatter.AutoMatter;
import org.hamcrest.Matchers;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

public class CustomJodaTimeTest {

  private ObjectMapper mapper;

  @AutoMatter
  public interface JodaFoobar {

    @JsonSerialize(using = CustomJodaDateTime.Serializer.class)
    @JsonDeserialize(using = CustomJodaDateTime.Deserializer.class)
    DateTime foo();
  }

  @Before
  public void setUp() {
    mapper = new ObjectMapper()
        .registerModule(new AutoMatterModule());
  }

  @Test
  public void testCustomDateTimeSerialization() throws Exception {
    final JodaFoobar foobar = new JodaFoobarBuilder()
        .foo(DateTime.parse("2017-08-28T14:56:15+0000"))
        .build();

    final String json = mapper.writeValueAsString(foobar);

    final JodaFoobar parsed = mapper.readValue(json, JodaFoobar.class);
    assertThat(parsed, Matchers.is(foobar));
  }
}
