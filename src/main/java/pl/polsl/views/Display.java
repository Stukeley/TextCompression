package pl.polsl.views;

import pl.polsl.models.History;

/**
 * View class responsible for displaying information in the Console.
 * @author Rafał Klinowski
 * @version 1.0
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

    }
}
