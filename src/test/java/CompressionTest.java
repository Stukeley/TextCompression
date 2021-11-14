import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.polsl.models.Algorithm;

/**
 * Class containing tests of the Text Compression algorithm.
 * @author Rafał Klinowski
 * @version 1.0
 */
public class CompressionTest {

    private Algorithm algorithm;

    @BeforeEach
    void setUp() {
        algorithm = new Algorithm();
    }

    @ParameterizedTest
    @CsvSource({"aaaabbcc,a4b2c2", "xyz,x1y1z1", "aaaaaaaAAaaaaaaaaa,a7A2a9", "asdfaassddffASDF,a1s1d1f1a2s2d2f2A1S1D1F1"})
    void testCompressionResultForValidInput(String input, String expected) {

    }

    @ParameterizedTest
    @ValueSource(strings = {"aaaa,bbcc", "A1B1C1", ""})
    void testCompressionThrowsForInvalidInput(String input) {

    }

    @Test
    void testCompressionThrowsForNullInput() {

    }
}
