public class EmptyTaskDescriptionException extends WatermelonException {
    public EmptyTaskDescriptionException(String message) {
        super(message);
    }

    public EmptyTaskDescriptionException() {
        super("empty date!");
    }
}