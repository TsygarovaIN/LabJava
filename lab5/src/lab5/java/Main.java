package lab5.java;

import javafx.application.Application;
import javafx.stage.Stage;
import lab5.java.view.BaseWindow;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            BaseWindow baseWindow = new BaseWindow();
            baseWindow.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
