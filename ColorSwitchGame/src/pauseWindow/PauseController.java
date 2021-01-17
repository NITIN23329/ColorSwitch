package pauseWindow;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import mainWindow.MainWindowMain;
import mainWindow.NewGame;

import java.io.File;
import java.util.Optional;


public class PauseController {
    @FXML
    private ImageView resumeButton;
    @FXML
    private ImageView home;

    @FXML
    public void initialize()
    {
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), resumeButton);
        fadeTransition.setFromValue(0.3);
        fadeTransition.setToValue(1);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();

    }
    @FXML
    public void handleResume(){
        buttonPlay("buttonClick.mp3");
        Stage stage = (Stage) resumeButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void handleHome(){
        buttonPlay("alert.mp3");
        MainWindowMain.mainPagePlay();
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Returning to Main Menu");
        alert.setHeaderText("All your progress will be deleted!! \n\tAre you sure you want to exit the Main Menu?");
        alert.setContentText("Press 'OK' to Exit , 'Cancel' to return");

        Optional<ButtonType> result =alert.showAndWait();
        if(result.isPresent() && result.get().equals(ButtonType.OK)){
            handleResume();
            NewGame.closeGameScreen(NewGame.getStage());
        }
        else buttonPlay("buttonClick.mp3");
    }
    public void buttonPlay(String str){
        String path = "/Users/nitin23329/Documents/intellij/codes/ColorSwitchGame/src/media/"+str;
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

    }
}
