package All_Obstacle;

import Screen_move.Ball;
import javafx.animation.*;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Square_Obstacle extends Obstacle implements PausePlay {
    private final Line square1;
    private final Line square2;
    private final Line square3;
    private final Line square4;
    private Timeline rotateSquare;
    private double startingTime;
    private double initialPosition1,initialPosition2,initialPosition3,initialPosition4;
    private final double pivotX,pivotY1,pivotY2;

    public Square_Obstacle(Line line1, Line line2, Line line3, Line line4, double startingTime) {
        super(false);
        this.square1 = line1;
        this.square2 = line2;
        this.square3 = line3;
        this.square4 = line4;
        this.startingTime=startingTime;

        this.initialPosition1=line1.getLayoutY();
        this.initialPosition2=line2.getLayoutY();
        this.initialPosition3=line3.getLayoutY();
        this.initialPosition4=line4.getLayoutY();

        rotateSquare = new Timeline();

        // y pivot for squares 1 and 2
        this.pivotY1 = (square3.getLayoutY()-square1.getLayoutY())/2f;
        // x pivot for all squares
        this.pivotX = (square1.getStartX()+square1.getEndX())/2f;
        // y pivot for square 3 and 4.
        this.pivotY2 = (square4.getLayoutX()-square2.getLayoutX())/2f;

        rotateSquares(square1,pivotX,pivotY1);
        rotateSquares(square2,pivotX,pivotY1);
        rotateSquares(square3,pivotX,pivotY2);
        rotateSquares(square4,pivotX,pivotY2);


        rotateSquare.setCycleCount(Animation.INDEFINITE);

        rotateSquare.play();

    }

    public double getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(double startingTime) {
        this.startingTime = startingTime;
    }

    public double getInitialPosition1() {
        return initialPosition1;
    }

    public void setInitialPosition1(double initialPosition1) {
        this.initialPosition1 = initialPosition1;
    }

    public double getInitialPosition2() {
        return initialPosition2;
    }

    public void setInitialPosition2(double initialPosition2) {
        this.initialPosition2 = initialPosition2;
    }

    public double getInitialPosition3() {
        return initialPosition3;
    }

    public void setInitialPosition3(double initialPosition3) {
        this.initialPosition3 = initialPosition3;
    }

    public double getInitialPosition4() {
        return initialPosition4;
    }

    public void setInitialPosition4(double initialPosition4) {
        this.initialPosition4 = initialPosition4;
    }

    private void rotateSquares(Line square, double pivotX , double pivotY){
        Rotate rotate = new Rotate();
        rotate.setPivotX(pivotX);
        rotate.setPivotY(pivotY);
        square.getTransforms().add(rotate);
        rotateSquare.getKeyFrames().add(new KeyFrame(Duration.seconds(startingTime),new KeyValue(rotate.angleProperty(),360)));
    }

    public boolean detectCollision(Ball ball)
    {
        Shape shape1=Shape.intersect(square1,ball.getCircle());
        Shape shape2=Shape.intersect(square2,ball.getCircle());
        Shape shape3=Shape.intersect(square3,ball.getCircle());
        Shape shape4=Shape.intersect(square4,ball.getCircle());

        if(shape1.getLayoutBounds().getWidth()!=-1)
        {
            if(square1.getStroke()!=ball.getCircle().getFill())
            {
                setBall_Collision(true);
                return true;
            }
            else
            {
                return false;
            }
        }
        if(shape2.getLayoutBounds().getWidth()!=-1)
        {
            if(square2.getStroke()!=ball.getCircle().getFill())
            {
                setBall_Collision(true);
                return true;
            }
            else
            {
                return false;
            }
        }
        if(shape3.getLayoutBounds().getWidth()!=-1)
        {
            if(square3.getStroke()!=ball.getCircle().getFill())
            {
                setBall_Collision(true);
                return true;
            }
            else
            {
                return false;
            }
        }
        if(shape4.getLayoutBounds().getWidth()!=-1)
        {
            if(square4.getStroke()!=ball.getCircle().getFill())
            {
                setBall_Collision(true);
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }

    public Line getSquare1() {
        return square1;
    }

    public Line getSquare2() {
        return square2;
    }

    public Line getSquare3() {
        return square3;
    }

    public Line getSquare4() {
        return square4;
    }

    @Override
    public void initial_position() {
        getSquare1().setLayoutY(initialPosition1);
        getSquare2().setLayoutY(initialPosition2);
        getSquare3().setLayoutY(initialPosition3);
        getSquare4().setLayoutY(initialPosition4);
    }

    @Override
    public void move() {
        Translate(getSquare1());
        Translate(getSquare2());
        Translate(getSquare3());
        Translate(getSquare4());
    }

    @Override
    public double check_position_on_screen() {
        return Math.min(Math.min(Math.min(getSquare1().getLayoutY(), getSquare2().getLayoutY()), getSquare3().getLayoutY()), getSquare4().getLayoutY());
    }

    private void Translate(Line line)
    {
        line.setLayoutY(line.getLayoutY()+30);
    }

    @Override
    public void changeLevel() {
        if(startingTime>1)
        {
            rotateSquare.stop();
            startingTime-=0.5;
            rotateSquare=new Timeline();

            rotateSquares(square1,pivotX,pivotY1);
            rotateSquares(square2,pivotX,pivotY1);
            rotateSquares(square3,pivotX,pivotY2);
            rotateSquares(square4,pivotX,pivotY2);


            rotateSquare.setCycleCount(Animation.INDEFINITE);

            rotateSquare.play();
        }
    }


    @Override
    public void ruk() {
        rotateSquare.pause();
    }

    @Override
    public void chalu() {
        rotateSquare.play();
    }
}
