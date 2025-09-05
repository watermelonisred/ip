package watermelon;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import watermelon.command.*;
import watermelon.exception.WatermelonException;
import watermelon.exception.InvalidCommandException;
import watermelon.exception.EmptyTaskDescriptionException;
import watermelon.exception.EmptyDateException;
import watermelon.exception.InvalidInputException;

public class Parser {
    private TaskList tasklist;
    private Storage storage;

    public Parser(TaskList tasklist, Storage storage) {
        this.tasklist = tasklist;
        this.storage = storage;
    }

    public Command parseCommand(String input) throws WatermelonException {
        Matcher todo = Pattern.compile("^todo\\s*(.*)$").matcher(input);
        Matcher deadline = Pattern.compile(
                "^deadline(?:\\s+([^/]+?))?(?:\\s*/\\s*(?:by\\s*(.+)?)?)?$").matcher(input);
        Matcher event = Pattern.compile(
                        "^event(?:\\s+([^/]+?))?(?:\\s*/\\s*from(?:\\s+([^/]+?))?)?(?:\\s*/\\s*to(?:\\s+(.+)?)?)?$")
                .matcher(input);
        Matcher mark = Pattern.compile("^mark(?:\\s+(.+))?$").matcher(input);
        Matcher unmark = Pattern.compile("^unmark(?:\\s+(.+))?$").matcher(input);
        Matcher delete = Pattern.compile("^delete(?:\\s+(.+))?$").matcher(input);
        Matcher find = Pattern.compile("find( .*)?").matcher(input);

        if (input.equals("list")) { // list
            return new ListCommand(tasklist);
        } else if (mark.matches()) { // mark
            if (mark.group(1) == null || mark.group(1).isEmpty()) {
                throw new InvalidInputException("missing task number!");
            }
            if (!mark.group(1).trim().matches("-?\\d+")) {
                throw new InvalidInputException("not a valid integer!");
            }
            int taskNumber = Integer.parseInt(mark.group(1));
            if (taskNumber < 1 || taskNumber > tasklist.size()) {
                throw new InvalidInputException("not a valid task number!");
            }
            return new MarkCommand(tasklist, taskNumber, storage);
        } else if (unmark.matches()) { // unmark
            if (unmark.group(1) == null || unmark.group(1).isEmpty()) {
                throw new InvalidInputException("missing task number!");
            }
            if (!unmark.group(1).trim().matches("-?\\d+")) {
                throw new InvalidInputException("not a valid integer!");
            }
            int taskNumber = Integer.parseInt(unmark.group(1));
            if (taskNumber < 1 || taskNumber > tasklist.size()) {
                throw new InvalidInputException("not a valid task number!");
            }
            return new UnmarkCommand(tasklist, taskNumber, storage);
        } else if (todo.matches()) { // add todo
            if (todo.group(1).isEmpty()) {
                throw new EmptyTaskDescriptionException("todo task description is empty!");
            }
            return new TodoCommand(tasklist, todo.group(1).trim(), storage);
        } else if (deadline.matches()) { // add deadline
            if (deadline.group(1) == null || deadline.group(1).isEmpty()) {
                throw new EmptyTaskDescriptionException("deadline task description is empty!");
            }
            if (deadline.group(2) == null || deadline.group(2).isEmpty()) {
                throw new EmptyDateException();
            }
            return new DeadlineCommand(tasklist, deadline.group(1).trim(), deadline.group(2), storage);
        } else if (event.matches()) { // add event
            if (event.group(1) == null || event.group(1).isEmpty()) {
                throw new EmptyTaskDescriptionException("event task description is empty!");
            }
            if (event.group(2) == null || event.group(2).isEmpty()) {
                throw new EmptyDateException("start date/time is missing!");
            }
            if (event.group(3) == null || event.group(3).isEmpty()) {
                throw new EmptyDateException("end date/time is missing!");
            }
            return new EventCommand(tasklist, event.group(1).trim(), event.group(2).trim(), event.group(3), storage);
        } else if (delete.matches()) { // delete
            if (delete.group(1) == null || delete.group(1).isEmpty()) {
                throw new InvalidInputException("missing task number!");
            }
            if (!delete.group(1).trim().matches("-?\\d+")) {
                throw new InvalidInputException("not a valid integer!");
            }
            int taskNumber = Integer.parseInt(delete.group(1));
            if (taskNumber < 1 || taskNumber > tasklist.size()) {
                throw new InvalidInputException("not a valid task number!");
            }
            return new DeleteCommand(tasklist, taskNumber, storage);
        } else if (find.matches()) { // find
            if (find.group(1) == null || find.group(1).isBlank()) {
                throw new InvalidInputException("missing keyword!");
            }
            return new FindCommand(tasklist, find.group(1).trim());
        } else { // invalid command
            throw new InvalidCommandException();
        }
    }
}