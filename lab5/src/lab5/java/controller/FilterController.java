package lab5.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lab5.java.database.Commands;
import lab5.java.database.Products;
import lab5.java.database.WorkWithDatabase;


import java.util.ArrayList;

public class FilterController implements Errors {

    @FXML
    private TextField minCost;

    @FXML
    private TextField maxCost;

    @FXML
    private TableView<Products> tableView;

    @FXML
    private TableColumn<Products, Integer> idColumn;

    @FXML
    private TableColumn<Products, String> prodidColumn;

    @FXML
    private TableColumn<Products, String> titleColumn;

    @FXML
    private TableColumn<Products, Integer> costColumn;

   private WorkWithDatabase work = new WorkWithDatabase();

    @FXML
    public void filterButton() {

        if (!minCost.getText().trim().isEmpty() && !maxCost.getText().trim().isEmpty()) {

            ArrayList<Products> productList = work.filterByPrice(Integer.parseInt(minCost.getText().trim()), Integer.parseInt(maxCost.getText().trim()));

            ObservableList<Products> products = FXCollections.observableArrayList(productList);

            idColumn.setCellValueFactory(new PropertyValueFactory<Products, Integer>("id"));
            prodidColumn.setCellValueFactory(new PropertyValueFactory<Products, String>("Prodid"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<Products, String>("Title"));
            costColumn.setCellValueFactory(new PropertyValueFactory<Products, Integer>("Cost"));

            tableView.setItems(products);
        }
        else {
            showError("Filed Min cost and Max cost must be filled");
        }

    }
}

