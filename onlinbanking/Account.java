package org.example;

import java.sql.*;

public class Account {
    private int accountId;
    private int customerId;
    private String type;
    private double balance;

    // Constructor now requires the accountId to be set as well, since we need the account balance.
    public Account(int customerId, String type) {
        this.customerId = customerId;
        this.type = type;
        this.balance = 0.0;  // Initial balance is set to 0.0 by default
    }

    public boolean createAccount() {
        String query = "INSERT INTO accounts (customer_id, type, balance) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, customerId);
                ps.setString(2, type);
                ps.setDouble(3, 0.0);  // Initial balance is 0.0
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    // Retrieve the generated account ID
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        this.accountId = rs.getInt(1);

                        // Now fetch the balance for the account
                        String balanceQuery = "SELECT balance FROM accounts WHERE account_id = ?";
                        try (PreparedStatement balanceStmt = conn.prepareStatement(balanceQuery)) {
                            balanceStmt.setInt(1, accountId);
                            ResultSet balanceResult = balanceStmt.executeQuery();
                            if (balanceResult.next()) {
                                this.balance = balanceResult.getDouble("balance");
                            }
                        }
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Deposit amount
    public void deposit(double amount) {
        this.balance += amount;
        updateBalance();
    }

    // Withdraw amount
    public void withdraw(double amount) {
        if (amount <= balance) {
            this.balance -= amount;
            updateBalance();
        } else {
            System.out.println("Insufficient funds for withdrawal");
        }
    }

    private void updateBalance() {
        String query = "UPDATE accounts SET balance = ? WHERE account_id = ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setDouble(1, balance);
                ps.setInt(2, accountId);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double getBalance() {
        return balance;
    }
}
