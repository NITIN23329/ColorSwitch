package All_Obstacle;

import Screen_move.Ball;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Shape;

public class Color_Switch extends Obstacle implements PausePlay {
    private final Circular_Obstacle Color_switch_circle;

    public Color_Switch(double centerX, double centerY, double layoutX, double layoutY, double length, double radiusX, double radiusY, double starting_angle, boolean clockwise, boolean color_switch, double time) {
        super(false);
        this.Color_switch_circle = new Circular_Obstacle(centerX, centerY, layoutX, layoutY, length, radiusX, radiusY, starting_angle, clockwise, color_switch, time);
    }

    public Circular_Obstacle getColor_switch_circle() {
        return Color_switch_circle;
    }

    public void detectCollision(Ball ball) {
        Shape shape1 = Shape.intersect(getColor_switch_circle().getArc1(), ball.getCircle());
        Shape shape2 = Shape.intersect(getColor_switch_circle().getArc2(), ball.getCircle());
        Shape shape3 = Shape.intersect(getColor_switch_circle().getArc3(), ball.getCircle());
        Shape shape4 = Shape.intersect(getColor_switch_circle().getArc4(), ball.getCircle());

        if (shape1.getLayoutBounds().getWidth() != -1) {
            change_Color(ball,getColor_switch_circle().getArc1());
        }
        if (shape2.getLayoutBounds().getWidth() != -1) {
            change_Color(ball,getColor_switch_circle().getArc2());
        }
        if (shape3.getLayoutBounds().getWidth() != -1) {
            change_Color(ball,getColor_switch_circle().getArc3());
        }
        if (shape4.getLayoutBounds().getWidth() != -1) {
            change_Color(ball,getColor_switch_circle().getArc4());
        }

    }

    private void change_Color(Ball ball, Arc arc)
    {
        ball.setColor(arc.getFill());
    }
    @Override
    public void initial_position() {
        Color_switch_circle.initial_position();
    }

    @Override
    public void move() {
        Color_switch_circle.move();
    }

    @Override
    public double check_position_on_screen() {
        return Math.min(Math.min(Math.min(Color_switch_circle.getArc1().getLayoutY(),Color_switch_circle.getArc2().getLayoutY()),Color_switch_circle.getArc3().getLayoutY()),Color_switch_circle.getArc4().getLayoutY());
    }

    @Override
    public void changeLevel() {
        Color_switch_circle.changeLevel();
    }


    @Override
    public void ruk() {
        Color_switch_circle.ruk();
    }

    @Override
    public void chalu() {
        Color_switch_circle.chalu();
    }
}
