import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.models.Algorithm;
import pl.polsl.models.TextCompressionException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class containing tests of the Text Compression algorithm.
 * @author Rafał Klinowski
 * @version 1.0
 */
public class CompressionTest {

    /**
     * Variable representing the object on which we will invoke all the tests.
     */
    private Algorithm algorithm;

    /**
     * Setup function, called automatically before each test.
     * The function creates a new object of type Algorithm.
     */
    @BeforeEach
    void setUp() {
        algorithm = new Algorithm();
    }

    /**
     * Parametrized function testing the compress method for valid input.
     * The function is called multiple times; input and expected output values are taken from a csv source.
     * Expected behaviour: the actual output matches the expected output.
     */
    @ParameterizedTest
    @CsvSource({"aaaabbcc,a4b2c2", "xyz,x1y1z1", "aaaaaaaAAaaaaaaaaa,a7A2a9", "asdfaassddffASDF,a1s1d1f1a2s2d2f2A1S1D1F1"})
    void testCompressionResultForValidInput(String input, String expected) {
        try {
            String actual = algorithm.compress(input);
            assertEquals(expected, actual);
        }
        catch (TextCompressionException ex) {
            fail("The method was not supposed to throw any exceptions for this input!");
        }
    }

    /**
     * Parametrized function testing the compress method for invalid input (containing numbers or characters).
     * The function is called multiple times; input values are taken from a value source.
     * Expected behaviour: the function throws an exception of type TextCompressionException every time.
     */
    @ParameterizedTest
    @ValueSource(strings = {"aaaa,bbcc", "A1B1C1", ""})
    void testCompressionThrowsForInvalidInput(String input) {
        try {
            algorithm.compress(input);
            fail("The method was supposed to throw an exception of type TextCompressionException for invalid input!");
        }
        catch (TextCompressionException ex) {
            return;
        }
    }

    /**
     * Function testing the compress method for null input;
     * Expected behaviour: the function throws an exception of type TextCompressionException.
     */
    @Test
    void testCompressionThrowsForNullInput() {
        String input = null;

        try {
            algorithm.compress(input);
            fail("The method was supposed to throw an exception of type TextCompressionException for null input!");
        }
        catch (TextCompressionException ex) {
            return;
        }
    }
}
