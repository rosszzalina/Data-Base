package com.example.health;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PatientDiseaseCRUD {

    public void createPatientDisease(int patientId, int diseaseId) {
        String sql = "INSERT INTO PatientDisease (patient_id, disease_id) VALUES (?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, patientId);
            pstmt.setInt(2, diseaseId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePatientDisease(int patientId, int diseaseId) {
        String sql = "DELETE FROM PatientDisease WHERE patient_id = ? AND disease_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, patientId);
            pstmt.setInt(2, diseaseId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
