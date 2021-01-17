package All_Obstacle;

import Screen_move.Ball;
import javafx.scene.paint.Color;

public class Double_Circle_Obstacle extends Obstacle implements PausePlay{
    private final Circular_Obstacle left_circle;
    private final Circular_Obstacle right_circle;

    public Double_Circle_Obstacle(Circular_Obstacle right_circle, Circular_Obstacle left_circle) {
        super(false);
        this.left_circle = left_circle;
        this.right_circle = right_circle;
        right_circle.getArc1().setStroke(Color.YELLOW);
        right_circle.getArc2().setStroke(Color.RED);
        right_circle.getArc3().setStroke(Color.AQUA);
        right_circle.getArc4().setStroke(Color.MAGENTA);

    }

    public Circular_Obstacle getLeft_circle() {
        return left_circle;
    }

    public Circular_Obstacle getRight_circle() {
        return right_circle;
    }

    public boolean detectCollision(Ball ball)
    {
        return left_circle.detectCollision(ball) || right_circle.detectCollision(ball);
    }

    @Override
    public void initial_position() {
        left_circle.initial_position();
        right_circle.initial_position();
    }

    @Override
    public void move() {
        left_circle.move();
        right_circle.move();
    }

    @Override
    public double check_position_on_screen() {
        return left_circle.check_position_on_screen();
    }

    @Override
    public void changeLevel() {
        left_circle.changeLevel();
        right_circle.changeLevel();
    }

    @Override
    public void ruk() {
        left_circle.ruk();
        right_circle.ruk();
    }

    @Override
    public void chalu() {
        left_circle.chalu();
        right_circle.chalu();
    }
}
