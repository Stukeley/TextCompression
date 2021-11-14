package pl.polsl.models;

/**
 * Helper class responsible for filtering input Strings.
 * @author Rafał Klinowski
 * @version 1.1
 */
public class StringHelper {
    /**
     * Method checking if a String contains a symbol, so any character that is not alphanumeric (A-Z, a-z, 0-9).
     * @param inputText Text, input by the user, to check for symbols.
     * @return True if inputText contains a symbol (character other than A-Z, a-z, 0-9); false otherwise.
     */
    public boolean containsSymbols(String inputText) {
        for (int i=0;i<inputText.length();i++) {
            char singleChar = inputText.charAt(i);

            if (!Character.isDigit(singleChar) && !Character.isLetter(singleChar)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Method checking if a String contains a digit (0-9).
     * @param inputText Text, input by the user, to check for digits.
     * @return True if inputText contains a digit (0-9); false otherwise.
     */
    public boolean containsNumbers(String inputText) {
        for (int i=0;i<inputText.length();i++) {
            char singleChar = inputText.charAt(i);

            if (Character.isDigit(singleChar)) {
                return true;
            }
        }

        return false;
    }
}
