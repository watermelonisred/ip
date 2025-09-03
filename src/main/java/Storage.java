import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Handles saving and loading of tasks to/from disk storage
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor that accepts a file path
     * @param filePath the path to the data file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file
     * @return ArrayList of tasks loaded from file, empty list if file doesn't exist
     * @throws IOException if there's an error reading from file
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks; // Return empty list if file doesn't exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTaskFromFile(line.trim());
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves tasks to the storage file
     * @param tasks the list of tasks to save
     * @throws StorageOperationException if there's an error writing to file
     */
    public void saveTasks(ArrayList<Task> tasks) throws StorageOperationException {
        createDirectory();

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                String line = taskToFileFormat(task);
                if (!line.isEmpty()) {
                    writer.println(line);
                }
            }
        } catch (IOException e) {
            throw new StorageOperationException("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Creates the parent directory if it doesn't exist
     * @throws StorageOperationException if directory creation fails
     */
    private void createDirectory() throws StorageOperationException {
        try {
            Path parentDir = Paths.get(filePath).getParent();
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }
        } catch (IOException e) {
            throw new StorageOperationException("Error creating directory: " + e.getMessage());
        }
    }

    /**
     * Converts a Task object to file format string
     * Format: TYPE | STATUS | DESCRIPTION [| ADDITIONAL_INFO]
     * @param task the task to convert
     * @return formatted string representation of the task
     */
    private String taskToFileFormat(Task task) {
        if (task == null) {
            return "";
        }

        if (task instanceof Todo) {
            return task.toFileFormat();
        } else if (task instanceof Deadline) {
            return task.toFileFormat();
        } else if (task instanceof Event) {
            return task.toFileFormat();
        }

        return "";
    }

    /**
     * Parses a line from the file and creates the appropriate Task object
     * @param line the line to parse
     * @return Task object created from the line, null if parsing fails
     */
    private Task parseTaskFromFile(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }

        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            return null; // Invalid format
        }

        String taskType = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task = null;

        try {
            switch (taskType) {
            case "T":
                task = new Todo(description, isDone);
                break;
            case "D":
                if (parts.length >= 4) {
                    String by = parts[3].trim();
                    task = new Deadline(description, by, isDone);
                }
                break;
            case "E":
                if (parts.length >= 5) {
                    String from = parts[3].trim();
                    String to = parts[4].trim();
                    task = new Event(description, from, to, isDone);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown task type: " + taskType); // Unknown task type
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }

        return task;
    }

    /**
     * Checks if the storage file exists
     * @return true if the file exists, false otherwise
     */
    public boolean fileExists() {
        return new File(filePath).exists();
    }

    /**
     * Gets the file path being used for storage
     * @return the file path
     */
    public String getFilePath() {
        return filePath;
    }
}