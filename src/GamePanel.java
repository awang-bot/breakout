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
    public Thread gameThread;
    public Image image;
    public Graphics graphics;
    public boolean running;
    public Cactus cactus;
    public Pterodactyl bird;
    public Dinosaur dino;
    public Score score;
    public int xSpeed; // TODO connect speed of ground, cactus, and pterodactyl
    // TODO game speeds up over time

    // TODO states...
    public int state;
    private static final int START_STATE = 0; // start button
    private static final int GAME_STATE = 1; // game is running
    private static final int DEAD_STATE = 2; // restart, return to menu?? should we have a menu?
    private static final int MENU_STATE = 4; // MAYbe..
    public boolean pause = false; // pause game during game
    public boolean dead = false;

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public GamePanel() {
//		cactus = new Cactus();
//		bird = new Pterodactyl();
		running = true;
		cactus = null;
		bird = null;
		dino = new Dinosaur();
    	
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
    public void run(){
        long lastTime = System.nanoTime();
        double ticks = 60.0;
        double nanoseconds = 1000000000 / ticks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoseconds;
            lastTime = now;
            if (delta >= 1) {
                if (running) {
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

        dino.draw(g);
        if (cactus != null)
            cactus.draw(g);
        if (bird != null)
            bird.draw(g);
        if (dead)
        {
        	g.setColor(Color.black);
            g.fillRect(0, 0, 500, 500);
        }

        // in the if-statement, create a new cactus/pterodactyl object (not globally!)

//    	// FIXME maybe this can be a different method in gamepanel? for efficiency
//    	// generate a random integer to choose a random obstacle
//    	if (randomInt(0, 8)<=6) // if the number is from 0 to 6, create a cactus
//    	{
//    		cactus = new Cactus(randomInt(1, 6), GAME_WIDTH, 100); // choose a random number from 1 to 6
//    		// FIXME: change the last parameter to ground.GROUND_BORDER_HEIGHT+cactus height
//    		cactus.draw(g);
////    		cactus.move();
//    	}
//    	else // otherwise, create a pterodactyl
//    	{
//    		bird = new Pterodactyl(randomInt(1,3), 500); //FIXME: change last param to game_width - the bird's width somehow
//    		while (bird.x >0) {
//        		bird.draw(g);
////    			bird.move();
//    		}
//    	}

        /*
        // display start button
        if (displayStart) {
            start.render(g);
        }
        // display game
        if (displayGame) {
            paddle1.draw(g);
            paddle2.draw(g);
            ball.draw(g);
            score.draw(g);
        }
        // display restart button
        if (displayWin) {
            win.render(g);
        }
        // display pause menu
        if (pauseMenu) {
            pause.render(g)
        }
        */
    }

    /**
     * This method will check if the obstacle passes the left border.
     * If so, it sets the obstacle to null.
     */
    public void checkObstacleLeftBorder()
    {
    	if(cactus !=null && cactus.x < 0)
    		cactus = null; // set cactus to null
    	if(bird !=null && bird.x < 0)
    		bird = null; // set bird to null
    }


    /**
     * Update the game while it is running.
     */
    private void updateGame(){
        // TODO update object positions
//    	if (!dinoOutOfBound())
//    		dino.move();
    	dino.midJump();
        checkCollision();
        checkObstacleLeftBorder();
        handleObstacle();
//        score.updateScore();
//        score.updateHighScore();
    }


    /**
     * This method handles the cactus and pterodactyl movements.
     */
    public void handleObstacle()
    {
    	// FIXME maybe this can be a different method in gamepanel? for efficiency
    	// generate a random integer to choose a random obstacle
    	if (randomInt(0, 8)<=6) // if the number is from 0 to 6, create a cactus
    	{
    		if (cactus == null)
    			cactus = new Cactus(randomInt(1, 6), GAME_WIDTH, 100); // choose a random number from 1 to 6
    		// FIXME: change the last parameter to ground.GROUND_BORDER_HEIGHT+cactus height
//    		cactus.draw(g);
    		cactus.move();
    	}
    	else // otherwise, create a pterodactyl
    	{
    		if (bird == null)
    			bird = new Pterodactyl(randomInt(1,3), 500); //FIXME: change last param to game_width - the bird's width somehow
//        	bird.draw(g);
    		bird.move();

    	}
    }

    /**
     *  helper method to determine a random integer for an obstacle
     */
    public int randomInt(int add, int multiplier)
    {
    	// get a random integer from
    	// 0 to 6 are cacti, 7 is for pterodactyl
    	return (int)(Math.random()*multiplier);
    }

    /**
     * check if the dinosaur collides with an obstacle
     */
    private void checkCollision(){
    	
    	// if the dino crouches, runs, or jumps and hits a cactus or a bird, then it dies
    	if (dino.crouch_animation.getBounds().intersects(cactus) || dino.normal_animation.getBounds().intersects(cactus) || dino.normal_animation.getBounds().intersects(bird.birdFlap.getBounds()) || dino.crouch_animation.getBounds().intersects(bird.birdFlap.getBounds()) || dino.getJumpBounds().intersects(bird.birdFlap.getBounds()) || dino.getJumpBounds().intersects(cactus));
    	{
    		dead = true;
    	}
    }


    /**
     * Invoked when mouse button is clicked.
     */
    public void mousePressedAction(MouseEvent e){

    }

    /**
     * Invoked when mouse cursor hovers.
     */
    public void mouseHoverAction(MouseEvent e){

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
        // TODO press space or up_arrow to jump
   		dino.keyPressed(e);
        // TODO press down_arrow to go down or crouch
        // TODO press space or up_arrow to restart or
    }

    /**
     * Invoked when a key has been released.
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }

}
