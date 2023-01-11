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

    /**
     * movement speed of dinosaur jumping up/down
     */
    public int yVelocity;
    public static final int x = 100; //FIXME: fix according to the screensize later, should not be manual (#)
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
    public static final int UPPER_BOUND = 300;
    public static final int LOWER_BOUND = 100;

    // TODO: create an array for the dinosaur graphics
    // TODO: figure out the animations...

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Dinosaur() {
        super(x, 100, 1, 1); // TODO figure out y-coordinate

        normal_animation = new Animation(150);
        crouch_animation = new Animation(150);

        normal_animation.addFrame(Resource.getResourceImage("resources/dino_normal_1.png"));
        normal_animation.addFrame(Resource.getResourceImage("resources/dino_normal_2.png"));
        crouch_animation.addFrame(Resource.getResourceImage("resources/dino_crouch_1.png"));
        crouch_animation.addFrame(Resource.getResourceImage("resources/dino_crouch_2.png"));

        image = Resource.getResourceImage("resources/dino_start.png");
        yVelocity = -5;
        state = NORM_RUN_STATE;
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
            case START_STATE: {
                this.y = 20;
                image = Resource.getResourceImage("resources/dino_start.png");
                break;
            }
            case NORM_RUN_STATE: {
                this.y = 20;
                normal_animation.updateFrame();
                image = normal_animation.getFrame();
                break;
            }
            case JUMP_STATE: {
                jump();
                image = Resource.getResourceImage("resources/dino_jump.png");
                break;
            }
            case CROUCH_STATE: {
                this.y = 10;
                crouch_animation.updateFrame();
                image = crouch_animation.getFrame();
                break;
            }
            case DEAD_STATE: {
                this.y = 20;
                image = Resource.getResourceImage("resources/dino_dead.png");
                break;
            }
        }

        this.width = image.getWidth();
        this.height = image.getHeight();

    }

    public void jump() {
        y -= yVelocity;
        if (y >= UPPER_BOUND) {
            yVelocity *= -1;
        } else if (y <= LOWER_BOUND) {
            state = NORM_RUN_STATE;
            yVelocity *= -1;
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, x, y, null);
    }


    /**
     * updates the direction of the dinosaur based on user input
     */
    public void keyPressed(KeyEvent e) {
        if ((state == NORM_RUN_STATE)) {
            if (e.getKeyCode() == 32 || e.getKeyCode() == 49) {
                state = JUMP_STATE;
            } else if (e.getKeyCode() == 40) {
                state = CROUCH_STATE;
            }
        } else if (state == JUMP_STATE)
            if (e.getKeyCode() == 40) {
                yVelocity = -10;
            }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 40) {
            yVelocity = -5;
            state = NORM_RUN_STATE;
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

}
