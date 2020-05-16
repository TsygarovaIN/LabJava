package lab4;

import java.sql.*;

public class Commands {
    private final Connection connection;
    private int numberProduct;

    public Commands(Connection connection, int numberProduct)  {
        this.connection = connection;
        this.numberProduct = numberProduct;
    }

    public void add(String nameProduct, int costProduct) throws SQLException {
        String sql = "INSERT INTO Products (Prodid, Title, Cost) Values(?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, ++numberProduct);
            preparedStatement.setString(2, nameProduct);
            preparedStatement.setInt(3, costProduct);
            preparedStatement.executeUpdate();

            System.out.println("Товар добавлен");
            System.out.println();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Товар не был добавлен! Должны быть уникальные имена!");
        }
    }

    public void delete(String nameProduct) throws SQLException {
        String sql = "DELETE FROM Products WHERE Title = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nameProduct);
            preparedStatement.executeUpdate();

            System.out.println("Товар удалён");
            System.out.println();
        }
    }

    public void showAll() throws SQLException {
        String sql = "SELECT * FROM Products";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                System.out.print(resultSet.getInt("id") + "  ");
                System.out.print(resultSet.getInt("Prodid") + "  ");
                System.out.print(resultSet.getString("Title") + "  ");
                System.out.print(resultSet.getInt("Cost"));
                System.out.println();
            }
        }
        System.out.println();
    }

    public void price(String nameProduct) throws SQLException {
        String sql = "SELECT Cost FROM Products WHERE Title = ?";
       try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
           preparedStatement.setString(1, nameProduct);

           try (ResultSet resultSet = preparedStatement.executeQuery(sql)) {
               if (resultSet.next())
                   System.out.println("Стоимость товара: " + resultSet.getInt("Cost"));
               else
                   System.out.println("Данного товара нет в таблице");
               System.out.println();
           }
       }
    }

    public void changePrice(String nameProduct, int newCost) throws SQLException {
        String sql = "UPDATE Products SET Cost = ? WHERE Title = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(2, nameProduct);
            preparedStatement.setInt(1, newCost);
            preparedStatement.executeUpdate();

            System.out.println("Данные изменены");
            System.out.println();
        }
    }

    public void FilterByPrice(int minCost, int maxCost) throws SQLException {
        String sql = "SELECT * FROM Products WHERE Cost >= ? AND Cost <= ?";
       try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
           preparedStatement.setInt(1, minCost);
           preparedStatement.setInt(2, maxCost);

           try (ResultSet resultSet = preparedStatement.executeQuery(sql)) {
               while (resultSet.next()) {
                   System.out.print(resultSet.getInt("id") + "  ");
                   System.out.print(resultSet.getInt("Prodid") + "  ");
                   System.out.print(resultSet.getString("Title") + "  ");
                   System.out.print(resultSet.getInt("Cost"));
                   System.out.println();
               }
           }
       }
        System.out.println();
    }
}
