package watermelon.command;

import watermelon.TaskList;
import watermelon.exception.StorageOperationException;

public abstract class Command {
    TaskList taskList;

    abstract public void execute() throws StorageOperationException;
}
