package com.example.health;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdviceCRUD {

    public void createAdvice(int adviceId, int patientId, String adviceText) {
        String sql = "INSERT INTO Advice (advice_id, patient_id, advice) VALUES (?, ?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, adviceId);
            pstmt.setInt(2, patientId);
            pstmt.setString(3, adviceText);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readAdvice(int adviceId) {
        String sql = "SELECT * FROM Advice WHERE advice_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, adviceId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Advice: " + rs.getString("advice"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAdvice(int adviceId) {
        String sql = "DELETE FROM Advice WHERE advice_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, adviceId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

