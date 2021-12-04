package pl.polsl.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import pl.polsl.models.Algorithm;
import pl.polsl.models.TextCompressionException;

import java.net.URL;
import java.util.ResourceBundle;

public class TextCompressionController implements Initializable {

    @FXML
    private TextArea inputTextArea;
    @FXML
    private Button runButton;
    @FXML
    private Button showHistoryButton;
    @FXML
    private Label resultLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO
    }

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

    @FXML
    private void showHistory(ActionEvent event) {
        // TODO
    }
}
