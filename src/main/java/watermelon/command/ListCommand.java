package watermelon.command;

import watermelon.TaskList;

/**
 * Represents a list command that contains a tasklist.
 * Upon execution, tasks in tasklist are printed.
 */
public class ListCommand extends Command {
    /**
     * Constructs a ListCommand object with given details.
     *
     * @param taskList Tasklist to be listed.
     */
    public ListCommand(TaskList taskList) {
        super.taskList = taskList;
    }

    /**
     * Prints out tasks in tasklist.
     */
    @Override
    public void execute() {
        taskList.showList();
    }
}
