package watermelon.command;

import watermelon.TaskList;

public class ListCommand extends Command {
    public ListCommand(TaskList taskList) {
        super.taskList = taskList;
    }

    @Override
    public void execute() {
        taskList.showList();
    }
}
