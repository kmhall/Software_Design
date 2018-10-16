import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TicTacToeBoard extends JFrame {

    /**
     * TicTacToe Board Set Up
     * 0    1   2
     * 3    4   5
     * 6    7   8
     */
    private JPanel[] panelPosition;
    private JLabel[] labelPosition;


    private JButton compComp;
    private JButton compHuman;
    private JButton humanHuman;



    public TicTacToeBoard() {
        super("TicTacToe");
        setLayout(new GridLayout(4, 3, 30, 30));

        labelPosition = new JLabel[9];
        panelPosition = new JPanel[9];


        compComp = new JButton("Comp vs. Comp");
        compHuman = new JButton("Comp vs. Human");
        humanHuman = new JButton("Human vs Human");

        ButtonHandler buttonHandler = new ButtonHandler();

        compComp.addActionListener(buttonHandler);
        compHuman.addActionListener(buttonHandler);
        humanHuman.addActionListener(buttonHandler);

        add(compComp);
        add(compHuman);
        add(humanHuman);

        /*
        Add 9 Panels and Labels to the GUI as well as the corresponding Array.
         */
        for (int i = 0; i < 9; i++) {
            JPanel panel = new JPanel();
            panel.setBackground(Color.PINK);

            JLabel label = new JLabel("_");
            panel.add(label);

            //Add MouseHandler to each JPanel
            MouseHandler mouseHandler = new MouseHandler();
            panel.addMouseListener(mouseHandler);

            //Add each panel to Container for TicTacToeBoard
            add(panel);

            //Add panels and labels to corresponding Array
            panelPosition[i] = panel;
            labelPosition[i] = label;
        }
    }

    private class ButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == compComp){
                System.out.println("Starting Computer VS Computer Game");
                clearBoard();
            }
            if(e.getSource() == compHuman){
                System.out.println("Starting Computer VS Human Game");
                clearBoard();

            }
            if(e.getSource() == humanHuman){
                System.out.println("Starting Human VS Human Game");
                clearBoard();

            }

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

    private void clearBoard(){
        for(int i=0;i<9;i++){
            labelPosition[i].setText("_");
        }
    }

    private void startHumanVsHuman(){

    }
}
