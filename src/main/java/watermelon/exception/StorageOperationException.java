package watermelon.exception;

/**
 * Exception thrown when storage operations fail
 */
public class StorageOperationException extends WatermelonException {

    /**
     * Constructor with error message
     * @param message the error message
     */
    public StorageOperationException(String message) {
        super(message);
    }
}
