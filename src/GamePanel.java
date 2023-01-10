import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    // ================================================================================
    // VARIABLES
    // ================================================================================
//    public static final int GAME_WIDTH = 526, GAME_HEIGHT = 150;
  public static final int GAME_WIDTH = 1500, GAME_HEIGHT = 660;

	public Thread gameThread;
    public Image image;
    public Graphics graphics;
    public boolean running;
    public Cactus cactus;
    public Pterodactyl bird;
    public Dinosaur dino;
   

    Score score;

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public GamePanel() {
        // TODO initialize objects either in constructor or with a method
//		cactus = new Cactus();
//		bird = new Pterodactyl();
		running = true; // TODO ????
		cactus = null;
		bird = null;
		dino = new Dinosaur();

        // enable user input
        this.setFocusable(true); // allow the focus to be on the game screen
        requestFocus(); // set the focus on the game screen

        // check for key input
        this.addKeyListener(this);
        // check for mouse click
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mousePressedAction(e);
            }
        });

        // check for mouse hover
        addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent e) {
                mouseHoverAction(e);
            }
        });

        // TODO add this.setPreferredSize(SCREEN_SIZE);
		this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));

        // thread
        gameThread = new Thread(this);
        gameThread.start(); // call run()

    }

    // ================================================================================
    // METHODS
    // ================================================================================
    public void run(){
        long lastTime = System.nanoTime();
        double ticks = 60.0;
        double nanoseconds = 1000000000 / ticks; // 1000000000 ns = 1 sec, so the game will update 60 times per second
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoseconds; // accumulate the time passed
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
        // TODO GamePanel draw()
        dino.draw(g);
        if (cactus != null)
            cactus.draw(g);
        if (bird != null)
            bird.draw(g);

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


    private void checkCollision(){
    	
    	
    	
    }
    
//	/**
// 	 * check the y-boundary of the dinosaur - it cannot be more than the height of the ground
// 	 * this is not acc in use so double check if necessary
// 	 */
// 	public boolean dinoOutOfBound()
// 	{
// 		if (dino.y > 500) //FIXME: change it to less than the ground height
// 			return true;
// 		return false;
// 	}

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
    	if (!dino.continueJump)
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
