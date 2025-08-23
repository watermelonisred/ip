import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Watermelon {
    public static void main(String[] args) {
        String logo = "__        __    _                           _             \n"
                + "\\ \\      / /_ _| |_ ___ _ __ _ __ ___   ___| | ___  _ __  \n"
                + " \\ \\ /\\ / / _` | __/ _ \\ '__| '_ ` _ \\ / _ \\ |/ _ \\| '_ \\ \n"
                + "  \\ V  V / (_| | ||  __/ |  | | | | | |  __/   (_) | | | |\n"
                + "   \\_/\\_/ \\__,_|\\__\\___|_|  |_| |_| |_|\\___|_|\\___/|_| |_|\n";

        String indent = " ".repeat(4);
        System.out.println("Hello from\n" + logo);

        System.out.println(indent + "____________________________________________________________");
        System.out.println(indent + " Hello! I'm Watermelon\n" +
                indent + " What can I do for you?");
        System.out.println(indent + "____________________________________________________________");

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        ArrayList<Task> tasklist = new ArrayList<>();
        while (!input.equals("bye")) {
            System.out.println(indent + "____________________________________________________________");
            Matcher markMatcher = Pattern.compile("^mark (\\d+)$").matcher(input);
            Matcher unmarkMatcher = Pattern.compile("^unmark (\\d+)$").matcher(input);
            if (input.equals("list")) { // list
                System.out.println(indent + "Here are the tasks in your list:");
                for (int i = 0; i < tasklist.size(); i++) {
                    int task_index = i + 1;
                    System.out.println(indent + task_index + "." + tasklist.get(i));
                }
            } else if (markMatcher.matches()) { // mark
                int taskNumber = Integer.parseInt(markMatcher.group(1));
                System.out.println(indent + "Nice! I've marked this task as done:");
                Task task = tasklist.get(taskNumber - 1);
                task.markAsDone();
                System.out.println(indent + indent + task);
            } else if (unmarkMatcher.matches()){ // unmark
                int taskNumber = Integer.parseInt(unmarkMatcher.group(1));
                System.out.println(indent + "OK, I've marked this task as not done yet:");
                Task task = tasklist.get(taskNumber - 1);
                task.markAsUndone();
                System.out.println(indent + indent + task);
            } else { // add tasks
                Task task = new Task(input);
                tasklist.add(task);
                System.out.println(indent + "added: " + task.getDescription());
            }
            System.out.println(indent + "____________________________________________________________");
            input = scanner.nextLine();
        }
        // "bye"
        scanner.close();
        System.out.println(indent + "____________________________________________________________");
        System.out.println(indent + "Bye. Hope to see you again soon!");
        System.out.println(indent + "____________________________________________________________");
    }
}
