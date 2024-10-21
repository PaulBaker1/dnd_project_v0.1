import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.format.DateTimeParseException;

public class DateTimeParser {

    // DateTimeFormatter that handles both formats with and without seconds
    private static final DateTimeFormatter FLEXIBLE_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd'T'HH:mm")
            .optionalStart()
            .appendPattern(":ss")
            .optionalEnd()
            .optionalStart()
            .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true)
            .optionalEnd()
            .toFormatter();

    public static void main(String[] args) {
        String dateTimeString = "2024-10-21T14:00";
        try {
            LocalDateTime parsedDate = parseDateTime(dateTimeString);
            System.out.println("Parsed DateTime: " + parsedDate);
        } catch (DateTimeParseException e) {
            System.err.println("Failed to parse date-time string: " + dateTimeString);
            e.printStackTrace();
        }
    }

    // Method to parse date-time using the flexible formatter
    public static LocalDateTime parseDateTime(String dateTimeString) {
        try {
            return LocalDateTime.parse(dateTimeString, FLEXIBLE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Invalid date-time format: " + dateTimeString, dateTimeString, e.getErrorIndex());
        }
    }
}
