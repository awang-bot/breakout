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
        this.setTitle("game name"); // change later
        this.setResizable(false);
        this.setBackground(Color.black); // change?
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        panel = new GamePanel();
        this.add(panel);
        this.pack();

        this.setVisible(true);
    }


}
