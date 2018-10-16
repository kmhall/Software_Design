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

    private int currentPlayerTurn;
    private Player[] currentPlayers;

    private JButton compComp;
    private JButton compHuman;
    private JButton humanHuman;



    public TicTacToeBoard() {
        super("TicTacToe");
        setLayout(new GridLayout(4, 3, 30, 30));

        labelPosition = new JLabel[9];
        panelPosition = new JPanel[9];

        currentPlayers = new Player[2];
        currentPlayerTurn = 0;

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
            if (e.getSource() == compComp) {

                System.out.println("Starting Computer VS Computer Game");

                ComputerPlayer player1 = new ComputerPlayer();
                ComputerPlayer player2 = new ComputerPlayer();

                startGame(player1,player2);


            }
            if (e.getSource() == compHuman) {


                System.out.println("Starting Computer VS Human Game");

                ComputerPlayer player1 = new ComputerPlayer();
                HumanPlayer player2 = new HumanPlayer();

                startGame(player1,player2);


            }
            else if(e.getSource() == humanHuman){


                System.out.println("Starting Human VS Human Game");

                HumanPlayer player1 = new HumanPlayer();
                HumanPlayer player2 = new HumanPlayer();

                startGame(player1,player2);



            }
        }
    }

    private void startGame(Player player1,Player player2){
        clearBoard();

        currentPlayers[0] = player1;
        currentPlayers[1] = player2;


        while(checkForWinner() == false){
            int index = currentPlayers[currentPlayerTurn].move();
            if(validMove(index) == true){
                updateBoard(index);
            }
        }
    }

    public Boolean validMove(int index){
        if(labelPosition[index].getText().equals("_")){
            return true;
        }
        return false;
    }

    public void updateBoard(int index){
            if(currentPlayerTurn == 0){
                System.out.print("X " + index + " ");
                labelPosition[index].setText("X");
                currentPlayerTurn = 1;
            }else if(currentPlayerTurn == 1){
                System.out.print("O "+ index + " ");
                labelPosition[index].setText("O");
                currentPlayerTurn = 0;
        }
    }



    private void clearBoard(){
        for(int i=0;i<9;i++){
            labelPosition[i].setText("_");
        }
    }

    private  class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            for(int i = 0;i<9;i++){
                if(e.getSource() == panelPosition[i] && currentPlayerTurn == 0){
                    labelPosition[i].setText("X");
                    currentPlayerTurn = 1;
                }else if(e.getSource() == panelPosition[i] && currentPlayerTurn == 1){
                    labelPosition[i].setText("O");
                    currentPlayerTurn = 0;
                }

            }
            checkForWinner();
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

    private Boolean checkForWinner(){


        if(!getLabelText(0).equals("_") && getLabelText(0).equals(getLabelText(1)) && getLabelText(1).equals(getLabelText(2))){
            System.out.println("Game Over");
            return true;
        }
        if(!getLabelText(3).equals("_") && getLabelText(3).equals(getLabelText(4)) && getLabelText(4).equals(getLabelText(5))){
            System.out.println("Game Over");
            return true;
        }
        if(!getLabelText(6).equals("_") && getLabelText(6).equals(getLabelText(7)) && getLabelText(7).equals(getLabelText(8))){
            System.out.println("Game Over");
            return true;
        }
        if(!getLabelText(0).equals("_") && getLabelText(0).equals(getLabelText(3)) && getLabelText(3).equals(getLabelText(6))){
            System.out.println("Game Over");
            return true;
        }
        if(!getLabelText(1).equals("_") && getLabelText(1).equals(getLabelText(4)) && getLabelText(4).equals(getLabelText(7))){
            System.out.println("Game Over");
            return true;
        }
        if(!getLabelText(2).equals("_") && getLabelText(2).equals(getLabelText(5)) && getLabelText(5).equals(getLabelText(8))){
            System.out.println("Game Over");
            return true;
        }
        if(!getLabelText(0).equals("_") && getLabelText(0).equals(getLabelText(4)) && getLabelText(4).equals(getLabelText(8))){
            System.out.println("Game Over");
            return true;
        }
        if(!getLabelText(6).equals("_") && getLabelText(6).equals(getLabelText(4)) && getLabelText(4).equals(getLabelText(2))){
            System.out.println("Game Over");
            return true;
        }
        return false;
    }

    public String getLabelText(int index){
        return labelPosition[index].getText();
    }

}

