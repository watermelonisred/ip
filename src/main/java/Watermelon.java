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

            Matcher todo = Pattern.compile("^todo\\s+(.+)$").matcher(input);
            Matcher deadline = Pattern.compile("^deadline\\s+([^/]+)(?:/by\\s+(.+))?$").matcher(input);
            Matcher event = Pattern.compile("^event\\s+([^/]+)(?:/from\\s+([^/]+))?(?:/to\\s+(.+))?$").matcher(input);
            Matcher mark = Pattern.compile("^mark (\\d+)$").matcher(input);
            Matcher unmark = Pattern.compile("^unmark (\\d+)$").matcher(input);

            if (input.equals("list")) { // list
                System.out.println(indent + "Here are the tasks in your list:");
                for (int i = 0; i < tasklist.size(); i++) {
                    int task_index = i + 1;
                    System.out.println(indent + task_index + "." + tasklist.get(i));
                }
            } else if (mark.matches()) { // mark
                int taskNumber = Integer.parseInt(mark.group(1));
                System.out.println(indent + "Nice! I've marked this task as done:");
                Task task = tasklist.get(taskNumber - 1);
                task.markAsDone();
                System.out.println(indent + indent + task);
            } else if (unmark.matches()){ // unmark
                int taskNumber = Integer.parseInt(unmark.group(1));
                System.out.println(indent + "OK, I've marked this task as not done yet:");
                Task task = tasklist.get(taskNumber - 1);
                task.markAsUndone();
                System.out.println(indent + indent + task);
            } else if (todo.matches()) { // add todo
                Task task = new Todo(input);
                tasklist.add(task);
                System.out.println(indent + "Got it. I've added this task:");
                System.out.println(indent + indent + task);
                System.out.println(indent + String.format("Now you have %d tasks in the list.", tasklist.size()));
            } else if (deadline.matches()) { // add deadline
                Task task = new Deadline(deadline.group(1).trim(), deadline.group(2));
                tasklist.add(task);
                System.out.println(indent + "Got it. I've added this task:");
                System.out.println(indent + indent + task);
                System.out.println(indent + String.format("Now you have %d tasks in the list.", tasklist.size()));
            } else if (event.matches()) { // add event
                Task task = new Event(event.group(1).trim(), event.group(2).trim(), event.group(3));
                tasklist.add(task);
                System.out.println(indent + "Got it. I've added this task:");
                System.out.println(indent + indent + task);
                System.out.println(indent + String.format("Now you have %d tasks in the list.", tasklist.size()));
            } else { // invalid command
                System.out.println(indent + "Sorry, I couldn't understand your message. Could you please try again?");
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
