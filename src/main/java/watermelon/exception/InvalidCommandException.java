package watermelon.exception;

public class InvalidCommandException extends WatermelonException {
    public InvalidCommandException(String message) {
        super(message);
    }

    public InvalidCommandException() {
        super("invalid command!");
    }
}
