import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * <pre>  Anne and Atisa
 * January 7, 2023
 * GamePanel
 * This program will act as the main game loop</pre>
 */
public class GamePanel extends JPanel implements Runnable, KeyListener {

    // ================================================================================
    // VARIABLES
    // ================================================================================
    public static final int GAME_WIDTH = 1500;
    public static final int GAME_HEIGHT = 660;
    public static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    public static final int LAND_HEIGHT = 100; // FIXME change this later
    public Thread gameThread;
    public Image image;
    public Graphics graphics;
    public Cactus cactus;
    public Pterodactyl bird;
    public Dinosaur dino;
    public Score score;
    public Land land; 
    public int speedX;

    // TODO states...
    public int state;
    private static final int START_STATE = 0; // start button
    private static final int GAME_STATE = 1; // game is running
    private static final int DEAD_STATE = 2; // restart, return to menu?? should we have a menu?
    private static final int MENU_STATE = 4; // MAYbe..
    public boolean pause = false; // pause game during game

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public GamePanel() {
        score = new Score();
//        state = START_STATE; // TODO later
        state = GAME_STATE;
        dino = new Dinosaur();
        speedX = -5; // starting speed //TODO speedup() method
        land = new Land(); 
        cactus = null; // set to null to choose design randomly after
        bird = null;

        this.setFocusable(true);
        requestFocus();

        this.addKeyListener(this);
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mousePressedAction(e);
            }
        });

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mousePressedAction(e);
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                mouseHoverAction(e);
            }
        });

        this.setPreferredSize(SCREEN_SIZE);

        gameThread = new Thread(this);
        gameThread.start();

    }

    // ================================================================================
    // METHODS
    // ================================================================================
    public void run() {
        long lastTime = System.nanoTime();
        double ticks = 60.0;
        double nanoseconds = 1000000000 / ticks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoseconds;
            lastTime = now;
            if (delta >= 1) {
                if (state == GAME_STATE) { // TODO make switch and cases later
                    updateGame();
                }
                repaint();
                delta--;
            }
        }
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {

    	// if landWidth - land.x < GAME_WIDTH, draw another land from the start
    	if (land == null)
    	{
    		land.draw(g);
    	}
    	
    	if (land.landWidth - land.x < GAME_WIDTH)
    	{
    		land.draw(g);
    	}
    	dino.move();
        dino.draw(g);
        if (cactus != null) {
            cactus.move();
            cactus.draw(g);
        }
        if (bird != null) {
            bird.move();
            bird.draw(g);
        }
    }

    public void updateSpeedX(){

    }

    /**
     * This method handles the cactus and pterodactyl movements.
     */
   public void handleObstacle() {
        if ((int)(Math.random()*8) <= 6)
        {
            if (cactus == null)
                cactus = new Cactus((int)(Math.random()*6), GAME_WIDTH); // choose a random number from  0 to 6
            // FIXME: change the last parameter to ground.GROUND_BORDER_HEIGHT+cactus height
//            cactus.move(); // should this be called again? if it's already called in draw
        } else {
            if (bird == null)
                bird = new Pterodactyl((int)(Math.random()*3), GAME_WIDTH); //FIXME: change last param to game_width - the bird's width somehow
//            bird.move();
        }  
    }

    /**
     * Invoked when mouse button is clicked.
     */
    public void mousePressedAction(MouseEvent e) {
        // TODO pause menu
    }

    /**
     * Invoked when mouse cursor hovers.
     */
    public void mouseHoverAction(MouseEvent e) {
        // TODO button changes when hover
    }

    /**
     * Invoked when a key has been typed.
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        dino.keyPressed(e);
        // TODO press space or up_arrow to restart or resume game
    }

    /**
     * Invoked when a key has been released.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        dino.keyReleased(e);
    }

    // ================================================================================
    // HELPER METHODS
    // ================================================================================

    /**
     * Update the game while it is running.
     */
    private void updateGame() {

        checkCollision();
        handleObstacle();
        checkObstacleLeftBorder();
        score.updateScore();
        score.updateHighScore();
    }

    /**
     * check if the dinosaur collides with an obstacle
     */
    private void checkCollision() {
        // if the dino hits a cactus or a bird, then it dies
        if (bird != null) {
            if (dino.intersects(bird.birdFlap.getBounds())){
//                state = DEAD_STATE;
            }
        }
        if (cactus != null){
            if (dino.intersects(cactus)){
//                state = DEAD_STATE;
            }
        }
    }

    /**
     * This method will check if the obstacle passes the left border.
     * If so, it sets the obstacle to null.
     */
    public void checkObstacleLeftBorder() {
        if (cactus != null && cactus.x < 0)
            cactus = null; // set cactus to null
        if (bird != null && bird.x < 0)
            bird = null; // set bird to null
    }

}
