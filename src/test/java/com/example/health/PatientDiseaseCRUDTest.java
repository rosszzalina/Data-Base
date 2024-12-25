package com.example.health;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class PatientDiseaseCRUDTest {

    private PatientDiseaseCRUD patientDiseaseCRUD;

    @BeforeEach
    void setUp() {
        patientDiseaseCRUD = new PatientDiseaseCRUD();
        clearDatabase();
    }

    // Clears the PatientDisease table before each test to ensure a clean state
    void clearDatabase() {
        String sql = "DELETE FROM PatientDisease";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void createPatient(int patientId, String name) {
        String sql = "INSERT INTO Patient (patient_id, name) VALUES (?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, patientId);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to create a disease
    void createDisease(int diseaseId, String name) {
        String sql = "INSERT INTO Disease (disease_id, name) VALUES (?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, diseaseId);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void createPatientDisease() {
        int patientId = 1;
        int diseaseId = 101;
        createPatient(patientId, "John Doe");
        createDisease(diseaseId, "Flu");

        // Act & Assert
        assertDoesNotThrow(() -> patientDiseaseCRUD.createPatientDisease(patientId, diseaseId));
    }

    @Test
    void deletePatientDisease() {
        int patientId = 2;
        int diseaseId = 102;
        createPatient(patientId, "Jane Smith");
        createDisease(diseaseId, "Cold");
        patientDiseaseCRUD.createPatientDisease(patientId, diseaseId);

        // Act & Assert
        assertDoesNotThrow(() -> patientDiseaseCRUD.deletePatientDisease(patientId, diseaseId));
    }
}