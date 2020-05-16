package lab4;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static int NUMBER_PRODUCTS;

    public static void main(String[] args) throws ClassNotFoundException {
        final String USER_NAME = "root";
        final String PASSWORD = "12345678";
        final String CONNECTION_URL = "jdbc:mysql://localhost:3306/Products?serverTimezone=Europe/Moscow&useSSL=false";
        Class.forName("com.mysql.cj.jdbc.Driver");

        try(Connection connection = DriverManager.getConnection(CONNECTION_URL,USER_NAME, PASSWORD)) {
            if(connection.isValid(1))
              System.out.println("Successful connection.");
            else
                System.out.println("Failed connection.");

            Scanner scanner = new Scanner(System.in);
            Statement statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE Products");
            String sql = "INSERT INTO Products (Prodid, Title, Cost) Values(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            System.out.print("Введите кличество добавляемых товаров: ");
            NUMBER_PRODUCTS = scanner.nextInt();

            int number = 0;
            for (int i = 0; i < NUMBER_PRODUCTS; i++) {
                preparedStatement.setInt(1, i+1);
                preparedStatement.setString(2, "товар№" + (i + 1));
                preparedStatement.setInt(3, new Random().nextInt(10000) + 1);
                number += preparedStatement.executeUpdate();
            }
            System.out.println(number + " товаров успешно добавлены.");
            Commands command = new Commands(connection, NUMBER_PRODUCTS);

            System.out.println("Введите команду для работы с таблицей или exit для выхода");
            System.out.println("Команды:\n1 - add\n2 - delete\n3 - show_all\n4 - price\n5 - change_price\n6 - filter_by_price");
            String choice = scanner.next();

            while (!choice.equals("exit")) {
                switch (choice) {
                    case "1" -> {
                        System.out.println("Введите имя и цену товара:");
                        String nameAdd = scanner.next();
                        int costAdd = scanner.nextInt();
                        command.add(nameAdd, costAdd);
                    }
                    case "2" -> {
                        System.out.println("Введите название товара для его удаления: ");
                        String nameDelete = scanner.next();
                        command.delete(nameDelete);
                    }
                    case "3" -> {
                        System.out.println("Вывод всех товаров: ");
                        command.showAll();
                    }
                    case "4" -> {
                        System.out.println("Введите название товара для уточнения его цены: ");
                        String namePrice = scanner.next();
                        command.price(namePrice);
                    }
                    case "5" -> {
                        System.out.println("Введите название товара и его новую цену: ");
                        String nameChangePrice = scanner.next();
                        int newCost = scanner.nextInt();
                        command.changePrice(nameChangePrice, newCost);
                    }
                    case "6" -> {
                        System.out.println("Введите ценовой диапазон: ");
                        int minCost = scanner.nextInt();
                        int maxCost = scanner.nextInt();
                        command.FilterByPrice(minCost, maxCost);
                    }
                }
                System.out.println("Введите команду для работы с таблицей или exit для выхода");
                System.out.println("Команды:\n1 - add\n2 - delete\n3 - show_all\n4 - price\n5 - change_price\n6 - filter_by_price");
                choice = scanner.next();
            }
            scanner.close();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
