public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;
    private Storage storage;

    public EventCommand(TaskList tasklist, String description, String from, String to, Storage storage) {
        super.tasklist = tasklist;
        this.description = description;
        this.from = from;
        this.to = to;
        this.storage = storage;
    }

    @Override
    public void execute() throws StorageOperationException {
        tasklist.addEvent(description, from, to);
        storage.saveTasks(tasklist);
    }
}
