package lab5.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lab5.java.database.Commands;
import lab5.java.database.WorkWithDatabase;

public class ChangePriceController implements Errors{
    @FXML
    private TextField titleId;

    @FXML
    private TextField cost;

    private WorkWithDatabase worker = new WorkWithDatabase();

    @FXML
    public void changePriceButton() {
        if (!titleId.getText().trim().isEmpty() && !cost.getText().trim().isEmpty()) {
            worker.changePrice(titleId.getText().trim(), Integer.parseInt(cost.getText().trim()));
        }
        else {
            showError("Fields Ttitle and Cost must be filled!");
        }
    }
}
