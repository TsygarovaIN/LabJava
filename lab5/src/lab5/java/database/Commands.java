package lab5.java.database;

import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class Commands {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private int NUMBER_PRODUCTS = 20;

    public Commands() {
        clearTable();
        completeTable();
    }

    public void add(String nameProduct, int costProduct){
        String sql = "INSERT INTO Products (Prodid, Title, Cost) Values(?, ?, ?)";
        try {
            getConnection(new Connections());
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ++NUMBER_PRODUCTS);
            preparedStatement.setString(2, nameProduct);
            preparedStatement.setInt(3, costProduct);
            preparedStatement.executeUpdate();
            freeResources();
        }
        catch (SQLIntegrityConstraintViolationException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("This product was not added! There must be unique names!");
            alert.showAndWait();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String nameProduct) {
        String sql = "DELETE FROM Products WHERE Title = ?";
        try {
            getConnection(new Connections());
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nameProduct);
            preparedStatement.executeUpdate();
            freeResources();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Products> showAll() {
        String sql = "SELECT * FROM Products";
        ArrayList<Products> list = new ArrayList<>();
        try {
            getConnection(new Connections());
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int prodid = resultSet.getInt("Prodid");
                String productName = resultSet.getString("Title");
                int cost = resultSet.getInt("Cost");

                list.add(new Products(id, prodid, productName, cost));
            }
            resultSet.close();
            freeResources();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int price(String nameProduct) {
        String sql = "SELECT Cost FROM Products WHERE Title = ?";
        int cost = 0;
        try {
            getConnection(new Connections());
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nameProduct);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cost = resultSet.getInt("Cost");
            } else
                System.out.println("Данного товара нет в таблице");
            freeResources();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cost;
    }

    public void changePrice(String nameProduct, int newCost) {
        String sql = "UPDATE Products SET Cost = ? WHERE Title = ?";
        try {
            getConnection(new Connections());
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(2, nameProduct);
            preparedStatement.setInt(1, newCost);
            preparedStatement.executeUpdate();

            freeResources();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Products> filterByPrice(int minCost, int maxCost) {
        String sql = "SELECT * FROM Products WHERE Cost >= ? AND Cost <= ?";
        ArrayList<Products> listProducts = new ArrayList<>();
        try {
            getConnection(new Connections());
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, minCost);
            preparedStatement.setInt(2, maxCost);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int prodid = resultSet.getInt("Prodid");
                String productName = resultSet.getString("Title");
                int cost = resultSet.getInt("Cost");

                listProducts.add(new Products(id, prodid, productName, cost));
            }
            resultSet.close();
            freeResources();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listProducts;
    }

    private void clearTable() {
        String sql = "TRUNCATE TABLE Products";
        try {
            getConnection(new Connections());
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            freeResources();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void completeTable() {
        String sql = "INSERT INTO Products (Prodid, Title, Cost) Values(?, ?, ?)";
        try {
            getConnection(new Connections());
            preparedStatement = connection.prepareStatement(sql);

            for (int i = 1; i <= NUMBER_PRODUCTS; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.setString(2, "товар№" + i);
                preparedStatement.setInt(3, new Random().nextInt(1000) * i);
                preparedStatement.executeUpdate();
            }
            freeResources();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getConnection(Connections connections) {
        this.connection = connections.getConnection();
    }

    private void freeResources() {
        try {
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}