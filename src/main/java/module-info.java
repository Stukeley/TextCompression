/**
 * The module (representing the whole project) with required JavaFX dependencies.
 * Exports the Controllers package, so that it can be referenced by the FXML files.
 */
module DekompresjaTekstu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens pl.polsl.controllers to javafx.fxml;
    exports pl.polsl.controllers;
}