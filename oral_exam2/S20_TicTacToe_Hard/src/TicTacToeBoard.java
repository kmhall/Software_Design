import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TicTacToeBoard extends JFrame {

    private final int MAX_MOVES = 9;

    private JButton compComp;
    private JButton compHuman;
    private JButton humanHuman;

    private JLabel winnerLabel;
    /**
     * TicTacToe Board Set Up
     * 0    1   2
     * 3    4   5
     * 6    7   8
     */
    private JPanel[] panelPosition;
    private JLabel[] labelPosition;


    public TicTacToeBoard() {
        super("TicTacToe");


        setLayout(new GridLayout(5, 3, 30, 30));

        labelPosition = new JLabel[9];
        panelPosition = new JPanel[9];

        compComp = new JButton("Comp vs. Comp");
        compHuman = new JButton("Comp vs. Human");
        humanHuman = new JButton("Human vs. Human");

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

        winnerLabel = new JLabel();
        add(winnerLabel);
    }

    private class ButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == compComp) {

                System.out.println("Starting Computer VS Computer Game");

                ComputerPlayer player1 = new ComputerPlayer();
                ComputerPlayer player2 = new ComputerPlayer();

                Player.setCurrentPlayers(player1,player2);

            }
            if (e.getSource() == compHuman) {

                System.out.println("Starting Computer VS Human Game");

                ComputerPlayer player1 = new ComputerPlayer();
                HumanPlayer player2 = new HumanPlayer();

                Player.setCurrentPlayers(player1,player2);
            }
            else if(e.getSource() == humanHuman){

                System.out.println("Starting Human VS Human Game");

                HumanPlayer player1 = new HumanPlayer();
                HumanPlayer player2 = new HumanPlayer();

                Player.setCurrentPlayers(player1,player2);
            }
            clearBoard();
            startGame();
        }
    }

    private void startGame(){

        while(winnerNotFound() == true){
            int moveChoice = Player.getCurrentPlayer(Player.getCurrentPlayerTurn()).move();
            if(validMove(moveChoice)){
                Player.incrementMoves();
                updateBoard(moveChoice);
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

            if(Player.getCurrentPlayerTurn() == 0){
                System.out.print("X " + index + " ");
                labelPosition[index].setText("X");
                Player.setNextTurn();
            }
            else if(Player.getCurrentPlayerTurn()  == 1) {
                System.out.print("O "+ index + " ");
                labelPosition[index].setText("O");
                Player.setNextTurn();
        }
    }

    private void clearBoard(){
        Player.resetPlayer();
        for(int i=0;i<9;i++){
            labelPosition[i].setText("_");
        }
    }

    private  class MouseHandler implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            for(int i = 0;i<9;i++){
                if(e.getSource() == panelPosition[i] && Player.getCurrentPlayerTurn() == 0){
                    labelPosition[i].setText("X");
                    Player.setNextTurn();
                }else if(e.getSource() == panelPosition[i] &&  Player.getCurrentPlayerTurn() == 1){
                    labelPosition[i].setText("O");
                    Player.setNextTurn();
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

    private Boolean winnerNotFound(){


        if(!getLabelText(0).equals("_") && getLabelText(0).equals(getLabelText(1)) && getLabelText(1).equals(getLabelText(2))){
            displayWinner(0);
            return false;
        }
        if(!getLabelText(3).equals("_") && getLabelText(3).equals(getLabelText(4)) && getLabelText(4).equals(getLabelText(5))){
            displayWinner(3);
            return false;
        }
        if(!getLabelText(6).equals("_") && getLabelText(6).equals(getLabelText(7)) && getLabelText(7).equals(getLabelText(8))){
            displayWinner(6);
            return false;
        }
        if(!getLabelText(0).equals("_") && getLabelText(0).equals(getLabelText(3)) && getLabelText(3).equals(getLabelText(6))){
            displayWinner(0);
            return false;
        }
        if(!getLabelText(1).equals("_") && getLabelText(1).equals(getLabelText(4)) && getLabelText(4).equals(getLabelText(7))){
            displayWinner(1);
            return false;
        }
        if(!getLabelText(2).equals("_") && getLabelText(2).equals(getLabelText(5)) && getLabelText(5).equals(getLabelText(8))){
            displayWinner(2);
            return false;
        }
        if(!getLabelText(0).equals("_") && getLabelText(0).equals(getLabelText(4)) && getLabelText(4).equals(getLabelText(8))){
            displayWinner(0);
            return false;
        }
        if(!getLabelText(6).equals("_") && getLabelText(6).equals(getLabelText(4)) && getLabelText(4).equals(getLabelText(2))){
            displayWinner(6);
            return false;
        }
        if(Player.getMoves() == MAX_MOVES){
            winnerLabel.setText("Cats Game");
            return false;
        }
        return true;
    }

    private void displayWinner(int index){
        if(getLabelText(index) == "X"){
            winnerLabel.setText("Player 1 Won!");
        }
        else{
            winnerLabel.setText("Player 2 Won!");
        }
    }

    public String getLabelText(int index){
        return labelPosition[index].getText();
    }

}

