/**
 * Anne Liu and Atisa Wang
 * January 7, 2023
 * Pterodactyl
 * This class manages the characteristics and methods of the pterodactyl obstacle.
 */

import java.awt.*;
import java.awt.image.BufferedImage;

public class Pterodactyl extends Rectangle {

	// ================================================================================
	// VARIABLES
	// ================================================================================

	public static final int[] Y_POS = {165, 215, 265 }; // stores the y-coordinates of the pterodactyls
	private int xVelocity;
	public static final int BIRD_HEIGHT = 70, BIRD_WIDTH = 92; // pterodactyl's dimensions
	/**
	 * Animation object to animate the pterodactyl's wings.
	 */
	public Animation birdFlap;
	/**
	 * Pterodactyl image
	 */
	public BufferedImage image;

	// ================================================================================
	// CONSTRUCTOR
	// ================================================================================
	public Pterodactyl(int index, int x, int xSpeed) {
		this.x = x; // sets the starting x-coordinate
		xVelocity = xSpeed; // sets the original speed, will end up changing in GamePanel as the game speeds
							// up
		y = Y_POS[index]; // choose the y-coordinate of the pterodactyl
		this.width = BIRD_WIDTH;
		this.height = BIRD_HEIGHT;

		// initialize animation and the two images used for it
		birdFlap = new Animation(80);
		birdFlap.addFrame(Resource.getResourceImage("pterodactyl/pterodactyl_up.png"));
		birdFlap.addFrame(Resource.getResourceImage("pterodactyl/pterodactyl_down.png"));

		image = birdFlap.getFrame();
	}

	// ================================================================================
	// METHODS
	// ================================================================================

	/**
	 * move the pterodactyl to the left
	 */
	public void move() {
		// continue to update the animation frames
		birdFlap.updateFrame();
		image = birdFlap.getFrame();
		x -= xVelocity; // move the x-coordinate to the left
		// specify the width and height to be the specific frame's width/height (as
		// there are 2 images)
		width = image.getWidth();
		height = image.getHeight();
	}

	/**
	 * draw the pterodactyl onto the screen
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image, x, y, null);
	}

	/**
	 * allow the xVelocity to be specified in GamePanel class
	 * 
	 * @param speed
	 */
	public void setXVelocity(int speed) {
		xVelocity = speed;
	}

}
