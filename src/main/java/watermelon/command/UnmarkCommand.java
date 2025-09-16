package watermelon.command;

import watermelon.Storage;
import watermelon.TaskList;
import watermelon.exception.StorageOperationException;
import watermelon.task.Task;

/**
 * Represents an unmark command that contains information on tasklist, task number and storage.
 * Upon execution, task corresponding to task number is marked as undone in tasklist.
 * Unmark completion message is printed and changes made are saved to storage.
 */
public class UnmarkCommand extends Command {
    private int taskNumber;
    private Storage storage;

    /**
     * Constructs a UnmarkCommand object with given details.
     *
     * @param taskList Tasklist containing task to be unmarked.
     * @param taskNumber Task number of task to be marked as undone.
     * @param storage Storage where changes made are saved.
     */
    public UnmarkCommand(TaskList taskList, int taskNumber, Storage storage) {
        super.taskList = taskList;
        this.taskNumber = taskNumber;
        this.storage = storage;
    }

    /**
     * Marks task as undone in tasklist, prints unmark completion message and saves changes into storage.
     * @throws StorageOperationException {@inheritDoc}
     */
    @Override
    public void execute() throws StorageOperationException {
        Task task = taskList.unmarkTask(taskNumber);
        storage.saveTasks(taskList);
        message = "OK, I've marked this task as not done yet:\n" + INDENT + task;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
