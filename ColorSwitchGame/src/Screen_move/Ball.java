package Screen_move;

import All_Obstacle.*;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import java.util.ArrayList;

public class Ball  {
    private final javafx.scene.paint.Paint color;
    private final Circle circle;
    private Label scoreLabel;
    private int LevelCount;
    private Scene scene;
    private Timeline timeline;

    public Ball(Paint color, double radius, Coordinate position) {
        this.color = color;
        circle=new Circle(radius,color);
        circle.relocate(position.getX(),position.getY());
        this.timeline =new Timeline();
        this.LevelCount=1;
    }

    public int getLevelCount() {
        return LevelCount;
    }

    public boolean[] getBoolean_obstacle_arr() {
        return boolean_obstacle_arr;
    }

    public Paint getColor() {
        return color;
    }
    public Circle getCircle() {
        return circle;
    }

    public void setLevelCount(int levelCount) {
        LevelCount = levelCount;
    }

    public void setBoolean_obstacle_arr(boolean[] boolean_obstacle_arr) {
        this.boolean_obstacle_arr = boolean_obstacle_arr;
    }

    public void setScoreLabel(Label scoreLabel) {
        this.scoreLabel = scoreLabel;
    }

    public Obstacle[] getObstacle_on_screen() {
        return obstacle_on_screen;
    }

    public void setObstacle_on_screen(Obstacle[] obstacle_on_screen) {
        this.obstacle_on_screen = obstacle_on_screen;
    }

    public Scene getScene() {
        return scene;
    }

    public void setColor(Paint color)
    {
        getCircle().setFill(color);
    }

    private boolean[] boolean_obstacle_arr;
    private  Obstacle[] obstacle_on_screen=new Obstacle[2];


