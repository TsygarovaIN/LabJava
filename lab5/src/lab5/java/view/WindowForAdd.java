package lab5.java.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowForAdd {
    public void init() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/lab5/fxmlFiles/windowForAdd.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Add window");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
