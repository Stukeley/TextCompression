import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.models.Algorithm;
import pl.polsl.models.TextCompressionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Class containing tests of the Text Decompression algorithm.
 * @author Rafał Klinowski
 * @version 1.0
 */
public class DecompressionTest {

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
     * Parametrized function testing the decompress method for valid input.
     * The function is called multiple times; input and expected output values are taken from a csv source.
     * Expected behaviour: the actual output matches the expected output.
     */
    @ParameterizedTest
    @CsvSource({"a4b2c2,aaaabbcc", "x1y1z1,xyz", "a7A2a9,aaaaaaaAAaaaaaaaaa", "a1s1d1f1a2s2d2f2A1S1D1F1,asdfaassddffASDF"})
    void testDecompressionResultForValidInput(String input, String expected) {
        try {
            String actual = algorithm.decompress(input);
            assertEquals(expected, actual);
        }
        catch (TextCompressionException ex) {
            fail("The method was not supposed to throw any exceptions for this input!");
        }
    }

    /**
     * Parametrized function testing the decompress method for invalid input (containing characters, no numbers or a zero).
     * The function is called multiple times; input values are taken from a value source.
     * Expected behaviour: the function throws an exception of type TextCompressionException every time.
     */
    @ParameterizedTest
    @ValueSource(strings = {"a4,b2c2", "aaaabbcc", "", "a0b2c0"})
    void testDecompressionThrowsForInvalidInput(String input) {

        try {
            algorithm.decompress(input);
            fail("The method was supposed to throw an exception of type TextCompressionException for invalid input!");
        }
        catch (TextCompressionException ex) {
            return;
        }
    }

    /**
     * Function testing the decompress method for null input;
     * Expected behaviour: the function throws an exception of type TextCompressionException.
     */
    @Test
    void testDecompressionThrowsForNullInput() {
        String input = null;

        try {
            algorithm.decompress(input);
            fail("The method was supposed to throw an exception of type TextCompressionException for null input!");
        }
        catch (TextCompressionException ex) {
            return;
        }
    }
}
