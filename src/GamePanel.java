import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
    public static final int LAND_HEIGHT = 380; // FIXME change this later
    public Thread gameThread;
    public Image image;
    public Graphics graphics;
    public ArrayList<Cactus> cactusArr;
    public ArrayList<Pterodactyl> birdArr;
    public Dinosaur dino;
    public Score score;
    public Land land1, land2;
    public int xVelocity;
    private long previousTime1;
    private int deltaTime1;
    private long previousTime2;
    private int deltaTime2;

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
        xVelocity = 5; // starting speed
        score = new Score();
//        state = START_STATE; // TODO later
        state = GAME_STATE;
        dino = new Dinosaur();
        land1 = new Land(0);
        cactusArr = new ArrayList<>(); // set to null to choose design randomly after
        birdArr = new ArrayList<>();
        previousTime1 = 0;
        previousTime2 = 0;

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

    } // end of constructor

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
//                    speedUp(); (get rid of this)
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

        if (land1 != null) {
            land1.draw(g);
            if (!dino.dead)
                land1.move();
            if (land1.x <= GAME_WIDTH - Land.LAND_WIDTH) {
                land2 = new Land(GAME_WIDTH);
            }
            if (land1.x <= -Land.LAND_WIDTH) {
                land1 = null;
            }
        }
        if (land2 != null) {
            land2.draw(g);
            if (!dino.dead)
                land2.move();
            if (land2.x <= GAME_WIDTH - Land.LAND_WIDTH) {
                land1 = new Land(GAME_WIDTH);
            }
            if (land2.x <= -Land.LAND_WIDTH) {
                land2 = null;
            }
        }

        dino.draw(g);
        if (!dino.dead)
           dino.move();

        if (cactusArr != null) {
            for (Cactus cactus : cactusArr) {
                cactus.draw(g);
                if (!dino.dead)
                    cactus.move();

            }
        }
        if (birdArr != null) {
            for (Pterodactyl bird : birdArr) {
                bird.draw(g);
                if (!dino.dead)
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
        // TODO press space or up_arrow or enter to restart or resume game
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
        if (score.currentScore()%10 == 0) // speed up when the points are divisible by 10
        	speedUp();
    }

    /**
     * check if the dinosaur collides with an obstacle
     */
    private void checkCollision() {
        // if the dino hits a cactus or a bird, then it dies
        if (birdArr != null) {
            for (Pterodactyl bird : birdArr) {
                if (dino.intersects(bird)) {
                    dino.setDinoDead();
                   state = DEAD_STATE;
                }
            }
        }
        if (cactusArr != null) {
            for (Cactus cactus : cactusArr) {
                if (dino.intersects(cactus)) {
                    dino.setDinoDead();
                    state = DEAD_STATE;
                }
            }
        }
    }

    private void speedUp() {
        deltaTime2 = 100000000;
        if (System.currentTimeMillis() - previousTime2 >= deltaTime2) {
            if (xVelocity < 15) {
                xVelocity++;
                for (Cactus cactus : cactusArr) {
                    cactus.setXVelocity(xVelocity);
                }
                for (Pterodactyl bird : birdArr) {
                    bird.setXVelocity(xVelocity);
                }
                if (land1 != null) {
                    land1.setXVelocity(xVelocity);
                }
                if (land2 != null) {
                    land2.setXVelocity(xVelocity);
//                    dino.setDinoDead();
//                    state = DEAD_STATE;
                }
            }
        }
    }

    /**
     * This method handles the cactus and pterodactyl movements.
     */
    private void handleObstacle() {
        deltaTime1 = (int) (Math.random() * 15000 + 2000);

        if (System.currentTimeMillis() - previousTime1 >= deltaTime1) {
            if ((int) (Math.random() * 8) <= 6) {
                cactusArr.add(new Cactus((int) (Math.random() * 6), GAME_WIDTH));
            } else {
                birdArr.add(new Pterodactyl((int) (Math.random() * 3), GAME_WIDTH));
            }
            previousTime1 = System.currentTimeMillis();
        }
    }

    /**
     * This method will check if the obstacle passes the left border.
     * If so, it sets the obstacle to null.
     */
    private void checkObstacleLeftBorder() {
        if (birdArr != null) {
            for (Cactus cactus : cactusArr) {
                if (cactus.x < -cactus.width) ;
                cactus = null;
            }
        }
        if (birdArr != null) {
            for (Pterodactyl bird : birdArr) {
                if (bird.x < -bird.width) ;
                bird = null;
            }
        }
    }

}
