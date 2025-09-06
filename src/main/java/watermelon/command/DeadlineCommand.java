package watermelon.command;

import watermelon.Storage;
import watermelon.TaskList;
import watermelon.exception.StorageOperationException;

/**
 * Represents a deadline command that contains information on a deadline task (task description, deadline and storage).
 * Upon execution, deadline task is added to tasklist, task completion message is printed and task is saved to storage.
 */
public class DeadlineCommand extends Command {
    private String description;
    private String by;
    private Storage storage;

    /**
     * Constructs a DeadlineCommand object with given details.
     *
     * @param taskList Tasklist to add deadline task to.
     * @param description Description of deadline task.
     * @param by Deadline of deadline task.
     * @param storage Storage where deadline task is stored.
     */
    public DeadlineCommand(TaskList taskList, String description, String by, Storage storage) {
        super.taskList = taskList;
        this.description = description;
        this.by = by;
        this.storage = storage;
    }

    /**
     * Adds deadline task to tasklist, prints task completion message and saves the task into storage.
     * @throws StorageOperationException {@inheritDoc}
     */
    @Override
    public void execute() throws StorageOperationException {
        taskList.addDeadline(description, by);
        storage.saveTasks(taskList);
    }
}
