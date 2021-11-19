import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.polsl.models.History;
import pl.polsl.models.HistoryException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class containing tests of the History class and related collection.
 * @author Rafał Klinowski
 * @version 1.0
 */
public class HistoryTest {
    private History history;

    @BeforeEach
    void setUp() {
        history = new History();
    }

    @Test
    void historySizeUpdatesCorrectly() {
        history.add("aaaabbcc", "a4b2c2");
        history.add("aabbcc", "a2b2c2");
        history.add("abc", "a1b1c1");

        assertEquals(3, history.getHistorySize());

        history.add("new", "n1e1w1");

        assertEquals(4, history.getHistorySize());
    }

    @Test
    void historyGetsValueByInput() {
        String firstInput = "aaaabbcc", firstOutput = "a4b2c2";
        String secondInput = "aabbcc", secondOutput = "a2b2c2";
        String thirdInput = "abc", thirdOutput = "a1b1c1";

        history.add(firstInput, firstOutput);
        history.add(secondInput, secondOutput);
        history.add(thirdInput, thirdOutput);

        try {
            String first = history.getEntryOutputByInput(firstInput);
            String third = history.getEntryOutputByInput(thirdInput);

            assertEquals(firstOutput, first);
            assertEquals(thirdOutput, third);
        }
        catch (HistoryException ex) {
            fail("The method was not supposed to throw any exceptions for this input!");
        }
    }

    @Test
    void historyGetsValueByIndex() {
        String firstInput = "aaaabbcc", firstOutput = "a4b2c2";
        String secondInput = "aabbcc", secondOutput = "a2b2c2";
        String thirdInput = "abc", thirdOutput = "a1b1c1";

        history.add(firstInput, firstOutput);
        history.add(secondInput, secondOutput);
        history.add(thirdInput, thirdOutput);

        try {
            Map.Entry<String, String> first = history.getEntryByIndex(0);
            Map.Entry<String, String> third = history.getEntryByIndex(2);

            assertEquals(firstInput, first.getKey());
            assertEquals(firstOutput, first.getValue());
            assertEquals(thirdInput, third.getKey());
            assertEquals(thirdOutput, third.getValue());
        }
        catch (HistoryException ex) {
            fail("The method was not supposed to throw any exceptions for this input!");
        }
    }

    @Test
    void historyThrowsForInvalidIndex() {
        history.add("aaaabbcc", "a4b2c2");
        history.add("aabbcc", "a2b2c2");
        history.add("abc", "a1b1c1");

        try {
            history.getEntryByIndex(4);
            fail("The method was supposed to throw an exception for this input!");
        }
        catch (HistoryException ex) {
            return;
        }
    }

    @Test
    void historyThrowsForNullInput() {
        history.add("aaaabbcc", "a4b2c2");
        history.add("aabbcc", "a2b2c2");
        history.add("abc", "a1b1c1");

        try {
            history.getEntryOutputByInput(null);
            fail("The method was supposed to throw an exception for null input!");
        }
        catch (HistoryException ex) {
            return;
        }
    }
}
