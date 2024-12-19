module com.example.health {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.health to javafx.fxml;
    exports com.example.health;
}