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
    private static final String[] FILEPATH = {"resources/land1.png","resources/land2.png","resources/land3.png"};
    public BufferedImage land1;
    public BufferedImage land2;
    public BufferedImage land3;
    public BufferedImage buffImage;
    public final int landWidth = 2400;
    public int x;
    public static final int y = 400;


    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Land(){
    	x = 0;
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

    public void move()
    {
    	x-=5;
    }
    
    public void draw(Graphics g) {
    	/**
    	 * random integer for land design index num
    	 */
    	int randomLand = (int)(Math.random()*2);
        Graphics2D g2d = (Graphics2D) g;
    	
        if (randomLand == 0)
        	buffImage = land1;
        else if (randomLand == 1)
        	buffImage = land2;
        else
        	buffImage = land3;
    	
        if(x < 0)
        	g2d.drawImage(buffImage, x, y, null); // draw cactus
    }

}
