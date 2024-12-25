package com.example.health;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientCRUD {

    public void createPatient(int patientId, String name, String surname, double age, double height, double weight, double gender, Gender heartRate, ObservableList<Diseases> Diseases) {
        String sql = "INSERT INTO Patient (patient_id, name,surname, age, height, weight, gender, heart_rate, chronic_diseases) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, patientId);
            pstmt.setString(2, name);
            pstmt.setString(3, surname);
            pstmt.setDouble(4, age);
            pstmt.setDouble(5, height);
            pstmt.setDouble(6, weight);
            pstmt.setString(7, gender);
            pstmt.setInt(8, heartRate);
            pstmt.setArray(9, conn.createArrayOf("varchar", Diseases));
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
