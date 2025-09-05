package watermelon.command;

import watermelon.Storage;
import watermelon.TaskList;
import watermelon.exception.StorageOperationException;

public class DeleteCommand extends Command {
    private int taskNumber;
    private Storage storage;

    public DeleteCommand(TaskList taskList, int taskNumber, Storage storage) {
        super.taskList = taskList;
        this.taskNumber = taskNumber;
        this.storage = storage;
    }

    @Override
    public void execute() throws StorageOperationException {
        taskList.deleteTask(taskNumber);
        storage.saveTasks(taskList);
    }
}
