package lab5.java.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections{
    private final String USER_NAME = "root";
    private final String PASSWORD = "12345678";
    private final String CONNECTION_URL = "jdbc:mysql://localhost:3306/Products?serverTimezone=Europe/Moscow&useSSL=false";

    private Connection connection;

    public Connections(){
        try {
            connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Failed connection." + e.getMessage());
        }

    }

    public Connection getConnection() {
        return connection;
    }
}

