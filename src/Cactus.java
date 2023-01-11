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
 * CACTUS
 * This class manages the characteristics and methods of the cactus obstacle. </pre>
 */
public class Cactus extends Rectangle {
    // ================================================================================
    // VARIABLES
    // ================================================================================
    /**
     * Each row represents a different cactus obstacle.
     * The first column is the ID.
     * The second column is the file path.
     * The third column is the width (px).
     * The fourth column is the height (px).
     */
    public static final String[][] CACTUS_ARR = {{"1", "resources/cactus1.png", "34", "70"}, {"2", "resources/cactus2.png", "68", "70"}, {"3", "resources/cactus3.png", "102", "70"}, {"4", "resources/cactus4.png", "50", "96"}, {"5", "resources/cactus5.png", "100", "96"}, {"6", "resources/cactus6.png", "150", "96"}};

    /**
     * Cactus identifiers: 1 - one small, 2 - two small, 3 - three small, 4 - one big, 5 - two big, 6 - group of four
     */
    private int cactusWidth, cactusHeight;
    public final int id;
    public final String imagePath;
    public final BufferedImage buffImage;

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Cactus(int index, int x, int y) {
    	// the cactus will typically start from the right-most side of the screen
        super(x, y, Integer.parseInt(CACTUS_ARR[index][2]), Integer.parseInt(CACTUS_ARR[index][3]));

        this.x = x;
        this.y = y;

        // FIXME: make this more efficient - this seems very INefficient
        cactusWidth = Integer.parseInt(CACTUS_ARR[index][2]);
        cactusHeight = Integer.parseInt(CACTUS_ARR[index][3]);
        
        id = Integer.parseInt(CACTUS_ARR[index][0]); // set ID
        imagePath = CACTUS_ARR[index][1]; // set image path
        // initialize buffImage
        try {
            buffImage = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // ================================================================================
    // METHODS
    // ================================================================================

    /**
     * move the cactus 5px left
     */
    public void move() {
        x -= 5;
    }
    

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        // FIXME: draw it from the ground and up, not just down from the top y coord
        g2d.drawImage(buffImage, x, y, null); // draw cactus png
    }


}
