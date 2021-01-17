package All_Obstacle;

import Screen_move.Ball;

public class Double_Plus_Obstacle extends Obstacle implements PausePlay{
    private final Plus_Obstacle plus_obstacle_left;
    private final Plus_Obstacle plus_obstacle_right;

    public Double_Plus_Obstacle(Plus_Obstacle plus_obstacle_left, Plus_Obstacle plus_obstacle_right) {
        super(false);
        this.plus_obstacle_left = plus_obstacle_left;
        this.plus_obstacle_right = plus_obstacle_right;
    }

    public Plus_Obstacle getPlus_obstacle_left() {
        return plus_obstacle_left;
    }

    public Plus_Obstacle getPlus_obstacle_right() {
        return plus_obstacle_right;
    }

    public boolean detectCollision(Ball ball)
    {
        return plus_obstacle_left.detectCollision(ball) || plus_obstacle_right.detectCollision(ball);
    }
    @Override
    public void initial_position() {
        plus_obstacle_left.initial_position();
        plus_obstacle_right.initial_position();
    }

    @Override
    public void move() {
        plus_obstacle_left.move();
        plus_obstacle_right.move();
    }

    @Override
    public double check_position_on_screen() {
        return plus_obstacle_right.check_position_on_screen();
    }

    @Override
    public void changeLevel() {
        plus_obstacle_left.changeLevel();
        plus_obstacle_right.changeLevel();
    }


    @Override
    public void ruk() {
        plus_obstacle_left.ruk();
        plus_obstacle_right.ruk();
    }

    @Override
    public void chalu() {
        plus_obstacle_left.chalu();
        plus_obstacle_right.chalu();
    }
}
