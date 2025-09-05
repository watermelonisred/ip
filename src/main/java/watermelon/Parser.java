package watermelon;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import watermelon.exception.WatermelonException;
import watermelon.exception.InvalidCommandException;
import watermelon.exception.EmptyTaskDescriptionException;
import watermelon.exception.EmptyDateException;
import watermelon.exception.InvalidInputException;
import watermelon.command.Command;
import watermelon.command.ListCommand;
import watermelon.command.MarkCommand;
import watermelon.command.UnmarkCommand;
import watermelon.command.TodoCommand;
import watermelon.command.DeadlineCommand;
import watermelon.command.EventCommand;
import watermelon.command.DeleteCommand;

public class Parser {
    private TaskList taskList;
    private Storage storage;

    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
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

        if (input.equals("list")) { // list
            return new ListCommand(taskList);
        } else if (mark.matches()) { // mark
            if (mark.group(1) == null || mark.group(1).isEmpty()) {
                throw new InvalidInputException("missing task number!");
            }
            if (!mark.group(1).trim().matches("-?\\d+")) {
                throw new InvalidInputException("not a valid integer!");
            }
            int taskNumber = Integer.parseInt(mark.group(1));
            if (taskNumber < 1 || taskNumber > taskList.size()) {
                throw new InvalidInputException("not a valid task number!");
            }
            return new MarkCommand(taskList, taskNumber, storage);
        } else if (unmark.matches()) { // unmark
            if (unmark.group(1) == null || unmark.group(1).isEmpty()) {
                throw new InvalidInputException("missing task number!");
            }
            if (!unmark.group(1).trim().matches("-?\\d+")) {
                throw new InvalidInputException("not a valid integer!");
            }
            int taskNumber = Integer.parseInt(unmark.group(1));
            if (taskNumber < 1 || taskNumber > taskList.size()) {
                throw new InvalidInputException("not a valid task number!");
            }
            return new UnmarkCommand(taskList, taskNumber, storage);
        } else if (todo.matches()) { // add todo
            if (todo.group(1).isEmpty()) {
                throw new EmptyTaskDescriptionException("todo task description is empty!");
            }
            return new TodoCommand(taskList, todo.group(1).trim(), storage);
        } else if (deadline.matches()) { // add deadline
            if (deadline.group(1) == null || deadline.group(1).isEmpty()) {
                throw new EmptyTaskDescriptionException("deadline task description is empty!");
            }
            if (deadline.group(2) == null || deadline.group(2).isEmpty()) {
                throw new EmptyDateException();
            }
            return new DeadlineCommand(taskList, deadline.group(1).trim(), deadline.group(2), storage);
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
            return new EventCommand(taskList, event.group(1).trim(), event.group(2).trim(), event.group(3), storage);
        } else if (delete.matches()) { // delete
            if (delete.group(1) == null || delete.group(1).isEmpty()) {
                throw new InvalidInputException("missing task number!");
            }
            if (!delete.group(1).trim().matches("-?\\d+")) {
                throw new InvalidInputException("not a valid integer!");
            }
            int taskNumber = Integer.parseInt(delete.group(1));
            if (taskNumber < 1 || taskNumber > taskList.size()) {
                throw new InvalidInputException("not a valid task number!");
            }
            return new DeleteCommand(taskList, taskNumber, storage);
        } else { // invalid command
            throw new InvalidCommandException();
        }
    }
}