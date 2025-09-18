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
        assert taskList != null : "taskList should not be null";

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:");

        for (int i = 0; i < taskList.getSize(); i++) {
            int task_index = i + 1;
            sb.append("\n" + INDENT + task_index + "." + taskList.getTask(i));
        }

        message = sb.toString();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
