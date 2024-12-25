module com.example.health {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;


    opens com.example.health to javafx.fxml;
    exports com.example.health;
}