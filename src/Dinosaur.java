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
    public static final int x = 100; //FIXME: fix according to the screensize later, should not be manual (#)
    public BufferedImage image;
    public int state;
    public static final int START_STATE = 0;
    public static final int NORM_RUN_STATE = 1;
    public static final int JUMP_STATE = 2;
    public static final int CROUCH_STATE = 3;
    public static final int DEAD_STATE = 4;
    public Animation normal_animation;
    public Animation crouch_animation;
    public boolean midJump, up;
    /**
     * if the dino is dead, true.
     */
    public boolean dead;
	private int numAdd; // counts the number of times velocity was added TEST!!
    /**
     * when true, the dino is still in the air jumping.
     */
    public static final int UPPER_BOUND = 140;
    public static final int LOWER_BOUND = 300;
    public SoundEffect sound;

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Dinosaur() {
        super(x, GamePanel.LAND_HEIGHT+79, 1, 1); // TODO figure out y-coordinate

        normal_animation = new Animation(150);
        crouch_animation = new Animation(150);
//        continueJump = false;
        midJump = false;
        up = true;
        dead = false;
        
        y = GamePanel.LAND_HEIGHT - 67; // or 300, TODO
        yVelocity = -15;// TODO: MAYBE WE CAN HAVE A HELPER METHOD THAT RESETS THE VELOCITY! THIS IS IMPROTANT FOR JUMP LATER unless we delete the part i put in jump because it seems to be a bit faulty
       
        sound = new SoundEffect();

        normal_animation.addFrame(Resource.getResourceImage("dino/dino_normal_1.png"));
        normal_animation.addFrame(Resource.getResourceImage("dino/dino_normal_2.png"));
        crouch_animation.addFrame(Resource.getResourceImage("dino/dino_crouch_1.png"));
        crouch_animation.addFrame(Resource.getResourceImage("dino/dino_crouch_2.png"));

        image = Resource.getResourceImage("dino/dino_start.png");
        state = NORM_RUN_STATE; // TODO change this to start state later
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
            	if (up)
            		yVelocity = -10;
            	else
            		yVelocity = 10;
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
//    	dead = true;
    }

    // ================================================================================
    // HELPER METHODS
    // ================================================================================

   private void jump() {
    //TODO: fix fancy jump    	
        y += yVelocity;

//        // TRY to make it slow down a bit at the top
        if ((y < UPPER_BOUND+15) && y > UPPER_BOUND)
        	if (up)
        	{
        		yVelocity=-2.5;
        	}
        	else
        	{
        		yVelocity=2.5;
        	}
        else if ((y > UPPER_BOUND+10) && (y < LOWER_BOUND))
        {
        	if (yVelocity <0)
        	{
        		yVelocity=-10;
        	}
        	else
        	{
        		yVelocity =7; // i don't know if this is necessary given the below condition
        	}
        }
        else if (y <= UPPER_BOUND) {
        	up = false;
	        yVelocity = 2.5;
//	        midJump = true;
	    } else if (y >= LOWER_BOUND) {
	        state = NORM_RUN_STATE;
	        yVelocity = -10;
	        up = true;
//	        midJump = false;
	    }
        
//        // TEST: OTHERWISE IT'S GOING TOO SLOW. DELETE LATER
//        if (y <= UPPER_BOUND) {
//	        yVelocity = 10;
////	        midJump = true;
//	    } else if (y >= LOWER_BOUND) {
//	        state = NORM_RUN_STATE;
//	        yVelocity = -10;
////	        midJump = false;
//	    }
        
    }
    
    


}
