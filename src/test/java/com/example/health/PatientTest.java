package com.example.health;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    @Test
    void testValidPatientInitialization() {
        ObservableList<Diseases> diseases = FXCollections.observableArrayList();
        diseases.add(Diseases.DIABETES);

        Patient patient = new Patient("John", "Doe", 30, 180, 70, 75, Gender.Male, diseases);

        assertEquals("John", Patient.getName());
        assertEquals("Doe", Patient.getSurname());
        assertEquals(30, patient.getAge());
        assertEquals(180, patient.getHeight());
        assertEquals(75, patient.getWeight());
        assertEquals(70, patient.getHeartRate());
        assertEquals(Gender.Male, Patient.getGender());
        assertEquals(diseases, Patient.getDiseases());
    }

    @Test
    void testInvalidPatientInitialization() {
        ObservableList<Diseases> diseases = FXCollections.observableArrayList();
        diseases.add(Diseases.ALZHEIMERS_DISEASE);

        Exception exception = assertThrows(RuntimeException.class, () ->
                new Patient("", "Doe", 150, 180, 70, 75, Gender.Male, diseases)
        );

        assertTrue(exception.getMessage().contains("Invalid patient details provided."));
    }

    @Test
    void testCalculateBMIAnalysis() {
        ObservableList<Diseases> diseases = FXCollections.observableArrayList();
        Patient patient = new Patient("Alice", "Smith", 28, 160, 72, 50, Gender.Female, diseases);

        assertEquals("Underweight", Patient.getBMIAnalysis());
    }

    @Test
    void testCalculateWaterIntake() {
        ObservableList<Diseases> diseases = FXCollections.observableArrayList();
        Patient patient = new Patient("Bob", "Johnson", 25, 175, 65, 70, Gender.Male, diseases);

        assertEquals(2.33, Patient.calculateWaterIntake(), 0.01);
    }

    @Test
    void testCalculateWaterCups() {
        ObservableList<Diseases> diseases = FXCollections.observableArrayList();
        Patient patient = new Patient("Bob", "Johnson", 25, 175, 65, 70, Gender.Male, diseases);

        assertEquals(9.33, Patient.calculateWaterCups(), 0.01);
    }

    @Test
    void testCalculateBFP() {
        ObservableList<Diseases> diseases = FXCollections.observableArrayList();
        Patient patient = new Patient("Eve", "Taylor", 35, 165, 75, 60, Gender.Female, diseases);

        double bfp = Patient.calculateBFP();
        assertEquals(27.88, bfp, 0.01); // Replace with the expected value based on the formula.
    }

    @Test
    void testGetHeartRateAnalysis() {
        ObservableList<Diseases> diseases = FXCollections.observableArrayList();
        Patient patient = new Patient("Chris", "Lee", 40, 170, 50, 80, Gender.Male, diseases);

        assertEquals("Bradycardia", Patient.getHeartRateAnalysis());
    }

    @Test
    void testSettersAndValidation() {
        ObservableList<Diseases> diseases = FXCollections.observableArrayList();
        Patient patient = new Patient("Alex", "Brown", 45, 180, 80, 85, Gender.Male, diseases);

        patient.setAge(50);
        assertEquals(50, patient.getAge());

        patient.setHeight(175);
        assertEquals(175, patient.getHeight());

        patient.setWeight(90);
        assertEquals(90, patient.getWeight());

        patient.setHeartRate(75);
        assertEquals(75, patient.getHeartRate());
    }
}
