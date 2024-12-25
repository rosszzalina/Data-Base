package com.example.health;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Objects;

import static java.lang.Integer.parseInt;

public class HelloController {

    @FXML
    private AnchorPane anchor;
    @FXML
    private TextField heightField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField weightField;
    @FXML
    private TextField heartRateField;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private RadioButton female;
    @FXML
    private RadioButton male;
    @FXML
    private ChoiceBox<Diseases> diseasesChoiceBox;
    @FXML
    private ListView<Diseases> chosenDiseasesListView;

    @FXML
    private Button submitButton;

    private ObservableList<Diseases> allDiseases;
    private ObservableList<Diseases> chosenDiseases;

    PatientCRUD pationCRUD = new PatientCRUD();

    @FXML
    private void initialize() {
        allDiseases = // get all the diseases with observable array list
        chosenDiseases = FXCollections.observableArrayList();


        diseasesChoiceBox.setItems(allDiseases);
        chosenDiseasesListView.setItems(chosenDiseases);


        diseasesChoiceBox.setOnAction(event -> {
            Diseases selectedDisease = diseasesChoiceBox.getValue();
            if (selectedDisease != null && !chosenDiseases.contains(selectedDisease)) {
                chosenDiseases.add(selectedDisease);
                allDiseases.remove(selectedDisease);
                diseasesChoiceBox.setValue(null); // Reset the choice box selection
            }
        });


        chosenDiseasesListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Double-click to remove
                Diseases selectedDisease = chosenDiseasesListView.getSelectionModel().getSelectedItem();
                if (selectedDisease != null) {
                    chosenDiseases.remove(selectedDisease);
                    allDiseases.add(selectedDisease);
                }
            }
        });
    }


    @FXML
    private void handleSubmitButtonAction(ActionEvent actionEvent) throws IOException {
        if (nameField.getText().isEmpty() ||
                surnameField.getText().isEmpty() ||
                heightField.getText().isEmpty() ||
                weightField.getText().isEmpty() ||
                heartRateField.getText().isEmpty() ||
                birthDatePicker.getValue() == null ||
                (!male.isSelected() && !female.isSelected())) {

            Patient.showErrorDialog("All fields except diseases must be filled.");
            return;
        }

        String name = nameField.getText();
        String surname = surnameField.getText();
        String height = heightField.getText();
        String weight = weightField.getText();
        String heartRate = heartRateField.getText();
        Gender gender = null;
        if (male.isSelected()) {
            gender = Gender.Male;
        } else if (female.isSelected()) {
            gender = Gender.Female;
        }
        String birthDate = birthDatePicker.getValue().toString();
        String firstFourChars = birthDate.substring(0, 4);
        int birthYear = Integer.parseInt(firstFourChars);
        int currentYear = LocalDate.now().getYear();
        int age = currentYear - birthYear;

        ObservableList<Diseases> selectedDiseases = FXCollections.observableArrayList(chosenDiseases);

        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Birthdate: " + birthDate);
        System.out.println("Age: " + age);
        System.out.println("Height: " + height);
        System.out.println("Weight: " + weight);
        System.out.println("Heart Rate: " + heartRate);
        System.out.println("Gender: " + gender);
        System.out.println("Diseases: " + selectedDiseases);
        Patient newPatient = new Patient(name, surname, age,  Double.parseDouble(height), parseInt(heartRate), Double.parseDouble(weight), gender, selectedDiseases);

        try {
            pationCRUD.createPatient(1, name, surname, age,  Double.parseDouble(height), parseInt(heartRate), Double.parseDouble(weight), gender, selectedDiseases);
        }

        Patient.right = true;
        System.out.println(newPatient);

        Parent parent;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/health/advice-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            Patient.showErrorDialog("Something went Wrong");
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = (Stage) anchor.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Advice Window");
        stage.centerOnScreen();
    }
}
