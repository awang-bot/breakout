import java.awt.*;

public class MainMenu {

    // only appears once at the beginning of the game
    // start, instructions, mute, exit

	// ================================================================================
	// VARIABLES
	// ================================================================================

	// font
	Font ps2pFont;
	// dimensions
	public static final int GAME_WIDTH = GamePanel.GAME_WIDTH;
	public static final int GAME_HEIGHT = GamePanel.GAME_HEIGHT;
	public static final int BUTTON_WIDTH = 500;
	public static final int BUTTON_HEIGHT = 55;
	public static final int BUTTON_X = 33;
	// buttons
	Rectangle start = new Rectangle(492, 203, BUTTON_WIDTH, BUTTON_HEIGHT);
	Rectangle instructions = new Rectangle(492, 275, BUTTON_WIDTH, BUTTON_HEIGHT);
	Rectangle quit = new Rectangle(492, 347, BUTTON_WIDTH, BUTTON_HEIGHT);
	Rectangle back = new Rectangle(30, 40, BUTTON_WIDTH, BUTTON_HEIGHT);
	// true for displaying main menu, false for displaying instructions
	boolean mainMenu = true;
	// mouse hover
	boolean startHover = false;
	boolean instructionsHover = false;
	boolean quitHover = false;
	boolean backHover = false;

	// ================================================================================
	// CONSTRUCTOR
	// ================================================================================
	public MainMenu() {
	}

	// ================================================================================
	// METHODS
	// ================================================================================

	// render menu
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// main menu
		if (mainMenu) {
			
			// pong title
			g.setColor(Color.black);
			g.setFont(ps2pFont.deriveFont(60F).deriveFont(Font.BOLD));
			g.drawString("PONG", 400, 150);
			
			// menu buttons
			g.setFont(new Font("Gill Sans", Font.PLAIN, 30));
			rectDraw(g, g2);
			g.drawString("START", 700, 244);
			g.drawString("INSTRUCTIONS", 640, 315);
			g.drawString("QUIT", 710, 385);
		}
		// display instructions
		else {
			// instructions title
			g.setColor(Color.black);
			g.setFont(new Font("Verdana", Font.BOLD, 40));
			g.drawString("INSTRUCTIONS", 550, 120);
			
			// back to menu button
			g.setFont(new Font("Gill Sans", Font.PLAIN, 30));
			rectDraw(g, g2);
			g.drawString("BACK", 65, 80);
			
			// text
			g.setColor(Color.black);
			
			g.setFont(new Font("Gill Sans", Font.PLAIN, 20));
			g.drawString("PLAYER 1 will control the left paddle.", 335, 200);
			g.drawString("PLAYER 2 will control the right paddle.", 330, 235);
			g.drawString("Use the paddles to hit a ball back and forth.", 310, 270);
			g.drawString("Players earn points when their opponent misses the ball.", 260, 305);
			g.drawString("The first player to reach 3 points wins.", 335, 340);

			g.drawString("PLAYER 1: Press 'W' to move paddle up, 'S' to move paddle down.", 210, 400);
			g.drawString("PLAYER 2: Press UP arrow key to move paddle up, DOWN arrow key to move paddle down.", 100, 435);
		}
	}

	// draw button rectangles
	public void rectDraw(Graphics g, Graphics2D g2) {
		if (mainMenu) {
			g2.setColor(startHover ? Color.getHSBColor((float)(0.92), (float)(0.50), (float)(0.72)) : Color.darkGray);
			g2.fill(start);
			g2.setColor(instructionsHover ? Color.getHSBColor((float)(0.92), (float)(0.50), (float)(0.72)) : Color.darkGray);
			g2.fill(instructions);
			g2.setColor(quitHover ? Color.getHSBColor((float)(0.92), (float)(0.50), (float)(0.72)) : Color.darkGray);
			g2.fill(quit);
			g.setColor(Color.white);
		} else {
			g2.setColor(backHover ? Color.getHSBColor((float)(0.92), (float)(0.50), (float)(0.72)) : Color.darkGray);
			g2.fill(back);
			g.setColor(Color.white);
		}
	}

	// check for pointer hover over button
	public void updateHoverState(Point pointer) {
		startHover = start.contains(pointer);
		instructionsHover = instructions.contains(pointer);
		quitHover = quit.contains(pointer);
		backHover = back.contains(pointer);
	}

}
