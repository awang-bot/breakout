/*
 * Anne Liu and Atisa Wang
 * January 8, 2023
 * Dinosaur
 * This class will manage the behaviours and characteristics of the dinosaur. 
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
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

    public boolean start_state = true;
	public boolean normalRun_state = false;
	public boolean jump_state = false;
	public boolean crouch_state = false;
	public boolean dead = false;
	Animation normalRun_animation;
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
    public Dinosaur(int width, int height)
    {
//    	super(x, 500, width, height); // the y-coord should be the ground's height
		normalRun_animation = new Animation(100);
		try {
			normalRun_animation.addFrame(ImageIO.read(new File("resources/dino1.png")));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		try {
			normalRun_animation.addFrame(ImageIO.read(new File("resources/dino2.png")));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
    	this.width = width;
    	this.height = height;
    	y=500; // initialize y to be 500 to start there
    	yVelocity = -5;
    	up= true;
    	continueJump = false;
    }
    
    
    // ================================================================================
    // METHODS
	/**
	 * draws the current location of the paddle to the screen
	 */
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
	} // end of draw

    // ================================================================================

    /**
     * updates the direction of the dinosaur based on user input
     */
 	public void keyPressed(KeyEvent e) {
 		if (e.getKeyCode() == 32) {
 			continueJump = true;
 			up = true;
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

 	public void midJump()
 	{
 		if (continueJump && (y<=LOWER_BOUND && y>=UPPER_BOUND)) {
	 		move();
 		}
 	}

 	/**
 	 * Move the dinosaur
 	 */
 	public void move()
 	{
// 		if (y<=500 && y>=400)
// 		{
	 		if (jump_state)
	 		{
//	 			continueJump = true;
	 			y += yVelocity; // add to go back down, minus to go back up
//	 			up = false;
	 			if (y == UPPER_BOUND)
	 			{
	 				jump_state = false;
	 				yVelocity*=-1;
	 			}
	 		}
	 		else if (!jump_state) {
//	 			up = true;
//	 			yVelocity*=-1;
	 			y+=yVelocity;
	 			if (y == LOWER_BOUND)
	 			{
//	 				up = true;
	 				yVelocity*=-1;
	 				continueJump = false;
	 			}

 		}
// 		}
 	} // end of move

	public Rectangle dinoBounds(){
		return (this.getBounds());
	}

 	
}
