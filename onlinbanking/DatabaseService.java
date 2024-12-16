package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseService {

    // This method is responsible for fetching data from the database
    public void fetchData() {
        Connection conn = DatabaseConnection.getConnection();

        if (conn != null) {
            try {
                String query = "SELECT * FROM some_table";  // Replace with your actual query
                PreparedStatement stmt = conn.prepareStatement(query);

                // Execute the query
                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    // Process the result
                    System.out.println("Result: " + resultSet.getString("column_name"));
                }

            } catch (SQLException e) {
                // Handle SQL exceptions
                System.out.println("SQL error: " + e.getMessage());
            } finally {
                // Always close the connection to avoid memory leaks
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Database connection failed.");
            // Handle error appropriately
        }
    }
}
