module com.example.optimalgamestrategyproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens birzeit.university.optimalgamestrategyproject to javafx.fxml;
    exports birzeit.university.optimalgamestrategyproject;
    exports controllers;
    opens controllers to javafx.fxml;
    exports exception;
    opens exception to javafx.fxml;
}