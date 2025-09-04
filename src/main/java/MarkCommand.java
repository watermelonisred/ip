public class MarkCommand extends Command {
    private int taskNumber;
    private Storage storage;

    public MarkCommand(TaskList tasklist, int taskNumber, Storage storage) {
        super.tasklist = tasklist;
        this.taskNumber = taskNumber;
        this.storage = storage;
    }

    @Override
    public void execute() throws StorageOperationException {
        tasklist.markTask(taskNumber);
        storage.saveTasks(tasklist);
    }
}
