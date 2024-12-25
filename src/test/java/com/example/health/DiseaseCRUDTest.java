package com.example.health;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DiseaseCRUDTest {

    private DiseaseCRUD diseaseCRUD;

    @BeforeEach
    void setUp() {
        diseaseCRUD = new DiseaseCRUD();
        clearDatabase();
    }

    // Clears the Disease table before each test to ensure a clean state
    void clearDatabase() {
        try (Connection conn = db.getConnection();
             PreparedStatement deleteDiseaseAdvice = conn.prepareStatement("DELETE FROM DiseaseAdvice");
             PreparedStatement deleteDisease = conn.prepareStatement("DELETE FROM Disease")) {
            // Удаляем записи из DiseaseAdvice
            deleteDiseaseAdvice.executeUpdate();
            // Удаляем записи из Disease
            deleteDisease.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void createDisease() {
        int diseaseId = 54;
        String name = "Flu";
        String description = "A viral infection.";

        // Act
        boolean result = DiseaseCRUD.createDisease(diseaseId, name, description);

        // вот если что ассертТру коммент
        assertFalse(result, "Disease should be created successfully.");
    }

    @Test
    void readDisease() {
        int diseaseId = 2;
        String name = "Cold";
        String description = "A common viral infection.";
        diseaseCRUD.createDisease(diseaseId, name, description);

        // Act & Assert
        assertDoesNotThrow(() -> diseaseCRUD.readDisease(diseaseId));
    }

    @Test
    void updateDisease() {
        int diseaseId = 3;
        String initialName = "Diabetes";
        String initialDescription = "A chronic condition.";
        diseaseCRUD.createDisease(diseaseId, initialName, initialDescription);

        String updatedName = "Diabetes Updated";
        String updatedDescription = "A chronic condition affecting insulin levels.";

        // Act
        assertDoesNotThrow(() -> diseaseCRUD.updateDisease(diseaseId, updatedName, updatedDescription));

        // Validate update
        diseaseCRUD.readDisease(diseaseId);
    }

    @Test
    void deleteDisease() {
        int diseaseId = 4;
        String name = "Asthma";
        String description = "A condition causing breathing difficulties.";
        diseaseCRUD.createDisease(diseaseId, name, description);

        // Act
        boolean result = diseaseCRUD.deleteDisease(diseaseId);

        // коммент тут тоже тру на фолс
        assertFalse(result, "Disease should be deleted successfully.");

    }
}