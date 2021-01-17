package mainWindow;

import All_Obstacle.Obstacle;
import Screen_move.InitialiseGame;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SavedGames extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("List of saved Games");
        VBox vBox=new VBox(MainWindowMain.list);
        vBox.setAlignment(Pos.CENTER);
        Scene scene=new Scene(vBox,300,270);
        primaryStage.setScene(scene);
        primaryStage.show();

        MainWindowMain.list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String[] item=MainWindowMain.list.getSelectionModel().getSelectedItem().split(" ");
                int gameNumber=Integer.parseInt(item[1])-1;
                if(gameNumber>-1)
                {
                    MainWindowMain.stopMainPagePlay();
                    NewGame.gameScreenPlay();
                    PropertyOfSavedGames savedGames=MainWindowMain.savedGames.get(gameNumber);
                    MainWindowMain.score=(int)savedGames.getScore();
                    MainWindowMain.highScore=(int)savedGames.getHighScore();
                    MainWindowMain.total_Star=(int)savedGames.getCollectedStars();
                    try {
                        NewGame newGame=new NewGame();
                        newGame.setStage(new Stage());
                        AnchorPane anchorPane= FXMLLoader.load(NewGame.class.getResource("/Screen_move/Obstacle_GameScreen.fxml"));
                        NewGame.getStage().setTitle("Game Screen");
                        NewGame.getStage().setResizable(false);


                        Obstacle[] obstacles =new Obstacle[11];
                        AnimationTimer animationTimer=new InitialiseGame(anchorPane,obstacles,savedGames.getBall(),true);
                        animationTimer.start();
                        anchorPane.getChildren().add(savedGames.getBall().getCircle());
                        Scene scene = new Scene(anchorPane, 750, 700);

                        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                            @Override
                            public void handle(KeyEvent keyEvent) {
                                savedGames.getBall().move(savedGames.getBall(),scene,obstacles);
                            }
                        });
                        NewGame.getStage().setScene(scene);
                        NewGame.getStage().initModality(Modality.APPLICATION_MODAL);
                        NewGame.getStage().initStyle(StageStyle.UNDECORATED);
                        NewGame.getStage().show();
                        primaryStage.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

}
