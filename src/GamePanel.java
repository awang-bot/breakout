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
    Thread gameThread;
    Image image;
    Graphics graphics;
    boolean running;

    Score score;

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public GamePanel() {
        // TODO initialize objects either in constructor or with a method

        // enable user input
        this.setFocusable(true);
        requestFocus();

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

        // thread
        gameThread = new Thread(this);
        gameThread.start();

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

    /**
     * Update the game while it is running.
     */
    private void updateGame(){
        // TODO update object positions
        checkCollision();
        score.updateScore();
        score.updateHighScore();
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        // TODO GamePanel draw()
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

    private void checkCollision(){

    }

    /**
     * Invoked when mouse button is clicked.
     * @param e the event to be processed
     */
    public void mousePressedAction(MouseEvent e){

    }

    /**
     * Invoked when mouse cursor hovers.
     * @param e the event to be processed
     */
    public void mouseHoverAction(MouseEvent e){

    }

    /**
     * Invoked when a key has been typed.
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO press space or up_arrow to jump
        // TODO press down_arrow to go down or crouch
        // TODO press space or up_arrow to restart or
    }

    /**
     * Invoked when a key has been released.
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }

}
