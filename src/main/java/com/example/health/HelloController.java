package com.example.health;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;

import java.util.List;
import java.util.Vector;


public class HelloController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField surnameField;

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private TextField heightField;

    @FXML
    private TextField weightField;

    @FXML
    private TextField heartRateField;

    @FXML
    private ChoiceBox<String> genderChoiceBox;

    @FXML
    private ListView<String> diseaseListView;

    @FXML
    private Button submitButton;

    @FXML
    private void handleSubmitButtonAction() {
        System.out.println("Submit button clicked!");
        // Add logic to process user input
    }
    @FXML
    private ComboBox<Gender> genderComboBox;

    @FXML
    public void initialize() {
        // Populate ComboBox with Gender enum values
        genderComboBox.getItems().addAll(Gender.values());
    }
    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

    @FXML
    private void onSubmit() {
        try {
            // Retrieve user inputs
            String name = nameField.getText();
            String surname = surnameField.getText();
            double height = Double.parseDouble(heightField.getText());
            double weight = Double.parseDouble(weightField.getText());
            int heartRate = Integer.parseInt(heartRateField.getText());
            String genderStr = genderChoiceBox.getValue();
            Gender gender = genderStr.equalsIgnoreCase("Male") ? Gender.Male : Gender.Female;
            List<String> selectedDiseases = diseaseListView.getSelectionModel().getSelectedItems();

            // Convert diseases to enum
            Vector<Diseases> diseases = new Vector<>();
            for (String disease : selectedDiseases) {
                diseases.add(Diseases.valueOf(disease));
            }

            // Create Patient object
            Patient patient = new Patient(name + " " + surname, 0, height, heartRate, weight, gender, diseases);

            // Show next scene (advice)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("advice-view.fxml"));
            Stage stage = (Stage) submitButton.getScene().getWindow();
            Scene scene = new Scene(loader.load());

            AdviceController adviceController = loader.getController();
            adviceController.setPatient(patient);

            stage.setScene(scene);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Input");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
