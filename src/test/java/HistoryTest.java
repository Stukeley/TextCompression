import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.polsl.models.History;
import pl.polsl.models.TextCompressionException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class containing tests of the History class and related collection.
 * @author Rafał Klinowski
 * @version 1.1
 */
public class HistoryTest {
    /**
     * Variable representing the object on which we will invoke all the tests.
     */
    private History history;

    /**
     * Setup function, called automatically before each test.
     * The function creates a new object of type History.
     */
    @BeforeEach
    void setUp() {
        history = new History();

        try {
            history.add("aaaabbcc", "a4b2c2");
            history.add("aabbcc", "a2b2c2");
            history.add("abc", "a1b1c1");
        }
        catch (TextCompressionException ex) {
            fail("The method was not supposed to throw for this input.");
        }
    }

    /**
     * Function testing the getHistorySize method.
     * It adds some objects to the History, then verifies if the size is correct.
     * Expected behaviour: size is equal to the amount of elements added.
     */
    @Test
    void historySizeUpdatesCorrectly() {

        assertEquals(3, history.getHistorySize(), "History size did not update properly!");

        try {
            history.add("new", "n1e1w1");
            assertEquals(4, history.getHistorySize(), "History size did not update properly!");
        }
        catch (TextCompressionException ex) {
            fail("The method was not supposed to throw for this input.");
        }
    }

    /**
     * Function testing the getEntryOutputByInput method for valid input.
     * It adds some objects to the History, then verifies if the returned objects are correct.
     * Expected behaviour: the actual output matches the expected output.
     */
    @Test
    void historyGetsValueByInput() {
        String firstInput = "aaaabbcc", firstOutput = "a4b2c2";
        String thirdInput = "abc", thirdOutput = "a1b1c1";

        try {
            String first = history.getEntryOutputByInput(firstInput);
            String third = history.getEntryOutputByInput(thirdInput);

            assertEquals(firstOutput, first, "Wrong element was returned from History!");
            assertEquals(thirdOutput, third, "Wrong element was returned from History!");
        }
        catch (TextCompressionException ex) {
            fail("The method was not supposed to throw any exceptions for this input!");
        }
    }

    /**
     * Function testing the getEntryByIndex method for valid input.
     * It adds some objects to the History, then verifies if the returned objects are correct.
     * Expected behaviour: the actual output and input of the returned object matches the expected values.
     */
    @Test
    void historyGetsValueByIndex() {
        String firstInput = "aaaabbcc", firstOutput = "a4b2c2";
        String thirdInput = "abc", thirdOutput = "a1b1c1";

        try {
            Map.Entry<String, String> first = history.getEntryByIndex(0);
            Map.Entry<String, String> third = history.getEntryByIndex(2);

            assertEquals(firstInput, first.getKey(), "Wrong value was returned from History!");
            assertEquals(firstOutput, first.getValue(), "Wrong value was returned from History!");
            assertEquals(thirdInput, third.getKey(), "Wrong value was returned from History!");
            assertEquals(thirdOutput, third.getValue(), "Wrong value was returned from History!");
        }
        catch (TextCompressionException ex) {
            fail("The method was not supposed to throw any exceptions for this input!");
        }
    }

    /**
     * Function testing the getEntryByIndex method for invalid index value.
     * It adds some objects to the History, then tries to get an object by index that does not exist.
     * Expected behaviour: throws an exception of type TextCompressionException.
     */
    @Test
    void historyThrowsForInvalidIndex() {

        try {
            history.getEntryByIndex(4);
            fail("The method was supposed to throw an exception for this input!");
        }
        catch (TextCompressionException ex) {
            return;
        }
    }

    /**
     * Function testing the getEntryOutputByInput method for null input.
     * It adds some objects to the History, then tries to get an object by passing "null" as the input value.
     * Expected behaviour: throws an exception of type TextCompressionException.
     */
    @Test
    void historyThrowsForNullInput() {

        try {
            history.getEntryOutputByInput(null);
            fail("The method was supposed to throw an exception for null input!");
        }
        catch (TextCompressionException ex) {
            return;
        }
    }

    /**
     * Function testing the add method for null input.
     * It tries adding a new entry to History with the input (key) set to null.
     * Expected behaviour: throws an exception of type TextCompressionException.
     */
    @Test
    void historyThrowsWhenAddingNullInput() {

        try {
            history.add(null, "Hello");
            fail("The method was supposed to throw an exception for null input!");
        }
        catch (TextCompressionException ex) {
            return;
        }
    }

    /**
     * Function testing the Iterator functionality of History.
     * It adds some objects to the History, then uses a foreach loop to see if the elements are returned correctly - in the right order and amount.
     * Expected behaviour: all the elements are returned and in the correct order.
     */
    @Test
    void historyIteratorReturnsCorrectly() {

        int i = 0;

        for (Map.Entry<String, String> expected : history) {

            try {
                Map.Entry<String, String> actual = history.getEntryByIndex(i++);

                assertEquals(expected.getKey(), actual.getKey(), "Wrong value was returned from History!");
                assertEquals(expected.getValue(), actual.getValue(), "Wrong value was returned from History!");
            }
            catch (TextCompressionException ex) {
                fail("This method was not supposed to throw an exception for this test case!");
            }
        }

        assertEquals(3, i);
    }
}
