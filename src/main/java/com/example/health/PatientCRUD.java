package com.example.health;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientCRUD {

    public void createPatient(int patientId, String name, double age, double height, double weight, String gender, int heartRate, String[] chronicDiseases) {
        String sql = "INSERT INTO Patient (patient_id, name, age, height, weight, gender, heart_rate, chronic_diseases) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, patientId);
            pstmt.setString(2, name);
            pstmt.setDouble(3, age);
            pstmt.setDouble(4, height);
            pstmt.setDouble(5, weight);
            pstmt.setString(6, gender);
            pstmt.setInt(7, heartRate);
            pstmt.setArray(8, conn.createArrayOf("varchar", chronicDiseases));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readPatient(int patientId) {
        String sql = "SELECT * FROM Patient WHERE patient_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, patientId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getDouble("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePatient(int patientId, String name) {
        String sql = "UPDATE Patient SET name = ? WHERE patient_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, patientId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePatient(int patientId) {
        String sql = "DELETE FROM Patient WHERE patient_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, patientId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
