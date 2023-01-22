/*
 * Anne Liu and Atisa Wang
 * January 11, 2023
 * Score
 * This will manage the player's current and high score as the game continues.
 */

import java.awt.*;
import java.io.*;
import java.text.DecimalFormat;

public class Score {

	// ================================================================================
	// VARIABLES
	// ================================================================================
	private Font ps2pFont;
	private DecimalFormat decimalFormat;
	private int score;
	private static long deltaTime;
	private long previousTime;

	// ================================================================================
	// CONSTRUCTOR
	// ================================================================================
	public Score() {
		ps2pFont = Resource.getResourceFont("game/PressStart2P.ttf", 20F);
		decimalFormat = new DecimalFormat("00000");
		score = 0;
		deltaTime = 100;
		previousTime = 0;
	}

	// ================================================================================
	// METHODS
	// ================================================================================

	/**
	 * draws the scores (high score and current score) onto the screen
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setFont(ps2pFont);

		// draw high score
		String temp = "HI " + decimalFormat.format((double) getHighScore());
		g.setColor(Color.gray);
		g.drawString(temp, 1100, 50);

		// draw score
		temp = decimalFormat.format((double) score); // reset temp to store the score in real-time
		g.setColor(Color.darkGray);
		g.drawString(temp, 1300, 50);
	}

	/**
	 * updates the score as the player runs through the game
	 */
	public void updateScore() {
		if (System.currentTimeMillis() - previousTime >= deltaTime) {
			score++;
			previousTime = System.currentTimeMillis();
		}
	}

	/**
	 * updates the player's high score, called in GamePanel class
	 */
	public void updateHighScore() {
		if (score >= getHighScore()) {
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter("resources/game/highscore.txt", false));
				bw.write(score + "");
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
	/**
	 * get the high score for the updateHighScore() and draw() methods
	 * 
	 * @return
	 */
	private int getHighScore() {
		// local variable declaration
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
