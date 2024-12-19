package com.example.health;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DiseaseCRUD {

    public void createDisease(int diseaseId, String name, String description) {
        String sql = "INSERT INTO Disease (disease_id, name, description) VALUES (?, ?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, diseaseId);
            pstmt.setString(2, name);
            pstmt.setString(3, description);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readDisease(int diseaseId) {
        String sql = "SELECT * FROM Disease WHERE disease_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, diseaseId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Description: " + rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDisease(int diseaseId, String name) {
        String sql = "UPDATE Disease SET name = ? WHERE disease_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, diseaseId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDisease(int diseaseId) {
        String sql = "DELETE FROM Disease WHERE disease_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, diseaseId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

