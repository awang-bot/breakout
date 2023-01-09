import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    // ================================================================================
    // VARIABLES
    // ================================================================================
    GamePanel panel;

    // ================================================================================
    // CONSTRUCTOR
    // ================================================================================
    public GameFrame() {
        this.setTitle("Dinosaur Game"); // change later
        this.setResizable(false);
        this.setBackground(Color.white);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new GamePanel();
        this.add(panel);
        this.pack();

        this.setVisible(true);
		this.setLocationRelativeTo(null);// set window in middle of screen

    }


}
