package All_Obstacle;
import Screen_move.Ball;
import javafx.animation.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class Circular_Obstacle extends Obstacle implements PausePlay {

    private final Arc arc1;
    private final Arc arc2;
    private final Arc arc3;
    private final Arc arc4;
    private double initialPosition1,initialPosition2,initialPosition3,initialPosition4;
    private Timeline rotateArc;
    private double startingLevelTime;
    private boolean clockwise;
    private boolean Color_switch;

    public Circular_Obstacle(double centerX, double centerY, double layoutX, double layoutY, double length, double radiusX, double radiusY, double starting_angle, boolean clockwise, boolean color_switch, double time) {
        super(false);
        this.startingLevelTime=time;
        this.clockwise=clockwise;
        this.Color_switch=color_switch;
        this.arc1=new Arc();
        this.arc2=new Arc();
        this.arc3=new Arc();
        this.arc4=new Arc();
        double strokeWidth = 20;
        if(color_switch)
        {
            initialize_ColorSwitch(getArc1(),centerX,centerY,layoutX,layoutY,length,Color.RED, radiusX,radiusY,starting_angle);
            initialize_ColorSwitch(getArc2(),centerX,centerY,layoutX,layoutY,length,Color.YELLOW,radiusX,radiusY,starting_angle+90);
            initialize_ColorSwitch(getArc3(),centerX,centerY,layoutX,layoutY,length,Color.MAGENTA,radiusX,radiusY,starting_angle+180);
            initialize_ColorSwitch(getArc4(),centerX,centerY,layoutX,layoutY,length,Color.AQUA,radiusX,radiusY,starting_angle+270);
        }
        else
        {
            initialize(getArc1(),centerX,centerY,layoutX,layoutY,length,Color.RED, strokeWidth,radiusX,radiusY,starting_angle);
            initialize(getArc2(),centerX,centerY,layoutX,layoutY,length,Color.YELLOW, strokeWidth,radiusX,radiusY,starting_angle+90);
            initialize(getArc3(),centerX,centerY,layoutX,layoutY,length,Color.MAGENTA, strokeWidth,radiusX,radiusY,starting_angle+180);
            initialize(getArc4(),centerX,centerY,layoutX,layoutY,length,Color.AQUA, strokeWidth,radiusX,radiusY,starting_angle+270);
        }

        this.initialPosition1=getArc1().getLayoutY();
        this.initialPosition2=getArc2().getLayoutY();
        this.initialPosition3=getArc3().getLayoutY();
        this.initialPosition4=getArc4().getLayoutY();

        rotateArc = new Timeline();

        rotateArcs(arc1,clockwise,time);
        rotateArcs(arc2,clockwise,time);
        rotateArcs(arc3,clockwise,time);
        rotateArcs(arc4,clockwise,time);

        rotateArc.setCycleCount(Animation.INDEFINITE);
        rotateArc.play();

    }

    public boolean isColor_switch() {
        return Color_switch;
    }


    public void setClockwise(boolean clockwise) {
        this.clockwise = clockwise;
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

    public boolean isClockwise() {
        return clockwise;
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

    public Arc getArc1() {
        return arc1;
    }

    public Arc getArc2() {
        return arc2;
    }

    public Arc getArc3() {
        return arc3;
    }

    public Arc getArc4() {
        return arc4;
    }

    private void rotateArcs(Arc arc,boolean clockwise,double time){
        if(clockwise)
            rotateArc.getKeyFrames().add(new KeyFrame(Duration.seconds(time), new KeyValue(arc.startAngleProperty(), arc.getStartAngle() - 360)));
        else
            rotateArc.getKeyFrames().add(new KeyFrame(Duration.seconds(time), new KeyValue(arc.startAngleProperty(), arc.getStartAngle() + 360)));
    }

    private void initialize(Arc arc,double centerX,double centerY,double layoutX,double layoutY,double length,Color stroke_color,double stroke_width,double RadiusX,double RadiusY,double angle)
    {
        arc.setCenterX(centerX);
        arc.setCenterY(centerY);
        arc.setLayoutX(layoutX);
        arc.setLayoutY(layoutY);
        arc.setRadiusX(RadiusX);
        arc.setRadiusY(RadiusY);
        arc.setFill(Paint.valueOf("#282828"));  // color
        arc.setLength(length);
        arc.setStartAngle(angle);
        arc.setStroke(stroke_color);
        arc.setStrokeLineCap(StrokeLineCap.BUTT);
        arc.setStrokeWidth(stroke_width);
    }

    private void initialize_ColorSwitch(Arc arc,double centerX,double centerY,double layoutX,double layoutY,double length,Color fill_color,double RadiusX,double RadiusY,double angle)
    {
        arc.setCenterX(centerX);
        arc.setCenterY(centerY);
        arc.setLayoutX(layoutX);
        arc.setLayoutY(layoutY);
        arc.setRadiusX(RadiusX);
        arc.setRadiusY(RadiusY);
        arc.setFill(fill_color);  // color
        arc.setLength(length);
        arc.setStartAngle(angle);
        arc.setStroke(Color.BLACK);
        arc.setStrokeType(StrokeType.INSIDE);
        arc.setType(ArcType.ROUND);
    }

    public boolean detectCollision(Ball ball)
    {
        Shape shape1=Shape.intersect(arc1,ball.getCircle());
        Shape shape2=Shape.intersect(arc2,ball.getCircle());
        Shape shape3=Shape.intersect(arc3,ball.getCircle());
        Shape shape4=Shape.intersect(arc4,ball.getCircle());

        if(shape1.getLayoutBounds().getWidth()!=-1)
        {
            if(arc1.getStroke()!=ball.getColor())
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
            if(arc2.getStroke()!=ball.getColor())
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
            if(arc3.getStroke()!=ball.getColor())
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
            if(arc4.getStroke()!=ball.getColor())
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
        getArc1().setLayoutY(initialPosition1);
        getArc2().setLayoutY(initialPosition2);
        getArc3().setLayoutY(initialPosition3);
        getArc4().setLayoutY(initialPosition4);
    }

    @Override
    public void move() {
        arc1.setLayoutY(arc1.getLayoutY()+30);
        arc2.setLayoutY(arc2.getLayoutY()+30);
        arc3.setLayoutY(arc3.getLayoutY()+30);
        arc4.setLayoutY(arc4.getLayoutY()+30);
    }

    @Override
    public double check_position_on_screen() {
        return Math.min(Math.min(Math.min(arc1.getLayoutY(),arc2.getLayoutY()),arc3.getLayoutY()),arc4.getLayoutY())-2*arc1.getRadiusY();
    }

    @Override
    public void changeLevel() {
        if(startingLevelTime>1)
        {
            rotateArc.stop();
            startingLevelTime-=0.5;
            rotateArc = new Timeline();
            rotateArcs(arc1,clockwise,startingLevelTime);
            rotateArcs(arc2,clockwise,startingLevelTime);
            rotateArcs(arc3,clockwise,startingLevelTime);
            rotateArcs(arc4,clockwise,startingLevelTime);
            rotateArc.setCycleCount(Animation.INDEFINITE);
            rotateArc.play();
        }

    }


    @Override
    public void ruk() {
        rotateArc.pause();
    }

    @Override
    public void chalu() {
        rotateArc.play();
    }
}