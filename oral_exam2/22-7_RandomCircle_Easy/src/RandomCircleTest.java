import javax.swing.*;
import java.awt.*;

/**
 * RandomCircleTest is a Class that contains the main method where the JFrame can be viewed.
 */
public class RandomCircleTest {

    /**
     *Main method of RandomCircleTest. Creates an instance of a RandomCircleFrame, sets close operation,
     * sets size of the window, and sets the visibility.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        RandomCircleFrame frame = new RandomCircleFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setMinimumSize(new Dimension(200,300));
        frame.setVisible(true);
    }
}


