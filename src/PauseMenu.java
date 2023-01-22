/*
 * Anne and Atisa
 * January 19, 2023
 * PauseMenu
 * This class will allow the user to pause their game.
 */
import java.awt.*;

public class PauseMenu {  // pause button is on

    // ================================================================================
    // VARIABLES
    // ================================================================================
    public Font ps2pFont;
    public static final int GAME_WIDTH = GamePanel.GAME_WIDTH;
    public static final int GAME_HEIGHT = GamePanel.GAME_HEIGHT;
    public static final int BUTTON_WIDTH = 250;
    public static final int BUTTON_HEIGHT = 55;
    public Rectangle resume;
    public Rectangle mute;
    public Rectangle returnMenu;
    private boolean resumeHover = false;
    private boolean muteHover = false;
    private boolean menuHover = false;

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public PauseMenu() {
        ps2pFont = Resource.getResourceFont("game/PressStart2P.ttf", 20F);

        // initialize all the rectangles that will appear as buttons
        resume = new Rectangle(((GAME_WIDTH - BUTTON_WIDTH) / 2), 260, BUTTON_WIDTH, BUTTON_HEIGHT);
        mute = new Rectangle(((GAME_WIDTH - BUTTON_WIDTH) / 2), 325, BUTTON_WIDTH, BUTTON_HEIGHT);
        returnMenu = new Rectangle(((GAME_WIDTH - BUTTON_WIDTH) / 2), 390, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    // ================================================================================
    // METHODS
    // ================================================================================

    // draw win message
    public void render(Graphics g, boolean muted) {
        Graphics2D g2d = (Graphics2D) g;
        FontMetrics fm;
        String text;
        int x, y;

        // draw rectangles
        rectDraw(g2d);

        // pause title
        g.setFont(ps2pFont.deriveFont(25F));
        fm = g.getFontMetrics(ps2pFont.deriveFont(25F));
        text = "GAME PAUSED";
        x = (GAME_WIDTH - fm.stringWidth(text)) / 2;
        y = 235;
        g2d.setColor(Color.white);
        g.drawString(text, x, y);

        // button text
        g.setFont(ps2pFont.deriveFont(20F));
        fm = g.getFontMetrics(ps2pFont.deriveFont(20F));

        text = "RESUME";
        x = resume.x + (resume.width - fm.stringWidth(text)) / 2;
        y = resume.y + ((resume.height - fm.getHeight()) / 2) + fm.getAscent();
        g.drawString(text, x, y);

        if (muted){
            text = "UNMUTE";
        } else {
            text = "MUTE";
        }
        x = mute.x + (mute.width - fm.stringWidth(text)) / 2;
        y = mute.y + ((mute.height - fm.getHeight()) / 2) + fm.getAscent();
        g.drawString(text, x, y);

        text = "MENU";
        x = returnMenu.x + (returnMenu.width - fm.stringWidth(text)) / 2;
        y = returnMenu.y + ((returnMenu.height - fm.getHeight()) / 2) + fm.getAscent();
        g.drawString(text, x, y);
    }

    // draw rectangles
    public void rectDraw(Graphics2D g2d) {
        // box
        g2d.setColor(Color.getHSBColor(0, 0, 0.2F));
        g2d.fillRect(((GAME_WIDTH - 400) / 2), ((GAME_HEIGHT - 300) / 2), 400, 300);
        // shadow
        g2d.setColor(Color.getHSBColor(0, 0, 0.3F));
        g2d.fillRect(((GAME_WIDTH - 400) / 2 - 10), ((GAME_HEIGHT - 300) / 2 - 10), 400, 300);
        // buttons
        Color pink = Color.getHSBColor((float) (0.92), (float) (0.50), (float) (0.72));
        g2d.setColor(resumeHover ? pink : Color.darkGray);
        g2d.fill(resume);
        g2d.setColor(muteHover ? pink : Color.darkGray);
        g2d.fill(mute);
        g2d.setColor(menuHover ? pink : Color.darkGray);
        g2d.fill(returnMenu);
    }

    // check for pointer hover over button
    public void updateHoverState(Point pointer) {
        resumeHover = resume.contains(pointer);
        muteHover = mute.contains(pointer);
        menuHover = returnMenu.contains(pointer);
    }
}
