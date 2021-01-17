package mainWindow;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class MainWindowMain extends Application {
    public static int highScore;
    public static int total_Star=3;
    public static int score;
    public static ArrayList<PropertyOfSavedGames> savedGames;
    public static ListView<String> list;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        primaryStage.setTitle("Home Page");
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, 568, 504);
        primaryStage.setScene(scene);
        mainPagePlay();
        primaryStage.show();
    }
    private  static MediaPlayer mediaPlayer;

    public   static void mainPagePlay(){
        String path = "/Users/nitin23329/Documents/intellij/codes/ColorSwitchGame/src/media/mainPage.mp3";
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();
        mediaPlayer.setCycleCount(Animation.INDEFINITE);
    }


    public static void stopMainPagePlay(){
        mediaPlayer.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
