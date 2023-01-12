import javax.imageio.ImageIO;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Land {

    // ================================================================================
    // VARIABLES
    // ================================================================================

    public static final int LAND_POS_Y = 100; // FIXME change y-coordinate of ground
    /**
     *
     */
    public static final String FILEPATH = "resources/land.png";
    public BufferedImage land;
    public static final int LAND_WIDTH = 2400;
    public int x;
    public static final int y = 400;


    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Land(int x){
        this.x = x;
        land = Resource.getResourceImage(FILEPATH);
    }

    // ================================================================================
    // METHODS
    // ================================================================================

    public void move()
    {
        x-=5;
    }

    public void draw(Graphics g) {
        /**
         * random integer for land design index num
         */
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(land, x, y, null); // draw cactus
    }

}