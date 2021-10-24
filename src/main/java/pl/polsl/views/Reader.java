package pl.polsl.views;

import java.util.Scanner;

/**
 * View class responsible for getting information from the user through the Console.
 * @author Rafał Klinowski
 * @version 1.0
 */
public class Reader
{
    /**
     * Method getting a String from the User through system Console.
     * This method uses a Scanner operating on the System.in stream to fetch user input.
     * @return A single line of text input by the user.
     */
    public String readTextFromConsole()
    {
        String userInput = "";

        Scanner scanner = new Scanner(System.in);
        userInput = scanner.next();

        return userInput;
    }
}
