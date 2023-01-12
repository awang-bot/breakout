import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

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
    public Random random;
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
        random = new Random();
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
        land.draw(g);
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
        if ((random.nextInt(7)+1) <= 6) {
            if (cactus == null) { //TODO remove later, make arraylist of cactus to have more than one at a time
                // Choose a random cactus formation
                cactus = new Cactus((random.nextInt(6)), GAME_WIDTH);
                cactus.move();
            }
        } else {
            if (bird == null) {
                bird = new Pterodactyl((random.nextInt(3)), GAME_WIDTH);
                bird.move();
            }
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
            if (dino.normal_animation.getBounds().intersects(bird.birdFlap.getBounds()) || dino.crouch_animation.getBounds().intersects(bird.birdFlap.getBounds()) || dino.getJumpBounds().intersects(bird.birdFlap.getBounds())){
//                state = DEAD_STATE;
            }
        }
        if (cactus != null){
            if (dino.normal_animation.getBounds().intersects(cactus) || dino.crouch_animation.getBounds().intersects(cactus) || dino.getJumpBounds().intersects(cactus)){
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
