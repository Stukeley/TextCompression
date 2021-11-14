package pl.polsl.controllers;

import pl.polsl.models.Algorithm;
import pl.polsl.models.History;
import pl.polsl.models.TextCompressionException;
import pl.polsl.views.Display;

/**
 * Main controller class responsible for managing the communication between the user and the program.
 * @author Rafał Klinowski
 * @version 1.2
 */
public class Main {
    /**
     * Application entry point. This method gets user input (through command line arguments or console interface),
     * determines which algorithm to use (compression or decompression), and displays the output on screen.
     * The method automatically detects whether the compression or decompression algorithm should be used.
     * @param args Command line parameters. Only one parameter of type String is expected - the text to compress or decompress.
     */
    public static void main(String[] args) {
        boolean repeatAlgorithm = true;
        History history = new History();

        do {
            String userInput = "";

            if (args.length == 0) {
                System.out.println("No command line parameters have been input.");
                System.out.println("Please type in the string to compress or decompress (letters and numbers only):");

                Reader reader = new Reader();
                userInput = reader.readTextFromConsole();
            }
            else {
                userInput = args[0];
            }

            Algorithm algorithm = new Algorithm();
            String output = "";

            try {
                boolean isInputCompressed = algorithm.isStringCompressed(userInput);

                if (isInputCompressed) {
                    output = algorithm.decompress(userInput);
                }
                else {
                    output = algorithm.compress(userInput);
                }

            }
            catch (TextCompressionException ex) {
                System.out.println(ex.getMessage());
                return;
            }

            Display display = new Display();
            display.displayStringInConsole(output);

            // Add entry to History.
            history.add(userInput, output);

            // Ask if user wants to repeat the algorithm, or see History.
            display.displayStringInConsole("Press 1 if you want to repeat the algorithm.\nPress 2 if you want to see history." +
                    "\nPress any other key to exit.");

            String userChoice = new Reader().readTextFromConsole();

            if (userChoice.equals("1")) {
                continue;
            }
            else if (userChoice.equals("2")) {
                display.displayHistoryInConsole(history);
            }
            else {
                repeatAlgorithm = false;
                display.displayStringInConsole("End of program.");
            }

        } while(repeatAlgorithm);
    }
}
