package pl.polsl.models;

/**
 * Custom exception class. This exception is thrown when an error occurs related to the compression or decompression algorithm.
 * For example, an exception of this type is thrown when the user types a symbol in the input string.
 * @author Rafał Klinowski
 * @version 1.0
 */
public class TextCompressionException extends Exception {
    /**
     * Constructor calling base constructor of the Exception class.
     * @param errorMessage Text containing details about the exception, displayed on the user's screen.
     */
    public TextCompressionException(String errorMessage)
    {
        super(errorMessage);
    }
}
