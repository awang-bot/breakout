/* Anne Liu and Atisa Wang
January 7, 2022
Animation
This program will handle the animations of the obstacles and dinosaurs. */

import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.util.List;

public class Animation
{
  private List<BufferedImage> dinoFrame;
  
  public Animation()
  {
    dinoFrame = new ArrayList<BufferedImage>(); // initialize the dinoFrame arraylist
  
  }
  
  public void addFrame(BufferedImage frame)
  {
    dinoFrame.add(frame);
  }
  
  public void updateFrame()
  {
  }
  
  public BufferedImage getFrame()
  {
    if (dinoFrame.size() > 0) { // make sure that there is something you can return so it will not error out
      return frames.get(0); 
    }
    return null;
  }

} // end of Animation class
