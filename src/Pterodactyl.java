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
//    public static final String[][] PTERODACTYL_ARR = {{"7", "low"}, {"8", "mid"}, {"9", "high"}};
    public static final int[][] PTERODACTYL_ARR = {{1, 100}, {2, 150}, {3, 150}}; // FIXME: check when the first index is used... i don't think it's ever
    // FIXME replace [][1] with actual y-coordinate later

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
    public Pterodactyl(int index, int x) {
        // below is changed because of line 26:     public static final int[][] PTERODACTYL_ARR = {{7, 100}, {8, 150}, {9, 150}};
//    	super(x, Integer.parseInt(PTERODACTYL_ARR[index][0]));
//    	this.x = x;
//        y = Integer.parseInt(PTERODACTYL_ARR[index][0]);

        super(x, PTERODACTYL_ARR[index][1]);
        this.x = x;
        y = PTERODACTYL_ARR[index][1];

        birdFlap = new Animation(80);

        birdFlap.addFrame(Resource.getResourceImage("resources/pterodactyl_up.png"));
        birdFlap.addFrame(Resource.getResourceImage("resources/pterodactyl_down.png"));

        image = birdFlap.getFrame();
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
        x -= 5;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

//        g.setColor(Color.blue);
//        g.fillRect(x, y, BIRD_WIDTH, BIRD_HEIGHT);

        g2d.drawImage(image, x, y, null);

        // FIXME: draw it from the ground and up, not just down from the top y coord
    }

//    public void outOfScreen(){
//        // TODO if x < GAME_WIDTH ...
//    }

}
