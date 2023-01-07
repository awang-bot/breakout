/* Anne Liu and Atisa Wang
January 7, 2022
Pterodactyl
This class will manage the characteristics of the pterodactyl obstacle. */

import java.awt.*; // I'm not sure if we use this

public class Pterodactyl extends Rectangle
{

  // variable declaration
  public static final int[] HEIGHT_OFF_GROUND_ARR = {20, 60, 120}; // pterodactyl height off the ground array (list of 3 height options)
  							// this height will depend on how big the dinosaur is
  public int heightOffGround;
  public String birdImageURL→ {30, 100, 170} (or smth like that)
  public static final int BIRD_HEIGHT = 20, BIRD_WIDTH = 30; //width, height of object itself
  
  // constructor
  public Pterodactyl(int heightNum, int x, int y) // heightNum will be a number from 0 to 2 
  {
  	super(x,y);
  	heightOffGround = HEIGHT_OFF_GROUND_ARR[heightNum];
  }
  
  public void move()
  {
    x-=1;
  }

  // draws the current location of the pterodactyl to the screen
  public void draw(Graphics g) {
    
		g.setColor(Color.white);
		g.fillRect(x, y, BIRD_HEIGHT, BIRD_WIDTH);
	} // end of draw
  

}
