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

    private JPanel gameChoosePanel;
    private JPanel playGamePanel;

    private JButton compComp;
    private JButton compHuman;
    private JButton humanHuman;



    public TicTacToeBoard() {
        super("TicTacToe");
        setLayout(new GridLayout(3, 3, 30, 30));

        gameOption = new Boolean[]{false,false,false};

        labelPosition = new JLabel[9];
        panelPosition = new JPanel[9];

        for (int i = 0; i < 9; i++) {
            JPanel panel = new JPanel();
            panel.setBackground(Color.PINK);

            JLabel label = new JLabel(Integer.toString(i));
            panel.add(label);

            MouseHandler handler = new MouseHandler();
            panel.addMouseListener(handler);

            add(panel);

            panelPosition[i] = panel;
            labelPosition[i] = label;
        }
    }

    private  class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            for(int i = 0;i<9;i++){
                if(e.getSource() == panelPosition[i]){
                    labelPosition[i].setText("asdf");
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
}
