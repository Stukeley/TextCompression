import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.polsl.models.History;

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
    }

    @Test
    void historyGetsValueByInput() {

    }

    @Test
    void historyGetsValueByIndex() {

    }

    @Test
    void historyThrowsForInvalidIndex() {

    }

    @Test
    void historyThrowsForNullInput() {

    }
}
