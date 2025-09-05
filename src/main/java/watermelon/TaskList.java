package watermelon;

import java.io.PrintWriter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import watermelon.task.Task;
import watermelon.task.Todo;
import watermelon.task.Deadline;
import watermelon.task.Event;

public class TaskList {
    private static final String INDENT = " ".repeat(4);
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public void saveToFile(PrintWriter writer) {
        for (Task task : tasks) {
            String line = task.toFileFormat();
            if (!line.isEmpty()) {
                writer.println(line);
            }
        }
    }

    public void showList() {
        System.out.println(INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int task_index = i + 1;
            System.out.println(INDENT + task_index + "." + tasks.get(i));
        }
    }

    public void markTask(int taskNumber) {
        System.out.println(INDENT + "Nice! I've marked this task as done:");
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        System.out.println(INDENT + INDENT + task);
    }

    public void unmarkTask(int taskNumber) {
        System.out.println(INDENT + "OK, I've marked this task as not done yet:");
        Task task = tasks.get(taskNumber - 1);
        task.markAsUndone();
        System.out.println(INDENT + INDENT + task);
    }

    public void addTodo(String description) {
        Task task = new Todo(description);
        tasks.add(task);
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + INDENT + task);
        System.out.println(INDENT + String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    public void addDeadline(String description, String by)
            throws DateTimeParseException {
        Task task = new Deadline(description, by);
        tasks.add(task);
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + INDENT + task);
        System.out.println(INDENT + String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    public void addEvent(String description, String from, String to)
            throws DateTimeParseException {
        Task task = new Event(description, from, to);
        tasks.add(task);
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + INDENT + task);
        System.out.println(INDENT + String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    public void deleteTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        System.out.println(INDENT + "Noted. I've removed this task:");
        System.out.println(INDENT + INDENT + task);
        tasks.remove(taskNumber - 1);
        System.out.println(INDENT + String.format("Now you have %d tasks in the list.", tasks.size()));
    }
}