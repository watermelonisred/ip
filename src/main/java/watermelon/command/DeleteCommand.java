package watermelon.command;

import watermelon.Storage;
import watermelon.TaskList;
import watermelon.exception.StorageOperationException;

public class DeleteCommand extends Command {
    private int taskNumber;
    private Storage storage;

    public DeleteCommand(TaskList tasklist, int taskNumber, Storage storage) {
        super.tasklist = tasklist;
        this.taskNumber = taskNumber;
        this.storage = storage;
    }

    @Override
    public void execute() throws StorageOperationException {
        tasklist.deleteTask(taskNumber);
        storage.saveTasks(tasklist);
    }
}
