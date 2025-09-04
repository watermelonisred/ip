package watermelon.command;

import watermelon.TaskList;
import watermelon.exception.StorageOperationException;

public abstract class Command {
    TaskList tasklist;

    abstract public void execute() throws StorageOperationException;
}
