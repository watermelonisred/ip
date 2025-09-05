package watermelon.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event task with a start and end date.
 */
public class Event extends Task {
    /** The start date & time of the task */
    protected LocalDateTime from;
    /** The end date & time of the task */
    protected LocalDateTime to;

    /**
     * Constructs a new Event task with the specified description, start date and end date.
     */
    public Event(String description, String from, String to) throws DateTimeParseException {
        super(description);
        this.from = stringToDateTime(from);
        this.to = stringToDateTime(to);
    }

    /**
     * Constructs a new Event task with the specified description, start date, end date and completion status.
     */
    public Event(String description, String from, String to, boolean isDone) throws DateTimeParseException {
        super(description, isDone);
        this.from = stringToDateTime(from);
        this.to = stringToDateTime(to);
    }

    /**
     * Converts a string with "ddMMyyyy HHmm" format into a {@link LocalDateTime}.
     * @param input String representing a date & time.
     * @return A {@link LocalDateTime} representing the specified date & time.
     * @throws DateTimeParseException If input does not match the "ddMMyyyy HHmm" pattern.
     */
    private LocalDateTime stringToDateTime(String input) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy HHmm");
        LocalDateTime dt = LocalDateTime.parse(input, formatter);
        return dt;
    }

    /**
     * Converts this task to a file format string for storage.
     */
    @Override
    public String toFileFormat() {
        return String.format("E | %s | %s | %s | %s", super.getStatusIcon().equals("X") ? 1 : 0, description,
                from.format(DateTimeFormatter.ofPattern("ddMMyyyy HHmm")),
                to.format(DateTimeFormatter.ofPattern("ddMMyyyy HHmm")));
    }

    @Override
    public String toString() {
        String formatted_from = from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String formatted_to = to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return "[E]" + super.toString() + " (from: " + formatted_from + " to: " + formatted_to + ")";
    }
}
