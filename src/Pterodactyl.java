import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <pre>
 * Anne Liu and Atisa Wang
 * January 7, 2023
 *
 * PTERODACTYL
 * This class manages the characteristics and methods of the pterodactyl obstacle. </pre>
 */
public class Pterodactyl extends Rectangle {

    // ================================================================================
    // VARIABLES
    // ================================================================================

    /**
     * Each row represents a different pterodactyl obstacle.
     * The first column is the ID.
     * The second column is the y-coordinate.
     */
    public static final int[] Y_POS = {150, 200, 250}; // FIXME y-coordinate
    private int xVelocity;
    /**
     * Pterodactyl PNG is 80px tall.
     */
    public static final int BIRD_HEIGHT = 80;
    /**
     * Pterodactyl PNG is 92px wide.
     */
    public static final int BIRD_WIDTH = 92;
    /**
     * Animation object to animate the pterodactyl's wings.
     */
    public Animation birdFlap;
    /**
     * Pterodactyl x-coordinate
     */
    public int x;
    /**
     * Pterodactyl y-coordinate
     */
    public int y;
    /**
     * Pterodactyl image
     */
    public BufferedImage image;


    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Pterodactyl(int index, int x, int xSpeed) {

    	
    	this.x = x;
        xVelocity = xSpeed;
        y = Y_POS[index]; 
        this.width = BIRD_WIDTH;
        this.height = BIRD_HEIGHT;

        birdFlap = new Animation(80);

        birdFlap.addFrame(Resource.getResourceImage("pterodactyl/pterodactyl_up.png"));
        birdFlap.addFrame(Resource.getResourceImage("pterodactyl/pterodactyl_down.png"));

        image = birdFlap.getFrame();
        
        // PREVIOUSLY, THE CONSTRUCTOR WAS AS FLWS:
        /**
         * super(x, Y_POS[index], BIRD_WIDTH, BIRD_HEIGHT);
        xVelocity = 5;

        birdFlap = new Animation(80);

        birdFlap.addFrame(Resource.getResourceImage("pterodactyl/pterodactyl_up.png"));
        birdFlap.addFrame(Resource.getResourceImage("pterodactyl/pterodactyl_down.png"));

        image = birdFlap.getFrame();
         */
    }


    // ================================================================================
    // METHODS
    // ================================================================================

    /**
     * move the pterodactyl 5px left
     */
    public void move() {
        birdFlap.updateFrame();
        image = birdFlap.getFrame();
        x -= xVelocity;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(image, x, y, null);
    }

    public void setXVelocity(int speed){
        xVelocity = speed;
    }

}
