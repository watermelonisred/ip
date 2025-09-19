package watermelon.command;

import java.time.LocalDate;
import java.util.ArrayList;

import watermelon.TaskList;
import watermelon.Ui;
import watermelon.exception.InvalidTaskTypeException;
import watermelon.exception.StorageOperationException;
import watermelon.task.Task;

/**
 * Represents a schedule command that contains a date, ui, storage, tasklist and message.
 * Upon execution, tasks scheduled on specified date are saved into the message.
 */
public class ScheduleCommand extends Command {
    private LocalDate date;

    /**
     * Constructs a ScheduleCommand object with given details.
     */
    public ScheduleCommand(TaskList taskList, LocalDate date, Ui ui) {
        super.taskList = taskList;
        super.ui = ui;
        this.date = date;
    }

    @Override
    public void execute() throws StorageOperationException, InvalidTaskTypeException {
        ArrayList<Task> scheduledTasks = taskList.findScheduledTask(date);
        assert scheduledTasks != null : "scheduledTasks should not be null";

        if (scheduledTasks.isEmpty()) {
            message = ui.showNoTasksScheduledMessage();
            return;
        }

        message = ui.showTasksScheduledMessage(scheduledTasks, date);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
