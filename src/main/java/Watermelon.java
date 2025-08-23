import java.util.Scanner;

public class Watermelon {
    public static void main(String[] args) {
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

        String indent = " ".repeat(4);

        System.out.println(indent + "____________________________________________________________");

        System.out.println(indent + " Hello! I'm Watermelon\n" +
                indent + " What can I do for you?");

        System.out.println(indent + "____________________________________________________________");

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        // System.out.println(input);
        while (!input.equals("bye")) {
            System.out.println(indent + "____________________________________________________________");
            System.out.println(indent + input);
            System.out.println(indent + "____________________________________________________________");
            input = scanner.nextLine();
        }
        scanner.close();
        System.out.println(indent + "____________________________________________________________");
        System.out.println(indent + "Bye. Hope to see you again soon!");
        System.out.println(indent + "____________________________________________________________");
    }
}
