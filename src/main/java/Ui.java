import java.util.Scanner;

public class Ui {
    private static final String INDENT = " ".repeat(4);
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcomeMessage() {
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
    }

    public void endChat() {
        scanner.close();
        System.out.println(INDENT + "____________________________________________________________");
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        System.out.println(INDENT + "____________________________________________________________");
    }

    public String readCommand() { // returns input from user
        return scanner.nextLine();
    }

    public void showErrorMessage(String msg) {
        System.out.println(msg);

    }

    public void showLine() {
        System.out.println(INDENT + "____________________________________________________________");
    }
}
