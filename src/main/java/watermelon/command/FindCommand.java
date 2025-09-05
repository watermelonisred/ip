package watermelon.command;

import watermelon.TaskList;
import watermelon.exception.StorageOperationException;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(TaskList taskList, String keyword) {
        super.tasklist = taskList;
        this.keyword = keyword;
    }

    @Override
    public void execute() throws StorageOperationException {
        tasklist.findTask(keyword);
    }
}
