import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;

public class Score {

    // ================================================================================
    // VARIABLES
    // ================================================================================
    public BufferedImage[] scoreImgArr;
    private int score;
    private static long deltaTime;
    private long previousTime;
    private int x;
    // TODO save highScore in file

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Score() {
        scoreImgArr = new BufferedImage[5];
        score = 0;
        deltaTime = 200;
        previousTime = 0;
        x = 700;
    }

    // ================================================================================
    // METHODS
    // ================================================================================

    public void draw(Graphics g) {
        int tempScore = score;
        String tempFilePath = "";
        ArrayList<Integer> digits = new ArrayList<>();

        while (tempScore > 0) {
            digits.add(tempScore % 10);
            tempScore = tempScore / 10;
        }

        if (score < 10000) {
            scoreImgArr[0] = Resource.getResourceImage("score/score0.png");
        } else {
            tempFilePath = returnFilePath(digits.get(digits.size()-5));
            scoreImgArr[0] = Resource.getResourceImage(tempFilePath);
        }
        if (score < 1000) {
            scoreImgArr[1] = Resource.getResourceImage("score/score0.png");
        } else {
            tempFilePath = returnFilePath(digits.get(digits.size()-4));
            scoreImgArr[1] = Resource.getResourceImage(tempFilePath);
        }
        if (score < 100) {
            scoreImgArr[2] = Resource.getResourceImage("score/score0.png");
        } else {
            tempFilePath = returnFilePath(digits.get(digits.size()-3));
            scoreImgArr[2] = Resource.getResourceImage(tempFilePath);
        }
        if (score < 10) {
            scoreImgArr[3] = Resource.getResourceImage("score/score0.png");
        } else {
            tempFilePath = returnFilePath(digits.get(digits.size()-2));
            scoreImgArr[4] = Resource.getResourceImage(tempFilePath);
        }
        if (score>0){
            tempFilePath = returnFilePath(digits.get(digits.size()-1));
            scoreImgArr[4] = Resource.getResourceImage(tempFilePath);
        }

        for (BufferedImage image : scoreImgArr){
            g.drawImage(image, x, 50, null);
            x += 20;
        }
        x = 700;
    }

    public void updateScore() {
        if (System.currentTimeMillis() - previousTime >= deltaTime) {
            score++;
            previousTime = System.currentTimeMillis();
            deltaTime--;
        }
    }

    public void updateHighScore() {
        if (score >= getHighScore()) {
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"/score/highscore.txt", false));
                bw.write("" + score);
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
     * @return
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
            br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/resources/game/highscore.txt"));
            file_highScore = br.readLine();
            br.close();
        } catch (IOException e) {
            file_highScore = "0";
        }
        return Integer.parseInt(file_highScore);
    }

    private String returnFilePath(int digit) {
        String filepath = "";
        if (digit == 0) {
            filepath = "score/score0.png";
        } else if (digit == 1) {
            filepath = "score/score1.png";
        } else if (digit == 2) {
            filepath = "score/score2.png";
        } else if (digit == 3) {
            filepath = "score/score3.png";
        } else if (digit == 4) {
            filepath = "score/score4.png";
        } else if (digit == 5) {
            filepath = "score/score5.png";
        } else if (digit == 6) {
            filepath = "score/score6.png";
        } else if (digit == 7) {
            filepath = "score/score7.png";
        } else if (digit == 8) {
            filepath = "score/score8.png";
        } else if (digit == 9) {
            filepath = "score/score9.png";
        }
            return filepath;
        }

    }
