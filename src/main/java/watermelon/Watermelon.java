package watermelon;

import java.time.format.DateTimeParseException;

import watermelon.command.Command;
import watermelon.exception.WatermelonException;

/**
 * Entry point of the Watermelon application.
 *
 * <p> This class wires together the main components:
 * {@link Storage}, {@link Ui}, {@link TaskList} and {@link Parser}. </p>
 */
public class Watermelon {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    /**
     * Creates a new Watermelon instance with the specified file path.
     *
     * <p> This sets up the storage, UI, task list (by loading existing tasks), and command parser. </p>
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Watermelon(String filePath) {
        storage = new Storage(filePath); // initialise storage
        ui = new Ui();
        taskList = new TaskList(storage.loadTasks()); // load existing tasks from storage
        parser = new Parser(taskList, storage);
    }

    /**
     * Starts the Watermelon application.
     *
     * <p> Displays a welcome message, enters a loop reading user input,
     * parses and executes commands until the user types "bye".
     * Errors are caught and displayed without terminating the program. </p>
     */
    public void run() {
        ui.showWelcomeMessage();

        String input = ui.readCommand(); // read input

        while (!input.equals("bye")) {
            ui.showLine(); // print divider line

            try {
                Command command = parser.parseCommand(input);
                command.execute();
            } catch (WatermelonException e) {
                ui.showErrorMessage("Error: " + e.getMessage());
            } catch (DateTimeParseException e) {
                ui.showErrorMessage("Invalid date/time format. Please input date/time in ddMMyyyy HHmm format!");
            }

            ui.showLine(); // print divider line
            input = ui.readCommand(); // read next input
        }
        ui.endChat(); // show bye message and close program
    }

    /**
     * The main entry point of the program.
     *
     * <p> Creates a new {@code Watermelon} instance pointing to {@code ./data/watermelon.txt} and runs it. </p>
     */
    public static void main(String[] args) {
        new Watermelon("./data/watermelon.txt").run();
    }
}