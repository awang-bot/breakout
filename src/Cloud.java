/* Anne Liu and Atisa Wang
 * January 21, 2023
 * Cloud
 * This class will manage the behaviours and characteristics of the cloud in the background!
 */

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cloud extends Rectangle {

	// ================================================================================
	// VARIABLES
	// ================================================================================
	private static final BufferedImage BUFF_IMAGE = Resource.getResourceImage("background/cloud.png");
	private int xVelocity;
	public static final int WIDTH = 92;

	// ================================================================================
	// CONSTRUCTOR
	// ================================================================================
	public Cloud() {
		// initialize the cloud position
		x = 1500;
		y = 250;
		xVelocity = 7;
	}

	// ================================================================================
	// METHODS
	// ================================================================================

	/**
	 * move the cloud to the left according to the xVelocity
	 */
	public void move() {
		x -= xVelocity;
	}

	/**
	 * draw the cloud onto the screen
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(BUFF_IMAGE, x, y, null); // draw cactus
	}

}
