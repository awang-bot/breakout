import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
//    public static final String[][] PTERODACTYL_ARR = {{"7", "low"}, {"8", "mid"}, {"9", "high"}};
    public static final int[][] PTERODACTYL_ARR = {{1, 100}, {2, 150}, {3, 150}}; // FIXME y-coordinates and we might not need image ids?
     /**
      * Filepath to pterodactyl_up.png.
     */
    public final static String FILEPATH_UP = "resources/pterodactyl_up.png";
    /**
     * Filepath to pterodactyl_down.png.
     */
    public final static String FILEPATH_DOWN = "resources/pterodactyl_down.png";
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
    // i don't think we need the imagepath/buffimage
//    public final String imagePath;
//    public final BufferedImage buffImage;


    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Pterodactyl(int index, int x) {
    	// below is changed because of line 26:     public static final int[][] PTERODACTYL_ARR = {{7, 100}, {8, 150}, {9, 150}};
//    	super(x, Integer.parseInt(PTERODACTYL_ARR[index][0]));
//    	this.x = x;
//        y = Integer.parseInt(PTERODACTYL_ARR[index][0]);
    	
    	super(x, PTERODACTYL_ARR[index][1]);
    	this.x = x;
        y = PTERODACTYL_ARR[index][1];
        // FIXME: make this efficient??
        
        birdFlap = new Animation(90); // FIXME figure out deltaTime value

        try {
            birdFlap.addFrame(ImageIO.read(new File(FILEPATH_UP)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            birdFlap.addFrame(ImageIO.read(new File(FILEPATH_DOWN)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // ================================================================================
    // METHODS
    // ================================================================================

    /**
     * move the pterodactyl 5px left
     */
    public void move() {
        x -= 5;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        /* use this to test the functionality of draw*/
        g.setColor(Color.blue);
        g.fillRect(x, y, BIRD_WIDTH, BIRD_HEIGHT);
        
        // FIXME: draw it from the ground and up, not just down from the top y coord
        g2d.setColor(new Color(0f, 0f, 0f, 0f)); // transparent colour
        g2d.drawRect(x, y, width, height); // draw rectangle
        g2d.drawRect(x, y, BIRD_WIDTH, BIRD_HEIGHT); 
        // TODO add pterodactyl flying animation with BufferedImage and whatnot
    }

//    public void outOfScreen(){
//        // TODO if x < GAME_WIDTH ...
//    }

}
