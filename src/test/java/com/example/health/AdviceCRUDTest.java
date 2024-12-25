package com.example.health;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AdviceCRUDTest {

    private AdviceCRUD adviceCRUD;

    @BeforeEach
    void setUp() {
        adviceCRUD = new AdviceCRUD();
        clearDatabase();
    }

    void clearDatabase() {
        String sql = "DELETE FROM Advice";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    // че за фигня где он patient нашел коммент
    void createAdvice() {
        int adviceId = 60;
        int diseaseId = 60;
        String advice = "Stay hydrated and exercise regularly.";

        // Act
        assertDoesNotThrow(() -> adviceCRUD.createAdvice(adviceId, diseaseId, advice));

    }

    @Test
    void readAdvice() {
        int adviceId = 2;
        int patientId = 101;
        String adviceText = "Take prescribed medication on time.";
        adviceCRUD.createAdvice(adviceId, patientId, adviceText);

        // Act & Assert
        assertDoesNotThrow(() -> adviceCRUD.readAdvice(adviceId));
    }

    @Test
    void deleteAdvice() {
        int adviceId = 3;
        int patientId = 102;
        String adviceText = "Follow a healthy diet.";
        adviceCRUD.createAdvice(adviceId, patientId, adviceText);

        // Act
        assertDoesNotThrow(() -> adviceCRUD.deleteAdvice(adviceId));
    }
}