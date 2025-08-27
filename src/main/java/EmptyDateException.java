public class EmptyDateException extends WatermelonException {
    public EmptyDateException(String message) {
        super(message);
    }

    public EmptyDateException() {
        super("missing date/time!");
    }
}
