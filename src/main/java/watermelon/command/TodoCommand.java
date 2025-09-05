package watermelon.command;

import watermelon.Storage;
import watermelon.TaskList;
import watermelon.exception.StorageOperationException;

public class TodoCommand extends Command {
    private String description;
    private Storage storage;

    public TodoCommand(TaskList taskList, String description, Storage storage) {
        super.taskList = taskList;
        this.description = description;
        this.storage = storage;
    }

    @Override
    public void execute() throws StorageOperationException {
        taskList.addTodo(description);
        storage.saveTasks(taskList);
    }
}
