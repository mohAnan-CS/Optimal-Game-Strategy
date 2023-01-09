package controllers;

import com.example.optimalgamestrategyproject.Runner;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.util.Duration;
import problem.Game;
import problem.OptimalGameStrategy;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    public static int[] COIN_ARRAY;

    @FXML
    private TextArea dpTextArea;

    @FXML
    private TextField resultTextFiled;

    @FXML
    private TextField coinTextFiled;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private HBox hbox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dpTextArea.setDisable(true);
        resultTextFiled.setDisable(true);
        coinTextFiled.setDisable(true);


        hbox.setSpacing(30);

        for (int j : COIN_ARRAY) {

            Circle circle = new Circle(30, Color.WHITE);
            circle.setStrokeWidth(3);
            circle.setStrokeMiterLimit(10);
            circle.setStrokeType(StrokeType.CENTERED);
            circle.setStroke(Color.valueOf("0x333333"));
            Text text = new Text(circle.getCenterX() , circle.getCenterY(),String.valueOf(j));
            text.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
            text.setBoundsType(TextBoundsType.VISUAL);
            Group group = new Group(circle, text);
            hbox.getChildren().add(group);
            hbox.setPadding(new Insets(100, 30, 100, 30));

        }

        scrollPane.setContent(hbox);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

    }

    @FXML
    void playOnAction() throws InterruptedException {

        Game game = OptimalGameStrategy.play(COIN_ARRAY);
        List<Integer> listCoins = game.getCoins();

        SequentialTransition sequence = new SequentialTransition();
        for (Node child : hbox.getChildren()) {
            if (child instanceof Group group) {

                // Circle circle = (Circle) group.getChildren().get(0);
                Text text = (Text) group.getChildren().get(1);
                int number = Integer.parseInt(text.getText().trim());
                if (listCoins.contains(number)){
                    TranslateTransition transition = new TranslateTransition(Duration.millis(500), group);
                    transition.setFromY(group.getTranslateY());
                    transition.setToY(group.getTranslateY() - 50);
                    sequence.getChildren().add(transition);
                }else{
                    TranslateTransition transition = new TranslateTransition(Duration.millis(500), group);
                    transition.setFromY(group.getTranslateY());
                    transition.setToY(group.getTranslateY() + 50);
                    sequence.getChildren().add(transition);
                }
            }
        }

        // Start the animation
        sequence.play();
        
        int[][] dpTableArray = game.getDbTable();
        String dpTableString = "";
        for (int[] ints : dpTableArray) {
            for (int anInt : ints) {
                dpTableString += anInt + " ";
            }
            dpTableString += "\n";
        }
        dpTextArea.setText(dpTableString);

        resultTextFiled.setText(String.valueOf(game.getExceptedResult()));

        coinTextFiled.setText(String.valueOf(game.getCoins().get(0)));

    }

}
