package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginScreen extends Application {

    // Start method that creates the UI components
    @Override
    public void start(Stage primaryStage) {
        // Create a grid layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10)); // Padding for the grid
        grid.setVgap(8); // Vertical gap between elements
        grid.setHgap(10); // Horizontal gap between elements

        // Create UI components (labels, text fields, button)
        Label userLabel = new Label("Username:");
        GridPane.setConstraints(userLabel, 0, 0);
        TextField userTextField = new TextField();
        GridPane.setConstraints(userTextField, 1, 0);

        Label passLabel = new Label("Password:");
        GridPane.setConstraints(passLabel, 0, 1);
        PasswordField passField = new PasswordField();
        GridPane.setConstraints(passField, 1, 1);

        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 2);

        // Add an event handler for the login button
        loginButton.setOnAction(e -> handleLogin(primaryStage, userTextField.getText(), passField.getText()));

        // Add all components to the grid
        grid.getChildren().addAll(userLabel, userTextField, passLabel, passField, loginButton);

        // Create a scene and set the grid as the root node
        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setTitle("Login Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to handle login logic and scene change
    private void handleLogin(Stage primaryStage, String username, String password) {
        // Create a Customer object with the provided credentials
        Customer customer = new Customer(username, password);

        // Authenticate user via the login method in Customer class
        if (customer.login()) {
            System.out.println("Login successful!");

            // Create the next screen (Dashboard)
            Label dashboardLabel = new Label("Welcome to your account dashboard!");
            GridPane dashboardGrid = new GridPane();
            dashboardGrid.setPadding(new Insets(10, 10, 10, 10));
            dashboardGrid.setVgap(8);
            dashboardGrid.setHgap(10);
            dashboardGrid.add(dashboardLabel, 0, 0);

            // Create a new scene for the dashboard
            Scene dashboardScene = new Scene(dashboardGrid, 300, 200);

            // Change the scene to the dashboard
            primaryStage.setScene(dashboardScene);
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        launch(args);
    }
}
