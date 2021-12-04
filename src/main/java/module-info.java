module DekompresjaTekstu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens pl.polsl.controllers to javafx.fxml;
    exports pl.polsl.controllers;
}