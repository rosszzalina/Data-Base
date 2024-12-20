package com.example.health;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AdviceController {

    @FXML
    private ImageView profile1;
    @FXML
    private Label adviceLabel;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Button getBackButton, generateAdvice;
    @FXML
    private ObservableList<Diseases> diseases = FXCollections.observableArrayList(
            Diseases.DIABETES, Diseases.ALZHEIMERS_DISEASE, Diseases.ADHD
    );;
    @FXML
    private Label fullName;
    @FXML
    private Label Age;
    @FXML
    private Label Height;
    @FXML
    private Label Weight;
    @FXML
    private Label HeartBeat;
    @FXML
    private Label Gender1;
    @FXML
    private Label WaterPerDay;
    @FXML
    private ChoiceBox<Diseases> patientDiseases;

    @FXML
    private void initialize() {
        fullName.setText(Patient.name + " " + Patient.surname);
        Age.setText("Age: " + Patient.age);
        Height.setText("Height: " + Patient.height + " cm");
        Weight.setText("Weight: " + Patient.weight + " kg");
        HeartBeat.setText("HB: " + Patient.heartRate + " per/m");
        Gender1.setText("Gender: " + Patient.getGender());
        WaterPerDay.setText(String.format("%.2f liters", Patient.calculateWaterIntake()) + " (or " + String.format("%.0f cups", Patient.calculateWaterCups()) + ")\n\n");
        ObservableList<Diseases> allDiseases = Patient.getDiseases();
        patientDiseases.setItems(allDiseases);
        patientDiseases.setOnAction(event -> {
            Diseases selectedDisease = patientDiseases.getValue();
            String advice = getDiseaseAdvice(selectedDisease);
            showAdviceDialog(advice, selectedDisease.toString());
            patientDiseases.setValue(null);
        });
//        Image profile = new Image(getClass().getResourceAsStream("/images/profile.png"));
//        profile1.setImage(profile);
//        generateAdvice();
    }

    String getDiseaseAdvice(Diseases disease) {
        if (disease == Diseases.DIABETES) {
            return "For diabetes, it's important to monitor blood sugar levels regularly. Follow a balanced diet, avoid high sugar intake, exercise regularly, and take medications as prescribed.";
        } else if (disease == Diseases.ALZHEIMERS_DISEASE) {
            return "For Alzheimer's disease, maintaining mental stimulation through puzzles, reading, and social activities can be helpful. Stay physically active, follow a balanced diet, and ensure proper medication adherence.";
        } else if (disease == Diseases.ADHD) {
            return "For ADHD, it's essential to create a structured environment, minimize distractions, and follow a regular routine. Medication and behavioral therapy can be helpful in managing symptoms.";
        } else {
            return "No specific advice available for this disease.";
        }
    }


    private void generateAdvice() {
        StringBuilder advice = new StringBuilder();
        advice.append("Welcome, ").append(Patient.getName() + " " + Patient.getSurname() + "!\n\n");
        advice.append("BMI Analysis: ").append(Patient.getBMIAnalysis()).append("\n");
        advice.append("Body Fat Percentage: ").append(String.format("%.2f%%", Patient.calculateBFP())).append("\n");
        advice.append("Heart Rate Analysis: ").append(Patient.getHeartRateAnalysis()).append("\n");

        if (Patient.getDiseases() != null && !Patient.getDiseases().isEmpty()) {
            advice.append("Chronic Diseases: \n");
            for (Diseases disease : Patient.getDiseases()) {
                advice.append("- ").append(disease.name()).append("\n");
            }
        }

        adviceLabel.setText(advice.toString());
    }

    @FXML
    private void getBack(ActionEvent actionEvent) throws IOException {
        Parent parent;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/health/hello-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Something went wrong");
            return;
        }
        Patient.right = false;
        Scene scene = new Scene(parent);
        Stage stage = (Stage) anchor.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Registration");
        stage.centerOnScreen();
    }

    private void showErrorDialog(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(text);
        alert.showAndWait();
    }

    static void showAdviceDialog(String text, String disease) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(disease);
        alert.setHeaderText(text);
        alert.showAndWait();
    }

}
