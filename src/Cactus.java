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
    public final int id;
    public final String imagePath;
    public final BufferedImage buffImage;

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Cactus(int index, int x, int y) {
        super(x, y, Integer.parseInt(CACTUS_ARR[index][2]), Integer.parseInt(CACTUS_ARR[index][3]));

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
        x -= Dinosaur.getSpeedX();
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(0f, 0f, 0f, 0f)); // transparent colour
        g2d.drawRect(x, y, width, height); // draw rectangle
        g2d.drawImage(buffImage, x, y, null); // draw cactus png

    }

}
