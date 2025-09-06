package watermelon;

import java.io.PrintWriter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import watermelon.task.Task;
import watermelon.task.Todo;
import watermelon.task.Deadline;
import watermelon.task.Event;

/**
 * Represents a list of tasks that can be managed and edited.
 * Provides methods to add, mark/unmark, delete, display and save tasks.
 */
public class TaskList {
    /** Indentation string used for console output. */
    private static final String INDENT = " ".repeat(4);

    private ArrayList<Task> tasklist;

    /**
     * Creates a new {@code TaskList} with an existing list of tasks.
     */
    public TaskList(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
    }

    /**
     * Returns the number of tasks stored in the {@code TaskList}.
     */
    public int size() {
        return tasklist.size();
    }

    /**
     * Writes all tasks in this TaskList to the specified {@link PrintWriter},
     * using each task’s {@link Task#toFileFormat()} representation.
     * Empty lines are ignored.
     *
     * @param writer The writer to which tasks will be saved.
     */
    public void saveToFile(PrintWriter writer) {
        for (Task task : tasklist) {
            String line = task.toFileFormat();
            if (!line.isEmpty()) {
                writer.println(line);
            }
        }
    }

    /**
     * Prints all tasks in the TaskList to standard output, prefixed with their task number.
     */
    public void showList() {
        System.out.println(INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < tasklist.size(); i++) {
            int task_index = i + 1;
            System.out.println(INDENT + task_index + "." + tasklist.get(i));
        }
    }

    /**
     * Marks the task at the given index as done and prints confirmation message.
     *
     * @param taskNumber The task number of the task to be marked.
     */
    public void markTask(int taskNumber) {
        System.out.println(INDENT + "Nice! I've marked this task as done:");
        Task task = tasklist.get(taskNumber - 1);
        task.markAsDone();
        System.out.println(INDENT + INDENT + task);
    }

    /**
     * Marks the task at the given index as undone and prints confirmation message.
     *
     * @param taskNumber The task number of the task to be unmarked.
     */
    public void unmarkTask(int taskNumber) {
        System.out.println(INDENT + "OK, I've marked this task as not done yet:");
        Task task = tasklist.get(taskNumber - 1);
        task.markAsUndone();
        System.out.println(INDENT + INDENT + task);
    }

    /**
     * Adds a new {@link Todo} task to the TaskList and prints confirmation message.
     *
     * @param description The task description.
     */
    public void addTodo(String description) {
        Task task = new Todo(description);
        tasklist.add(task);
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + INDENT + task);
        System.out.println(INDENT + String.format("Now you have %d tasks in the list.", tasklist.size()));
    }

    /**
     * Adds a new {@link Deadline} task to the TaskList and prints confirmation message.
     *
     * @param description The task description.
     * @param by The due date/time in expected format ("ddMMyyyy HHmm").
     * @throws DateTimeParseException If the date/time string does not match the "ddMMyyyy HHmm" pattern.
     */
    public void addDeadline(String description, String by)
            throws DateTimeParseException {
        Task task = new Deadline(description, by);
        tasklist.add(task);
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + INDENT + task);
        System.out.println(INDENT + String.format("Now you have %d tasks in the list.", tasklist.size()));
    }

    /**
     * Adds a new {@link Event} task to the TaskList and prints confirmation message.
     *
     * @param description The task description.
     * @param from The start date/time in expected format ("ddMMyyyy HHmm").
     * @param to The end date/time in expected format ("ddMMyyyy HHmm").
     * @throws DateTimeParseException If either date/time string does not match the "ddMMyyyy HHmm" pattern.
     */
    public void addEvent(String description, String from, String to)
            throws DateTimeParseException {
        Task task = new Event(description, from, to);
        tasklist.add(task);
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + INDENT + task);
        System.out.println(INDENT + String.format("Now you have %d tasks in the list.", tasklist.size()));
    }

    /**
     * Deletes the task at the given index from the TaskList and prints confirmation message.
     *
     * @param taskNumber The task number of the task to be deleted.
     */
    public void deleteTask(int taskNumber) {
        Task task = tasklist.get(taskNumber - 1);
        System.out.println(INDENT + "Noted. I've removed this task:");
        System.out.println(INDENT + INDENT + task);
        tasklist.remove(taskNumber - 1);
        System.out.println(INDENT + String.format("Now you have %d tasks in the list.", tasklist.size()));
    }
}