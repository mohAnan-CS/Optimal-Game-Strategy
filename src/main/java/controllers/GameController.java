package controllers;

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
import game.Game;
import game.OptimalGameStrategy;

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

        prepareScrollPane();
        setAllProprieties();
        setDisableForFiled();

    }


    //////////////////////////////////// FUNCTIONS FOR PREPARING TO START A GAME ////////////////////////////////////

    private void prepareScrollPane(){

        for (int j : COIN_ARRAY) {

            Circle circle = creatCircle();
            Text text = creatText(j);
            Group group = new Group(circle, text);
            hbox.getChildren().add(group);

        }

    }

    private void setAllProprieties(){

        hbox.setSpacing(30);
        hbox.setPadding(new Insets(100, 30, 100, 30));
        scrollPane.setContent(hbox);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

    }

    private void setDisableForFiled(){

        dpTextArea.setDisable(true);
        resultTextFiled.setDisable(true);
        coinTextFiled.setDisable(true);

    }

    private Circle creatCircle(){

        Circle circle = new Circle(30, Color.WHITE);
        circle.setStrokeWidth(3);
        circle.setStrokeMiterLimit(10);
        circle.setStrokeType(StrokeType.CENTERED);
        circle.setStroke(Color.valueOf("0x333333"));

        return circle;

    }

    private Text creatText(int index){

        Text text = new Text(String.valueOf(index));
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        text.setBoundsType(TextBoundsType.VISUAL);

        return text;
    }

    //////////////////////////////////// FUNCTION FOR PLAY GAME ////////////////////////////////////

    @FXML
    void playOnAction(){

        Game game = OptimalGameStrategy.play(COIN_ARRAY);
        List<Integer> listCoins = game.getCoins();

        SequentialTransition sequence = new SequentialTransition();
        for (Node child : hbox.getChildren()) {

            if (child instanceof Group group) {

                Text text = (Text) group.getChildren().get(1);
                int number = Integer.parseInt(text.getText().trim());

                if (listCoins.contains(number)){
                    creatTransition(group, sequence, 0);
                }else{
                    creatTransition(group, sequence, 1);
                }
            }
        }

        sequence.play();
        showDpTable(game);
        showResult(game);
        showCoin(game);

    }

    //////////////////////////////////// FUNCTIONS FOR CIRCLE ANIMATIONS ////////////////////////////////////

    // turn . 0 for player . 1 for computer
    private void creatTransition(Group group, SequentialTransition sequence, int turn){

        TranslateTransition transition = new TranslateTransition(Duration.millis(500), group);
        transition.setFromY(group.getTranslateY());
        if (turn == 0)
            transition.setToY(group.getTranslateY() - 50);
        else
            transition.setToY(group.getTranslateY() + 50);
        sequence.getChildren().add(transition);

    }

    //////////////////////////////////// FUNCTIONS THAT SHOW THE RESULT IN TEXT FILED AND TEXT AREA ////////////////////////////////////

    private void showDpTable(Game game){

        int[][] dpTableArray = game.getDbTable();
        String dpTableString = "";
        for (int[] ints : dpTableArray) {
            for (int anInt : ints) {
                dpTableString += anInt + " ";
            }
            dpTableString += "\n";
        }

        dpTextArea.setText(dpTableString);
    }

    private void showResult(Game game){

        String result = String.valueOf(game.getExceptedResult());
        resultTextFiled.setText(result);

    }

    private void showCoin(Game game){

        String coin = String.valueOf(game.getCoins().get(0));
        coinTextFiled.setText(coin);

    }

}
