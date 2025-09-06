package watermelon.command;

import watermelon.TaskList;
import watermelon.exception.StorageOperationException;

/**
 * Represents a command that captures details entered by the user.
 * All subclasses of Command  must implement a {@link #execute()} method.
 */
public abstract class Command {
    TaskList taskList;

    /**
     * Executes the command by modifying the TaskList accordingly and saving the task where needed.
     * @throws StorageOperationException when a problem occurs during saving of task to data file.
     */
    abstract public void execute() throws StorageOperationException;
}
