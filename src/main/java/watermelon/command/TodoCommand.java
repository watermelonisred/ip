package watermelon.command;

import watermelon.Storage;
import watermelon.TaskList;
import watermelon.exception.StorageOperationException;
import watermelon.task.Task;

/**
 * Represents a todo command that contains information on a todo task (task description and storage).
 * Upon execution, todo task is added to tasklist, task completion message is printed and task is saved to storage.
 */
public class TodoCommand extends Command {
    private String description;
    private Storage storage;

    /**
     * Constructs a TodoCommand object with given details.
     *
     * @param taskList Tasklist to add todo task to.
     * @param description Description of todo task.
     * @param storage Storage where todo task is stored.
     */
    public TodoCommand(TaskList taskList, String description, Storage storage) {
        super.taskList = taskList;
        this.description = description;
        this.storage = storage;
    }

    /**
     * Adds todo task to tasklist, prints task completion message and saves the task into storage.
     * @throws StorageOperationException {@inheritDoc}
     */
    @Override
    public void execute() throws StorageOperationException {
        Task task = taskList.addTodo(description);
        assert task != null : "task should not be null";
        storage.saveTasks(taskList);
        message = "Got it. I've added this task:\n"
                + INDENT + task + "\n"
                + String.format("Now you have %d tasks in the list.", taskList.getSize());
    }

    @Override
    public String getMessage() {
        return message;
    }
}
