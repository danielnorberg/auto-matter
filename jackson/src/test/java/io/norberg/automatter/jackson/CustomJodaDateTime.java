package io.norberg.automatter.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class CustomJodaDateTime {

  private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
  private static final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern(DATE_TIME_FORMAT).withOffsetParsed();

  private CustomJodaDateTime() {
  }

  public static class Serializer extends JsonSerializer<DateTime> {

    @Override
    public void serialize(final DateTime dateTime,
        final JsonGenerator gen,
        final SerializerProvider provider) throws IOException {
      gen.writeString(FORMATTER.print(dateTime));
    }
  }

  public static class Deserializer extends JsonDeserializer<DateTime> {

    @Override
    public DateTime deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException {
      String dateTimeString = jp.getText();
      if (dateTimeString == null) {
        throw new JsonParseException("Invalid date. Expected string date with format " + DATE_TIME_FORMAT,
            jp.getCurrentLocation());
      }
      return FORMATTER.parseDateTime(dateTimeString);
    }
  }
}