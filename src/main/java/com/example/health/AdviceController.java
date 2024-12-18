package com.example.health;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

    public class AdviceController {

        @FXML
        private Label adviceLabel;

        private Patient patient;

        public void setPatient(Patient patient) {
            this.patient = patient;
            generateAdvice();
        }

        private void generateAdvice() {
            StringBuilder advice = new StringBuilder();
            advice.append("Welcome, ").append(patient.getName()).append("!\n\n");
            advice.append("BMI Analysis: ").append(patient.getBMIAnalysis()).append("\n");
            advice.append("Body Fat Percentage: ").append(String.format("%.2f%%", patient.calculateBFP())).append("\n");
            advice.append("Heart Rate Analysis: ").append(patient.getHeartRateAnalysis()).append("\n");
            advice.append("Daily Water Intake: ").append(String.format("%.2f liters", patient.calculateWaterIntake())).append(" (or ")
                    .append(String.format("%.0f cups", patient.calculateWaterCups())).append(")\n\n");

            if (!patient.getDiseases().isEmpty()) {
                advice.append("Chronic Diseases: \n");
                for (Diseases disease : patient.getDiseases()) {
                    advice.append("- ").append(disease).append("\n");
                }
            }

            adviceLabel.setText(advice.toString());
        }
}
