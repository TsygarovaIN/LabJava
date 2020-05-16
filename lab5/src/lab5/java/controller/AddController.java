package lab5.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lab5.java.database.Commands;
import lab5.java.database.WorkWithDatabase;

import java.sql.SQLIntegrityConstraintViolationException;

public class AddController implements Errors{
    @FXML
    private TextField titleId;

    @FXML
    private TextField cost;

   private WorkWithDatabase worker = new WorkWithDatabase();


    @FXML
    public void addButton() {
        if (!titleId.getText().trim().isEmpty() && !cost.getText().trim().isEmpty()) {
            worker.add(titleId.getText().trim(), Integer.parseInt(cost.getText().trim()));
        } else {
            showError("Fields Title and Cost must be filled!");
        }
    }
}
