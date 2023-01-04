public class Score {

    // ================================================================================
    // VARIABLES
    // ================================================================================
    private int score;
    private static int highScore = 0;

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Score() {
        score = 0;
    }

    // ================================================================================
    // METHODS
    // ================================================================================

    draw(Graphics g){

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

    // update high score if current score is greater than previous high score
    public void updateHighScore() {
        if (score > highScore) {
            highScore = score;
        }
    }

}
