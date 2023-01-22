/*
 * Anne Liu and Atisa Wang
 * January 7, 2023
 * Cactus
 * This class manages the characteristics and methods of the cactus obstacle. 
 */

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cactus extends Rectangle {
    // ================================================================================
    // VARIABLES
    // ================================================================================
    /**
     * Each row represents a different cactus obstacle.
     * The first column is the file name.
     * The second column is the width (px).
     * The third column is the height (px).
     */
    public static final String[][] CACTUS_ARR = {{"cactus/cactus1.png", "32", "69"}, {"cactus/cactus2.png", "68", "70"}, {"cactus/cactus3.png", "102", "70"}, {"cactus/cactus4.png", "50", "100"}, {"cactus/cactus5.png", "100", "100"}, {"cactus/cactus6.png", "150", "100"}};
    private final String FILE_NAME;
    private final BufferedImage BUFF_IMAGE;
    private int xVelocity;

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Cactus(int index, int x, int xSpeed) {
    	// initialize the cactus position and dimensions
        this.x = x;
        xVelocity = xSpeed;
        y = GamePanel.LAND_HEIGHT+29-Integer.parseInt(CACTUS_ARR[index][2]);
        width = Integer.parseInt(CACTUS_ARR[index][1]);
        height = Integer.parseInt(CACTUS_ARR[index][2]);

        // initialize the cactus image
        FILE_NAME = CACTUS_ARR[index][0];
        BUFF_IMAGE = Resource.getResourceImage(FILE_NAME);
    	
    }

    // ================================================================================
    // METHODS
    // ================================================================================

    /**
     * move the cactus to the left
     */
    public void move() {
        x -= xVelocity;
    }
    
    /**
     * draw the cactus onto the screen
     * @param g
     */
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(BUFF_IMAGE, x, y, null); // draw cactus
    }

    /**
     * allow the xVelocity to be set in GamePanel
     * @param speed
     */
    public void setXVelocity(int speed){
        xVelocity = speed;
    }

}
