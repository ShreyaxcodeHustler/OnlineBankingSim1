package org.example;

import java.sql.*;

public class Transaction {
    private int transactionId;
    private int accountId;
    private String type;
    private double amount;
    private Timestamp timestamp;

    public Transaction(int accountId, String type, double amount) {
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
    }

    public void recordTransaction() {
        String query = "INSERT INTO transactions (account_id, type, amount) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, accountId);
                ps.setString(2, type);
                ps.setDouble(3, amount);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

