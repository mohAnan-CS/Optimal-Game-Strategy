package controllers;

import com.example.optimalgamestrategyproject.CoinGameApplication;
import com.example.optimalgamestrategyproject.Exceptions;
import com.example.optimalgamestrategyproject.Runner;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private TextField textFiledCoins;

    @FXML
    private Label pathLabel;

    @FXML
    void btnReadFileOnAction() throws FileNotFoundException {

        try {
            System.out.println("hi");
            Runner.readFileCoin(pathLabel.getText().trim());
        }catch (IllegalArgumentException illegalArgumentException){
            creatAlert(illegalArgumentException.getMessage());
        }

    }

    @FXML
    void btnBrowseFileOnAction() {

        FileChooser fileChooserShares = new FileChooser();
        fileChooserShares.setTitle("Select coin file .txt");
        fileChooserShares.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );

        File selectedFile = fileChooserShares.showOpenDialog(null);
        if (String.valueOf(selectedFile).equals("null")) {
            return;
        } else {
            pathLabel.setText(selectedFile.toString());
        }


    }

    @FXML
    void btnEnterCoinOnAction() {

        try {
            Exceptions.checkTextFiledEmpty(textFiledCoins.getText());
            String[] textFiledCoinSplit = textFiledCoins.getText().split(",");

            String coins = "";
            for (String s : textFiledCoinSplit) {
                coins += s;
            }

            Exceptions.containsNumber(coins);
            Exceptions.checkNumberCoinValidate(textFiledCoinSplit.length);
        }catch (IllegalArgumentException illegalArgumentException){
            creatAlert(illegalArgumentException.getMessage());
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        switchStage();

    }

    public void creatAlert(String error){

        Alert alertCreat = new Alert(Alert.AlertType.ERROR);
        alertCreat.setTitle("Error");
        alertCreat.setHeaderText(null);
        alertCreat.setContentText(error);
        alertCreat.showAndWait();

    }

    private void switchStage() {

        try {

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/view/game-view.fxml")));
            CoinGameApplication.STAGE.setScene(new Scene(root));
            CoinGameApplication.STAGE.centerOnScreen();
            CoinGameApplication.STAGE.show();

        } catch (IOException ioException) {
            creatAlert(ioException.getMessage());
        } catch (Exception exception) {
            creatAlert(exception.getMessage());
        }


    }
}