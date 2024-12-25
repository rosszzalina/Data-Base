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
    String name;
     String surname;
    int age;
     double height;
     double weight;
     Gender gender;
     int heartRate;
    ObservableList<Diseases> ChronicDiseases;

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
    private Patient patient;





    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @FXML
    private void initialize() {
        patient = new Patient(name, surname, age, height, heartRate, weight, gender, diseases);

        setPatient(patient);
        fullName.setText(patient.name + " " + patient.surname);
        Age.setText("Age: " + patient.age);
        Height.setText("Height: " + patient.height + " cm");
        Weight.setText("Weight: " + patient.weight + " kg");
        HeartBeat.setText("HB: " + patient.heartRate + " per/m");
        Gender1.setText("Gender: " + patient.getGender());
        WaterPerDay.setText(String.format("%.2f liters", patient.calculateWaterIntake()) + " (or " + String.format("%.0f cups", patient.calculateWaterCups()) + ")\n\n");
        ObservableList<Diseases> allDiseases = patient.getDiseases();
        patientDiseases.setItems(allDiseases);
        patientDiseases.setOnAction(event -> {
            Diseases selectedDisease = patientDiseases.getValue();
            if (selectedDisease != null) {
                String advice = getDiseaseAdvice(selectedDisease);
                showAdviceDialog(advice, selectedDisease.toString());
            } else {
                showErrorDialog("Please select a disease to get advice.");
            }
            patientDiseases.setValue(null);
        });
//        Image profile = new Image(getClass().getResourceAsStream("/images/profile.png"));
//        profile1.setImage(profile);
//        generateAdvice();
    }

    String getDiseaseAdvice(Diseases disease) {
        String advices = "drink it";
        return advices;
    }


    private void generateAdvice() {
        StringBuilder advice = new StringBuilder();
        advice.append("Welcome, ").append(patient.getName() + " " + patient.getSurname() + "!\n\n");
        advice.append("BMI Analysis: ").append(patient.getBMIAnalysis()).append("\n");
        advice.append("Body Fat Percentage: ").append(String.format("%.2f%%", patient.calculateBFP())).append("\n");
        advice.append("Heart Rate Analysis: ").append(patient.getHeartRateAnalysis()).append("\n");

        if (patient.getDiseases() != null && !patient.getDiseases().isEmpty()) {
            advice.append("Chronic Diseases: \n");
            for (Diseases disease : patient.getDiseases()) {
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
