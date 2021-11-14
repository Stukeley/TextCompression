import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.models.Algorithm;

/**
 * Class containing tests of the Text Decompression algorithm.
 * @author Rafał Klinowski
 * @version 1.0
 */
public class DecompressionTest {

    private Algorithm algorithm;

    @BeforeEach
    void setUp() {
        algorithm = new Algorithm();
    }

    @ParameterizedTest
    @CsvSource({"a4b2c2,aaaabbcc", "x1y1z1,xyz", "a7A2a9,aaaaaaaAAaaaaaaaaa", "a1s1d1f1a2s2d2f2A1S1D1F1,asdfaassddffASDF"})
    void testDecompressionResultForValidInput(String input, String expected) {

    }

    @ParameterizedTest
    @ValueSource(strings = {"a4,b2c2", "aaaabbcc", ""})
    void testDecompressionThrowsForInvalidInput(String input) {

    }

    @Test
    void testDecompressionThrowsForNullInput() {

    }
}
