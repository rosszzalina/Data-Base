package com.example.health;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DiseaseAdviceCRUDTest {

    private DiseaseAdviceCRUD diseaseAdviceCRUD;

    @BeforeEach
    void setUp() {
        diseaseAdviceCRUD = new DiseaseAdviceCRUD();
        clearDatabase();
    }


    void clearDatabase() {
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Disease");
             PreparedStatement pstmtAdvice = conn.prepareStatement("DELETE FROM DiseaseAdvice")) {
            pstmtAdvice.executeUpdate(); // Удалить связанные записи в DiseaseAdvice
            pstmt.executeUpdate(); // Удалить записи из Disease
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Helper method to create a disease
    void createDisease(int diseaseId, String diseaseName) {
        String sql = "INSERT INTO Disease (disease_id, name) VALUES (?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, diseaseId);
            pstmt.setString(2, diseaseName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDisease(int diseaseId) {
        String deleteDependentSql = "DELETE FROM patientdisease WHERE disease_id = ?";
        String deleteDiseaseSql = "DELETE FROM disease WHERE disease_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement dependentStmt = conn.prepareStatement(deleteDependentSql);
             PreparedStatement diseaseStmt = conn.prepareStatement(deleteDiseaseSql)) {

            // Удалить зависимые записи
            dependentStmt.setInt(1, diseaseId);
            dependentStmt.executeUpdate();

            // Удалить запись из disease
            diseaseStmt.setInt(2, diseaseId);
            diseaseStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void createDiseaseAdvice() {
        // Arrange
        int adviceId = 2;
        int diseaseId = 2;
        String adviceText = "Take prescribed medication regularly.";

        // Ensure the disease exists in the database
        createDisease(diseaseId, "Flu");

        // Act & Assert
        assertDoesNotThrow(() -> diseaseAdviceCRUD.createDiseaseAdvice(adviceId, diseaseId, adviceText));
    }

    @Test
    void deleteDiseaseAdvice() {
        int adviceId = 2;
        int diseaseId = 2;
        String adviceText = "Stay hydrated and rest.";

        // Ensure the disease exists and the advice is created
        createDisease(diseaseId, "Cold");
        diseaseAdviceCRUD.createDiseaseAdvice(adviceId, diseaseId, adviceText);

        // Act & Assert
        assertDoesNotThrow(() -> diseaseAdviceCRUD.deleteDiseaseAdvice(adviceId, diseaseId));
    }


}
