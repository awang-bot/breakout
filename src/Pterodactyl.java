/* Anne Liu and Atisa Wang
January 7, 2022
Pterodactyl
This class will manage the characteristics of the pterodactyl obstacle. */

import java.awt.*; // I'm not sure if we use this

public class Pterodactyl extends Rectangle
{

  // variable declaration
  public int[] heightOffGroundArr; // pterodactyl height off the ground
  int heightOffGround;
  String birdImageURL→ {10, 20, 30} (or smth like that)
  public static final int BIRDHEIGHT, birdWidth; //width, height of object itself

  
  // constructor
  public Pterodactyl()
  {
    

  }
  
  public void move()
  {
    x-=1;
  }

  // draws the current location of the pterodactyl to the screen
  public void draw(Graphics g) {
    
		g.setColor(Color.white);
		g.fillRect(x, y, BALL_DIAMETER, BALL_DIAMETER);
	} // end of draw
  

}
