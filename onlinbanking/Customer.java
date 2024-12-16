package org.example;

import java.sql.*;

public class Customer {
    private int customerId;
    private String name;
    private String address;
    private String contact;
    private final String username;
    private final String password;

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Register new customer
    public boolean registerCustomer() {
        String query = "INSERT INTO customers (name, address, contact, username, password) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, name);
                ps.setString(2, address);
                ps.setString(3, contact);
                ps.setString(4, username);
                ps.setString(5, password);
                int rowsAffected = ps.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Login
    public boolean login() {
        String query = "SELECT * FROM customers WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    this.customerId = rs.getInt("customer_id");
                    this.name = rs.getString("name");
                    this.address = rs.getString("address");
                    this.contact = rs.getString("contact");
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getCustomerId() {
        return customerId;
    }
}

