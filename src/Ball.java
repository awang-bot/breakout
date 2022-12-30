// Anne and Atisa
// December 29, 2022
// Ball
// This class will manage the properties and behaviours of the ball.

import java.awt.*;
import java.awt.event.*;

public class Ball extends Rectangle {
  
  // variable declaration
	public int xVelocity, yVelocity;
	public static final int SPEED = 4; // movement speed of ball
	public static final int BALL_DIAMETER = 20; // size of ball

	// constructor creates ball at given location with given dimensions. also initializes velocities
	public RandomBall(int x, int y) {
		super(x, y, BALL_DIAMETER, BALL_DIAMETER);
		xVelocity = 0;
		yVelocity = 0;
	} // end of constructor

	// when a key is pressed, call on move
	public void keyPressed(KeyEvent e) {
		move();
	} // end of keyPressed

}
