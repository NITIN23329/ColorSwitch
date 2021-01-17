package All_Obstacle;

import Screen_move.Ball;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Line_Obstacle  extends Obstacle implements PausePlay{
    private final Line line1;
    private final Line line2;
    private final Line line3;
    private final Line line4;
    private TranslateTransition translateTransition1,translateTransition2,translateTransition3,translateTransition4;
    private double initialPosition1,initialPosition2,initialPosition3,initialPosition4;
    private double startingLevelTime;

    public Line_Obstacle(Line line1, Line line2, Line line3, Line line4, double startingLevelTime) {
        super(false);
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.line4 = line4;
        this.startingLevelTime=startingLevelTime;
        translateTransition1=new TranslateTransition();
        translateTransition2=new TranslateTransition();
        translateTransition3=new TranslateTransition();
        translateTransition4=new TranslateTransition();
        this.initialPosition1=line1.getLayoutY();
        this.initialPosition2=line2.getLayoutY();
        this.initialPosition3=line3.getLayoutY();
        this.initialPosition4=line4.getLayoutY();
        move_lines(getLine1(),translateTransition1);
        move_lines(getLine2(),translateTransition2);
        move_lines(getLine3(),translateTransition3);
        move_lines(getLine4(),translateTransition4);
    }

    private void move_lines(Line line,TranslateTransition transition) {
        transition.setFromX(line.getStartX()-130);
        transition.setToX(line.getEndX()+130);
        transition.setDuration(Duration.seconds(startingLevelTime));
        transition.setCycleCount(Animation.INDEFINITE);
        transition.setAutoReverse(true);
        transition.setNode(line);
        transition.play();
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

    public double getStartingLevelTime() {
        return startingLevelTime;
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

    public void setStartingLevelTime(double startingLevelTime) {
        this.startingLevelTime = startingLevelTime;
    }

    public boolean detectCollision(Ball ball)
    {
        Shape shape1=Shape.intersect(line1,ball.getCircle());
        Shape shape2=Shape.intersect(line2,ball.getCircle());
        Shape shape3=Shape.intersect(line3,ball.getCircle());
        Shape shape4=Shape.intersect(line4,ball.getCircle());

        if(shape1.getLayoutBounds().getWidth()!=-1)
        {
            if(line1.getStroke()!=ball.getCircle().getFill())
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
            if(line2.getStroke()!=ball.getCircle().getFill())
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
            if(line3.getStroke()!=ball.getCircle().getFill())
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
            if(line4.getStroke()!=ball.getCircle().getFill())
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

    public Line getLine1() {
        return line1;
    }

    public Line getLine2() {
        return line2;
    }

    public Line getLine3() {
        return line3;
    }

    public Line getLine4() {
        return line4;
    }


    @Override
    public void move() {
        Translate(getLine1());
        Translate(getLine2());
        Translate(getLine3());
        Translate(getLine4());
    }

    @Override
    public void initial_position()
    {
        getLine1().setLayoutY(initialPosition1);
        getLine2().setLayoutY(initialPosition2);
        getLine3().setLayoutY(initialPosition3);
        getLine4().setLayoutY(initialPosition4);

    }

    @Override
    public double check_position_on_screen() {
        return Math.min(Math.min(Math.min(getLine1().getLayoutY(),getLine2().getLayoutY()),getLine3().getLayoutY()),getLine4().getLayoutY());
    }

    private void Translate(Line line)
    {
        line.setLayoutY(line.getLayoutY()+30);
    }

    @Override
    public void changeLevel() {
        if(startingLevelTime>1)
        {
            translateTransition1.stop();
            translateTransition2.stop();
            translateTransition3.stop();
            translateTransition4.stop();

            startingLevelTime-=0.5;
            translateTransition1=new TranslateTransition();
            translateTransition2=new TranslateTransition();
            translateTransition3=new TranslateTransition();
            translateTransition4=new TranslateTransition();
            move_lines(getLine1(),translateTransition1);
            move_lines(getLine2(),translateTransition2);
            move_lines(getLine3(),translateTransition3);
            move_lines(getLine4(),translateTransition4);
        }
    }

    @Override
    public void ruk() {
        translateTransition1.pause();
        translateTransition2.pause();
        translateTransition3.pause();
        translateTransition4.pause();
    }

    @Override
    public void chalu() {
        translateTransition1.play();
        translateTransition2.play();
        translateTransition3.play();
        translateTransition4.play();
    }
}

