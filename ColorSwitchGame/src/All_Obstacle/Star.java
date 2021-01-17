package All_Obstacle;

import Screen_move.Ball;
import javafx.fxml.FXML;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class Star extends Obstacle{
    @FXML
    private final Polygon upper_part, lower_part;
    private double initial_upper,initial_lower;

    public Star(Polygon upper, Polygon lower,double initial_upper,double initial_lower) {
        super(false);
        this.initial_lower=initial_lower;
        this.initial_upper=initial_upper;
        this.upper_part = upper;
        this.lower_part = lower;
    }
    public boolean detectCollision(Ball ball){
        Shape shape = Shape.intersect(lower_part,ball.getCircle());
        if(shape.getLayoutBounds().getWidth()!=-1){
            setBall_Collision(true);
            getUpper_part().setOpacity(0.0);
            getLower_part().setOpacity(0.0);
            return true;
        }
        return false;
    }

    public double getInitial_upper() {
        return initial_upper;
    }

    public void setInitial_upper(double initial_upper) {
        this.initial_upper = initial_upper;
    }

    public double getInitial_lower() {
        return initial_lower;
    }

    public void setInitial_lower(double initial_lower) {
        this.initial_lower = initial_lower;
    }

    public Polygon getUpper_part() {
        return upper_part;
    }

    public Polygon getLower_part() {
        return lower_part;
    }

    @Override
    public void move() {
        upper_part.setLayoutY(upper_part.getLayoutY()+30);
        lower_part.setLayoutY(lower_part.getLayoutY()+30);
    }

    @Override
    public void changeLevel() {

    }

    @Override
    public void initial_position() {
        getUpper_part().setLayoutY(initial_upper);
        getLower_part().setLayoutY(initial_lower);
        getLower_part().setOpacity(1);
        getUpper_part().setOpacity(1);
        setScore_Updated(false);
        setBall_Collision(false);
    }

    @Override
    public double check_position_on_screen() {
        return Math.min(getLower_part().getLayoutY(),getUpper_part().getLayoutY());
    }

    @Override
    public void ruk() {
        //nothing
    }

    @Override
    public void chalu() {
        //nothing
    }

}

