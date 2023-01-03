import javax.swing.*;

public class Character {

    // change into either flappy bird or dinosaur


    /*
1. ImageIcon
This is typically used when you have small images that you want to add to buttons or to use as window icons. These may be created directly from anything that implements the Image interface.

2. BufferedImage
Typically used when you need to manipulate individual pixels within an image, or when you want to double-buffer a custom paint(Graphics g) method. The image resides in RAM, so it can take up a lot of space, and modifications to BufferedImage instances are not typically hardware-accelerated.

3. VolatileImage
Hardware-accelerated image, so it's fast, but you run the risk of the hardware-backed buffer being overwritten before you're done painting (although this rarely happens and according to Oracle, is only an issue for Windows-based machines). It's a little more difficult to use than BufferedImage for double-buffering custom paint(Graphics g) methods, but is well worth it if you find yourself doing a lot of pre-processing before rendering to screen.

4. Image
This is basically just an interface that defines some bare-bones functionality that every Image should have. You should use this when you don't need to modify an image's contents and/or want to make methods that handle read-only-image data most flexible.
     */

    // variables

    public int x;
    public int y;
    public static int WIDTH; // make final later
    public static int HEIGHT; // make final later




}
