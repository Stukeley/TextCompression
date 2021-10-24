package pl.polsl.models;

/**
 * Main model class, containing the compression and decompression algorithm implementation.
 * @author Rafał Klinowski
 * @version 1.0
 */
public class Algorithm
{
    /**
     * Method taking a non-compressed string and compressing it.
     * For example: for the input "aaaabbcc", the String "a4b2c2" is returned.
     * @param input Non-compressed text input by the user, e.g. "aaaabbcc".
     * @return Compressed text, e.g. "a4b2c2".
     * @throws TextCompressionException Exception thrown when the input format is invalid (e.g. contains a symbol or is empty).
     */
    public String compress(String input) throws TextCompressionException
    {
        StringHelper stringHelper = new StringHelper();
        if (stringHelper.containsSymbols(input))
        {
            throw new TextCompressionException("Invalid input string - input contains invalid characters.");
        }

        StringBuilder outputBuilder = new StringBuilder();
        char repeatedChar;

        // Main loop - get a single character.
        for (int i=0;i<input.length();)
        {
            repeatedChar = input.charAt(i);

            int repeatedCharCount = 0;

            // Second loop - determines the number of consecutive repetitions of the same character.
            for (int j =i;j<input.length();j++)
            {
                if (input.charAt(j) != repeatedChar)
                {
                    break;
                }

                repeatedCharCount++;
                i++;
            }

            // Add character along with the number of consecutive repetitions of it to the output.
            outputBuilder.append(repeatedChar);
            outputBuilder.append(repeatedCharCount);
        }

        String output = outputBuilder.toString();

        if (output.isBlank())
        {
            // If the input is empty (and so is output), throw an exception.
            throw new TextCompressionException("Invalid input string - input was empty.");
        }

        return output;
    }

    /**
     * Method taking a compressed string and decompressing it.
     * For example: for the input "a4b2c2", the String "aaaabbcc" is returned.
     * @param input Compressed text input by the user, e.g. "a4b2c2".
     * @return Decompressed text, e.g. "aaaabbcc".
     * @throws TextCompressionException Exception thrown when the input format is invalid (e.g. contains a symbol, is empty or contains zeroes).
     */
    public String decompress(String input) throws TextCompressionException
    {
        StringHelper stringHelper = new StringHelper();
        if (stringHelper.containsSymbols(input))
        {
            throw new TextCompressionException("Invalid input string - input contains invalid characters.");
        }

        StringBuilder outputBuilder = new StringBuilder();
        char repeatedChar = ' ';
        int repeatedCharCount = -1;

        // Main loop - get a single character.
        for (int i=0;i<input.length();)
        {
            char tempChar = input.charAt(i);

            if (Character.isLetter(tempChar))
            {
                // If the character is a letter (A-Z, a-z), save it and check the next character.
                repeatedChar = tempChar;
                i++;
            }
            else if (Character.isDigit(tempChar))
            {
                // If the character is a digit (0-9), get all consecutive digits and convert them into a single number.
                // This is done in case the amount of characters has more than one digit (e.g. a15).
                StringBuilder totalRepeatedCountString = new StringBuilder();

                // Second loop - write all consecutive digits to totalRepeatedCountString.
                for (int j=i;j<input.length();j++)
                {
                    if (!Character.isDigit(input.charAt(j)))
                    {
                        break;
                    }

                    totalRepeatedCountString.append(input.charAt(j));
                }

                try
                {
                    repeatedCharCount = Integer.parseInt(totalRepeatedCountString.toString());

                    if (repeatedCharCount==0)
                    {
                        // If the amount is equal to 0, throw an exception - this situation is not allowed (e.g. "a0").
                        throw new TextCompressionException("Invalid number in input string - one of the numbers was 0.");
                    }

                    // Append as many characters as determined by the counter.
                    for (int j=0;j<repeatedCharCount;j++)
                    {
                        outputBuilder.append(repeatedChar);
                    }

                    // Shift current character index based on how many digits there were (e.g. "a15" shifts by two places).
                    i += totalRepeatedCountString.toString().length();
                }
                catch (Exception ex)
                {
                    throw ex;
                }
            }
        }

        String output = outputBuilder.toString();

        if (output.isBlank())
        {
            // If the input is empty (and so is output), throw an exception.
            throw new TextCompressionException("Invalid input string - input was empty.");
        }

        return output;
    }

    /**
     * Helper method checking if inputString is already compressed or not.
     * The method checks for digits inside inputText, and throws an exception if it contains non-alphanumeric characters.
     * @param inputText User input to be checked.
     * @return True if userInput is compressed (e.g. "a4b2c2"); false otherwise.
     * @throws TextCompressionException Exception thrown when the input format is invalid (e.g. contains a symbol).
     */
    public boolean isStringCompressed(String inputText) throws TextCompressionException
    {
        StringHelper stringHelper = new StringHelper();

        if (stringHelper.containsSymbols(inputText))
        {
            // If the string contains non-alphanumeric characters, it is incorrect.
            throw new TextCompressionException("Non-alphanumeric character found in input string.");
        }
        else if (stringHelper.containsNumbers(inputText))
        {
            // If the string contains numbers, it is compressed and should be decompressed by the algorithm.
            return true;
        }

        // Otherwise, the string is not compressed.
        return false;
    }
}
