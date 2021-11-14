package pl.polsl.models;

/**
 * Custom exception class. This exception is thrown when an error occurs related to the History collection.
 * For example, an exception of this type is thrown when the user requests an entry with a negative index.
 * @author Rafał Klinowski
 * @version 1.0
 */
public class HistoryException extends Exception {
    /**
     * Constructor calling base constructor of the Exception class.
     * @param errorMessage Text containing details about the exception, displayed on the user's screen.
     */
    public HistoryException(String errorMessage)
    {
        super(errorMessage);
    }
}
