import java.awt.*;
import java.awt.image.BufferedImage;

public class Land {

    // ================================================================================
    // VARIABLES
    // ================================================================================

    public static final int LAND_POS_Y = 380;
    private int xVelocity;
    public static final String FILEPATH = "background/land.png";
    public BufferedImage image;
    public static final int LAND_WIDTH = 2400;
    public int x;

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Land(int x) {
        this.x = x;
        xVelocity = 5;
        image = Resource.getResourceImage(FILEPATH);
    }

    // ================================================================================
    // METHODS
    // ================================================================================

    public void move() {
        x -= xVelocity;
    } // FIXME xSpeed

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(image, x, LAND_POS_Y, null);
    }

    public void setXVelocity(int speed){
        xVelocity = speed;
    }

}