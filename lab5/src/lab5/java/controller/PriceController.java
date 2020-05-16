package lab5.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lab5.java.database.Commands;
import lab5.java.database.WorkWithDatabase;

public class PriceController implements Errors {

    @FXML
    private TextField titleId;

    @FXML
    private TextArea costArea;

    private WorkWithDatabase work = new WorkWithDatabase();

    @FXML
    public void costButton() {

        if (!titleId.getText().trim().isEmpty()) {
            int cost = work.price(titleId.getText().trim());
            costArea.setText(String.valueOf(cost));
        }
        else {
            showError("Field Title must be filled");
        }

    }
}
