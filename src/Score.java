import java.awt.*;
import java.io.*;

public class Score {

    // ================================================================================
    // VARIABLES
    // ================================================================================
    private int score;
    private static int highScore = 0;
    private long deltaTime;
    private long previousTime;
    // TODO save highScore in file

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Score() {
        score = 0;
        deltaTime = 100;
        previousTime = 0;
    }

    // ================================================================================
    // METHODS
    // ================================================================================

    public void draw(Graphics g) {

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void updateScore() {
        if (System.currentTimeMillis() - previousTime >= deltaTime) {
            score++;
            previousTime = System.currentTimeMillis();
        }
    }

    private int getHighScore() {
        BufferedReader br = null;
        String file_highScore = "";
        try {
            br = new BufferedReader(new FileReader("resources/highscore.txt"));
            file_highScore = br.readLine();
            br.close();
        } catch (IOException e) {
            file_highScore = "";
        }
        return Integer.parseInt(file_highScore);
    }

    private void updateHighScore() {
        if (score >= getHighScore()) {
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter("resources/highscore.txt", false));
                bw.write("" + highScore);
                bw.flush();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
