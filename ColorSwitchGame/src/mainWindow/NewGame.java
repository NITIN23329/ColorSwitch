package mainWindow;

import All_Obstacle.Obstacle;
import Screen_move.Ball;
import Screen_move.Coordinate;
import Screen_move.InitialiseGame;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class NewGame {
    private static Stage stage;
    private static  Color ballColor;
    @FXML
    private RadioButton rb1,rb2,rb3,rb4;
    @FXML
    private Button button;

    public void selectColor(){
        buttonPlay();
        if(rb1.isSelected())ballColor = Color.RED;
        else if(rb2.isSelected())ballColor = Color.AQUA;
        else if(rb3.isSelected())ballColor = Color.YELLOW;
        else ballColor = Color.MAGENTA;
        System.out.println(ballColor);
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();

    }
    public void buttonPlay(){
        String path = "/Users/nitin23329/Documents/intellij/codes/ColorSwitchGame/src/media/buttonClick.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

    }
    private static MediaPlayer mediaPlayer;
    public   static void gameScreenPlay(){
        String path = "/Users/nitin23329/Documents/intellij/codes/ColorSwitchGame/src/media/gamePlay.mp3";
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();
        mediaPlayer.setCycleCount(Animation.INDEFINITE);
    }
    public static void startGame(Stage initialStage)
    {
        MainWindowMain.stopMainPagePlay();
        gameScreenPlay();
        final Obstacle[] obstacles=new Obstacle[11];
        try{
            stage =new Stage();
            AnchorPane anchorPane= FXMLLoader.load(NewGame.class.getResource("/Screen_move/Obstacle_GameScreen.fxml"));
            stage.setTitle("Game Screen");
            stage.setResizable(false);

            if(ballColor==null) ballColor = Color.AQUA;
            Ball ball=new Ball(ballColor,12,new Coordinate(350,660));

            AnimationTimer animationTimer=new InitialiseGame(anchorPane,obstacles,ball,false);

            animationTimer.start();

            anchorPane.getChildren().addAll(ball.getCircle());

            Scene scene = new Scene(anchorPane, 750, 700);

            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    ball.move(ball,scene,obstacles);
                }
            });
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();
            if(initialStage!=null)closeGameScreen(initialStage);

        }
        catch (IOException e){
            System.out.println("Can not load Game screen");
            e.printStackTrace();

        }
    }

    public static Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        NewGame.stage = stage;
    }

    public static void closeGameScreen(Stage stage){
        mediaPlayer.stop();
        stage.close();
    }
}
