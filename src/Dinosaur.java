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
    public int yVelocity;
    public static final int x = 100; //FIXME: fix according to the screensize later, should not be manual (#)
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
//    public boolean continueJump;
    public static final int UPPER_BOUND = 400;
    public static final int LOWER_BOUND = 500;

    // TODO: create an array for the dinosaur graphics
    // TODO: figure out the animations...

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Dinosaur() {
    	super(x, 500); // TODO figure out y-coordinate

        initializeAnimation();

        yVelocity = -5;
        state = START_STATE;
//        continueJump = false;
    }

    // ================================================================================
    // METHODS
    // ================================================================================

    /**
     * Update the dinosaur's position and dimensions.
     */
    public void move() {

        switch (state) {
            case START_STATE:
                try {
                    image = ImageIO.read(new File("resources/dino7.png")); // FIXME no dino7.png import it!!
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            case NORM_RUN_STATE:
                image = normal_animation.getFrame();
            case JUMP_STATE: {
                try {
                    image = ImageIO.read(new File("resources/dino5.png")); // FIXME which dino png???
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case CROUCH_STATE:
                image = crouch_animation.getFrame();
            case DEAD_STATE:
                try {
                    image = ImageIO.read(new File("resources/dino4.png")); // FIXME don't know dino pngs
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }

        this.y = image.getTileGridYOffset();
        this.width = image.getWidth();
        this.height = image.getHeight();

    } // end of move

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


    /**
     * updates the direction of the dinosaur based on user input
     */
    public void keyPressed(KeyEvent e) {
        if (((e.getKeyCode() == 32) || (e.getKeyCode() == 38)) && (state == NORM_RUN_STATE)){
            state = JUMP_STATE;
        } else if (state == JUMP_STATE && e.getKeyCode() == 40){
            yVelocity = -10;
        } else if (state == NORM_RUN_STATE && e.getKeyCode() == 40) {
            state = CROUCH_STATE;
        }
    } // end of keyPressed

    /**
     * Makes the dinosaur stop moving in that direction
     */
	public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 40) {
            yVelocity = -5;
			state = NORM_RUN_STATE;
		}
 	} // end of keyReleased

    public void midJump() {
        if (state == JUMP_STATE && (y <= LOWER_BOUND && y >= UPPER_BOUND)) {
            move();
        }
    }

    /**
     * @return a Rectangle with the Dinosaur's position and dimensions.
     */
    public Rectangle getJumpBounds() {
        Rectangle rect = new Rectangle();
        rect.x = this.x;
        rect.y = this.y;
        rect.width = this.image.getWidth();
        rect.height = this.image.getHeight();
        return rect;
    }

    // ================================================================================
    // HELPER METHODS
    // ================================================================================

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

}
