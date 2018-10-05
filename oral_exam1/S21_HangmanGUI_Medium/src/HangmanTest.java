import javax.swing.*;

/**
 * Hangman driver class
 */
public class HangmanTest {

    /**
     * Main method for driver class
     * @param args Arguments for main method
     */
    public static void main(String[] args){
        HangmanFrame frame = new HangmanFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setVisible(true);
    }
}
