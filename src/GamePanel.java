/*
 * Anne and Atisa
 * January 7, 2023
 * GamePanel
 * This program will act as the main game loop
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	// ================================================================================
	// VARIABLES
	// ================================================================================
	public static final int GAME_WIDTH = 1500;
	public static final int GAME_HEIGHT = 660;
	public static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	public static final int LAND_HEIGHT = 380;
	public Thread gameThread;
	public Image image;
	public Graphics graphics;
	public ArrayList<Cactus> cactusArr;
	public ArrayList<Pterodactyl> birdArr;
	public Cloud cloud;
	public Dinosaur dino;
	public Score score;
	public Land land1, land2;
	public int xVelocity;
	private long previousTime1;
	private int deltaTime1;
	private long previousTime2;
	private int deltaTime2;
	public MainMenu mainMenu;
	public GameOverMenu gameOverMenu;
	public PauseMenu pauseMenu;
	public BufferedImage pauseButton;
	public boolean mute;
	public boolean win;
	public int state;
	private static final int MENU_STATE = 0; // start button
	private static final int GAME_STATE = 1; // game is running
	private static final int DEAD_STATE = 2; // dinosaur has died
	private static final int PAUSE_STATE = 4; // game is paused
//	public SoundEffect sound;

	// ================================================================================
	// CONSTRUCTOR
	// ================================================================================
	public GamePanel() {
		// set to main menu
		state = MENU_STATE;
		mainMenu = new MainMenu();
		gameOverMenu = new GameOverMenu();
		pauseMenu = new PauseMenu();
		// initialize the pause button image
		pauseButton = Resource.getResourceImage("game/pause_button.png");
		// initialize objects
		newObjects();
		// read user input
		this.setFocusable(true);
		requestFocus();
		// read keyboard input
		this.addKeyListener(this);
		// read mouse input
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
		// set dimensions
		this.setPreferredSize(SCREEN_SIZE);
		// create thread
		gameThread = new Thread(this);
		gameThread.start();

	}

	// ================================================================================
	// METHODS
	// ================================================================================
	/**
	 * allow the game to run continuously
	 */
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
				if (state != MENU_STATE && (state != PAUSE_STATE))
					updateGame();
				repaint();
				delta--;
			}
		}
	}

	/**
	 * updates what appears in the window (runs continuously in the bg)
	 */
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image, 0, 0, this);
	}

	/**
	 * draw objects onto the screen
	 */
	public void draw(Graphics g) {
		// display main menu
		if (state == MENU_STATE) {
			mainMenu.render(g);
		}
		// draw game
		else {
			// draw land
			if (land1.x > -Land.LAND_WIDTH) {
				land1.draw(g);
			}
			if (land2.x > -Land.LAND_WIDTH) {
				land2.draw(g);
			}

			if (cloud != null)
				cloud.draw(g);

			// draw score
			try {
				score.draw(g);
			} catch (Exception e) {
			}

			// draw cactus
			try {
				for (Cactus cactus : cactusArr) {
					cactus.draw(g);
				}
			} catch (Exception e) {
			}

			// draw bird
			try {
				for (Pterodactyl bird : birdArr) {
					bird.draw(g);
				}
			} catch (Exception e) {
			}

			// draw dino
			dino.draw(g);

			// draw pause button
			if (state == GAME_STATE) {
				Graphics2D g2d = (Graphics2D) g;
				g2d.drawImage(pauseButton, 50, 50, this);
			}
			// draw game over menu
			if (state == DEAD_STATE) {
				gameOverMenu.render(g, win);
			}
			if (state == PAUSE_STATE) {
				pauseMenu.render(g, mute);
			}
		}
	}

	/**
	 * Invoked when mouse button is clicked.
	 */
	public void mousePressedAction(MouseEvent e) {
		// mouse location
		int x = e.getX();
		int y = e.getY();
		// menu
		if (state == MENU_STATE) {
			if (mainMenu.mainMenu) { // main menu
				// start game
				if (mainMenu.start.contains(x, y)) {
					newObjects();
					state = GAME_STATE;
				}
				// game instructions
				if (mainMenu.instructions.contains(x, y)) {
					mainMenu.mainMenu = false;
				}
				// quit program
				if (mainMenu.quit.contains(x, y)) {
					System.exit(0);
				}
			} else { // instructions
				// return to main menu
				if (mainMenu.back.contains(x, y)) {
					mainMenu.mainMenu = true;
				}
			}
		}
		if (state == GAME_STATE) {
			Rectangle rect = new Rectangle(50, 50, 40, 40);
			if (rect.contains(x, y)) {
				state = PAUSE_STATE; // pause the game
			}
		}
		// pause menu
		if (state == PAUSE_STATE) {
			if (pauseMenu.resume.contains(x, y)) {
				state = GAME_STATE; // resume the game
			}
			if (pauseMenu.returnMenu.contains(x, y)) {
				state = MENU_STATE; // return to the menu
			}
			if (pauseMenu.mute.contains(x, y)) { // mute/unmute the game, depending on the user's input
				if (mute) {
					mute = false;
				} else {
					mute = true;
				}
			}
		}
		// game over
		if (state == DEAD_STATE) {
			// return to main menu
			if (gameOverMenu.returnMenu.contains(x, y)) {
				state = MENU_STATE;
			}
			// replay
			if (gameOverMenu.restart.contains(x, y)) {
				newObjects();
				state = GAME_STATE;
			}
		}
	}

	/**
	 * Invoked when mouse cursor hovers.
	 */
	public void mouseHoverAction(MouseEvent e) {
		mainMenu.updateHoverState(new Point(e.getX(), e.getY()));
		pauseMenu.updateHoverState(new Point(e.getX(), e.getY()));
		gameOverMenu.updateHoverState(new Point(e.getX(), e.getY()));
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
		if (state == GAME_STATE)
			;
		dino.keyPressed(e, mute);
	}

	/**
	 * Invoked when a key has been released.
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (state == GAME_STATE)
			dino.keyReleased(e);
	}

	// ================================================================================
	// HELPER METHODS
	// ================================================================================

	/**
	 * create new objects to reset the game only objects that change and move should
	 * be reset
	 */
	private void newObjects() {
		score = new Score();
		xVelocity = 10; // starting speed
		dino = new Dinosaur();
		land1 = new Land(0, xVelocity);
		land2 = new Land(Land.LAND_WIDTH, xVelocity);
		cactusArr = new ArrayList<>(); // set to null to choose design randomly later
		birdArr = new ArrayList<>();
		previousTime1 = 0;
		previousTime2 = 0;
		mute = false;
		win = false;
//        sound = new SoundEffect(); 
	}

	/**
	 * Update the game while it is running.
	 */
	private void updateGame() {
		handleObstacle();
		checkCollision();
		checkObstacleLeftBorder();
		speedUp();
		if (score.currentScore() >= 99999) { // end the game regardless when this score is reached
			win = true;
			state = DEAD_STATE;
			dino.state = Dinosaur.DEAD_STATE;
		}
		move();
		if (score.currentScore() % 50 == 0) // draw a new cloud every 50 pxls
			cloud = new Cloud();
	}

	/**
	 * allow objects in the game to move
	 */
	private void move() {
		// move dino
		if (state == PAUSE_STATE)
			dino.move();
		else
			dino.move();

		// if the game is not paused and the dinosaur is not dead, move objects
		if (state != DEAD_STATE && state != PAUSE_STATE) {
			// move cacti
			if (cloud != null)
				cloud.move();
			for (Cactus cactus : cactusArr) {
				cactus.move();
			}
			// move birds
			for (Pterodactyl bird : birdArr) {
				bird.move();
			}
			// move land
			if (land1.x > -Land.LAND_WIDTH) {
				land1.move();
			} else {
				land1.setX(Land.LAND_WIDTH);
			}
			if (land2.x > -Land.LAND_WIDTH) {
				land2.move();
			} else {
				land2.setX(Land.LAND_WIDTH);
			}
			// update the scores
			score.updateScore();
			score.updateHighScore();
		}
	}

	/**
	 * check if the dinosaur collides with an obstacle
	 */
	private void checkCollision() {
		// if the dino hits a cactus or a bird, then it dies
		for (Pterodactyl bird : birdArr) {
			if (dino.intersects(bird)) {
				dino.state = Dinosaur.DEAD_STATE;
				state = DEAD_STATE;
			}
		}
		for (Cactus cactus : cactusArr) {
			if (dino.intersects(cactus)) {
				dino.state = Dinosaur.DEAD_STATE;
				state = DEAD_STATE;
			}
		}
	}

	/**
	 * allows the game to speed up as it continues
	 */
	private void speedUp() {
		deltaTime2 = 10000;
		if (System.currentTimeMillis() - previousTime2 >= deltaTime2) {
			if (xVelocity < 20) {
				// increase GamePanel's xVelocity
				xVelocity += 1;
				// increase every object's subsequent xVelocity
				for (Cactus cactus : cactusArr) {
					cactus.setXVelocity(xVelocity);
				}
				for (Pterodactyl bird : birdArr) {
					bird.setXVelocity(xVelocity);
				}
				// use two ifs as they may be in different positions; these statements are not
				// exclusive to one another
				if (land1 != null) {
					land1.setXVelocity(xVelocity);
				}
				if (land2 != null) {
					land2.setXVelocity(xVelocity);
				}
			}
			previousTime2 = System.currentTimeMillis();
		}
	}

	/**
	 * This method handles the cacti and pterodactyls.
	 */
	private void handleObstacle() {
		deltaTime1 = (int) (Math.random() * 15000 + 2000);

		if (System.currentTimeMillis() - previousTime1 >= deltaTime1) {
			if ((int) (Math.random() * 6) <= 4) {
				// add a new cactus to the array list
				cactusArr.add(new Cactus((int) (Math.random() * 6), GAME_WIDTH, xVelocity));
			} else {
				// add a new bird to the array list
				birdArr.add(new Pterodactyl((int) (Math.random() * 3), GAME_WIDTH, xVelocity));
			}
			previousTime1 = System.currentTimeMillis();
		}
	}

	/**
	 * This method will check if the obstacle passes the left border. If so, it sets
	 * the obstacle to null to not waste unnecessary space or memory
	 */
	private void checkObstacleLeftBorder() {
		if (birdArr != null) {
			for (Cactus cactus : cactusArr) {
				if (cactus.x < -cactus.width) {
					cactus = null; // set the element to null if it is completely off the screen
				}
			}
		}
		if (birdArr != null) {
			for (Pterodactyl bird : birdArr) {
				if (bird.x < -bird.width) { // set the pterodactyl element to null if it is completely off the screen
					bird = null;
				}
			}
		}
		if (cloud != null) {
			if (cloud.x < -cloud.width) // set the cloud to null if it is completely off the screen
				cloud = null;
		}
	}

}
