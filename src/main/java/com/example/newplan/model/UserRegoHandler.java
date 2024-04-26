package com.example.newplan.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//deleted

public class UserRegoHandler {

    private Connection getConnection() {
        // SQLite JDBC connection
        String connectionUrl = "jdbc:sqlite:PATH_HERE";
        Connection connect = null;
        try {
            connect = DriverManager.getConnection(connectionUrl);
        }
        // basic error handling
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connect;
    }

    public void insertUser(String name, String lastName, String email, String password) {
        String sql = "INSERT INTO PLACEHOLDER(name, lastName, email, password) VALUES(?,?,?,?)";

        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
