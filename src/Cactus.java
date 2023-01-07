/* Anne Liu and Atisa Wang
January 7, 2023
Cactus
This class will manage the characteristics of the cactus obstacle. */

import javax.imageio.ImageIO;
import java.awt.*; // I'm not sure if we use this
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Cactus extends Rectangle
{
  // ================================================================================
  // VARIABLES
  // ================================================================================
  public int cactusWidth, cactusHeight;
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
    super(x,y, Integer.parseInt(CACTUS_IMAGES[cactusNum][2]), Integer.parseInt(CACTUS_IMAGES[cactusNum][3]));

    id = Integer.parseInt(CACTUS_IMAGES[cactusNum][0]); // determine the design number
    imageURL = CACTUS_IMAGES[cactusNum][1]; // determine the URL of the image from the array
    cactusWidth = Integer.parseInt(CACTUS_IMAGES[cactusNum][2]); // cactus width
    cactusHeight = Integer.parseInt(CACTUS_IMAGES[cactusNum][3]); // cactus height
    try {
      buffImage = ImageIO.read(new File(imageURL));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  public void move() {
    // move 5 pixels to the left
    x-=5; // speeds up later? maximum speed? or maybe the speed boost will be coded in the gamepanel move()?
  }

  public void draw(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(new Color(0f, 0f, 0f, 0f));
	g2d.fillRect(x,y,cactusWidth, cactusHeight);


	
} // end of draw method

  
   


} // end of Cactus class
