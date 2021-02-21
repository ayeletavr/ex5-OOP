package exceptions;

/** An exception that raises when an order line is missing. */
 public class MissingOrderSubSectionException extends Exception {
    public MissingOrderSubSectionException() {
        super("ORDER sub-section missing.");
    }
}
