package All_Obstacle;

import Screen_move.Ball;
import javafx.animation.*;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Plus_Obstacle extends Obstacle implements PausePlay {
    private final Line plus1;
    private final Line plus2;
    private final Line plus3;
    private final Line plus4;

    private double initialPosition1,initialPosition2,initialPosition3,initialPosition4;
    private Timeline rotatePlus;
    private double startingTime;
    private final boolean clockwise;

    public Plus_Obstacle(Line plus1, Line plus2, Line plus3, Line plus4, boolean clockwise , double time) {
        super(false);
        this.plus1 = plus1;
        this.plus2 = plus2;
        this.plus3 = plus3;
        this.plus4 = plus4;
        this.startingTime=time;
        this.clockwise=clockwise;
        this.initialPosition1=plus1.getLayoutY();
        this.initialPosition2=plus2.getLayoutY();
        this.initialPosition3=plus3.getLayoutY();
        this.initialPosition4=plus4.getLayoutY();

        rotatePlus = new Timeline();



        rotatePluses(plus1,clockwise,startingTime);
        rotatePluses(plus2,clockwise,startingTime);
        rotatePluses(plus3,clockwise,startingTime);
        rotatePluses(plus4,clockwise,startingTime);

        rotatePlus.setCycleCount(Animation.INDEFINITE);
        rotatePlus.play();
    }

    public void setInitialPosition1(double initialPosition1) {
        this.initialPosition1 = initialPosition1;
    }

    public void setInitialPosition2(double initialPosition2) {
        this.initialPosition2 = initialPosition2;
    }

    public void setInitialPosition3(double initialPosition3) {
        this.initialPosition3 = initialPosition3;
    }

    public void setInitialPosition4(double initialPosition4) {
        this.initialPosition4 = initialPosition4;
    }

    public double getInitialPosition1() {
        return initialPosition1;
    }

    public double getInitialPosition2() {
        return initialPosition2;
    }

    public double getInitialPosition3() {
        return initialPosition3;
    }

    public double getInitialPosition4() {
        return initialPosition4;
    }

    public double getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(double startingTime) {
        this.startingTime = startingTime;
    }

    public boolean isClockwise() {
        return clockwise;
    }

    private void rotatePluses(Line plus, boolean clockwise, double time){
        Rotate rotate = new Rotate();
        rotate.pivotXProperty().bind(plus.startXProperty());

        plus.getTransforms().add(rotate);
        if(clockwise)
            rotatePlus.getKeyFrames().add(new KeyFrame(Duration.seconds(time),new KeyValue(rotate.angleProperty(),360)));
        else
            rotatePlus.getKeyFrames().add(new KeyFrame(Duration.seconds(time),new KeyValue(rotate.angleProperty(),-360)));
    }

    public Line getPlus1() {
        return plus1;
    }

    public Line getPlus2() {
        return plus2;
    }

    public Line getPlus3() {
        return plus3;
    }

    public Line getPlus4() {
        return plus4;
    }


    public boolean detectCollision(Ball ball)
    {
        Shape shape1=Shape.intersect(plus1,ball.getCircle());
        Shape shape2=Shape.intersect(plus2,ball.getCircle());
        Shape shape3=Shape.intersect(plus3,ball.getCircle());
        Shape shape4=Shape.intersect(plus4,ball.getCircle());

        if(shape1.getLayoutBounds().getWidth()!=-1)
        {
            if(plus1.getStroke()!=ball.getCircle().getFill())
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
            if(plus2.getStroke()!=ball.getCircle().getFill())
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
            if(plus3.getStroke()!=ball.getCircle().getFill())
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
            if(plus4.getStroke()!=ball.getCircle().getFill())
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

    @Override
    public void initial_position() {
        getPlus1().setLayoutY(initialPosition1);
        getPlus2().setLayoutY(initialPosition2);
        getPlus3().setLayoutY(initialPosition3);
        getPlus4().setLayoutY(initialPosition4);
    }

    @Override
    public void move()
    {
        Translate(getPlus1(),90);
        Translate(getPlus2(),0);
        Translate(getPlus3(),90);
        Translate(getPlus4(),0);
    }

    @Override
    public double check_position_on_screen() {
        return Math.min(Math.min(Math.min(getPlus1().getLayoutY(),getPlus2().getLayoutY()),getPlus3().getLayoutY()),getPlus4().getLayoutY());
    }

    private void Translate(Line line,double angle)
    {
        line.setLayoutY(line.getLayoutY()+30);
    }

    @Override
    public void changeLevel() {
        if(startingTime>1)
        {
            rotatePlus.stop();
            startingTime-=0.5;
            rotatePlus=new Timeline();
            rotatePluses(plus1,clockwise,startingTime);
            rotatePluses(plus2,clockwise,startingTime);
            rotatePluses(plus3,clockwise,startingTime);
            rotatePluses(plus4,clockwise,startingTime);

            rotatePlus.setCycleCount(Animation.INDEFINITE);
            rotatePlus.play();
        }
    }

    @Override
    public void ruk() {
        rotatePlus.pause();
    }

    @Override
    public void chalu() {
        rotatePlus.play();
    }
}
