package watermelon.command;

import watermelon.TaskList;
import watermelon.exception.StorageOperationException;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(TaskList taskList, String keyword) {
        super.taskList = taskList;
        this.keyword = keyword;
    }

    @Override
    public void execute() throws StorageOperationException {
        taskList.findTask(keyword);
    }
}