    public void  move(Ball ball, Scene scene, Obstacle[] obstacles){
        this.scene=scene;
        boolean_obstacle_arr =new boolean[obstacles.length];
        addObstacle(obstacles);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                changeLevel(obstacles);
                if(event.getCode().equals(KeyCode.SPACE)){
                    if(timeline !=null) timeline.stop();
                    if(ball.getCircle().getLayoutY()<=350)
                    {
                        moveObstacle(obstacle_on_screen[0],scene,obstacles);
                        if(obstacle_on_screen[1]!=null)
                        {
                            moveObstacle(obstacle_on_screen[0],scene,obstacles);

                            if(obstacle_on_screen[0].check_position_on_screen()>=200)
                            {
                                if(ball.getCircle().getLayoutY()<=350)
                                {
                                    moveObstacle(obstacle_on_screen[1],scene,obstacles);
                                }
                            }
                        }
                        timeline = new Timeline(
                                new KeyFrame(Duration.millis(700+(700-ball.getCircle().getLayoutY())*8), new KeyValue(ball.getCircle().layoutYProperty(), 660)));
                    }
                    else
                    {
                        timeline = new Timeline( new KeyFrame(Duration.millis(200), new KeyValue(ball.getCircle().layoutYProperty(), ball.getCircle().getLayoutY()-50)),
                                new KeyFrame(Duration.millis(700+(700-ball.getCircle().getLayoutY())*8), new KeyValue(ball.getCircle().layoutYProperty(), 660)));
                    }
                    timeline.play();
                }
            }
        });
    }
    public void pause()
    {
        timeline.pause();
    }
    public void resume()
    {
        timeline.play();

    }


    private void moveObstacle(Obstacle obstacle,Scene scene,Obstacle[] obstacles)
    {
        double scene_size=scene.getHeight();
        if(obstacle.getClass()== Plus_Obstacle.class)
        {
            move_Obstacle_helper(obstacle,scene_size,obstacles);
        }
        if(obstacle.getClass()== Square_Obstacle.class)
        {
            move_Obstacle_helper(obstacle,scene_size,obstacles);
        }
        if(obstacle.getClass()== Circular_Obstacle.class)
        {
            move_Obstacle_helper(obstacle,scene_size,obstacles);
        }
        if(obstacle.getClass()== Line_Obstacle.class)
        {
            move_Obstacle_helper(obstacle,scene_size,obstacles);
        }
        if(obstacle.getClass()==Double_Plus_Obstacle.class)
        {
            move_Obstacle_helper(obstacle,scene_size,obstacles);
        }
        if(obstacle.getClass()==Double_Circle_Obstacle.class)
        {
            move_Obstacle_helper(obstacle,scene_size,obstacles);
        }
        if(obstacle.getClass()== Square_Circle_Obstacle.class)
        {
            move_Obstacle_helper(obstacle,scene_size,obstacles);
        }
        if(obstacle.getClass()==Color_Switch.class)
        {
            move_Obstacle_helper(obstacle,scene_size+200,obstacles);
        }
        if(obstacle.getClass()==Star.class)
        {
            move_Obstacle_helper(obstacle,scene_size,obstacles);
        }
    }

    private void move_Obstacle_helper(Obstacle obstacle,double scene_size,Obstacle[] obstacle_arr)
    {
        double position=obstacle.check_position_on_screen();
        if(position>=scene_size)
        {
            outOfScene(obstacle_arr,obstacle);
        }
        else
        {
            if(obstacle.isBall_Collision())
            {
                outOfScene(obstacle_arr,obstacle);
            }
            else
            {
                obstacle.move();
            }
        }
    }

    private void outOfScene(Obstacle[] obstacle_arr,Obstacle obstacle)
    {
        int index=random_index(obstacle_arr);

        for (int i = 0; i < boolean_obstacle_arr.length; i++) {
            if(obstacle.getClass()==obstacle_arr[i].getClass())
            {
                boolean_obstacle_arr[i]=false;
            }
        }
        for (int i = 0; i <obstacle_arr.length; i++) {
            if(obstacle_arr[i].getClass()==obstacle.getClass())
            {
                obstacle_arr[i].initial_position();
            }
        }

        int position1=0; // position from where obstacle has gone out of screen
        int position2=0;
        for (int i = 0; i <obstacle_on_screen.length; i++) { //checking which obstacle has gone out of scene
            if(obstacle_on_screen[i].getClass()==obstacle.getClass())
            {
                position1=i;
            }
            else
            {
                position2=i;
            }
        }
        obstacle_on_screen[position1]=obstacle_on_screen[position2];
        obstacle_on_screen[position2]=obstacle_arr[index];
        boolean_obstacle_arr[index]=true;
    }

    private int random_index(Obstacle[] obstacles)
    {
        ArrayList<Integer> arrayList=new ArrayList<>();
        for (int i =0; i<boolean_obstacle_arr.length; i++) {
            if(!boolean_obstacle_arr[i]) // false at boolean_obstacle_arr position means this type of obstacle is not on the screen
            {
                arrayList.add(i);
            }
        }
        int index=(int)(Math.random() * (arrayList.size()));
        return arrayList.get(index);
    }

    private void addObstacle(Obstacle[] obstacles)

    {
        int index=random_index(obstacles);
        obstacle_on_screen[0]=obstacles[index];
        boolean_obstacle_arr[index]=true;

        int index1=random_index(obstacles);
        obstacle_on_screen[1]=obstacles[index1];
        boolean_obstacle_arr[index1]=true;
    }

    private void changeLevel(Obstacle[] obstacles)
    {
        if(Integer.parseInt(scoreLabel.getText())==5 && LevelCount==1)
        {
            changeLevel_helper(obstacles);
            LevelCount++;
        }
        else if(Integer.parseInt(scoreLabel.getText())==10 && LevelCount==2)
        {
            changeLevel_helper(obstacles);
            LevelCount++;
        }
        else if(Integer.parseInt(scoreLabel.getText())==25 && LevelCount==3)
        {
            changeLevel_helper(obstacles);
            LevelCount++;
        }
        else if(Integer.parseInt(scoreLabel.getText())==45 && LevelCount==4)
        {
            changeLevel_helper(obstacles);
            LevelCount++;
        }
    }
    private void changeLevel_helper(Obstacle[] obstacles)
    {
        for (Obstacle obstacle:obstacles) {
            obstacle.changeLevel();
        }
    }
}
