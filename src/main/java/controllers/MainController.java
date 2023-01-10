package controllers;

import alert.AlertMaker;
import birzeit.university.optimalgamestrategyproject.CoinGameApplication;
import exception.Exceptions;
import file.FileReader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    private static int numberOfCoins ;
    private static String[] splitCoins ;


    //////////////////////////////////// FUNCTIONS FOR FILE OPERATION ////////////////////////////////////

    @FXML
    void btnReadFileOnAction(){

        try {

            String path = pathLabel.getText().trim();
            FileReader.read(path);
            switchStage();

        }catch (IllegalArgumentException illegalArgumentException){
            AlertMaker.make(illegalArgumentException.getMessage());
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

    //////////////////////////////////// FUNCTIONS FOR MANUALLY ENTERING COIN OPERATION ////////////////////////////////////

    @FXML
    void btnEnterCoinOnAction() {

        try {

            checkAllException();

            int[] coin = new int[numberOfCoins];
            for (int i = 0; i < numberOfCoins; i++)
                coin[i] = Integer.parseInt(splitCoins[i]);

            GameController.COIN_ARRAY =coin;
            switchStage();

        }catch (IllegalArgumentException illegalArgumentException){
            AlertMaker.make(illegalArgumentException.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void checkAllException(){

        Exceptions.checkTextFiledEmpty(textFiledCoins.getText());
        String coins = removeCommaSeparateNumbers();
        Exceptions.containsNumber(coins);
        Exceptions.checkNumberCoinValidate(numberOfCoins);

    }

    private String removeCommaSeparateNumbers() {

        String[] textFiledCoinSplit = textFiledCoins.getText().split(",");
        String coins = "";
        for (String s : textFiledCoinSplit)
            coins += s;

        numberOfCoins = textFiledCoinSplit.length;
        splitCoins = textFiledCoinSplit;

        return coins;
    }

    //////////////////////////////////// FUNCTION FOR SWITCH STAGE ////////////////////////////////////

    private void switchStage() throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/view/" + "game-view".concat(".fxml"))));
        CoinGameApplication.STAGE.setScene(new Scene(root));
        CoinGameApplication.STAGE.centerOnScreen();
        CoinGameApplication.STAGE.show();

    }
}