import java.awt.*;

public class MainMenu {

	// ================================================================================
	// VARIABLES
	// ================================================================================
	public Font ps2pFont;
	public static final int GAME_WIDTH = GamePanel.GAME_WIDTH;
	public static final int BUTTON_WIDTH = 475;
	public static final int BUTTON_HEIGHT = 75;
	public Rectangle start;
	public Rectangle instructions;
	public Rectangle quit;
	public Rectangle back;
	boolean mainMenu = true;
	boolean startHover = false;
	boolean instructionsHover = false;
	boolean quitHover = false;
	boolean backHover = false;

	// ================================================================================
	// CONSTRUCTOR
	// ================================================================================
	public MainMenu() {
		ps2pFont = Resource.getResourceFont("game/PressStart2P.ttf", 20F);

		start = new Rectangle(((GAME_WIDTH - BUTTON_WIDTH)/2), 250, BUTTON_WIDTH, BUTTON_HEIGHT);
		instructions = new Rectangle(((GAME_WIDTH - BUTTON_WIDTH)/2), 350, BUTTON_WIDTH, BUTTON_HEIGHT);
		quit = new Rectangle(((GAME_WIDTH - BUTTON_WIDTH)/2), 450, BUTTON_WIDTH, BUTTON_HEIGHT);
		back = new Rectangle(50, 50, 180, 80);
	}

	// ================================================================================
	// METHODS
	// ================================================================================

	// render menu
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		FontMetrics fm;

		// main menu
		if (mainMenu) {

			// game title
			fm = g.getFontMetrics(ps2pFont.deriveFont(60F));
			String text = "DINOSAUR GAME";
			int x = (GAME_WIDTH - fm.stringWidth(text)) / 2;
			int y = 175;
			g.setFont(ps2pFont.deriveFont(60F));
			g.setColor(Color.darkGray);
			g.drawString(text, x, y);
			
			// buttons
			rectDraw(g2d);

			// button text
			fm = g.getFontMetrics(ps2pFont.deriveFont(30F));
			g.setFont(ps2pFont.deriveFont(30F));
			g.setColor(Color.white);

			text = "START";
			x = start.x + (start.width - fm.stringWidth(text)) / 2;
			y = start.y + ((start.height - fm.getHeight()) / 2) + fm.getAscent();
			g.drawString(text, x, y);

			text = "INSTRUCTIONS";
			x = instructions.x + (instructions.width - fm.stringWidth(text)) / 2;
			y = instructions.y + ((instructions.height - fm.getHeight()) / 2) + fm.getAscent();
			g.drawString(text, x, y);

			text = "QUIT";
			x = quit.x + (quit.width - fm.stringWidth(text)) / 2;
			y = quit.y + ((quit.height - fm.getHeight()) / 2) + fm.getAscent();
			g.drawString(text, x, y);
		}
		// display instructions
		else {
			g.setColor(Color.darkGray);

			// instructions title
			g.setFont(ps2pFont.deriveFont(60F));
			fm = g.getFontMetrics(ps2pFont.deriveFont(60F));
			String text = "INSTRUCTIONS";
			int x = (GAME_WIDTH - fm.stringWidth(text)) / 2;
			int y = 230;
			g.drawString(text, x, y);

			// back to menu button
			rectDraw(g2d);

			// back to menu text
			fm = g.getFontMetrics(ps2pFont.deriveFont(30F));
			g.setFont(ps2pFont.deriveFont(30F));
			g.setColor(Color.white);

			text = "BACK";
			x = back.x + (back.width - fm.stringWidth(text)) / 2;
			y = back.y + ((back.height - fm.getHeight()) / 2) + fm.getAscent();
			g.drawString(text, x, y);
			
			// text
			fm = g.getFontMetrics(ps2pFont.deriveFont(20F));
			g.setFont(ps2pFont.deriveFont(20F));
			g.setColor(Color.darkGray);

			text = "Avoid obstacles such as cacti and pterodactyls.";
			x = (GAME_WIDTH - fm.stringWidth(text)) / 2;
			y = 300;
			g.drawString(text, x, y);

			text = "Press SPACE or the UP ARROW to jump over obstacles.";
			x = (GAME_WIDTH - fm.stringWidth(text)) / 2;
			y = 350;
			g.drawString(text, x, y);

			text = "Press the DOWN ARROW to duck under obstacles.";
			x = (GAME_WIDTH - fm.stringWidth(text)) / 2;
			y = 400;
			g.drawString(text, x, y);

			text = "Players are able to win the game by reaching a score of 99999.";
			x = (GAME_WIDTH - fm.stringWidth(text)) / 2;
			y = 450;
			g.drawString(text, x, y);
		}
	}

	// draw button rectangles
	public void rectDraw(Graphics2D g2) {
		Color pink = Color.getHSBColor((float)(0.92), (float)(0.50), (float)(0.72));
		if (mainMenu) {
			g2.setColor(startHover ? pink : Color.darkGray);
			g2.fill(start);
			g2.setColor(instructionsHover ? pink : Color.darkGray);
			g2.fill(instructions);
			g2.setColor(quitHover ? pink : Color.darkGray);
			g2.fill(quit);
		} else {
			g2.setColor(backHover ? pink : Color.darkGray);
			g2.fill(back);
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
