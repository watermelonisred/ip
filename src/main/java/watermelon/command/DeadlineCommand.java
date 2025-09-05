package watermelon.command;

import watermelon.Storage;
import watermelon.TaskList;
import watermelon.exception.StorageOperationException;

public class DeadlineCommand extends Command {
    private String description;
    private String by;
    private Storage storage;

    public DeadlineCommand(TaskList taskList, String description, String by, Storage storage) {
        super.taskList = taskList;
        this.description = description;
        this.by = by;
        this.storage = storage;
    }

    @Override
    public void execute() throws StorageOperationException {
        taskList.addDeadline(description, by);
        storage.saveTasks(taskList);
    }
}
