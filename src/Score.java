import java.awt.*;
import java.io.*;

public class Score {

    // ================================================================================
    // VARIABLES
    // ================================================================================
    private int score;
    private static int highScore = 0;
    private static long deltaTime;
    private long previousTime;
    // TODO save highScore in file

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Score() {
        score = 0;
        deltaTime = 200;
        previousTime = 0;
    }

    // ================================================================================
    // METHODS
    // ================================================================================

    public void draw(Graphics g) {

    }

    public void updateScore() {
        if (System.currentTimeMillis() - previousTime >= deltaTime) {
            score++;
            previousTime = System.currentTimeMillis();
        }
    }

    public void updateHighScore() {
        if (score >= getHighScore()) {
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter("resources/highscore.txt", false));
                bw.write("" + score);
                bw.flush();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // ================================================================================
    // HELPER METHODS
    // ================================================================================
    private int getHighScore() {
        BufferedReader br = null;
        String file_highScore = "";
        try {
            br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/resources/game/highscore.txt"));
            file_highScore = br.readLine();
            br.close();
        } catch (IOException e) {
            file_highScore = "0";
        }
        return Integer.parseInt(file_highScore);
    }

}
