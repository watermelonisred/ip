package watermelon.command;

import watermelon.Storage;
import watermelon.TaskList;
import watermelon.exception.StorageOperationException;

public class DeadlineCommand extends Command {
    private String description;
    private String by;
    private Storage storage;

    public DeadlineCommand(TaskList tasklist, String description, String by, Storage storage) {
        super.tasklist = tasklist;
        this.description = description;
        this.by = by;
        this.storage = storage;
    }

    @Override
    public void execute() throws StorageOperationException {
        tasklist.addDeadline(description, by);
        storage.saveTasks(tasklist);
    }
}
