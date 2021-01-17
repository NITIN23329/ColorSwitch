package Obstacle_Collide;

import Screen_move.InitialiseGame;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;
import javafx.util.Duration;
import mainWindow.MainWindowMain;
import mainWindow.NewGame;

import java.io.File;

public class ObstacleCollisionController {
    @FXML
    private Arc leftTopArc1,leftTopArc2,leftTopArc3,leftTopArc4;
    @FXML
    private Arc rightTopArc1,rightTopArc2,rightTopArc3,rightTopArc4;
    @FXML
    private ImageView homeView,starResume;
    @FXML
    private ImageView restartView;
    @FXML
    private TextField total_star_collected,Best_Score_id,Score_id;


    @FXML
    private void initialize()
    {
        Timeline inner = new Timeline();
        Timeline middle = new Timeline();
        Timeline outer = new Timeline();
        Timeline top = new Timeline();

        addRotation(top,leftTopArc1, false);
        addRotation(top,leftTopArc2, false);
        addRotation(top,leftTopArc3, false);
        addRotation(top,leftTopArc4, false);

        addRotation(top,rightTopArc1, true);
        addRotation(top,rightTopArc2, true);
        addRotation(top,rightTopArc3, true);
        addRotation(top,rightTopArc4, true);

        inner.setCycleCount(Animation.INDEFINITE);
        middle.setCycleCount(Animation.INDEFINITE);
        outer.setCycleCount(Animation.INDEFINITE);
        top.setCycleCount(Animation.INDEFINITE);
        inner.play();
        middle.play();
        outer.play();
        top.play();

        UpdateScore();
    }

    private void addRotation(Timeline rotation, Arc arc, boolean isClockWise){
        if(isClockWise)
            rotation.getKeyFrames().add(new KeyFrame(Duration.seconds(2), new KeyValue(arc.startAngleProperty(), arc.getStartAngle() - 360, Interpolator.LINEAR)));
        else
            rotation.getKeyFrames().add(new KeyFrame(Duration.seconds(2), new KeyValue(arc.startAngleProperty(), arc.getStartAngle() + 360, Interpolator.LINEAR)));

    }

    private void UpdateScore()
    {
        Score_id.setText(Integer.toString(MainWindowMain.score));
        Best_Score_id.setText(Integer.toString(MainWindowMain.highScore));
        total_star_collected.setText(Integer.toString(MainWindowMain.total_Star));
    }
    public void buttonPlay(String str){
        String path = "/Users/nitin23329/Documents/intellij/codes/ColorSwitchGame/src/media/"+str;
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

    }
    @FXML
    public void handleHome(){
        buttonPlay("buttonClick.mp3");
        MainWindowMain.mainPagePlay();
        InitialiseGame.setChangeIsCollisionWindowOpen();
        Stage stage = (Stage) homeView.getScene().getWindow();
        stage.close();
        NewGame.closeGameScreen(NewGame.getStage());
        InitialiseGame.collided=false;
    }
    @FXML
    public void handleRestartView(){
        buttonPlay("buttonClick.mp3");
        InitialiseGame.setChangeIsCollisionWindowOpen();
        Stage stage = (Stage) restartView.getScene().getWindow();
        stage.close();
        NewGame.startGame(NewGame.getStage());
        InitialiseGame.collided=false;
    }

    @FXML
    public void ReduceAndStart()  {
        InitialiseGame.setChangeIsCollisionWindowOpen();
        if(MainWindowMain.total_Star>=3)
        {
            buttonPlay("buttonClick.mp3");
            Stage stage1 = (Stage) starResume.getScene().getWindow();
            stage1.close();
            MainWindowMain.total_Star=MainWindowMain.total_Star-3;
        }
        else
        {   buttonPlay("alert.mp3");
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("INSUFFICIENT STARS!! -_- \nYOU NEED AT LEAST 3 STARS TO CONTINUE TO PLAY");
            alert.showAndWait();
        }
        InitialiseGame.collided=false;
    }
}
