package watermelon.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = stringToDateTime(by);
    }

    public Deadline(String description, String by, boolean isDone) throws DateTimeParseException {
        super(description, isDone);
        this.by = stringToDateTime(by);
    }

    private LocalDateTime stringToDateTime(String input) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
        return dateTime;
    }

    @Override
    public String toFileFormat() {
        return String.format("D | %s | %s | %s", super.getStatusIcon().equals("X") ? 1 : 0, description,
                by.format(DateTimeFormatter.ofPattern("ddMMyyyy HHmm")));
    }

    @Override
    public String toString() {
        String formatted_by = by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return "[D]" + super.toString() + " (by: " + formatted_by + ")";
    }
}

