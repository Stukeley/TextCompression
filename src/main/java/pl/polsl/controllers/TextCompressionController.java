package pl.polsl.controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
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

    private History history;

    /**
     * Function called automatically upon initializing the controller, after fully processing its root element.
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
        }
        catch (TextCompressionException ex) {
            output = "There was an exception during the algorithm: " + ex.getMessage();
        }

        resultLabel.setText(output);
    }

    /**
     * Event handler invoked upon pressing the showHistoryButton on the User Interface.
     * Displays the History on the User Interface.
     * @param event Event arguments, passed automatically upon invoking the event handler.
     */
    @FXML
    private void showHistory(ActionEvent event) {

        table.getColumns().clear();

        TableColumn inputs = new TableColumn("Input");
        inputs.setPrefWidth(310);

        TableColumn outputs = new TableColumn("Output");
        outputs.setPrefWidth(310);

        table.getColumns().addAll(inputs, outputs);

        ObservableMap<String, String> map = history.asObservableMap();

//        inputs.setCellValueFactory();

        table.setVisible(true);
    }
}
