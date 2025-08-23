import java.util.ArrayList;
import java.util.Scanner;

public class Watermelon {
    public static void main(String[] args) {
        String indent = " ".repeat(4);
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
         */
        String logo = "__        __    _                           _             \n"
                + "\\ \\      / /_ _| |_ ___ _ __ _ __ ___   ___| | ___  _ __  \n"
                + " \\ \\ /\\ / / _` | __/ _ \\ '__| '_ ` _ \\ / _ \\ |/ _ \\| '_ \\ \n"
                + "  \\ V  V / (_| | ||  __/ |  | | | | | |  __/   (_) | | | |\n"
                + "   \\_/\\_/ \\__,_|\\__\\___|_|  |_| |_| |_|\\___|_|\\___/|_| |_|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(indent + "____________________________________________________________");

        System.out.println(indent + " Hello! I'm Watermelon\n" +
                indent + " What can I do for you?");

        System.out.println(indent + "____________________________________________________________");

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        ArrayList<String> tasklist = new ArrayList<>();
        while (!input.equals("bye")) {
            System.out.println(indent + "____________________________________________________________");
            if (input.equals("list")) {
                // tasklist is empty
                if (tasklist.size() == 0) {
                    System.out.println(indent + "tasklist is empty");
                }
                // tasklist is not empty
                for (int i = 0; i < tasklist.size(); i++) {
                    int task_index = i + 1;
                    System.out.println(indent + task_index + ". " + tasklist.get(i));
                }
            } else {
                tasklist.add(input);
                System.out.println(indent + "added: " + input);
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
