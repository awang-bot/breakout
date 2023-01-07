/* Anne Liu and Atisa Wang
January 7, 2023
Pterodactyl
This class will manage the characteristics of the pterodactyl obstacle. */

import java.awt.*;

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
    public static final String[][] PTERODACTYL_ARR = {{"7", "low"}, {"8", "mid"}, {"9", "high"}};
    // replace [][1] with actual y-coordinate later
    /**
     * Filepath to pterodactyl_down.png.
     */
    public final static String FILEPATH_UP = "resources/pterodactyl_up.png";
    /**
     * Filepath to pterodactyl_down.png.
     */
    public final static String FILEPATH_DOWN = "resources/pterodactyl_down.png";
    /**
     * pterodactyl_up.png is 60px tall.
     */
    public static final int BIRD_HEIGHT_UP = 20;
    /**
     * pterodactyl_down.png is 69px tall.
     */
    public static final int BIRD_HEIGHT_DOWN = 20;
    /**
     * Pterodactyl PNG is 30px wide.
     */
    public static final int BIRD_WIDTH = 30;
    /**
     * Animation object to animate the pterodactyl's wings.
     */
    private Animation birdFlap;


    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Pterodactyl(int index, int x, int y) {
        super(x, y);

        birdFlap = new Animation();
        birdFlap.addFrame(Resource.getResourceImage(FILEPATH_UP));
        birdFlap.addFrame(Resource.getResourceImage(FILEPATH_DOWN));
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

        g.setColor(Color.white);
        g.fillRect(x, y, BIRD_HEIGHT, BIRD_WIDTH);
    }

}