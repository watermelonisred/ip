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

/**
 * Parses raw user input strings and converts them into {@link Command} objects for execution.
 *
 * <p>This class uses regular expressions to recognise
 * supported commands (list, mark, unmark, todo, deadline, event, delete).
 * Based on the match, it constructs the corresponding {@link Command}
 * subclass or throws a {@link WatermelonException} if the input is invalid.</p>
 */
public class Parser {
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs a new {@code Parser} instance which uses the given task list and storage.
     *
     * @param taskList The {@link TaskList} on which commands will operate.
     * @param storage  The {@link Storage} used to save and load changes.
     */
    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Parses a user input string and returns the corresponding {@link Command}.
     *
     * @param input The full line of user input to parse.
     * @return A {@link Command} representing the user’s instruction.
     * @throws WatermelonException If the input is invalid, missing required fields,
     *                             refers to a non-existent task number, or does not
     *                             match any known command.
     */
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

        if (input.equals("list")) { // returns a ListCommand
            return new ListCommand(taskList);
        } else if (mark.matches()) { // returns a MarkCommand
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
        } else if (unmark.matches()) { // returns an UnmarkCommand
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
        } else if (todo.matches()) { // returns a TodoCommand
            if (todo.group(1).isEmpty()) {
                throw new EmptyTaskDescriptionException("todo task description is empty!");
            }
            return new TodoCommand(taskList, todo.group(1).trim(), storage);
        } else if (deadline.matches()) { // returns a DeadlineCommand
            if (deadline.group(1) == null || deadline.group(1).isEmpty()) {
                throw new EmptyTaskDescriptionException("deadline task description is empty!");
            }
            if (deadline.group(2) == null || deadline.group(2).isEmpty()) {
                throw new EmptyDateException();
            }
            return new DeadlineCommand(taskList, deadline.group(1).trim(), deadline.group(2), storage);
        } else if (event.matches()) { // returns a EventCommand
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
        } else if (delete.matches()) { // returns a DeleteCommand
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