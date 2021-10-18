package pl.polsl.views;

import java.util.Scanner;

public class Reader
{
    public String ReadTextFromConsole()
    {
        String userInput = "";

        Scanner scanner = new Scanner(System.in);
        userInput = scanner.next();

        return userInput;
    }
}
