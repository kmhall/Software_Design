import javax.swing.*;

public class TicTacToeBoardTest {
    /**
     * Main method for driver class
     * @param args Arguments for main method
     */
    public static void main(String[] args){
        TicTacToeBoard frame = new TicTacToeBoard();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setVisible(true);

    }
}
