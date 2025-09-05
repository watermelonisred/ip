package watermelon;

import java.time.format.DateTimeParseException;

import watermelon.command.Command;
import watermelon.exception.WatermelonException;

public class Watermelon {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private Parser parser;

    public Watermelon(String filePath) {
        storage = new Storage(filePath); // initialise storage
        ui = new Ui();
        taskList = new TaskList(storage.loadTasks()); // load existing tasks from storage
        parser = new Parser(taskList, storage);
    }

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

    public static void main(String[] args) {
        new Watermelon("./data/watermelon.txt").run();
    }
}