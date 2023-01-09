package com.example.optimalgamestrategyproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CoinGameApplication extends Application {

    public static Stage STAGE;

    @Override
    public void start(Stage stage) throws IOException {

        STAGE =stage;
        FXMLLoader fxmlLoader = new FXMLLoader(CoinGameApplication.class.getResource("/com/example/view/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Coin Maxi");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}