package watermelon.command;

import watermelon.Storage;
import watermelon.TaskList;
import watermelon.exception.StorageOperationException;

public class TodoCommand extends Command {
    private String description;
    private Storage storage;

    public TodoCommand(TaskList tasklist, String description, Storage storage) {
        super.tasklist = tasklist;
        this.description = description;
        this.storage = storage;
    }

    @Override
    public void execute() throws StorageOperationException {
        tasklist.addTodo(description);
        storage.saveTasks(tasklist);
    }
}
