package controllers;

import birzeit.university.optimalgamestrategyproject.CoinGameApplication;
import exception.Exceptions;
import file.FileReader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class MainController{

    @FXML
    private TextField textFiledCoins;

    @FXML
    private Label pathLabel;

    @FXML
    void btnReadFileOnAction(){

        try {

            String path = pathLabel.getText().trim();
            FileReader.read(path);
            switchStage();

        }catch (IllegalArgumentException illegalArgumentException){
            creatAlert(illegalArgumentException.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
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
        if (!String.valueOf(selectedFile).equals("null")) {
            pathLabel.setText(selectedFile.toString());
        }


    }

    @FXML
    void btnEnterCoinOnAction() {

        try {

            Exceptions.checkTextFiledEmpty(textFiledCoins.getText());
            String[] textFiledCoinSplit = textFiledCoins.getText().split(",");

            String coins = "";
            for (String s : textFiledCoinSplit)
                coins += s;

            Exceptions.containsNumber(coins);
            Exceptions.checkNumberCoinValidate(textFiledCoinSplit.length);

            int[] coin = new int[textFiledCoinSplit.length];
            for (int i = 0; i < textFiledCoinSplit.length; i++)
                coin[i] = Integer.parseInt(textFiledCoinSplit[i]);

            GameController.COIN_ARRAY =coin;
            switchStage();

        }catch (IllegalArgumentException illegalArgumentException){
            creatAlert(illegalArgumentException.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void creatAlert(String error){

        Alert alertCreat = new Alert(Alert.AlertType.ERROR);
        alertCreat.setTitle("Error");
        alertCreat.setHeaderText(null);
        alertCreat.setContentText(error);
        alertCreat.showAndWait();

    }

    private void switchStage() throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/view/" + "game-view".concat(".fxml"))));
        CoinGameApplication.STAGE.setScene(new Scene(root));
        CoinGameApplication.STAGE.centerOnScreen();
        CoinGameApplication.STAGE.show();

    }
}