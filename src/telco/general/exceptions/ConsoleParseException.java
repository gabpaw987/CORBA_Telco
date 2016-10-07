package telco.general.exceptions;

/**
 * This exception can occur if there is a problem when parsing console arguments.
 *
 * @author Gabriel
 */
public class ConsoleParseException extends Exception{

    /**
     * Creates a new instance of
     * <code>ConsoleParseException</code> without detail message.
     */
    public ConsoleParseException() {
        super();
    }

    /**
     * Constructs an instance of
     * <code>ConsoleParseException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ConsoleParseException(String msg) {
        super(msg);
    }
}
