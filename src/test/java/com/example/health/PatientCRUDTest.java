package com.example.health;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class PatientCRUDTest {

    private PatientCRUD patientCRUD;

    @BeforeEach
    void setUp() {
        patientCRUD = new PatientCRUD();
        clearDatabase();
    }

    private void clearDatabase() {
        String sql = "DELETE FROM Patient";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void createPatient() {
        int patientId = 1;
        String name = "John";
        String surname = "Doe";
        double age = 30.0;
        double height = 175.5;
        double weight = 70.0;
        String gender = "Male";
        int heartRate = 70;
        String[] Diseases = {"Diabetes", "Hypertension"};

        // Act
        assertDoesNotThrow(() -> patientCRUD.createPatient(
                patientId, name, surname, age, height, weight, gender, heartRate, Diseases
        ));

    }

    @Test
    void readPatient() {
        int patientId = 2;
        patientCRUD.createPatient(
                patientId, "Jane", "Doe", 28.0, 165.0, 60.0, "Female", 72, new String[]{"Asthma"}
        );

        // Act & Assert
        assertDoesNotThrow(() -> patientCRUD.readPatient(patientId));
    }

    @Test
    void updatePatient() {
        int patientId = 3;
        patientCRUD.createPatient(
                patientId, "Alice", "Smith", 25.0, 160.0, 55.0, "Female", 75, new String[]{"None"}
        );
        String updatedName = "Alice Updated";

        // Act
        assertDoesNotThrow(() -> patientCRUD.updatePatient(patientId, updatedName));

        // Validate update
        patientCRUD.readPatient(patientId);
    }

    @Test
    void deletePatient() {
            // Arrange
            int patientId = 4;
            patientCRUD.createPatient(
                    patientId, "Bob", "Johnson", 40.0, 180.0, 85.0, "Male", 65, new String[]{"Arthritis"}
            );

            // Act
            assertDoesNotThrow(() -> patientCRUD.deletePatient(patientId));

            // Validate deletion
            assertDoesNotThrow(() -> patientCRUD.readPatient(patientId));
        }

}