package watermelon.command;

import watermelon.TaskList;
import watermelon.Ui;
import watermelon.exception.StorageOperationException;
import watermelon.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(TaskList taskList, String keyword, Ui ui) {
        super.taskList = taskList;
        super.ui = ui;
        this.keyword = keyword;
    }

    @Override
    public void execute() throws StorageOperationException {
        ArrayList<Task> matchingTasks = taskList.findTask(keyword);

        if (matchingTasks.isEmpty()) {
            message = ui.showNoTasksFoundMessage();
            return;
        }

        message = ui.showTasksFoundMessage(matchingTasks);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
