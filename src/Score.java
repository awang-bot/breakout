import java.awt.*;

public class Score {

    // ================================================================================
    // VARIABLES
    // ================================================================================
    private int score;
    private static int highScore = 0;
    // TODO save highScore in file

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Score() {
        score = 0;
    }

    // ================================================================================
    // METHODS
    // ================================================================================

    public void draw(Graphics g){

    }

    // getters
    public int getScore() {
        return score;
    }

    public static int getHighScore() {
        return highScore;
    }

    // setters
    public void setScore(int score){
        this.score = score;
    }

    public void updateScore(){
        // +1 every 0.1 second or 1e+8 nanosecond
    }

    // update high score if current score is greater than previous high score
    public void updateHighScore() {
        if (score > highScore) {
            highScore = score;
        }
    }

}
