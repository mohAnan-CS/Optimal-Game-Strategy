package alert;

import javafx.scene.control.Alert;

public class AlertMaker {

    public static void make(String error){

        Alert alertCreat = new Alert(Alert.AlertType.ERROR);
        alertCreat.setTitle("Error");
        alertCreat.setHeaderText(null);
        alertCreat.setContentText(error);
        alertCreat.showAndWait();

    }

}
