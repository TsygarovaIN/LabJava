package lab5.java.controller;

import javafx.scene.control.Alert;

public interface Errors {
    default void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("ERROR");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
