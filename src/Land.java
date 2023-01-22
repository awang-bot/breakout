/*
 * Anne Liu and Atisa Wang
 * January 11, 2023
 * Land
 * This class will manage the land's characteristics and behaviours to be displayed on the screen by GamePanel
 */

import java.awt.*;
import java.awt.image.BufferedImage;

public class Land {

    // ================================================================================
    // VARIABLES
    // ================================================================================

    public static final int LAND_POS_Y = 380;
    private int xVelocity;
    private static final String FILEPATH = "background/land.png";
    private BufferedImage image;
    public static final int LAND_WIDTH = 2400;
    public int x;

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Land(int x, int xSpeed) {
        this.x = x;
        xVelocity = xSpeed;
        image = Resource.getResourceImage(FILEPATH);
    }

    // ================================================================================
    // METHODS
    // ================================================================================

    /** 
     * move the land to the left
     */
    public void move() {
        x -= xVelocity;
    }
    
    /**
     * allow GamePanel to set the starting x-coordinate of the land
     * @param x
     */
    public void setX(int x)
    {
    	this.x=x;
    }

    /**
     * draw the land onto the sceren
     * @param g
     */
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, x, LAND_POS_Y, null);
    }

    /** 
     * allow GamePanel to set the xVelocity as it will change throughout the game
     * @param speed
     */
    public void setXVelocity(int speed){
        xVelocity = speed;
    }

}
