package lab5.java.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowForFilter {
    public void init() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/lab5/fxmlFiles/windowForFilter.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Filter window");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
