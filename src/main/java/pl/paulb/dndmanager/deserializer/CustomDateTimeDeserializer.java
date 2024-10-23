package pl.paulb.dndmanager.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

public class CustomDateTimeDeserializer extends JsonDeserializer<DateTime> {

    // Create a Joda-Time DateTimeFormatter that can handle various formats
    private static final DateTimeFormatter FORMATTER_WITH_SECONDS = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
    private static final DateTimeFormatter FORMATTER_WITHOUT_SECONDS = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm");

    @Override
    public DateTime deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        String dateTimeString = jsonParser.getText();

        // Try to parse with seconds first
        try {
            return DateTime.parse(dateTimeString, FORMATTER_WITH_SECONDS);
        } catch (IllegalArgumentException e) {
            // Fallback to parsing without seconds
            return DateTime.parse(dateTimeString, FORMATTER_WITHOUT_SECONDS);
        }
    }
}