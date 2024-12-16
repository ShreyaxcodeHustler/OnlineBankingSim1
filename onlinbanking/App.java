package org.example;

public class App {

    // The main method serves as the entry point for the application
    public static void main(String[] args) {
        // Create a service instance to handle database operations
        DatabaseService databaseService = new DatabaseService();

        // Call the method to fetch data from the database
        databaseService.fetchData();
    }
}
