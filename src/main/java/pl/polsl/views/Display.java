package pl.polsl.views;

import pl.polsl.models.History;

import java.util.Map;

/**
 * View class responsible for displaying information in the Console.
 * @author Rafał Klinowski
 * @version 1.1
 */
public class Display {
    /**
     * Method displaying a String to the system Console.
     * @param textToShow Text to be shown in Console on the user's screen.
     */
    public void displayStringInConsole(String textToShow)
    {
        System.out.println(textToShow);
    }

    /**
     * Method displaying History, passed as parameter, to the system Console.
     * @param history History object to be displayed.
     */
    public void displayHistoryInConsole(History history) {
        for (Map.Entry<String, String> entry : history) {
            System.out.println("Input: " + entry.getKey() + "; output: " + entry.getValue());
        }
    }
}
