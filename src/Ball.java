import java.awt.*;

public class Ball extends Rectangle {

    // VARIABLES
    public static final int BALL_DIAMETER = 20;
    public static final int SPEED = 4;
    public int xVelocity, yVelocity;

    // CONSTRUCTOR
    public Ball(int x, int y) {
        super(x, y, BALL_DIAMETER, BALL_DIAMETER);
        xVelocity = 0;
        yVelocity = 0;
    }

    // METHODS
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, BALL_DIAMETER, BALL_DIAMETER);
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;
    }

    public void setYDirection(int yDirection) {
        yVelocity = yDirection;
    }

    public void setXDirection(int xDirection) {
        xVelocity = xDirection;
    }

}
