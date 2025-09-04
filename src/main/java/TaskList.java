import java.io.PrintWriter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class TaskList {
    private static final String INDENT = " ".repeat(4);
    private ArrayList<Task> tasklist;

    public TaskList(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
    }

    public void saveToFile(PrintWriter writer) {
        for (Task task : tasklist) {
            String line = task.toFileFormat();
            if (!line.isEmpty()) {
                writer.println(line);
            }
        }
    }

    public void showList() {
        System.out.println(INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < tasklist.size(); i++) {
            int task_index = i + 1;
            System.out.println(INDENT + task_index + "." + tasklist.get(i));
        }
    }

    public void markTask(Matcher mark) throws InvalidInputException {
        if (mark.group(1) == null || mark.group(1).isEmpty()) {
            throw new InvalidInputException("missing task number!");
        }
        if (!mark.group(1).trim().matches("-?\\d+")) {
            throw new InvalidInputException("not a valid integer!");
        }
        int taskNumber = Integer.parseInt(mark.group(1));
        if (taskNumber < 1 || taskNumber > tasklist.size()) {
            throw new InvalidInputException("not a valid task number!");
        }
        System.out.println(INDENT + "Nice! I've marked this task as done:");
        Task task = tasklist.get(taskNumber - 1);
        task.markAsDone();
        System.out.println(INDENT + INDENT + task);
    }

    public void unmarkTask(Matcher unmark) throws InvalidInputException {
        if (unmark.group(1) == null || unmark.group(1).isEmpty()) {
            throw new InvalidInputException("missing task number!");
        }
        if (!unmark.group(1).trim().matches("-?\\d+")) {
            throw new InvalidInputException("not a valid integer!");
        }
        int taskNumber = Integer.parseInt(unmark.group(1));
        if (taskNumber < 1 || taskNumber > tasklist.size()) {
            throw new InvalidInputException("not a valid task number!");
        }
        System.out.println(INDENT + "OK, I've marked this task as not done yet:");
        Task task = tasklist.get(taskNumber - 1);
        task.markAsUndone();
        System.out.println(INDENT + INDENT + task);
    }

    public void addTodo(Matcher todo)
            throws EmptyTaskDescriptionException {
        if (todo.group(1).isEmpty()) {
            throw new EmptyTaskDescriptionException("todo task description is empty!");
        }
        Task task = new Todo(todo.group(1).trim());
        tasklist.add(task);
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + INDENT + task);
        System.out.println(INDENT + String.format("Now you have %d tasks in the list.", tasklist.size()));
    }

    public void addDeadline(Matcher deadline)
            throws EmptyTaskDescriptionException, EmptyDateException, DateTimeParseException {
        if (deadline.group(1) == null || deadline.group(1).isEmpty()) {
            throw new EmptyTaskDescriptionException("deadline task description is empty!");
        }
        if (deadline.group(2) == null || deadline.group(2).isEmpty()) {
            throw new EmptyDateException();
        }
        Task task = new Deadline(deadline.group(1).trim(), deadline.group(2));
        tasklist.add(task);
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + INDENT + task);
        System.out.println(INDENT + String.format("Now you have %d tasks in the list.", tasklist.size()));
    }

    public void addEvent(Matcher event)
            throws EmptyTaskDescriptionException, EmptyDateException, DateTimeParseException {
        if (event.group(1) == null || event.group(1).isEmpty()) {
            throw new EmptyTaskDescriptionException("event task description is empty!");
        }
        if (event.group(2) == null || event.group(2).isEmpty()) {
            throw new EmptyDateException("start date/time is missing!");
        }
        if (event.group(3) == null || event.group(3).isEmpty()) {
            throw new EmptyDateException("end date/time is missing!");
        }
        Task task = new Event(event.group(1).trim(), event.group(2).trim(), event.group(3));
        tasklist.add(task);
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + INDENT + task);
        System.out.println(INDENT + String.format("Now you have %d tasks in the list.", tasklist.size()));
    }

    public void deleteTask(Matcher delete) throws InvalidInputException {
        if (delete.group(1) == null || delete.group(1).isEmpty()) {
            throw new InvalidInputException("missing task number!");
        }
        if (!delete.group(1).trim().matches("-?\\d+")) {
            throw new InvalidInputException("not a valid integer!");
        }
        int taskNumber = Integer.parseInt(delete.group(1));
        if (taskNumber < 1 || taskNumber > tasklist.size()) {
            throw new InvalidInputException("not a valid task number!");
        }
        Task task = tasklist.get(taskNumber - 1);
        System.out.println(INDENT + "Noted. I've removed this task:");
        System.out.println(INDENT + INDENT + task);
        tasklist.remove(taskNumber - 1);
        System.out.println(INDENT + String.format("Now you have %d tasks in the list.", tasklist.size()));
    }
}