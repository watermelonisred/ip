public class UnmarkCommand extends Command {
    private int taskNumber;
    private Storage storage;

    public UnmarkCommand(TaskList tasklist, int taskNumber, Storage storage) {
        super.tasklist = tasklist;
        this.taskNumber = taskNumber;
        this.storage = storage;
    }

    @Override
    public void execute() throws StorageOperationException {
        tasklist.unmarkTask(taskNumber);
        storage.saveTasks(tasklist);
    }
}
