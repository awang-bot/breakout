/* Anne Liu and Atisa Wang
January 7, 2022
Cactus
This class will manage the characteristics of the cactus obstacle. */

import java.awt.*; // I'm not sure if we use this

public class Cactus extends Rectangle
{
  // Variable declaration
  public int cactusWidth, cactusHeight;
  public static final String[][] CACTUS_IMAGES = {{"1", "resources/cactus1.png", "34", "70"}, {"2", "resources/cactus2.png", "68", "70"}, {"3", "resources/cactus3.png", "102", "70"}, {"4", "resources/cactus4.png", "50", "96"}, {"5", "resources/cactus5.png", "100", "96"}, {"6", "resources/cactus6.png", "150", "96"}};
                                  //(for the order of designing cacti – 0 maps to 1 small cactus, 1 maps to 1 big cactus, 2 maps to 2 small cacti, 3 maps to 2 big cacti and 1 small cactus, 4 maps to 4 cacti)
                                  //First column is design number
                                  //Second is graphics url
                                  //Third is rectangle (drawn behind) width
                                  //Fourth is rectangle height

  public int graphicNum;
  public String imageURL;
  
  public Cactus(int cactusNum, int x, int y)
  {
    super(x,y); // position
    // can probably put this into a for loop to make it more efficient
    graphicNum = Integer.parseInt(cactusGraphicsURL[cactusNum][0]); // determine the design number
    imageURL = cactusGraphicsURL[cactusNum][1]; // determine the URL of the image from the array
    cactusWidth = Integer.parseInt(cactusGraphicsURL[cactusNum][2]); // determine the cactus width
    cactusHeight = Integer.parseInt(cactusGraphipcsURL[cactusNum][3]); // cactus height
  }
  
  public void move()
  {
  	x-=5; // move x 5 pixels to the left
  }



  public void draw(Graphics g) {
  	g.setColor(Color.white); // set it as white for now
	g.fillRect(x,y,cactusWidth, cactusHeight); 
	
	//draw the actual image, need to import it from resources
	
} // end of draw method

  
   


} // end of Cactus class
