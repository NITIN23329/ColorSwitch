package All_Obstacle;

public abstract class Obstacle {
    private boolean Ball_Collision;
    private boolean Score_Updated; // added this as we continuously touch the star score continuously update

    public Obstacle(boolean isCollided) {
        this.Ball_Collision = isCollided;
    }

    public boolean isBall_Collision() {
        return Ball_Collision;
    }

    public void setBall_Collision(boolean ball_Collision) {
        Ball_Collision = ball_Collision;
    }

    public boolean isScore_Updated() {
        return Score_Updated;
    }

    public abstract void changeLevel(); // method to change speed of rotation
    public void setScore_Updated(boolean score_Updated) {
        Score_Updated = score_Updated;
    }

    public abstract void initial_position();

    public abstract void move();

    public abstract double check_position_on_screen();

    public abstract void ruk();
    public abstract void chalu();

}
