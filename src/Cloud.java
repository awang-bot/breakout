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
    public final String fileName = "background/cloud.png";
    public final BufferedImage buffImage;
    private int xVelocity;
    public static final int width = 92;

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Cloud() {
    	// initialize the cloud position
    	x = 1500;
    	y = 250;
    	xVelocity = 7;

        // initialize the cloud image
        buffImage = Resource.getResourceImage(fileName);
    	
    }

    // ================================================================================
    // METHODS
    // ================================================================================

    /**
     * move the cactus 5px left
     */
    public void move() {
        x -= xVelocity;
    }
    

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.drawImage(buffImage, x, y, null); // draw cactus
    }

    public void setXVelocity(int speed){
        xVelocity = speed;
    }

}

	
	
	
