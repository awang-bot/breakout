import java.awt.*;
import java.awt.image.BufferedImage;

public class Land {

    // ================================================================================
    // VARIABLES
    // ================================================================================

    public static final int LAND_POS_Y = 400;
    public static final String FILEPATH = "background/land.png";
    public BufferedImage land;

    public static final int LAND_WIDTH = 2400;
    public int x;

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Land(int x) {
        this.x = x;
        land = Resource.getResourceImage(FILEPATH);
    }

    // ================================================================================
    // METHODS
    // ================================================================================

    public void move() {
        x -= 5;
    } // FIXME xSpeed

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(land, x, LAND_POS_Y, null);
    }

}