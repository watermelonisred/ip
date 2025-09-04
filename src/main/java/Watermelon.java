import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Watermelon {
    private static final String INDENT = " ".repeat(4);
    private static final String DATA_FILE = "./data/watermelon.txt";
    private static Storage storage;

    private static void processCommand(String input, TaskList tasklist)
            throws InvalidInputException, EmptyTaskDescriptionException, EmptyDateException,
            InvalidCommandException, StorageOperationException {
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
        Matcher delete = Pattern.compile("^delete(?:\\s+(.+))?$").matcher(input);

        if (input.equals("list")) { // list
            tasklist.showList();
        } else if (mark.matches()) { // mark
            tasklist.markTask(mark);
            storage.saveTasks(tasklist);
        } else if (unmark.matches()) { // unmark
            tasklist.unmarkTask(unmark);
            storage.saveTasks(tasklist);
        } else if (todo.matches()) { // add todo
            tasklist.addTodo(todo);
            storage.saveTasks(tasklist);
        } else if (deadline.matches()) { // add deadline
            tasklist.addDeadline(deadline);
            storage.saveTasks(tasklist);
        } else if (event.matches()) { // add event
            tasklist.addEvent(event);
            storage.saveTasks(tasklist);
        } else if (delete.matches()) { // delete
            tasklist.deleteTask(delete);
            storage.saveTasks(tasklist);
        } else { // invalid command
            throw new InvalidCommandException();
        }
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

        storage = new Storage(DATA_FILE); // initialise storage
        TaskList tasklist;

        // Load existing tasks from storage
        tasklist = new TaskList(storage.loadTasks());

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            System.out.println(INDENT + "____________________________________________________________");

            try {
                processCommand(input, tasklist);
            } catch (WatermelonException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date/time format. Please input date/time in ddMMyyyy HHmm format!");
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