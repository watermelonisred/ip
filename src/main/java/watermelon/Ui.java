package watermelon;

import java.util.Scanner;

/**
 * Handles all user interface interactions for the Watermelon chatbot application.
 *
 * <p> This class manages displaying welcome/exit messages,
 * reading user input, printing error messages and printing separator lines.
 * It encapsulates a {@link Scanner} to read commands from standard input. </p>
 */
public class Ui {
    /** Indentation string used for console output. */
    private static final String INDENT = " ".repeat(4);

    /** Scanner for reading user input from standard input. */
    private Scanner scanner;

    /**
     * Creates a new {@code Ui} instance and initializes the input scanner.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays the Watermelon logo and welcome message to the console.
     */
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

    /**
     * Closes the scanner and displays a goodbye message.
     */
    public void endChat() {
        scanner.close();
        System.out.println(INDENT + "____________________________________________________________");
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        System.out.println(INDENT + "____________________________________________________________");
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The line entered by the user.
     */
    public String readCommand() { // returns input from user
        return scanner.nextLine();
    }

    /**
     * Displays an error message to the console.
     *
     * @param message The error message to display.
     */
    public void showErrorMessage(String message) {
        System.out.println(message);

    }

    /**
     * Prints a separator line to the console.
     */
    public void showLine() {
        System.out.println(INDENT + "____________________________________________________________");
    }
}