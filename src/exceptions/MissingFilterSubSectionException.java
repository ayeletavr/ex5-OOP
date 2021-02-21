package exceptions;

/** An exception that raises when a filter line is missing. */
public class MissingFilterSubSectionException extends Exception {
    public MissingFilterSubSectionException() {
        super("FILTER sub-section missing.");
    }
}
