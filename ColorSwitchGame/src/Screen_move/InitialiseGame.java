package Screen_move;

import All_Obstacle.*;
import SaveScreen.Save;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import mainWindow.MainWindowMain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InitialiseGame extends AnimationTimer {

    public static boolean scoreUpdated;
    public static boolean collided;
    private final int startingTime;
    private final Ball ball;
    private  Label scorelabel;

    private Color_Switch color_switcher;
    private Star star1;
    private Star star2;
    private Star star3;
    private Circular_Obstacle circular_obstacle;
    private Plus_Obstacle plus_obstacle;
    private Line_Obstacle line_obstacle;
    private Square_Obstacle square_obstacle;
    private Double_Circle_Obstacle double_circle_obstacle;
    private Double_Plus_Obstacle double_plus_obstacle;
    private Square_Circle_Obstacle square_circle_obstacle;
    private final ImageView pauseImage,saveImage;
    private final Obstacle[] obstacles;

    private static boolean isCollisionWindowOpen;

    public static void setChangeIsCollisionWindowOpen(){
        isCollisionWindowOpen = false;
    }

    public InitialiseGame(AnchorPane anchorPane,Obstacle[] obstacles,Ball ball,boolean savedGame) throws FileNotFoundException {
        isCollisionWindowOpen = false;
        this.obstacles=obstacles;
        this.ball=ball;
        this.startingTime=5;
        scoreUpdated=false;
        collided=false;
        this.pauseImage=initialize_pause();
        this.scorelabel=initialize_ScoreLabel();
        this.saveImage=initialize_save();

        if(!savedGame)
        {
            this.plus_obstacle=initialize_plus();
            this.line_obstacle=initialize_line();
            this.square_obstacle=initialize_square();
            this.double_plus_obstacle = initialize_double_Plus();
            this.double_circle_obstacle=initialize_double_circle();
            this.square_circle_obstacle=initialize_square_circle();
            this.circular_obstacle=initialize_circle();
            this.color_switcher=initialize_ColorSwitch();
            this.star1=initialize_star(21);
            this.star2=initialize_star(11);
            this.star3=initialize_star(15);

            obstacles[0]=circular_obstacle;
            obstacles[1]=square_obstacle;
            obstacles[2]=star1;
            obstacles[3]=line_obstacle;
            obstacles[4]=double_plus_obstacle;
            obstacles[5]=double_circle_obstacle;
            obstacles[6]=square_circle_obstacle;
            obstacles[7]=color_switcher;
            obstacles[8]=star2;
            obstacles[9]=plus_obstacle;
            obstacles[10]=star3;
        }
        else
        {
            scorelabel.setText(Integer.toString(MainWindowMain.score));
            boolean[] boolean_obstacle_arr =ball.getBoolean_obstacle_arr();
            Obstacle[] ballObstacleOnScreen =ball.getObstacle_on_screen();
            addInArr(0,ballObstacleOnScreen,obstacles,boolean_obstacle_arr,ballObstacleOnScreen[0]);
            addInArr(1,ballObstacleOnScreen,obstacles,boolean_obstacle_arr,ballObstacleOnScreen[1]);

        }
        ball.setScoreLabel(scorelabel);
        AddtoAnchorPane(anchorPane);

    }

    private void addInArr(int counter,Obstacle[] ballObstacleOnScreen,Obstacle[] obstacles,boolean[] boolean_obstacle_arr,Obstacle obstacle)
    {
        if(boolean_obstacle_arr[0] && obstacle.getClass()==Circular_Obstacle.class)
        {
            this.circular_obstacle=(Circular_Obstacle) ballObstacleOnScreen[counter];
            obstacles[0]=circular_obstacle;
        }
        else
        {
            this.circular_obstacle=initialize_circle();
            obstacles[0]=circular_obstacle;
        }
        if(boolean_obstacle_arr[1] && obstacle.getClass()==Square_Obstacle.class)
        {
            this.square_obstacle= (Square_Obstacle) ballObstacleOnScreen[counter];
            obstacles[1]=square_obstacle;
        }
        else
        {
            this.square_obstacle=initialize_square();
            obstacles[1]=square_obstacle;
        }
        if(boolean_obstacle_arr[2] && obstacle.getClass()==Star.class)
        {
            this.star1=(Star)ballObstacleOnScreen[counter];
            obstacles[2]=star1;
        }
        else
        {
            this.star1=initialize_star(21);
            obstacles[2]=star1;
        }
        if(boolean_obstacle_arr[3] && obstacle.getClass()==Line_Obstacle.class)
        {
            this.line_obstacle=(Line_Obstacle)ballObstacleOnScreen[counter];
            obstacles[3]=line_obstacle;
        }
        else
        {
            this.line_obstacle=initialize_line();
            obstacles[3]=line_obstacle;
        }
        if(boolean_obstacle_arr[4] && obstacle.getClass()==Plus_Obstacle.class)
        {
            this.double_plus_obstacle=(Double_Plus_Obstacle)ballObstacleOnScreen[counter];
            obstacles[4]=double_plus_obstacle;
        }
        else
        {
            this.double_plus_obstacle=initialize_double_Plus();
            obstacles[4]=double_plus_obstacle;
        }
        if(boolean_obstacle_arr[5] && obstacle.getClass()==Double_Circle_Obstacle.class)
        {
            this.double_circle_obstacle=(Double_Circle_Obstacle)ballObstacleOnScreen[counter];
            obstacles[5]=double_circle_obstacle;
        }
        else
        {
            this.double_circle_obstacle=initialize_double_circle();
            obstacles[5]=double_circle_obstacle;
        }
        if(boolean_obstacle_arr[6] && obstacle.getClass()==Square_Circle_Obstacle.class)
        {
            this.square_circle_obstacle=(Square_Circle_Obstacle)ballObstacleOnScreen[counter];
            obstacles[6]=square_circle_obstacle;
        }
        else
        {
            this.square_circle_obstacle=initialize_square_circle();
            obstacles[6]=square_circle_obstacle;
        }
        if(boolean_obstacle_arr[7] && obstacle.getClass()==Color_Switch.class)
        {
            this.color_switcher=(Color_Switch)ballObstacleOnScreen[counter];
            obstacles[7]=color_switcher;
        }
        else
        {
            this.color_switcher=initialize_ColorSwitch();
            obstacles[7]=color_switcher;
        }
        if(boolean_obstacle_arr[8] && obstacle.getClass()==Star.class)
        {
            this.star2=(Star)ballObstacleOnScreen[counter];
            obstacles[8]=star2;
        }
        else
        {
            this.star2=initialize_star(11);
            obstacles[8]=star2;
        }
        if(boolean_obstacle_arr[9] && obstacle.getClass()==Plus_Obstacle.class)
        {
            this.plus_obstacle= (Plus_Obstacle)ballObstacleOnScreen[counter];
            obstacles[9]=plus_obstacle;
        }
        else
        {
            this.plus_obstacle=initialize_plus();
            obstacles[9]=plus_obstacle;
        }
        if(boolean_obstacle_arr[10] && obstacle.getClass()==Star.class)
        {
            this.star3=(Star)ballObstacleOnScreen[counter];
            obstacles[10]=star3;
        }
        else
        {
            this.star3=initialize_star(15);
            obstacles[10]=star3;
        }
    }

    public Label getScorelabel() {
        return scorelabel;
    }

    public void setScorelabel(Label scorelabel) {
        this.scorelabel = scorelabel;
    }

    private void AddtoAnchorPane(AnchorPane anchorPane)
    {
        anchorPane.getChildren().addAll(
                star3.getLower_part(),star3.getUpper_part(),pauseImage,saveImage,
                circular_obstacle.getArc1(),circular_obstacle.getArc2(),circular_obstacle.getArc3(),circular_obstacle.getArc4(),
                star1.getUpper_part(),star1.getLower_part(),star2.getUpper_part(),star2.getLower_part(),
                scorelabel,
                color_switcher.getColor_switch_circle().getArc1(),color_switcher.getColor_switch_circle().getArc2(),color_switcher.getColor_switch_circle().getArc3(),color_switcher.getColor_switch_circle().getArc4(),
                plus_obstacle.getPlus1(),plus_obstacle.getPlus2(),plus_obstacle.getPlus3(),plus_obstacle.getPlus4(),
                line_obstacle.getLine1(),line_obstacle.getLine2(),line_obstacle.getLine3(),line_obstacle.getLine4(),
                square_obstacle.getSquare1(),square_obstacle.getSquare2(),square_obstacle.getSquare3(),square_obstacle.getSquare4(),
                double_plus_obstacle.getPlus_obstacle_left().getPlus1(),double_plus_obstacle.getPlus_obstacle_left().getPlus2(),double_plus_obstacle.getPlus_obstacle_left().getPlus3(),double_plus_obstacle.getPlus_obstacle_left().getPlus4(),
                double_plus_obstacle.getPlus_obstacle_right().getPlus1(),double_plus_obstacle.getPlus_obstacle_right().getPlus2(),double_plus_obstacle.getPlus_obstacle_right().getPlus3(),double_plus_obstacle.getPlus_obstacle_right().getPlus4(),
                double_circle_obstacle.getLeft_circle().getArc1(),double_circle_obstacle.getLeft_circle().getArc2(),double_circle_obstacle.getLeft_circle().getArc3(),double_circle_obstacle.getLeft_circle().getArc4(),
                double_circle_obstacle.getRight_circle().getArc1(),double_circle_obstacle.getRight_circle().getArc2(),double_circle_obstacle.getRight_circle().getArc3(),double_circle_obstacle.getRight_circle().getArc4(),
                square_circle_obstacle.getCircular_obstacle().getArc1(),square_circle_obstacle.getCircular_obstacle().getArc2(),square_circle_obstacle.getCircular_obstacle().getArc3(),square_circle_obstacle.getCircular_obstacle().getArc4(),
                square_circle_obstacle.getSquare_obstacle().getSquare1(),square_circle_obstacle.getSquare_obstacle().getSquare3(),square_circle_obstacle.getSquare_obstacle().getSquare2(),square_circle_obstacle.getSquare_obstacle().getSquare4()
        );
    }

    private Star initialize_star(double layoutY)
    {
        Polygon upper=new Polygon();
        upper.setFill(Color.WHITE);
        upper.setLayoutX(370);
        upper.setLayoutY(layoutY+10);
        upper.setStroke(Color.WHITE);
        upper.setRotate(180.0);
        upper.setStrokeLineCap(StrokeLineCap.ROUND);
        upper.getPoints().addAll(-20.0, -44.0, 13.0, -44.0, -3.0, -77.0);

        Polygon lower=new Polygon();
        lower.setFill(Color.WHITE);
        lower.setLayoutX(370);
        lower.setLayoutY(layoutY);
        lower.setStroke(Color.WHITE);
        lower.setStrokeLineCap(StrokeLineCap.ROUND);
        lower.getPoints().addAll(-20.0, -44.0, 13.0, -44.0, -3.0, -77.0);

        return new Star(upper,lower,layoutY+10,layoutY);
    }

    private ImageView initialize_save()
    {
        ImageView imageView=new ImageView();
        imageView.setLayoutY(600);
        imageView.setLayoutX(650);
        imageView.setFitHeight(79);
        imageView.setFitWidth(95);
        imageView.setPreserveRatio(true);
        imageView.setPickOnBounds(true);
        Image image=new Image(getClass().getResourceAsStream("/Images/save.png"));
        imageView.setImage(image);
        return imageView;
    }
    private ImageView initialize_pause() throws FileNotFoundException {
        ImageView imageView=new ImageView();
        imageView.setLayoutY(10);
        imageView.setLayoutX(650);
        imageView.setFitHeight(79);
        imageView.setFitWidth(95);
        imageView.setPreserveRatio(true);
        imageView.setPickOnBounds(true);
        Image image=new Image(getClass().getResourceAsStream("/Images/pause.jpg"));
        imageView.setImage(image);
        return imageView;
    }

    private Label initialize_ScoreLabel()
    {
        Label scoreLabel=new Label();
        scoreLabel.setLayoutY(10);
        scoreLabel.setLayoutX(25);
        scoreLabel.maxWidth(120);
        scoreLabel.prefHeight(40);
        scoreLabel.setText("0");
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setFont(new Font(35));
        scoreLabel.setStyle("-fx-font-family: 'Comic Sans MS'; -fx-font-weight: bold italic;");

        return scoreLabel;
    }

    private Color_Switch initialize_ColorSwitch()
    {
        return new Color_Switch(0,0,365,-50,90,20,20,45,true,true,startingTime);
    }

    private Circular_Obstacle initialize_circle()
    {
        return new Circular_Obstacle(15,-80,350,-100,100,100,100,0,true,false,startingTime);
    }

    private Double_Circle_Obstacle initialize_double_circle()
    {
        Circular_Obstacle circular_obstacle_right=new Circular_Obstacle(15,-80,460,-100,100,100,100,0,false,false,startingTime);
        Circular_Obstacle circular_obstacle_left=new Circular_Obstacle(15,-80,230,-100,100,100,100,0,true,false,startingTime);
        return new Double_Circle_Obstacle(circular_obstacle_right,circular_obstacle_left);
    }

    private Plus_Obstacle initialize_plus()
    {
        Line line1=new Line();
        line1.setStartX(30.0);
        line1.setEndX(-70.0);
        line1.setLayoutX(320.0);
        line1.setLayoutY(-170.0);
        line1.setStroke(Color.AQUA);
        line1.setStrokeWidth(20);
        line1.setRotate(90);

        Line line2=new Line();
        line2.setStartX(-70.0);
        line2.setEndX(30.0);
        line2.setLayoutX(370.0);
        line2.setLayoutY(-120.0);
        line2.setStroke(Color.YELLOW);
        line2.setStrokeWidth(20);

        Line line3=new Line();
        line3.setStartX(-70.0);
        line3.setEndX(30.0);
        line3.setLayoutX(320.0);
        line3.setLayoutY(-70.0);
        line3.setRotate(90);
        line3.setStroke(Color.MAGENTA);
        line3.setStrokeWidth(20);

        Line line4=new Line();
        line4.setStartX(30.0);
        line4.setEndX(-70);
        line4.setLayoutX(270.0);
        line4.setLayoutY(-120.0);
        line4.setStroke(Color.RED);
        line4.setStrokeWidth(20);



        return new Plus_Obstacle(line1,line2,line3,line4,true,startingTime);
    }

    private Line_Obstacle initialize_line()
    {
        Line line1=new Line();
        line1.setEndX(120);
        line1.setLayoutY(-40);
        line1.setLayoutX(110.0);
        line1.setStroke(Color.RED);
        line1.setStrokeWidth(20);

        Line line2=new Line();
        line2.setEndX(120);
        line2.setLayoutY(-40);
        line2.setLayoutX(230.0);
        line2.setStroke(Color.AQUA);
        line2.setStrokeWidth(20);

        Line line3=new Line();
        line3.setEndX(120);
        line3.setLayoutY(-40);
        line3.setLayoutX(350.0);
        line3.setStroke(Color.MAGENTA);
        line3.setStrokeWidth(20);

        Line line4=new Line();
        line4.setEndX(120);
        line4.setLayoutY(-40);
        line4.setLayoutX(470.0);
        line4.setStroke(Color.YELLOW);
        line4.setStrokeWidth(20);

        return new Line_Obstacle(line1,line2,line3,line4,startingTime);
    }

    private Square_Obstacle initialize_square()
    {
        Line line1=new Line();
        line1.setEndX(140.0);
        line1.setStartX(0.0);
        line1.setLayoutY(-180.0);
        line1.setLayoutX(300.0);
        line1.setStroke(Color.YELLOW);
        line1.setStrokeWidth(20);

        Line line2=new Line();
        line2.setStartX(140.0);
        line2.setEndX(0.0);
        line2.setLayoutX(370.0);
        line2.setLayoutY(-110.0);
        line2.setStroke(Color.AQUA);
        line2.setStrokeWidth(20);
        line2.setRotate(90);

        Line line3=new Line();
        line3.setEndX(0);
        line3.setStartX(140.0);
        line3.setLayoutY(-40.0);
        line3.setLayoutX(300.0);
        line3.setStroke(Color.RED);
        line3.setStrokeWidth(20);

        Line line4=new Line();
        line4.setEndX(0.0);
        line4.setStartX(140.0);
        line4.setLayoutX(230.0);
        line4.setLayoutY(-110.0);
        line4.setStroke(Color.MAGENTA);
        line4.setStrokeWidth(20);
        line4.setRotate(90);

        return new Square_Obstacle(line1,line2,line3,line4,startingTime);
    }

    private Double_Plus_Obstacle initialize_double_Plus()
    {
        Line line1=new Line();
        line1.setStartX(30.0);
        line1.setEndX(-70.0);
        line1.setLayoutX(270.0);
        line1.setLayoutY(-180.0);
        line1.setStroke(Color.AQUA);
        line1.setStrokeWidth(20);
        line1.setRotate(90);

        Line line2=new Line();
        line2.setStartX(-70.0);
        line2.setEndX(30.0);
        line2.setLayoutX(320.0);
        line2.setLayoutY(-130.0);
        line2.setStroke(Color.YELLOW);
        line2.setStrokeWidth(20);

        Line line3=new Line();
        line3.setStartX(-70.0);
        line3.setEndX(30.0);
        line3.setLayoutX(270.0);
        line3.setLayoutY(-80.0);
        line3.setRotate(90);
        line3.setStroke(Color.MAGENTA);
        line3.setStrokeWidth(20);

        Line line4=new Line();
        line4.setStartX(30.0);
        line4.setEndX(-70);
        line4.setLayoutX(220.0);
        line4.setLayoutY(-130.0);
        line4.setStroke(Color.RED);
        line4.setStrokeWidth(20);

        Plus_Obstacle plus_obstacle_left=new Plus_Obstacle(line1,line2,line3,line4,true,startingTime);

        Line Line1=new Line();
        Line1.setStartX(30.0);
        Line1.setEndX(-70.0);
        Line1.setLayoutX(510.0);
        Line1.setLayoutY(-180.0);
        Line1.setStroke(Color.AQUA);
        Line1.setStrokeWidth(20);
        Line1.setRotate(90);

        Line Line2=new Line();
        Line2.setStartX(-70.0);
        Line2.setEndX(30.0);
        Line2.setLayoutX(560.0);
        Line2.setLayoutY(-130.0);
        Line2.setStroke(Color.RED);
        Line2.setStrokeWidth(20);

        Line Line3=new Line();
        Line3.setStartX(-70.0);
        Line3.setEndX(30.0);
        Line3.setLayoutX(510.0);
        Line3.setLayoutY(-80.0);
        Line3.setRotate(90);
        Line3.setStroke(Color.MAGENTA);
        Line3.setStrokeWidth(20);

        Line Line4=new Line();
        Line4.setStartX(30.0);
        Line4.setEndX(-70);
        Line4.setLayoutX(460.0);
        Line4.setLayoutY(-130.0);
        Line4.setStroke(Color.YELLOW);
        Line4.setStrokeWidth(20);

        Plus_Obstacle plus_obstacle_right=new Plus_Obstacle(Line1,Line2,Line3,Line4,false,startingTime);


        return new Double_Plus_Obstacle(plus_obstacle_left,plus_obstacle_right);

    }

    private Square_Circle_Obstacle initialize_square_circle()
    {
        Circular_Obstacle circular_obstacle=new Circular_Obstacle(20,120,347,-259,90,120,120,45,true,false,startingTime);
        Line line1=new Line();
        line1.setStartX(-60);
        line1.setEndX(60.0);
        line1.setLayoutX(368);
        line1.setLayoutY(-199);
        line1.setStroke(Color.RED);
        line1.setStrokeWidth(20);

        Line line2=new Line();
        line2.setLayoutX(428);
        line2.setLayoutY(-139);
        line2.setStartX(-60);
        line2.setEndX(60);
        line2.setRotate(90);
        line2.setStroke(Color.AQUA);
        line2.setStrokeWidth(20);

        Line line3=new Line();
        line3.setLayoutX(368);
        line3.setLayoutY(-80);
        line3.setStartX(-60);
        line3.setEndX(60);
        line3.setStroke(Color.MAGENTA);
        line3.setStrokeWidth(20);

        Line line4=new Line();
        line4.setLayoutX(308);
        line4.setLayoutY(-139);
        line4.setStartX(-60);
        line4.setEndX(60);
        line4.setRotate(90);
        line4.setStroke(Color.YELLOW);
        line4.setStrokeWidth(20);

        Square_Obstacle square_obstacle=new Square_Obstacle(line1,line2,line3,line4,startingTime);

        return new Square_Circle_Obstacle(circular_obstacle,square_obstacle);
    }

    @Override
    public void handle(long l) {
        color_switcher.detectCollision(ball);
        boolean collision1 = star1.detectCollision(ball);
        boolean collision2 = star2.detectCollision(ball);
        boolean collision3=star3.detectCollision(ball);
        if(collision1 || collision2 || collision3)
            playSound("starCollect.mp3",100);

        if(collision1)
        {
            if(!star1.isScore_Updated())
            {
                scorelabel.setText(Integer.toString(Integer.parseInt(scorelabel.getText())+1));
                MainWindowMain.total_Star++;
                star1.setScore_Updated(true);
            }
        }
        else if(collision2)
        {
            if(!star2.isScore_Updated())
            {
                scorelabel.setText(Integer.toString(Integer.parseInt(scorelabel.getText())+1));
                MainWindowMain.total_Star++;
                star2.setScore_Updated(true);
            }
        }
        else if(collision3)
        {
            if(!star3.isScore_Updated())
            {
                scorelabel.setText(Integer.toString(Integer.parseInt(scorelabel.getText())+1));
                MainWindowMain.total_Star++;
                star3.setScore_Updated(true);
            }
        }
        else if(checkCollision() && !isCollisionWindowOpen &&!collided)
        {
            playSound("collision.mp3",100);
            isCollisionWindowOpen=true;
            Update_Score();
            try {
                collided=true;
                ball.pause();
                for (Obstacle obstacle:obstacles) {
                    obstacle.ruk();
                }

                Pane root = FXMLLoader.load(getClass().getResource("/Obstacle_Collide/Obstacle_Collision.fxml"));
                Stage stage = new Stage();

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("Pause Screen");
                // this will not allow user to interact with MainPage when Game Screen screen is open
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();


                ball.resume();
                for (Obstacle obstacle:obstacles) {
                    obstacle.chalu();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }


        pauseImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                try {
                    playSound("buttonClick.mp3",1);

                    ball.pause();
                    for (Obstacle obstacle:obstacles) {
                        obstacle.ruk();
                    }

                    Pane root = FXMLLoader.load(getClass().getResource("/pauseWindow/Pause.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.setTitle("Pause Screen");
                    // this will not allow user to interact with MainPage when Game Screen screen is open
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.showAndWait();


                    ball.resume();
                    for (Obstacle obstacle:obstacles) {
                        obstacle.chalu();
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        saveImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                playSound("alert.mp3",1);
                System.out.println("Saved button clicked");
                Update_Score();
                ball.pause();
                for (Obstacle obstacle:obstacles) {
                    obstacle.ruk();
                }
                Save save=new Save("saveinfo.txt");
                try {
                    save.saveData(ball,scorelabel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ball.resume();
                for (Obstacle obstacle:obstacles) {
                    obstacle.chalu();
                }
            }
        });
    }

    private boolean checkCollision() {
        return circular_obstacle.detectCollision(ball) || plus_obstacle.detectCollision(ball) || line_obstacle.detectCollision(ball) || square_obstacle.detectCollision(ball)
                || double_plus_obstacle.detectCollision(ball) || square_circle_obstacle.detectCollision(ball) || double_circle_obstacle.detectCollision(ball);
    }

    private void Update_Score()
    {
        int score=Integer.parseInt(scorelabel.getText());
        if(MainWindowMain.highScore<score)
        {
            MainWindowMain.highScore=score;
        }
        MainWindowMain.score=score;
    }

    public void playSound(String str,int vol){
        String path = "/Users/nitin23329/Documents/intellij/codes/ColorSwitchGame/src/media/"+str;
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        if(vol!=1)mediaPlayer.setVolume(vol);
        mediaPlayer.play();

    }



}
