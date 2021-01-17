package All_Obstacle;

import Screen_move.Ball;

public class Square_Circle_Obstacle extends Obstacle implements PausePlay{
    private final Circular_Obstacle circular_obstacle;
    private final Square_Obstacle square_obstacle;

    public Square_Circle_Obstacle(Circular_Obstacle circular_obstacle, Square_Obstacle square_obstacle) {
        super(false);
        this.circular_obstacle = circular_obstacle;
        this.square_obstacle = square_obstacle;
    }

    public Circular_Obstacle getCircular_obstacle() {
        return circular_obstacle;
    }

    public Square_Obstacle getSquare_obstacle() {
        return square_obstacle;
    }

    public boolean detectCollision(Ball ball)
    {
        return circular_obstacle.detectCollision(ball) || square_obstacle.detectCollision(ball);
    }
    @Override
    public void initial_position() {
        circular_obstacle.initial_position();
        square_obstacle.initial_position();
    }

    @Override
    public void move() {
        square_obstacle.move();
        circular_obstacle.move();
    }

    @Override
    public double check_position_on_screen() {
        return Math.min(circular_obstacle.check_position_on_screen(),square_obstacle.check_position_on_screen());
    }

    @Override
    public void changeLevel() {
        circular_obstacle.changeLevel();
        square_obstacle.changeLevel();
    }



    @Override
    public void ruk() {
        circular_obstacle.ruk();
        square_obstacle.ruk();
    }

    @Override
    public void chalu() {
        circular_obstacle.chalu();
        square_obstacle.chalu();
    }
}
