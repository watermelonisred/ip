package watermelon.command;

import watermelon.Storage;
import watermelon.TaskList;
import watermelon.exception.StorageOperationException;

/**
 * Represents an event command that contains information on event task (task description, start & end date and storage).
 * Upon execution, event task is added to taskList, task completion message is printed and task is saved to storage.
 */
public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;
    private Storage storage;

    /**
     * Constructs a EventCommand object with given details.
     *
     * @param taskList Tasklist to add deadline task to.
     * @param description Description of event task.
     * @param from Start date & time of event task.
     * @param to End date & time of event task.
     * @param storage Storage where event task is stored.
     */
    public EventCommand(TaskList taskList, String description, String from, String to, Storage storage) {
        super.taskList = taskList;
        this.description = description;
        this.from = from;
        this.to = to;
        this.storage = storage;
    }

    /**
     * Adds event task to tasklist, prints task completion message and saves the task into storage.
     * @throws StorageOperationException {@inheritDoc}
     */
    @Override
    public void execute() throws StorageOperationException {
        taskList.addEvent(description, from, to);
        storage.saveTasks(taskList);
    }
}
