import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Watermelon {
    private static final String INDENT = " ".repeat(4);
    private static final String DATA_FILE = "./data/watermelon.txt";
    private static Storage storage;

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
                Parser parser = new Parser(tasklist, storage);
                Command command = parser.parseCommand(input);
                command.execute();
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