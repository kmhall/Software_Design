import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MazeTraversalGUI extends JFrame {

    private final JTextArea mapDisplay;

    private final JButton startButton;

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
                }
        );

        add(startButton);
        add(mapDisplay);

    }


    public static void main(String[] args) {

        MazeTraversalGUI application = new MazeTraversalGUI();

        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.setSize(300,400);
        application.setVisible(true);

    }


}
