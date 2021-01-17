package mainWindow;

import Screen_move.Ball;

public class PropertyOfSavedGames {
    private final double scoreLabelScore;
    private final double score;
    private final double HighScore;
    private final double collectedStars;
    private final Ball ball;

    public PropertyOfSavedGames(double scoreLabelScore, double score, double highScore, double collectedStars,Ball ball) {
        this.scoreLabelScore = scoreLabelScore;
        this.score = score;
        HighScore = highScore;
        this.collectedStars = collectedStars;
        this.ball=ball;
    }

    public double getScoreLabelScore() {
        return scoreLabelScore;
    }

    public double getScore() {
        return score;
    }

    public double getHighScore() {
        return HighScore;
    }

    public double getCollectedStars() {
        return collectedStars;
    }

    public Ball getBall() {
        return ball;
    }
}
