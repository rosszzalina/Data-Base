package com.example.health;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    @FXML
    private TextField nameField, surnameField, heightField, weightField, heartRateField;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private ComboBox<Gender> genderComboBox;
    @FXML
    private ListView<String> diseasesListView;

    @FXML
    private Button submitButton;

    @FXML
    private void initialize() {
        // Заполнение ComboBox с выбором пола
        genderComboBox.setItems(FXCollections.observableArrayList(Gender.Male, Gender.Female));

        // Пример данных для списка хронических заболеваний
        ObservableList<String> diseases = FXCollections.observableArrayList(
                "Diabetes", "Hypertension", "Asthma", "None"
        );
        diseasesListView.setItems(diseases);
    }

    @FXML
    private void handleSubmitButtonAction() {
        // Получение данных из формы
        String name = nameField.getText();
        String surname = surnameField.getText();
        String height = heightField.getText();
        String weight = weightField.getText();
        String heartRate = heartRateField.getText();
        Gender gender = genderComboBox.getValue();
        String birthDate = birthDatePicker.getValue() != null ? birthDatePicker.getValue().toString() : "Not selected";
        ObservableList<String> selectedDiseases = diseasesListView.getSelectionModel().getSelectedItems();

        // Вывод данных в консоль (можно заменить на логику перехода на следующую страницу)
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Birthdate: " + birthDate);
        System.out.println("Height: " + height);
        System.out.println("Weight: " + weight);
        System.out.println("Heart Rate: " + heartRate);
        System.out.println("Gender: " + (gender != null ? gender.name() : "Not selected"));
        System.out.println("Diseases: " + selectedDiseases);
    }
}
