import javax.imageio.ImageIO;
import java.awt.*;
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
public class Pterodactyl{

    // ================================================================================
    // VARIABLES
    // ================================================================================

    /**
     * Each row represents a different pterodactyl obstacle.
     * The first column is the ID.
     * The second column is the y-coordinate.
     */
    public static final String[][] PTERODACTYL_ARR = {{"7", "low"}, {"8", "mid"}, {"9", "high"}};
    // FIXME replace [][1] with actual y-coordinate later
    /**
     * Filepath to pterodactyl_down.png.
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
    private Animation birdFlap;
    /**
     * Pterodactyl x-coordinate
     */
    public int x;
    /**
     * Pterodactyl y-coordinate
     */
    public int y;


    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Pterodactyl(int index, int x, int y) {
        this.x = x;
        this.y = y;

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
        x -= Dinosaur.getSpeedX();
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(0f, 0f, 0f, 0f)); // transparent colour
        g2d.drawRect(x, y, width, height); // draw rectangle
        // TODO add pterodactyl flying animation with BufferedImage and whatnot
    }

    public Rectangle getBounds(){
        return
    }

    public void outOfScreen(){
        // TODO if x < GAME_WIDTH ...
    }

}