package com.example.health;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DiseaseAdviceCRUD {

    public void createDiseaseAdvice(int adviceId, int diseaseId, String adviceText) {
        String sql = "INSERT INTO DiseaseAdvice (advice_id, disease_id, advice) VALUES (?, ?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, adviceId);
            pstmt.setInt(2, diseaseId);
            pstmt.setString(3, adviceText);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDiseaseAdvice(int adviceId, int diseaseId) {
        String sql = "DELETE FROM DiseaseAdvice WHERE advice_id = ? AND disease_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, adviceId);
            pstmt.setInt(2, diseaseId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

