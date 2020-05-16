package lab5.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lab5.java.database.Products;
import lab5.java.database.WorkWithDatabase;
import lab5.java.view.*;

import java.util.ArrayList;

public class BaseWindowController implements Errors {

    @FXML
    private TableView<Products> tableView;

    @FXML
    private TableColumn<Products, Integer> idColumn;

    @FXML
    private TableColumn<Products, Integer> prodidColumn;

    @FXML
    private TableColumn<Products, String> titleColumn;

    @FXML
    private TableColumn<Products, Integer> costColumn;

    private final WorkWithDatabase worker = new WorkWithDatabase();

    @FXML
    public void addButton() {
        WindowForAdd addWindow = new WindowForAdd();
        addWindow.init();
    }

    @FXML
    public void deleteButton() {
        WindowForDelete deleteWindow = new WindowForDelete();
        deleteWindow.init();
    }

    @FXML
    public void changePriceButton() {
        WindowForChangePrice changeWindow = new WindowForChangePrice();
        changeWindow.init();
    }

    @FXML
    public void showAllButton() {
        showAll();
    }

    @FXML
    public void priceButton() {
        WindowForPrice priceWindow = new WindowForPrice();
        priceWindow.init();
    }

    @FXML
    public void filterButton() {
        WindowForFilter filterWindow = new WindowForFilter();
        filterWindow.init();
    }

    private void showAll() {
        ArrayList<Products> productList = worker.showAll();
        ObservableList<Products> products = FXCollections.observableArrayList(productList);

        idColumn.setCellValueFactory(new PropertyValueFactory<Products, Integer>("id"));
        prodidColumn.setCellValueFactory(new PropertyValueFactory<Products, Integer>("Prodid"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Products, String>("Title"));
        costColumn.setCellValueFactory(new PropertyValueFactory<Products, Integer>("Cost"));

        tableView.setItems(products);
    }

}
