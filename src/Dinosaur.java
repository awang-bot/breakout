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

    public static final int x = 100;
    /**
     * movement speed of dinosaur jumping up/down
     */
    public double yVelocity;
    public boolean up;
    public boolean dead;
    public static final int UPPER_BOUND = 150;
    public static final int LOWER_BOUND = 300;
    public int state;
    public static final int START_STATE = 0;
    public static final int NORM_RUN_STATE = 1;
    public static final int JUMP_STATE = 2;
    public static final int CROUCH_STATE = 3;
    public static final int DEAD_STATE = 4;
    public BufferedImage image;
    public Animation normal_animation;
    public Animation crouch_animation;
    public SoundEffect sound;

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Dinosaur() {
        y = GamePanel.LAND_HEIGHT - 67;
        yVelocity = -10;
        sound = new SoundEffect();
        image = Resource.getResourceImage("dino/dino_start.png");
        state = NORM_RUN_STATE; // TODO change this to start state later

        normal_animation = new Animation(100);
        crouch_animation = new Animation(100);

        normal_animation.addFrame(Resource.getResourceImage("dino/dino_normal_1.png"));
        normal_animation.addFrame(Resource.getResourceImage("dino/dino_normal_2.png"));
        crouch_animation.addFrame(Resource.getResourceImage("dino/dino_crouch_1.png"));
        crouch_animation.addFrame(Resource.getResourceImage("dino/dino_crouch_2.png"));

    }

    // ================================================================================
    // METHODS
    // ================================================================================

    /**
     * Update the dinosaur's position and dimensions.
     */
    public void move() {

        switch (state) {
            case START_STATE -> {
                y = 20;
                image = Resource.getResourceImage("dino/dino_start.png");
                break;
            }
            case NORM_RUN_STATE -> {
                y = 300;
                normal_animation.updateFrame();
                image = normal_animation.getFrame();
                break;
            }
            case JUMP_STATE -> {
                jump();
                image = Resource.getResourceImage("dino/dino_jump.png");
                break;
            }
            case CROUCH_STATE -> {
                y = 333;
                crouch_animation.updateFrame();
                image = crouch_animation.getFrame();
                break;
            }
            case DEAD_STATE -> {
                this.y = 300;
                image = Resource.getResourceImage("dino/dino_dead.png");
		dead = true;
                break;
            }
        }

        width = image.getWidth();
        height = image.getHeight();

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
//                sound.setFile(0);
//                sound.play();
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

    public void setDinoDead()
    {
    	state = DEAD_STATE;
    	dead = true;
    }

    // ================================================================================
    // HELPER METHODS
    // ================================================================================

   private void jump() {
    //TODO: fix fancy jump
        y += yVelocity;

//        // TRY to make it slow down a bit at the top
        if ((y < UPPER_BOUND+20) && y > UPPER_BOUND)
        	if (yVelocity <0)
        	{
        		yVelocity+=0.5;
        	}
        	else
        	{
        		yVelocity -=0.5;
        	}
        else if ((y > UPPER_BOUND+20) && (y < LOWER_BOUND))
        {
        	if (yVelocity <0)
        	{
        		yVelocity=-5;
        	}
        	else
        	{
        		yVelocity =5;
        	}
        }
        else if (y <= UPPER_BOUND) {
        	up = false;
	        yVelocity = 5;
	    } else if (y >= LOWER_BOUND) {
	        state = NORM_RUN_STATE;
	        yVelocity = -5;
	        up = true;
	    }

    }


}
