package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Method to get the database connection
    public static Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/your_database_name";  // Replace with your DB name
        String username = "postgres";  // Replace with your username
        String password = "your_password";  // Replace with your password

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

