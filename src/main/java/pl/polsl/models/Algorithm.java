package pl.polsl.models;

public class Algorithm {
    // aaaabbcc -> a4b2c2
    public String Compress(String input)
    {
        return input;
    }

    // a4b2c2 -> aaaabbcc
    public String Decompress(String input)
    {
        // todo exception: gdy użytkownik poda 0 (np a0b2)
        return input;
    }

    public boolean IsStringCompressed(String inputText)
    {
        StringHelper stringHelper = new StringHelper();

        // If the string contains non-alphanumeric characters, it is incorrect.
        if (stringHelper.ContainsSymbols(inputText))
        {
            throw new TextCompressionException("Non-alphanumeric character found in input string.");
        }
        else if (stringHelper.ContainsNumbers(inputText))
        {
            return true;
        }

        return false;
    }
}
