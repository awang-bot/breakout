import javax.imageio.ImageIO;
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
    private static final String[] FILEPATH = {"resources/land1.png","resources/land2.png","resources/land3.png"};
    public BufferedImage land1;
    public BufferedImage land2;
    public BufferedImage land3;

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Land(){
        try {
            land1 = ImageIO.read(new File(FILEPATH[0]));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            land2 = ImageIO.read(new File(FILEPATH[1]));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            land3 = ImageIO.read(new File(FILEPATH[2]));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // ================================================================================
    // METHODS
    // ================================================================================







}
