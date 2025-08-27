import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Watermelon {
    private static final String INDENT = " ".repeat(4);

    private static void processCommand(String input, ArrayList<Task> tasklist)
            throws InvalidInputException, EmptyTaskDescriptionException, EmptyDateException, InvalidCommandException {
        // Matcher todo = Pattern.compile("^todo\\s+(.+)$").matcher(input);
        Matcher todo = Pattern.compile("^todo\\s*(.*)$").matcher(input);
        // Matcher deadline = Pattern.compile("^deadline\\s+([^/]+)(?:/by\\s+(.+))?$").matcher(input);
        Matcher deadline = Pattern.compile(
                "^deadline(?:\\s+([^/]+?))?(?:\\s*/\\s*(?:by\\s*(.+)?)?)?$").matcher(input);
        // Matcher event = Pattern.compile("^event\\s+([^/]+)(?:/from\\s+([^/]+))?(?:/to\\s+(.+))?$").matcher(input);
        Matcher event = Pattern.compile(
                        "^event(?:\\s+([^/]+?))?(?:\\s*/\\s*from(?:\\s+([^/]+?))?)?(?:\\s*/\\s*to(?:\\s+(.+)?)?)?$")
                .matcher(input);
        // Matcher mark = Pattern.compile("^mark (\\d+)$").matcher(input);
        Matcher mark = Pattern.compile("^mark(?:\\s+(.+))?$").matcher(input);
        // Matcher unmark = Pattern.compile("^unmark (\\d+)$").matcher(input);
        Matcher unmark = Pattern.compile("^unmark(?:\\s+(.+))?$").matcher(input);

        if (input.equals("list")) { // list
            handleListCommand(tasklist);
        } else if (mark.matches()) { // mark
            handleMarkCommand(mark, tasklist);
        } else if (unmark.matches()) { // unmark
            handleUnmarkCommand(unmark, tasklist);
        } else if (todo.matches()) { // add todo
            handleTodoCommand(todo, input, tasklist);
        } else if (deadline.matches()) { // add deadline
            handleDeadlineCommand(deadline, tasklist);
        } else if (event.matches()) { // add event
            handleEventCommand(event, tasklist);
        } else { // invalid command
            throw new InvalidCommandException();
        }
    }

    private static void handleListCommand(ArrayList<Task> tasklist) {
        System.out.println(INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < tasklist.size(); i++) {
            int task_index = i + 1;
            System.out.println(INDENT + task_index + "." + tasklist.get(i));
        }
    }

    private static void handleTodoCommand(Matcher todo, String input, ArrayList<Task> tasklist)
            throws EmptyTaskDescriptionException {
        if (todo.group(1).isEmpty()) {
            throw new EmptyTaskDescriptionException("todo task description is empty!");
        }
        Task task = new Todo(input);
        tasklist.add(task);
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + INDENT + task);
        System.out.println(INDENT + String.format("Now you have %d tasks in the list.", tasklist.size()));
    }

    private static void handleDeadlineCommand(Matcher deadline, ArrayList<Task> tasklist)
            throws EmptyTaskDescriptionException, EmptyDateException {
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

    private static void handleEventCommand(Matcher event, ArrayList<Task> tasklist)
            throws EmptyTaskDescriptionException, EmptyDateException {
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

    private static void handleMarkCommand(Matcher mark, ArrayList<Task> tasklist) throws InvalidInputException {
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

    private static void handleUnmarkCommand(Matcher unmark, ArrayList<Task> tasklist) throws InvalidInputException {
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

    public static void main(String[] args) {
        String logo = "__        __    _                           _            \n"
                    + "\\ \\      / /_ _| |_ ___ _ __ _ __ ___   ___| | ___  _ __  \n"
                    + " \\ \\ /\\ / / _` | __/ _ \\ '__| '_ ` _ \\ / _ \\ |/ _ \\| '_ \\ \n"
                    + "  \\ V  V / (_| | ||  __/ |  | | | | | |  __/ | (_) | | | |\n"
                    + "   \\_/\\_/ \\__,_|\\__\\___|_|  |_| |_| |_|\\___|_|\\___/|_| |_|\n";

        System.out.println("Hello from\n" + logo);

        System.out.println(INDENT + "____________________________________________________________");
        System.out.println(INDENT + " Hello! I'm Watermelon\n" +
                INDENT + " What can I do for you?");
        System.out.println(INDENT + "____________________________________________________________");

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        ArrayList<Task> tasklist = new ArrayList<>();

        while (!input.equals("bye")) {
            System.out.println(INDENT + "____________________________________________________________");

            try {
                processCommand(input, tasklist);
            } catch (WatermelonException e) {
                System.out.println("Error: " + e.getMessage());
            }

            System.out.println(INDENT + "____________________________________________________________");
            input = scanner.nextLine();
        }
        // "bye"
        scanner.close();
        System.out.println(INDENT + "____________________________________________________________");
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        System.out.println(INDENT + "____________________________________________________________");
    }
}