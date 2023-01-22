/*
 * Anne Liu and Atisa Wang
 * January 8, 2023
 * Dinosaur
 * This class will manage the behaviours and characteristics of the dinosaur.
 */

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Dinosaur extends Rectangle {

	// ================================================================================
	// VARIABLES
	// ================================================================================
	public double yVelocity;
	public static final int X = 100; // set the x-coordinate of the dinosaur to remain unchanged throughout
	public BufferedImage image;
	// manage the states of the dinosaur throughout the game
	public int state;
	public static final int NORM_RUN_STATE = 1;
	public static final int JUMP_STATE = 2;
	public static final int CROUCH_STATE = 3;
	public static final int DEAD_STATE = 4;
	// declare two animations for the dinosaur's movement
	public Animation normalAnimation;
	public Animation crouchAnimation;
	public boolean up; // checks when the dinosaur is in the air
	// set the upper and lower bounds to manage the jumping range
	public static final int UPPER_BOUND = 140;
	public static final int LOWER_BOUND = 315;
	public SoundEffect sound;

	// ================================================================================
	// CONSTRUCTOR
	// ================================================================================
	public Dinosaur() {
		super(X, GamePanel.LAND_HEIGHT + 79, 1, 1);

		normalAnimation = new Animation(150);
		crouchAnimation = new Animation(150);
		up = false; // initialize up to false as it is not yet in the air
		yVelocity = -15;
		sound = new SoundEffect();

		// manage the dinosaur's different normal/crouching state images
		normalAnimation.addFrame(Resource.getResourceImage("dino/dino_normal_1.png"));
		normalAnimation.addFrame(Resource.getResourceImage("dino/dino_normal_2.png"));
		crouchAnimation.addFrame(Resource.getResourceImage("dino/dino_crouch_1.png"));
		crouchAnimation.addFrame(Resource.getResourceImage("dino/dino_crouch_2.png"));

		image = Resource.getResourceImage("dino/dino_start.png");
		state = NORM_RUN_STATE; // set the starting state to the normal running position
	}

	// ================================================================================
	// METHODS
	// ================================================================================

	/**
	 * Update the dinosaur's position and dimensions.
	 */
	public void move() {
		// depending on the state, store different images to display and/or call
		// different methods.
		// set coordinates accordingly
		switch (state) {
		case NORM_RUN_STATE -> {
			y = 315;
			// call the animation so the legs can move
			normalAnimation.updateFrame();
			image = normalAnimation.getFrame();
			break;
		}
		case JUMP_STATE -> {
			jump(); // call jump to let the dinosaur go up and down
			image = Resource.getResourceImage("dino/dino_jump.png");
			break;
		}
		case CROUCH_STATE -> {
			y = 348; // set y lower to ensure it is still on the ground even when the height is
						// smaller
			// call the animation so the legs can move while the dinosaur is crouching
			crouchAnimation.updateFrame();
			image = crouchAnimation.getFrame();
			break;
		}
		case DEAD_STATE -> {
			if (y > 315) // make sure the dinosaur ends up on or above the ground, not beneath
				y = 315;
			image = Resource.getResourceImage("dino/dino_dead.png"); // store to display the dinosaur's dead state image
			break;
		}
		}
		width = image.getWidth() - 20; // -20 so that the dinosaur ends up closer to the obstacle
		height = image.getHeight() - 20;
	}

	/**
	 * draw the image onto the screen
	 * 
	 * @param g
	 */
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image, x, y, null);
	}

	/**
	 * updates the direction of the dinosaur based on user input
	 */
	public void keyPressed(KeyEvent e, boolean mute) {
		if ((state == NORM_RUN_STATE)) {
			if (e.getKeyCode() == 38 || e.getKeyCode() == 32) { // if the user presses space or upward arrow
				state = JUMP_STATE;
				up = true;
				if (!mute) {
//                   sound.setFile(0);
//                   sound.play();
				}
			} else if (e.getKeyCode() == 40) { // if the user presses the downward arrow
				state = CROUCH_STATE;
			}
		} else if (state == JUMP_STATE)
			if (e.getKeyCode() == 40) { // if the user presses the downward arrow while jumping
				yVelocity = 10; // make the dinosaur go down
			}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 40) {
			yVelocity = -10;
			state = NORM_RUN_STATE;
		}
	}

	// ================================================================================
	// HELPER METHODS
	// ================================================================================

	private void jump() {
		y += yVelocity;

		// if y is between two intervals, change its velocity
		if ((y < UPPER_BOUND + 15) && y > UPPER_BOUND) { // interval: 15 pxls over the upper bound to the upper bound
			if (up) {
				yVelocity = -2.5;
			} else {
				yVelocity = 2.5;
			}
		} else if ((y > UPPER_BOUND + 15) && (y < LOWER_BOUND)) { // interval: between the lower bound and a little bit
																	// under the upper bound
			if (yVelocity < 0) {
				yVelocity = -10;
			} else {
				yVelocity = 7;
			}
		} else if (y <= UPPER_BOUND) { // for when it reaches the upper bound
			up = false;
			yVelocity = 2.5;
		} else if (y >= LOWER_BOUND) { // for when it reaches the lower bound
			state = NORM_RUN_STATE;
			yVelocity = -10;
			up = true;
		}
	}

}
