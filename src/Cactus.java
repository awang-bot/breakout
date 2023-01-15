import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <pre>
 * Anne Liu and Atisa Wang
 * January 7, 2023
 *
 * CACTUS
 * This class manages the characteristics and methods of the cactus obstacle. </pre>
 */
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
    public final String fileName;
    public final BufferedImage buffImage;
    private int xVelocity;

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Cactus(int index, int x) {
    	// initialize the cactus position and dimensions
        this.x = x;
        xVelocity = 10;
        y = GamePanel.LAND_HEIGHT+14-Integer.parseInt(CACTUS_ARR[index][2]); 
        width = Integer.parseInt(CACTUS_ARR[index][1]);
        height = Integer.parseInt(CACTUS_ARR[index][2]);

        // initialize the cactus image
        fileName = CACTUS_ARR[index][0];
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
