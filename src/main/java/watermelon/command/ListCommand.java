package watermelon.command;

import watermelon.TaskList;
import watermelon.exception.StorageOperationException;

/**
 * Represents a list command that contains a tasklist.
 * Upon execution, tasks in tasklist are printed.
 */
public class ListCommand extends Command {
    /**
     * Constructs a ListCommand object with given details.
     *
     * @param tasklist Tasklist to be listed.
     */
    public ListCommand(TaskList tasklist) {
        super.tasklist = tasklist;
    }

    /**
     * Prints out tasks in tasklist.
     */
    @Override
    public void execute() {
        tasklist.showList();
    }
}
