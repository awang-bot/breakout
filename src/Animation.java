import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Anne Liu and Atisa Wang
 * January 7, 2022
 * Animation
 * This program will handle the animations of the obstacles and dinosaurs.
 * </pre>
 */
public class Animation {

    // TODO figure out how all this works

    // ================================================================================
    // VARIABLES
    // ================================================================================

    /**
     * Stores the key frames of the animation.
     */
    private List<BufferedImage> keyFrames;
    private long deltaTime;
    private int currentFrame = 0;
    private long previousTime;

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public Animation(int deltaTime) {
        this.deltaTime = deltaTime;
        keyFrames = new ArrayList<BufferedImage>();
        previousTime = 0;
    }

    // ================================================================================
    // METHODS
    // ================================================================================
    public void updateFrame() {
        if (System.currentTimeMillis() - previousTime >= deltaTime) {
            currentFrame++;
            if (currentFrame >= keyFrames.size()) {
                currentFrame = 0;
            }
            previousTime = System.currentTimeMillis();
        }
    }

    public void addFrame(BufferedImage image) {
        keyFrames.add(image);
    }

    public BufferedImage getFrame() {
        return keyFrames.get(currentFrame);
    }

    public Rectangle getBounds(){
        Rectangle rect = new Rectangle();
        rect.x = (int) getFrame().getTileGridXOffset();
        rect.y = (int) getFrame().getTileGridYOffset();
        rect.width = getFrame().getWidth();
        rect.height = getFrame().getHeight();
        return rect;
    }

}