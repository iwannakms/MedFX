module com.example.nucha {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.nucha to javafx.fxml;
    exports com.example.nucha;
}