/* Anne Liu and Atisa Wang
 * January 17, 2023
 * Win
 * This class will manage the win menu of the game.
 */

import java.awt.*;
import java.awt.image.BufferedImage;

public class WinMenu {

	// ================================================================================
	// VARIABLES
	// ================================================================================

	// buttons
	Rectangle returnMenu = new Rectangle(375, 285, 250, 55);
	Rectangle startAgain = new Rectangle(375,400,250,55);
	// check for mouse input
	boolean menuHover = false;
	boolean restartHover = false;
	// true when player 1 wins, false when player 2 wins
	boolean player1Win = false;
	
    public BufferedImage image;

	// ================================================================================
	// CONSTRUCTOR
	// ================================================================================
	public WinMenu() {
		image = Resource.getResourceImage("game/gameover.png");
	}

	// ================================================================================
	// METHODS
	// ================================================================================

	// draw win message
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		// background box
//		g2.setColor(Color.getHSBColor(0, 0, (float) 0.2));
//		g2.fillRect(310, 190, 400, 180);
//		g2.setColor(Color.getHSBColor(0, 0, (float) 0.3));
//		g2.fillRect(300, 180, 400, 180);
		g.setColor(Color.white);

		// win message
        g2.drawImage(image, 700, 300, null); // draw cactus


		// return to menu button
		rectDraw(g, g2);
		g2.setColor(Color.white);
		g.setFont(new Font("Gill Sans", Font.BOLD, 25));
		g.drawString("RETURN TO MENU", 390, 320);
		
		// restart button
//		rectDraw(g, g2);
		g2.setColor(Color.white); // idk if you need to call this again
		g.drawString("Restart", 450, 435);

	}

	// draw button rectangles
	public void rectDraw(Graphics g, Graphics2D g2) {
		g2.setColor(menuHover ? Color.getHSBColor((float)(0.92), (float)(0.50), (float)(0.72)) : Color.darkGray);
		g2.fill(returnMenu);
		
		g2.setColor(restartHover ? Color.getHSBColor((float)(0.92), (float)(0.50), (float)(0.72)) : Color.darkGray);
		g2.fill(startAgain);
	}

	// check if button is hovered over
	public void updateHoverState(Point pointer) {
		menuHover = returnMenu.contains(pointer);
		restartHover = startAgain.contains(pointer);
	}

}
