package watermelon.command;

import watermelon.TaskList;

public class ListCommand extends Command {
    public ListCommand(TaskList tasklist) {
        super.tasklist = tasklist;
    }

    @Override
    public void execute() {
        tasklist.showList();
    }
}
