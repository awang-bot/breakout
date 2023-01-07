import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Dinosaur {

    // ================================================================================
    // VARIABLES
    // ================================================================================

    // TODO add 2d array for different dino modes
    public int x;
    public int y;
    public int speedX;
    public BufferedImage image;
    public Rectangle dinoBoundary;


    public static int getSpeedX() {
        return speedX();
    }
}
