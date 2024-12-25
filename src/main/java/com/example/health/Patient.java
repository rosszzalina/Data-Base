package com.example.health;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class Patient {
    static String name;
    static String surname;
    static int age;
    static double height;
    static double weight;
    static Gender gender;
    static int heartRate;
    static ObservableList<Diseases> Diseases;
    static boolean right = false;

    public Patient(String name, String surname, int age, double height, int heartRate, double weight, Gender gender, ObservableList<Diseases> diseases) {
        System.out.println(heartRate);
        if (validBody(name, surname, age, height, heartRate, weight)) {
            Patient.name = name;
            Patient.surname = surname;
            Patient.age = age;
            Patient.height = height;
            Patient.weight = weight;
            Patient.heartRate = heartRate;
            Patient.gender = gender;
            Diseases = diseases;
        } else {
            showErrorDialog("Invalid patient details provided.");
        }
    }

    static void showErrorDialog(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(text);
        alert.showAndWait();
    }

    public static void showInfoDialog(String s) {
    }

    private boolean validBody(String name, String surname, int age, double height, int heartRate, double weight) {
        boolean isValid = true;

        if (name == null || name.trim().isEmpty()) {
            isValid = false;
        }

        if (surname == null || surname.trim().isEmpty()) {
            isValid = false;
        }

        if (age < 0 || age > 122) {
            isValid = false;
        }

        if (height < 54.6 || height > 272) {
            isValid = false;
        }

        if (weight < 2.1 || weight > 635) {
            isValid = false;
        }

        int maxHeartRate = 220 - age;
        if (heartRate < 30 || heartRate > maxHeartRate) {
            isValid = false;
            System.out.println("Heart rate must be between 30 and " + maxHeartRate + " bpm.");
        }

        return isValid;
    }

    public static String getBMIAnalysis() {
        double heightInMeters = height / 100;
        double bmi = weight / (heightInMeters * heightInMeters);
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal weight";
        } else if (bmi >= 25.0 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public static double calculateWaterIntake() {
        return weight / 30.0;
    }

    public static double calculateWaterCups() {
        double waterIntakeLiters = weight / 30.0;
        return (waterIntakeLiters * 1000) / 250;
    }

    public static double calculateBFP() {
        double bmi = weight / Math.pow(height / 100.0, 2);
        if (gender == Gender.Male) {
            return 1.20 * bmi + 0.23 * age - 16.2;
        } else {
            return 1.20 * bmi + 0.23 * age - 5.4;
        }
    }

    public static String getHeartRateAnalysis() {
        int maxHeartRate = 220 -  age;
        if (heartRate < 60) {
            return "Bradycardia";
        } else if (heartRate <= maxHeartRate * 0.85) {
            return "Normal";
        } else {
            return "Tachycardia";
        }
    }

    public static String getName() {
        return name;
    }

    public static String getSurname() {
        return surname;
    }

    public double getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public static ObservableList<Diseases> getDiseases() {
        return Diseases;
    }

    public static Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        Patient.gender = gender;
    }

    public void setName(String name) {
        Patient.name = name;
    }

    public void setSurname(String surname) {
        Patient.surname = surname;
    }

    public void setAge(int age) {
        if (age < 0 || age > 122) {
            throw new IllegalArgumentException("Invalid age. Age must be between 0 and 122 years.");
        }
        Patient.age = age;
    }

    public void setHeight(double height) {
        if (height < 54.6 || height > 272) {
            throw new IllegalArgumentException("Invalid height. Height must be between 54.6 cm and 272 cm.");
        }
        Patient.height = height;
    }

    public void setWeight(double weight) {
        if (weight < 2.1 || weight > 635) {
            throw new IllegalArgumentException("Invalid weight. Weight must be between 2.1 kg and 635 kg.");
        }
        Patient.weight = weight;
    }

    public void setHeartRate(int heartRate) {
        if (heartRate < 30 || heartRate > 220 - age) {
            throw new IllegalArgumentException("Invalid heart rate. Heart rate must be between 30 bpm and the maximum for the patient's age.");
        }
        Patient.heartRate = heartRate;
    }

    public void setDiseases(ObservableList<Diseases> diseases) {
        Diseases = diseases;
    }
}
