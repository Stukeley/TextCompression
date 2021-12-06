package pl.polsl.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import pl.polsl.models.Algorithm;
import pl.polsl.models.History;
import pl.polsl.models.TextCompressionException;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Controller class, responsible for managing JavaFX User Interface and its related events.
 *  @author Rafał Klinowski
 *  @version 1.0
 */
public class TextCompressionController implements Initializable {

    /**
     * TextArea object representing the input field on the User Interface.
     */
    @FXML
    private TextArea inputTextArea;
    /**
     * Button object representing the button used to invoke the algorithm.
     */
    @FXML
    private Button runButton;
    /**
     * Button object representing the button used to show history.
     */
    @FXML
    private Button showHistoryButton;
    /**
     * Label object representing the label which shows algorithm output on the User Interface.
     */
    @FXML
    private Label resultLabel;
    /**
     * TableView object representing the table displaying History on the User Interface.
     */
    @FXML
    private TableView table;

    /**
     * History object responsible for keeping track of user inputs and outputs.
     */
    private History history;

    /**
     * Function called automatically upon initializing the controller, after fully processing its root element.
     * Initializes History.
     * @param url Location used to resolve relative paths to the root object.
     * @param resourceBundle Resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        history = new History();
    }

    /**
     * Event handler invoked upon pressing the runButton on the User Interface.
     * Creates an object of type Algorithm and uses it to invoke the algorithm based on user input.
     * @param event Event arguments, passed automatically upon invoking the event handler.
     */
    @FXML
    private void runAlgorithm(ActionEvent event) {
        String userInput = inputTextArea.getText();
        String output;
        Algorithm algorithm = new Algorithm();

        try {
            boolean isInputCompressed = algorithm.isStringCompressed(userInput);

            if (isInputCompressed) {
                output = algorithm.decompress(userInput);
            }
            else {
                output = algorithm.compress(userInput);
            }

            resultLabel.setText(output);

            history.add(userInput, output);

            // Update history automatically, if visible.
            if (table.isVisible()) {
                updateHistory();
            }
        }
        catch (TextCompressionException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("There was an exception during the algorithm.");
            alert.setContentText(ex.getMessage());

            alert.showAndWait();
        }
    }

    /**
     * Event handler invoked upon pressing the showHistoryButton on the User Interface.
     * Displays the History on the User Interface.
     * @param event Event arguments, passed automatically upon invoking the event handler.
     */
    @FXML
    private void showHistory(ActionEvent event) {

        // If history is visible, hide it.
        // Otherwise, update it and show it.
        if (table.isVisible()) {
            table.setVisible(false);
            showHistoryButton.setText("Show History");
        }
        else {
            updateHistory();
            table.setVisible(true);
            showHistoryButton.setText("Hide History");
        }
    }

    /**
     * Method re-creating TableView and populating it with History data.
     * The method first clears the table, then repopulates its inputs and outputs.
     * It is called upon pressing the "Show History" button, or running the algorithm.
     */
    private void updateHistory() {
        table.getColumns().clear();

        int columnWidth = 300;

        TableColumn<Map.Entry<String, String>, String> inputs = new TableColumn<>("Input");
        inputs.setPrefWidth(columnWidth);
        inputs.setMaxWidth(columnWidth);

        TableColumn<Map.Entry<String, String>, String> outputs = new TableColumn<>("Output");
        outputs.setPrefWidth(columnWidth);
        outputs.setMaxWidth(columnWidth);

        table.getColumns().addAll(inputs, outputs);

        ObservableList<Map.Entry<String, String>> map = history.asObservableMap();

        inputs.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getKey()));
        outputs.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getValue()));

        table.setItems(map);
    }
}
