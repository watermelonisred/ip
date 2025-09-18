package watermelon.command;

import watermelon.TaskList;
import watermelon.exception.StorageOperationException;
import watermelon.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(TaskList taskList, String keyword) {
        super.taskList = taskList;
        this.keyword = keyword;
    }

    @Override
    public void execute() throws StorageOperationException {
        ArrayList<Task> matchingTasks = taskList.findTask(keyword);
        assert matchingTasks != null : "matchingTasks should not be null";

        if (matchingTasks.isEmpty()) {
            message = "Oops! Cannot find any matching tasks.";
            return;
        }

        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:");

        for (int i = 0; i < matchingTasks.size(); i++) {
            int task_index = i + 1;
            sb.append("\n" + task_index + "." + matchingTasks.get(i));
        }

        message = sb.toString();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
