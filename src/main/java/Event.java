import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) throws DateTimeParseException {
        super(description);
        this.from = stringToDateTime(from);
        this.to = stringToDateTime(to);
    }

    public Event(String description, String from, String to, boolean isDone) throws DateTimeParseException {
        super(description, isDone);
        this.from = stringToDateTime(from);
        this.to = stringToDateTime(to);
    }

    private LocalDateTime stringToDateTime(String input) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy HHmm");
        LocalDateTime dt = LocalDateTime.parse(input, formatter);
        return dt;
    }

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
