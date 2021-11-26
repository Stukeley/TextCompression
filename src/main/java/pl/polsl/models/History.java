package pl.polsl.models;

import java.util.*;
import java.util.stream.Stream;

/**
 * Class responsible managing user inputs and outputs in a collection of pair values.
 * Implements the Iterable and Iterator interfaces to allow using a foreach loop directly on objects of this class.
 * @author Rafał Klinowski
 * @version 1.2
 */
public class History implements Iterable<Map.Entry<String, String>>, Iterator<Map.Entry<String, String>> {

    /**
     * Collection representing the history of user inputs and outputs.
     * The type is Map.Entry - each element in the collection is a combination of an input (String) and output (String).
     */
    private final List<Map.Entry<String, String>> history;

    /**
     * Index responsible for iterating through the history directly (by referencing an object of this class).
     */
    private int index;

    /**
     * Constructor initializing the collection.
     * The collection will be empty at first.
     */
    public History() {
        history = new ArrayList<>();
    }

    /**
     * Method for adding a new Entry to the History.
     * This method creates a new Entry based on the parameters, and adds it to the collection.
     * @param input User input value, acting as the key in Entry.
     * @param output Algorithm output value, acting as the value in Entry.
     */
    public void add(String input, String output) throws TextCompressionException {
        if (input == null) {
            throw new TextCompressionException("History input (key) cannot be null!");
        }

        Map.Entry entry = new AbstractMap.SimpleEntry(input, output);

        history.add(entry);
    }

    /**
     * Method for retrieving an Entry based on index.
     * @param index Index in collection to get value for.
     * @throws TextCompressionException Exception thrown when the given index is negative or out of bounds.
     * @return The entry's value of given index in History.
     */
    public Map.Entry getEntryByIndex(int index) throws TextCompressionException {
        if (index < 0) {
            throw new TextCompressionException("Index cannot be less than 0 !");
        }

        try {
            Map.Entry requested = history.get(index);
            return requested;
        }
        catch (IndexOutOfBoundsException e) {
            throw new TextCompressionException("Requested index was outside of collection bounds!");
        }
    }

    /**
     * Method for retrieving an Entry based on Input (key) value.
     * If more than one value with that key is present, the first one will be returned.
     * @param input Input string to look for in the collection.
     * @throws TextCompressionException Exception thrown when the given input value is null.
     * @return The entry's value, where key is equal to input, in History, or null if value was not found.
     */
    public String getEntryOutputByInput(String input) throws TextCompressionException {
        if (input == null) {
            throw new TextCompressionException("Provided input value cannot be null!");
        }

        for (int i=0;i<getHistorySize();i++) {
            Map.Entry<String, String> entry = history.get(i);
            if (entry.getKey().equals(input)) {
                return entry.getValue();
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

    /**
     * Method returning a stream that contains all Entries in History that were made by the Compression algorithm.
     * @return Stream containing all inputs and outputs of the Compression algorithm.
     */
    public Stream<Map.Entry<String, String>> filterHistoryForCompressionOnly() {
        StringHelper stringHelper = new StringHelper();

        Stream<Map.Entry<String, String>> stream = history.stream().filter(entry -> !stringHelper.containsNumbers(entry.getKey()));

        return stream;
    }

    /**
     * Method returning a stream that contains all Entries in History that were made by the Decompression algorithm.
     * @return Stream containing all inputs and outputs of the Decompression algorithm.
     */
    public Stream<Map.Entry<String, String>> filterHistoryForDecompressionOnly() {
        StringHelper stringHelper = new StringHelper();

        Stream<Map.Entry<String, String>> stream = history.stream().filter(entry -> stringHelper.containsNumbers(entry.getKey()));

        return stream;
    }

    /**
     * Method resetting index to 0 and returning an iterator being an object of this class.
     * @return An iterator, being this object of the History class.
     */
    @Override
    public Iterator<Map.Entry<String, String>> iterator() {
        index = 0;
        return this;
    }

    /**
     * Method returning a boolean value representing if we can get the next value (that is, if current index is less than the size of history).
     * @return True if next value is available; false otherwise.
     */
    @Override
    public boolean hasNext() {
        return index < history.size();
    }

    /**
     * Method returning the next value in history.
     * Increments index afterwards.
     * @return The element at index in history.
     */
    @Override
    public Map.Entry<String, String> next() {
        return history.get(index++);
    }
}
