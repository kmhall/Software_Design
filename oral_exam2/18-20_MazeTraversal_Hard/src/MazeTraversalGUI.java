import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The MazeTraversalGUI class represents the GUI of a MazeTraversal.
 * @see JFrame
 */
public class MazeTraversalGUI extends JFrame {


    private final JTextArea mapDisplay;

    private final JButton startButton;

    /**
     * Constructor of MazeTraversalGUI. Sets layout, creates JTextArea and JButton,
     * adds ActionListener to button. When the button is clicked execute() of SwingWorker,
     * is called and the background process begins.
     */
    public MazeTraversalGUI(){
        super("Maze Traversal");
        setLayout(new GridLayout(2,1));

        startButton = new JButton("Start");
        mapDisplay = new JTextArea();
        mapDisplay.setFont(new Font("monospaced",Font.PLAIN,12));

        startButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        MazeTraversal task =
                                new MazeTraversal(mapDisplay);
                        task.execute(); // execute the task
                    }
                });

        add(startButton);
        add(mapDisplay);

    }


    /**
     * Main method of MazeTraversalGUI. Creates an instance of a MazeTraversalGUI, sets close operation,
     * sets size of the window, and sets the visibility.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        MazeTraversalGUI application = new MazeTraversalGUI();

        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.setSize(300,400);
        application.setVisible(true);

    }


}
