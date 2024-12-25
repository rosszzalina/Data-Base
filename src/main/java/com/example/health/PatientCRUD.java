package com.example.health;

import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientCRUD {

    public static boolean patientExists(int i) {
        // Метод реализации для проверки существования пациента
        String sql = "SELECT 1 FROM Patient WHERE patient_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, i);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Возвращает true, если запись найдена
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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

    public void createPatient(int i, String name, String surname, int age, double v, int i1, double v1, Gender gender, ObservableList<Diseases> selectedDiseases) {
    }
}
