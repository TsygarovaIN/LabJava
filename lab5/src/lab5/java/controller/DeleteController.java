package lab5.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lab5.java.database.Commands;
import lab5.java.database.WorkWithDatabase;

public class DeleteController implements Errors{
    @FXML
    private TextField titleId;

    private WorkWithDatabase worker = new WorkWithDatabase();

    @FXML
    public void deleteButton() {
        if (!titleId.getText().trim().isEmpty()) {
            worker.delete(titleId.getText().trim());
        }
        else {
            showError("Field Title must be filled!");
        }
    }
}
