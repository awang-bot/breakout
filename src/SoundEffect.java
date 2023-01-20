import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class SoundEffect {

    // ================================================================================
    // VARIABLES
    // ================================================================================
    Clip clip;
    URL[] soundURL = new URL[3];

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

    // set audio file
    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            System.out.println("Error playing sound effect.");
        }
    }

    // play audio
    public void play() {
        clip.start();
    }

}
