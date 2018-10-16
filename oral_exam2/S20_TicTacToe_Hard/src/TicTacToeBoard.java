import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TicTacToeBoard extends JFrame {

    /**
     * Game begins when one of the game options are True.
     * [False,False,False]
     * [Computer vs. Computer, Computer vs. Human, Human vs. Human]
     * [True,False,False]
     */
    private Boolean[] gameOption;

    /**
     * TicTacToe Board Set Up
     * 0    1   2
     * 3    4   5
     * 6    7   8
     */
    private JPanel[] panelPosition;
    private JLabel[] labelPosition;

    private Boolean gameComplete;

    private JButton compComp;
    private JButton compHuman;
    private JButton humanHuman;



    public TicTacToeBoard() {
        super("TicTacToe");
        setLayout(new GridLayout(3, 4, 30, 30));

        gameComplete = false;
        gameOption = new Boolean[]{false,false,false};
        labelPosition = new JLabel[9];
        panelPosition = new JPanel[9];



        /*
        Add 9 Panels and Labels to the GUI as well as the corresponding Array.
         */
        for (int i = 0; i < 9; i++) {
            JPanel panel = new JPanel();
            panel.setBackground(Color.PINK);

            JLabel label = new JLabel("_");
            panel.add(label);

            //Add MouseHandler to each JPanel
            MouseHandler handler = new MouseHandler();
            panel.addMouseListener(handler);

            //Add each panel to Container for TicTacToeBoard
            add(panel);

            //Add panels and labels to corresponding Array
            panelPosition[i] = panel;
            labelPosition[i] = label;
        }
    }
    private  class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            for(int i = 0;i<9;i++){
                if(e.getSource() == panelPosition[i]){
                    labelPosition[i].setText("X");
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public void startHumanVsHuman(){

    }
}
