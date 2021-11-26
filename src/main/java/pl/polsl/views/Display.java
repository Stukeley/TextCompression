package pl.polsl.views;

import pl.polsl.models.History;

import java.util.Map;
import java.util.stream.Stream;

/**
 * View class responsible for displaying information in the Console.
 * @author Rafał Klinowski
 * @version 1.2
 */
public class Display {
    /**
     * Method displaying a String to the system Console.
     * The method takes a variable amount of parameters, with each one being a single line of text to display.
     * @param textsToShow Texts to be shown in Console on the user's screen. Each passed parameter is one line of text.
     */
    public void displayStringsInConsole(String... textsToShow) {
        for (String text : textsToShow) {
            System.out.println(text);
        }
    }

    /**
     * Method displaying History, passed as parameter, to the system Console.
     * @param history History object to be displayed.
     */
    public void displayHistoryInConsole(History history) {

        System.out.println("------------------------");

        for (Map.Entry<String, String> entry : history) {
            System.out.println("Input: " + entry.getKey() + "; output: " + entry.getValue());
        }

        System.out.println("------------------------");
    }

    /**
     * Method displaying a Stream, representing the History, passed as parameter, to the system Console.
     * @param stream Stream of type Map.Entry to be displayed.
     */
    public void displayHistoryStreamInConsole(Stream<Map.Entry<String, String>> stream) {

        System.out.println("------------------------");

        stream.forEach(entry -> System.out.println("Input: " + entry.getKey() + "; output: " + entry.getValue()));

        System.out.println("------------------------");
    }
}
