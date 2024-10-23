package pl.paulb.dndmanager.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class FlexibleLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    // Create a DateTimeFormatter that handles both 'yyyy-MM-dd'T'HH:mm' and 'yyyy-MM-dd'T'HH:mm:ss'
    private static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd'T'HH:mm")
            .optionalStart()
            .appendPattern(":ss")
            .optionalEnd()
            .optionalStart()
            .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true)
            .optionalEnd()
            .toFormatter();

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        String dateTimeString = jsonParser.getText();
        return LocalDateTime.parse(dateTimeString, FORMATTER);
    }
}
