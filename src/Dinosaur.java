/*
 * Anne Liu and Atisa Wang
 * January 8, 2023
 * Dinosaur
 * This class will manage the behaviours and characteristics of the dinosaur.
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Dinosaur extends Rectangle {

    // ================================================================================
    // VARIABLES
    // ================================================================================

    /**
     * movement speed of dinosaur jumping up/down
     */
    public int yVelocity = 1;
    // position
    public static final int X_POS = 100; //FIXME: fix according to the screensize later, should not be manual (#)
    public int y;
    // dimensions
    public int width; // make final later
    public int height; // make final later
    // objects
    public BufferedImage image;
    public int state;
    public static final int START_STATE = 0;
    public static final int NORM_RUN_STATE = 1;
    public static final int JUMP_STATE = 2;
    public static final int CROUCH_STATE = 3;
    public static final int DEAD_STATE = 4;
    Animation normal_animation;
    Animation crouch_animation;
    /**
     * when true, the dino is still in the air jumping.
     */
    public boolean continueJump;
    public static final int UPPER_BOUND = 400;
    public static final int LOWER_BOUND = 500;

    // TODO: create an array for the dinosaur graphics
    // TODO: figure out the animations...

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Dinosaur(int width, int height) {
//    	super(x, 500, width, height); // the y-coord should be the ground's height

        initializeAnimation();

        this.width = width;
        this.height = height;
        y = 500; // initialize y to be 500 to start there
        yVelocity = -5;
        state = START_STATE;
        continueJump = false;
    }

    private void initializeAnimation() {
        normal_animation = new Animation(100);
        try {
            normal_animation.addFrame(ImageIO.read(new File("resources/dino1.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            normal_animation.addFrame(ImageIO.read(new File("resources/dino2.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // add animation for crouch run
        crouch_animation = new Animation(100);
        try {
            normal_animation.addFrame(ImageIO.read(new File("resources/dino5.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            normal_animation.addFrame(ImageIO.read(new File("resources/dino6.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // ================================================================================
    // METHODS

    /**
     * draws the current location of the paddle to the screen
     */
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

		// TODO testing dino, remove later?
        g.setColor(Color.black);
        g.fillRect(x, y, width, height);

        switch (state) {
            case START_STATE:
                try {
                    image = ImageIO.read(new File("resources/dino7.png")); // FIXME no dino7.png import it!!
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                g2d.drawImage(image, x, y, null);
            case NORM_RUN_STATE:
				image = normal_animation.getFrame();
                g2d.drawImage(image, x, y, null);
			case JUMP_STATE:
				// TODO jump_state draw image
			case CROUCH_STATE:
				image = crouch_animation.getFrame();
				g2d.drawImage(image, x, y, null);
            case DEAD_STATE:
                // TODO dead dino draw image
        }
    } // end of draw

    // ================================================================================

    /**
     * updates the direction of the dinosaur based on user input
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 32) {
            continueJump = true;
            state = JUMP_STATE;
        }
// 			setYDirection(SPEED * -1);
// 			yVelocity *=-1;

    } // end of keyPressed

    /**
     * Makes the dinosaur stop moving in that direction
     */
// 	public void keyReleased(KeyEvent e) {
// 		if (e.getKeyCode() == 32) {
// 			yVelocity *=-1;
// 			move();
// 		}
// 	} // end of keyReleased
    public void midJump() {
        if (continueJump && (y <= LOWER_BOUND && y >= UPPER_BOUND)) {
            move();
        }
    }

    /**
     * Update the dinosaur's position.
     */
    public void move() {
        switch (state) {
            case START_STATE:
                // start dino
            case NORM_RUN_STATE:
                image = normal_animation.getFrame();
                // run
			case JUMP_STATE: { // TODO up and down of jump add some if statements or something
                    continueJump = true;
                    y += yVelocity; // add to go back down, minus to go back up
//	 			up = false;
                    if (y == UPPER_BOUND) {
                        state = JUMP_STATE;
                        yVelocity *= -1;
                    }
                }
            case CROUCH_STATE:
                // crouch
            case DEAD_STATE:
                // dead dino
        }

    } // end of move

    public Rectangle getJumpBounds() {
        Rectangle rect = new Rectangle();
        rect.x = this.x;
        rect.y = this.y;
        rect.width = this.image.getWidth();
        rect.height = this.image.getHeight();
        return rect;
    }


}
