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
import java.util.Objects;

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
    private ChoiceBox<String> diseasesChoiceBox;
    @FXML
    private ListView<String> chosenDiseasesListView;

    @FXML
    private Button submitButton;

    private ObservableList<String> allDiseases;
    private ObservableList<String> chosenDiseases;

    @FXML
    private void initialize() {
        allDiseases = FXCollections.observableArrayList(
                "Diabetes", "Hypertension", "Asthma"
        );
        chosenDiseases = FXCollections.observableArrayList();


        diseasesChoiceBox.setItems(allDiseases);
        chosenDiseasesListView.setItems(chosenDiseases);


        diseasesChoiceBox.setOnAction(event -> {
            String selectedDisease = diseasesChoiceBox.getValue();
            if (selectedDisease != null && !chosenDiseases.contains(selectedDisease)) {
                chosenDiseases.add(selectedDisease);
                allDiseases.remove(selectedDisease);
                diseasesChoiceBox.setValue(null); // Reset the choice box selection
            }
        });


        chosenDiseasesListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Double-click to remove
                String selectedDisease = chosenDiseasesListView.getSelectionModel().getSelectedItem();
                if (selectedDisease != null) {
                    chosenDiseases.remove(selectedDisease);
                    allDiseases.add(selectedDisease);
                }
            }
        });
    }

    private void showErrorDialog(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Validation Error");
        alert.setHeaderText(text);
        alert.showAndWait();
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

            showErrorDialog("All fields except diseases must be filled.");
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
        String birthDate = birthDatePicker.getValue() != null ? birthDatePicker.getValue().toString() : "Not selected";

        ObservableList<String> selectedDiseases = FXCollections.observableArrayList(chosenDiseases);

        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Birthdate: " + birthDate);
        System.out.println("Height: " + height);
        System.out.println("Weight: " + weight);
        System.out.println("Heart Rate: " + heartRate);
        System.out.println("Gender: " + (gender != null ? gender.name() : "Not selected"));
        System.out.println("Diseases: " + selectedDiseases);

        Parent parent;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/health/advice-view.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
            showErrorDialog("Something went Wrong");
            return;
        }

        Scene scene = new Scene(parent);
        Stage stage = (Stage) anchor.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Advice Window");
        stage.centerOnScreen();
    }
}
