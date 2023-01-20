/* Anne Liu and Atisa Wang
 * January 17, 2023
 * Win
 * This class will manage the win menu of the game.
 */

import java.awt.*;

public class GameOverMenu {

    // ================================================================================
    // VARIABLES
    // ================================================================================
    public Font ps2pFont;
    public static final int GAME_WIDTH = GamePanel.GAME_WIDTH;
    public static final int GAME_HEIGHT = GamePanel.GAME_HEIGHT;
    public static final int BUTTON_WIDTH = 250;
    public static final int BUTTON_HEIGHT = 55;
    Rectangle returnMenu;
    Rectangle restart;
    boolean menuHover = false;
    boolean restartHover = false;

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public GameOverMenu() {
        ps2pFont = Resource.getResourceFont("game/PressStart2P.ttf", 20F);

        returnMenu = new Rectangle(((GAME_WIDTH - BUTTON_WIDTH)/2), 300, BUTTON_WIDTH, BUTTON_HEIGHT);
        restart = new Rectangle(((GAME_WIDTH - BUTTON_WIDTH)/2), 365, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    // ================================================================================
    // METHODS
    // ================================================================================

    // draw win message
    public void render(Graphics g, boolean win) {
        Graphics2D g2d = (Graphics2D) g;
        FontMetrics fm;
        String text;
        int x, y;

        // draw rectangles
        rectDraw(g2d);

        // player wins
        if (win) {
            g.setFont(ps2pFont.deriveFont(30F));
            fm = g.getFontMetrics(ps2pFont.deriveFont(30F));
            text = "YOU WIN!";
        }
        // player dies
        else {
            g.setFont(ps2pFont.deriveFont(25F));
            fm = g.getFontMetrics(ps2pFont.deriveFont(25F));
            text = "GAME OVER";
        }
        x = (GAME_WIDTH - fm.stringWidth(text)) / 2;
        y = 265;
        g2d.setColor(Color.white);
        g.drawString(text, x, y);

        // button text
        g.setFont(ps2pFont.deriveFont(20F));
        fm = g.getFontMetrics(ps2pFont.deriveFont(20F));

        text = "MENU";
        x = returnMenu.x + (returnMenu.width - fm.stringWidth(text)) / 2;
        y = returnMenu.y + ((returnMenu.height - fm.getHeight()) / 2) + fm.getAscent();
        g.drawString(text, x, y);

        text = "RESTART";
        x = restart.x + (restart.width - fm.stringWidth(text)) / 2;
        y = restart.y + ((restart.height - fm.getHeight()) / 2) + fm.getAscent();
        g.drawString(text, x, y);
    }

    // draw rectangles
    public void rectDraw(Graphics2D g2d) {
        // box
        g2d.setColor(Color.getHSBColor(0, 0, 0.2F));
        g2d.fillRect(((GAME_WIDTH - 400) / 2), ((GAME_HEIGHT - 225) / 2), 400, 225);
        // shadow
        g2d.setColor(Color.getHSBColor(0, 0, 0.3F));
        g2d.fillRect(((GAME_WIDTH - 400) / 2 - 10), ((GAME_HEIGHT - 225) / 2 - 10), 400, 225);
        // buttons
        Color pink = Color.getHSBColor((float) (0.92), (float) (0.50), (float) (0.72));
        g2d.setColor(menuHover ? pink : Color.darkGray);
        g2d.fill(returnMenu);
        g2d.setColor(restartHover ? pink : Color.darkGray);
        g2d.fill(restart);
    }

    // check for pointer hover over button
    public void updateHoverState(Point pointer) {
        menuHover = returnMenu.contains(pointer);
        restartHover = restart.contains(pointer);
    }

}
