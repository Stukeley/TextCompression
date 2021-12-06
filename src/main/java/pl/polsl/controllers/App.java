package pl.polsl.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Secondary controller class, responsible for invoking and managing the GUI of the application.
 *  @author Rafał Klinowski
 *  @version 1.0
 */
public class App extends Application {

    /**
     * Scene object representing the visible User Interface.
     */
    private static Scene scene;

    /**
     * Application entry point. This method creates and displays the User Interface.
     * If parameters are input, they are processed immediately.
     * @param args Command line parameters.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Overridden method invoked automatically upon launching the Application.
     * Creates a new Scene from FXML, adds it to the window visible on the screen, then displays it.
     * @param stage Automatically passed parameter representing the visible window.
     * @throws IOException Exception thrown when specified FXML file is not found.
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("textcompression"), 640, 480);

        stage.setScene(scene);
        stage.setTitle("Text Compression");
        stage.setResizable(false);

        stage.show();
    }

    /**
     * Automatically invoked method, which sets the scene's root to the loaded FXML.
     * @param fxml Name of FXML file to load.
     * @throws IOException Exception thrown when specified FXML file is not found.
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Method loading an FXML file with the specified name from the Application's resources.
     * Automatically appends the ".fxml" extension to the file's name.
     * @param fxml Name of FXML file to load.
     * @return Loaded FXML controls.
     * @throws IOException Exception thrown when specified FXML file is not found.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
