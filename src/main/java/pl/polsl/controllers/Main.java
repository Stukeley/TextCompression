package pl.polsl.controllers;

import pl.polsl.models.Algorithm;
import pl.polsl.views.Display;
import pl.polsl.views.Reader;

public class Main
{
    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {
        String userInput = "";

        if (args.length == 0)
        {
            System.out.println("No command line parameters have been input.");
            System.out.println("Please type in the string to compress or decompress (letters and numbers only):");

            Reader reader = new Reader();
            userInput = reader.ReadTextFromConsole();
        }
        else
        {
            userInput = args[0];
        }

        Algorithm algorithm = new Algorithm();
        String output = "";

        output = algorithm.Compress(userInput);

        Display display = new Display();
        display.DisplayStringInConsole(output);
    }
}
