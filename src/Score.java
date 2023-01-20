import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;

public class Score {

    // ================================================================================
    // VARIABLES
    // ================================================================================
    private  Font ps2pFont;
    private DecimalFormat decimalFormat;
    public BufferedImage[] scoreImgArr;
    private int score;
    private static long deltaTime;
    private long previousTime;
    private int x;

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Score() {
        ps2pFont = Resource.getResourceFont("game/PressStart2P.ttf", 20F);
        decimalFormat = new DecimalFormat("00000");
        scoreImgArr = new BufferedImage[5];
        score = 0;
        deltaTime = 100;
        previousTime = 0;
    }

    // ================================================================================
    // METHODS
    // ================================================================================

    public void draw(Graphics g) {
        g.setFont(ps2pFont);

        // draw high score
        String temp = "HI "+decimalFormat.format((double)getHighScore());
        g.setColor(Color.gray);
        g.drawString(temp, 1100, 50);

        // draw score
        temp = decimalFormat.format((double)score);
        g.setColor(Color.darkGray);
        g.drawString(temp, 1300, 50);

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
                bw = new BufferedWriter(new FileWriter("resources/game/highscore.txt", false));
                bw.write(score+"");
                bw.flush();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * accessor method for the current score
     *
     * @return the score
     */
    public int currentScore() {
        return score;
    }


    // ================================================================================
    // HELPER METHODS
    // ================================================================================
    private int getHighScore() {
        BufferedReader br = null;
        String file_highScore = "";
        try {
            br = new BufferedReader(new FileReader("resources/game/highscore.txt"));
            file_highScore = br.readLine();
            br.close();
        } catch (IOException e) {
            file_highScore = "0";
        }
        return Integer.parseInt(file_highScore);
    }

}
