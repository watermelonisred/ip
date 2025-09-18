package watermelon;

import java.io.PrintWriter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import watermelon.task.Deadline;
import watermelon.task.Event;
import watermelon.task.Task;
import watermelon.task.Todo;

/**
 * Represents a list of tasks that can be managed and edited.
 * Provides methods to add, mark/unmark, delete, display and save tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a new {@code TaskList} with an existing list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the number of tasks stored in the {@code TaskList}.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Writes all tasks in this TaskList to the specified {@link PrintWriter},
     * using each task’s {@link Task#toFileFormat()} representation.
     * Empty lines are ignored.
     *
     * @param writer The writer to which tasks will be saved.
     */
    public void saveToFile(PrintWriter writer) {
        for (Task task : tasks) {
            String line = task.toFileFormat();
            if (!line.isEmpty()) {
                writer.println(line);
            }
        }
    }

    /**
     * Marks the task at the given index as done and prints confirmation message.
     *
     * @param taskNumber The task number of the task to be marked.
     */
    public Task markTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Marks the task at the given index as undone and prints confirmation message.
     *
     * @param taskNumber The task number of the task to be unmarked.
     */
    public Task unmarkTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.markAsUndone();
        return task;
    }

    /**
     * Adds a new {@link Todo} task to the TaskList and prints confirmation message.
     *
     * @param description The task description.
     */
    public Task addTodo(String description) {
        Task task = new Todo(description);
        tasks.add(task);
        return task;
    }

    /**
     * Adds a new {@link Deadline} task to the TaskList and prints confirmation message.
     *
     * @param description The task description.
     * @param by The due date/time in expected format ("ddMMyyyy HHmm").
     * @throws DateTimeParseException If the date/time string does not match the "ddMMyyyy HHmm" pattern.
     */
    public Task addDeadline(String description, String by)
            throws DateTimeParseException {
        Task task = new Deadline(description, by);
        tasks.add(task);
        return task;
    }

    /**
     * Adds a new {@link Event} task to the TaskList and prints confirmation message.
     *
     * @param description The task description.
     * @param from The start date/time in expected format ("ddMMyyyy HHmm").
     * @param to The end date/time in expected format ("ddMMyyyy HHmm").
     * @throws DateTimeParseException If either date/time string does not match the "ddMMyyyy HHmm" pattern.
     */
    public Task addEvent(String description, String from, String to)
            throws DateTimeParseException {
        Task task = new Event(description, from, to);
        tasks.add(task);
        return task;
    }

    /**
     * Deletes the task at the given index from the TaskList and prints confirmation message.
     *
     * @param taskNumber The task number of the task to be deleted.
     */
    public Task deleteTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        return task;
    }

    /**
     * Finds tasks with description matching given keyword from the TaskList and prints to standard output.
     *
     * @param keyword The keyword of the task to be searched.
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }
}
