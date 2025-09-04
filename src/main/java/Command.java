public abstract class Command {
    TaskList tasklist;

    abstract public void execute() throws StorageOperationException;
}
