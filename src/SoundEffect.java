/*
 * Anne Liu and Atisa Wang
 * January 3, 2023
 * SoundEffect
 * This class will handle the mechanics allowing sound to play in the game.
 */

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class SoundEffect {

    // ================================================================================
    // VARIABLES
    // ================================================================================
    private Clip clip;
    private static final URL[] soundURL = new URL[3];

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public SoundEffect() {
        soundURL[0] = getClass().getResource("sound/jump.wav");
        soundURL[1] = getClass().getResource("sound/dead.wav");
    }

    // ================================================================================
    // METHODS
    // ================================================================================

    /**
     * set audio file
     * @param i
     */
    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            System.out.println("Error playing sound effect.");
        }
    }

    /**
     * play the audio
     */
    public void play() {
        clip.start();
    }

}
