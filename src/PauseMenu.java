import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

/*
 * Anne and Atisa
 * January 19
 * PauseMenu
 * This class will allow the user to pause their game.
 */


public class PauseMenu {
    // pause: resume, mute, instructions, restart, quit
    // dead menu
    // win menu
	
	// VARIABLE DECLARATION
	/**
	 * pause button
	 */
	public Rectangle pauseButton = new Rectangle(1350, 50, 100, 55);
	public boolean pauseHover;
	/**
	 * if true, the button says pause
	 * if false, the button says start
	 */
	public boolean showPause;
	
	
	/**
	 * constructor
	 */
	public PauseMenu()
	{
		pauseHover = false;
		showPause = true;
	}
	
	/**
	 * render the button and image
	 * @param g
	 */
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

//		// background box
//		g2.setColor(Color.getHSBColor(0, 0, (float) 0.2));
//		g2.fillRect(310, 190, 400, 180);
//		g2.setColor(Color.getHSBColor(0, 0, (float) 0.3));
//		g2.fillRect(300, 180, 400, 180);
//		g.setColor(Color.white);

		rectDraw(g, g2);
		g2.setColor(Color.black);
		g.setFont(new Font("Gill Sans", Font.BOLD, 25));
		if (showPause) {
			// show the pause button
			g.drawString("PAUSE", 1357, 80);
		}
		else {
//			rectDraw(g, g2);
			g.drawString("RESUME", 1355, 80);
		}
	}
	
	// draw button rectangles
		public void rectDraw(Graphics g, Graphics2D g2) {
			g2.setColor(pauseHover ? Color.getHSBColor((float)(0.92), (float)(0.50), (float)(0.72)) : Color.darkGray);
			g2.fill(pauseButton);
		}

		// check if button is hovered over
		public void updateHoverState(Point pointer) {
			pauseHover = pauseButton.contains(pointer);
		}
	
	
}
