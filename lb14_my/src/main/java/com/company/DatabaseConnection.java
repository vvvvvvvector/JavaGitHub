package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    public static void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:lb14_my.db");
            System.out.println("Successfully connected");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            connection.close();
            System.out.println("Disconnected");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
