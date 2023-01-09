module com.example.optimalgamestrategyproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.optimalgamestrategyproject to javafx.fxml;
    exports com.example.optimalgamestrategyproject;
    exports controllers;
    opens controllers to javafx.fxml;
}