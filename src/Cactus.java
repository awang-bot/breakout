/* Anne Liu and Atisa Wang
January 7, 2023
Cactus
This class will manage the characteristics of the cactus obstacle. */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
    public static final String[][] CACTUS_IMAGES = {{"1", "resources/cactus1.png", "34", "70"}, {"2", "resources/cactus2.png", "68", "70"}, {"3", "resources/cactus3.png", "102", "70"}, {"4", "resources/cactus4.png", "50", "96"}, {"5", "resources/cactus5.png", "100", "96"}, {"6", "resources/cactus6.png", "150", "96"}};

    public int id;
    public String imageURL;
    final BufferedImage buffImage;

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Cactus(int cactusNum, int x, int y) {
        super(x, y, Integer.parseInt(CACTUS_IMAGES[cactusNum][2]), Integer.parseInt(CACTUS_IMAGES[cactusNum][3]));

        id = Integer.parseInt(CACTUS_IMAGES[cactusNum][0]); // determine the design number
        imageURL = CACTUS_IMAGES[cactusNum][1]; // determine the URL of the image from the array
        try {
            buffImage = ImageIO.read(new File(imageURL));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void move() {
        // move 5 pixels to the left
        x -= 5; // speeds up later? maximum speed? or maybe the speed boost will be coded in the gamepanel move()?
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(0f, 0f, 0f, 0f)); // transparent colour
        g2d.drawRect(x, y, width, height); // draw rectangle
        g2d.drawImage(buffImage, x, y, null); // draw cactus png

    } // end of draw method


} // end of Cactus class
