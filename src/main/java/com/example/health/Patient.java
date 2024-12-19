package com.example.health;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class Patient {
    String name;
    String surname;
    int age;
    double height;
    double weight;
    Gender gender;
    int heartRate;
    ObservableList<Diseases> ChronicDiseases;

    public Patient(String name, String surname, int age, double height, int heartRate, double weight, Gender gender, ObservableList<Diseases> diseases) {
        if (validBody(name, surname, age, height, heartRate, weight)) {
            this.name = name;
            this.surname = surname;
            this.age = age;
            this.height = height;
            this.weight = weight;
            this.heartRate = heartRate;
            this.gender = gender;
            this.ChronicDiseases = diseases;
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

    public String getBMIAnalysis() {
        double heightInMeters = this.height / 100;
        double bmi = this.weight / (heightInMeters * heightInMeters);
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

    public double calculateWaterIntake() {
        return this.weight / 30.0;
    }

    public double calculateWaterCups() {
        double waterIntakeLiters = this.weight / 30.0;
        return (waterIntakeLiters * 1000) / 250;
    }

    public double calculateBFP() {
        double bmi = this.weight / Math.pow(this.height / 100.0, 2);
        if (this.gender == Gender.Male) {
            return 1.20 * bmi + 0.23 * this.age - 16.2;
        } else {
            return 1.20 * bmi + 0.23 * this.age - 5.4;
        }
    }

    public String getHeartRateAnalysis() {
        int maxHeartRate = 220 -  this.age;
        if (this.heartRate < 60) {
            return "Bradycardia";
        } else if (this.heartRate <= maxHeartRate * 0.85) {
            return "Normal";
        } else {
            return "Tachycardia";
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
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

    public ObservableList<Diseases> getDiseases() {
        return ChronicDiseases;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        if (age < 0 || age > 122) {
            throw new IllegalArgumentException("Invalid age. Age must be between 0 and 122 years.");
        }
        this.age = age;
    }

    public void setHeight(double height) {
        if (height < 54.6 || height > 272) {
            throw new IllegalArgumentException("Invalid height. Height must be between 54.6 cm and 272 cm.");
        }
        this.height = height;
    }

    public void setWeight(double weight) {
        if (weight < 2.1 || weight > 635) {
            throw new IllegalArgumentException("Invalid weight. Weight must be between 2.1 kg and 635 kg.");
        }
        this.weight = weight;
    }

    public void setHeartRate(int heartRate) {
        if (heartRate < 30 || heartRate > 220 - this.age) {
            throw new IllegalArgumentException("Invalid heart rate. Heart rate must be between 30 bpm and the maximum for the patient's age.");
        }
        this.heartRate = heartRate;
    }

    public void setDiseases(ObservableList<Diseases> diseases) {
        this.ChronicDiseases = diseases;
    }
}
