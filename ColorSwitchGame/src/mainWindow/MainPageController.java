package mainWindow;

import SaveScreen.Save;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Arc;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;


public class MainPageController {

    @FXML
    public StackPane myStackPane;
    @FXML
    private Arc innerArc1, innerArc2, innerArc3, innerArc4;
    @FXML
    private Arc middleArc1, middleArc2, middleArc3, middleArc4;
    @FXML
    private Arc outerArc1, outerArc2, outerArc3, outerArc4;
    @FXML
    private Arc leftTopArc1,leftTopArc2,leftTopArc3,leftTopArc4;
    @FXML
    private Arc rightTopArc1,rightTopArc2,rightTopArc3,rightTopArc4;
    @FXML
    private Button newGame;
    @FXML
    private Button resumeGame;
    @FXML
    private Button exit;
    @FXML
    private Button helpButton;
    @FXML
    private ImageView settingImage;
    @FXML
    public static Scene scene;



    @FXML
    private void initialize(){

        Tooltip myToolTip =  new Tooltip("SELECT COLOR OF BALL");
        myToolTip.setStyle("-fx-background-color: YELLOW; -fx-text-fill: BLACK;");
        Tooltip.install(settingImage ,myToolTip);

        Timeline inner = new Timeline();
        Timeline middle = new Timeline();
        Timeline outer = new Timeline();
        Timeline top = new Timeline();

        RotateTransition rotateHelp = new RotateTransition(Duration.seconds(2),helpButton);
        rotateHelp.setFromAngle(-45);
        rotateHelp.setToAngle(45);
        rotateHelp.setAutoReverse(true);
        rotateHelp.setCycleCount(Animation.INDEFINITE);

        RotateTransition rotateSetting = new RotateTransition(Duration.seconds(3),settingImage);
        rotateSetting.setToAngle(360);
        rotateSetting.setAutoReverse(true);
        rotateSetting.setCycleCount(Animation.INDEFINITE);


        addRotation(inner,innerArc1,2,true);
        addRotation(inner,innerArc2,2,true);
        addRotation(inner,innerArc3,2,true);
        addRotation(inner,innerArc4,2,true);

        addRotation(middle,middleArc1,4,false);
        addRotation(middle,middleArc2,4,false);
        addRotation(middle,middleArc3,4,false);
        addRotation(middle,middleArc4,4,false);

        addRotation(outer,outerArc1,3,true);
        addRotation(outer,outerArc2,3,true);
        addRotation(outer,outerArc3,3,true);
        addRotation(outer,outerArc4,3,true);

        addRotation(top,leftTopArc1,2,false);
        addRotation(top,leftTopArc2,2,false);
        addRotation(top,leftTopArc3,2,false);
        addRotation(top,leftTopArc4,2,false);

        addRotation(top,rightTopArc1,2,true);
        addRotation(top,rightTopArc2,2,true);
        addRotation(top,rightTopArc3,2,true);
        addRotation(top,rightTopArc4,2,true);

        inner.setCycleCount(Animation.INDEFINITE);
        middle.setCycleCount(Animation.INDEFINITE);
        outer.setCycleCount(Animation.INDEFINITE);
        top.setCycleCount(Animation.INDEFINITE);
        inner.play();
        middle.play();
        outer.play();
        top.play();
        rotateHelp.play();
        rotateSetting.play();

    }

    private void addRotation(Timeline rotation , Arc arc , int sec, boolean isClockWise){
        if(isClockWise)
            rotation.getKeyFrames().add(new KeyFrame(Duration.seconds(sec), new KeyValue(arc.startAngleProperty(), arc.getStartAngle() - 360, Interpolator.LINEAR)));
        else
            rotation.getKeyFrames().add(new KeyFrame(Duration.seconds(sec), new KeyValue(arc.startAngleProperty(), arc.getStartAngle() + 360, Interpolator.LINEAR)));

    }

    public  void buttonPlay(String str){
        String path = "/Users/nitin23329/Documents/intellij/codes/ColorSwitchGame/src/media/"+str;
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
    @FXML
    public void handleExitButton(){
        buttonPlay("alert.mp3");
        Alert alert =new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exiting Game");
        alert.setHeaderText("Are you sure you want to exit the game?");
        alert.setContentText("Press 'OK' to Exit , 'Cancel' to return to game");

        Optional<ButtonType> result =alert.showAndWait();
        if(result.isPresent() && result.get().equals(ButtonType.OK)){
            MainWindowMain.stopMainPagePlay();
            System.exit(0);
        }
    }

    @FXML
    public void handleHelpButton(){
        try {
            buttonPlay("buttonClick.mp3");
            Desktop d = Desktop.getDesktop();
            d.browse(new URI("https://www.youtube.com/watch?v=-KV35ZXSW8s"));
        }
        catch (URISyntaxException | IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void handleSettingImage(){
        try{
            buttonPlay("buttonClick.mp3");
            Parent root = FXMLLoader.load( getClass().getResource("/mainWindow/initBall.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            // this will not allow user to interact with MainPage when Game Screen screen is open
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.showAndWait();
        }
        catch (IOException e){
            System.out.println("can not load initBall.java");
            e.printStackTrace();
        }
    }

    @FXML
    public void handleNewGame()  {
        buttonPlay("buttonClick.mp3");
        NewGame.startGame(null);
    }

    @FXML
    public void handleResumeGame() throws IOException{
        buttonPlay("buttonClick.mp3");
        SavedGames savedGames=new SavedGames();
        Save save=new Save("saveinfo.txt");
        save.readData();
        Stage stage=new Stage();
        savedGames.start(stage);
    }

}
