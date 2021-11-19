package pl.polsl.models;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class responsible managing user inputs and outputs in a collection of pair values.
 * @author Rafał Klinowski
 * @version 1.0
 */
public class History {
    private List<Map.Entry<String, String>> history;

    /**
     * Constructor initializing the collection.
     * The collection will be empty at first.
     */
    public History() {
        history = new ArrayList<>();
    }

    /**
     * Constructor initializing the collection with a given value.
     * The collection will contain a single Entry at first, made of the input and output, with input being the key.
     * @param input User input value, acting as the key in Entry.
     * @param output Algorithm output value, acting as the value in Entry.
     */
    public History(String input, String output) {
        history = new ArrayList<>();

        add(input, output);
    }

    /**
     * Method for adding a new Entry to the History.
     * This method creates a new Entry based on the parameters, and adds it to the collection.
     * @param input User input value, acting as the key in Entry.
     * @param output Algorithm output value, acting as the value in Entry.
     */
    public void add(String input, String output) {
        Map.Entry entry = new AbstractMap.SimpleEntry(input, output);

        history.add(entry);
    }

    /**
     * Method for retrieving an Entry based on index.
     * @param index Index in collection to get value for.
     * @throws HistoryException Exception thrown when the given index is negative or out of bounds.
     * @return The entry's value of given index in History.
     */
    public Map.Entry getEntryByIndex(int index) throws HistoryException {
        if (index < 0) {
            throw new HistoryException("Index cannot be less than 0 !");
        }

        try {
            Map.Entry requested = history.get(index);
            return requested;
        }
        catch (IndexOutOfBoundsException e) {
            throw new HistoryException("Requested index was outside of collection bounds!");
        }
    }

    /**
     * Method for retrieving an Entry based on Input (key) value.
     * If more than one value with that key is present, the first one will be returned.
     * @param input Input string to look for in the collection.
     * @throws HistoryException Exception thrown when the given input value is null.
     * @return The entry's value, where key is equal to input, in History, or null if value was not found.
     */
    public String getEntryOutputByInput(String input) throws HistoryException {
        if (input == null) {
            throw new HistoryException("Provided input value cannot be null!");
        }

        for (int i=0;i<getHistorySize();i++) {
            Map.Entry entry = history.get(i);
            if (entry.getKey().equals(input)) {
                return entry.getValue().toString();
            }
        }

        return null;
    }

    /**
     * Method for retrieving the size of History.
     * @return The amount of elements currently in History.
     */
    public int getHistorySize() {
        return history.size();
    }
}
