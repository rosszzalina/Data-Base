module com.example.health {
    exports com.example.health;
    requires javafx.controls;
    requires javafx.fxml;
    opens com.example.health to javafx.fxml;
}
