package watermelon.command;

import watermelon.Storage;
import watermelon.TaskList;
import watermelon.exception.StorageOperationException;

public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;
    private Storage storage;

    public EventCommand(TaskList taskList, String description, String from, String to, Storage storage) {
        super.taskList = taskList;
        this.description = description;
        this.from = from;
        this.to = to;
        this.storage = storage;
    }

    @Override
    public void execute() throws StorageOperationException {
        taskList.addEvent(description, from, to);
        storage.saveTasks(taskList);
    }
}
