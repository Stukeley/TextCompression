import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.models.Algorithm;
import pl.polsl.models.StringHelper;

/**
 * Class containing tests of the StringHelper class, which is used directly by the algorithm.
 * @author Rafał Klinowski
 * @version 1.0
 */
public class StringHelperTest {

    private StringHelper helper;

    @BeforeEach
    void setUp() {
        helper = new StringHelper();
    }

    @ParameterizedTest
    @CsvSource({"aaaabbcc,false", "x1y2z3,false", "aaa,bbcde,true", "  ,true"})
    void testStringHelperSymbolDetectionForValidInput(String input, boolean expected) {

    }

    @ParameterizedTest
    @CsvSource({"aaaabbcc,false", "x1y2z3,true", "aaa,bbc1de,true", "  ,false"})
    void testStringHelperDigitDetectionForValidInput(String input, boolean expected) {

    }

    @Test
    void testStringHelperThrowsForNullInput() {

    }
}
