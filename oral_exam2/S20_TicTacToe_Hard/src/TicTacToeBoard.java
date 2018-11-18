import java.util.Scanner;

/**
 * TicTaToeBoard is a class that represents a tic tac toe board. This is where the board is created, players are added,
 * and validation is done.
 */
public class TicTacToeBoard {

    private Boolean gameOver;

    private String[] board;
    String playerChoice;

    private Player Player1;
    private Player Player2;


    /**
     * Constructs a TicTacToeBoard and validates the type of players.
     */
    public TicTacToeBoard(){

        board = new String[]{"_","_","_","_","_","_","_","_","_"};

        //Validate input of game type
        Scanner scanner = new Scanner(System.in);
        gameOver = false;
        do {
            System.out.print("Enter code to start game: \n Human vs. Human: 'HH' \n Human vs Computer: 'HC' \n Computer vs Computer: 'CC'\n");
            playerChoice = scanner.next();

        }while(!validPlayerChoiceAndSetPlayers());
    }

    /**
     * Tests for valid player choice and sets players accordingly.
     * @return Boolean, if the player choice is valid
     */
    private Boolean validPlayerChoiceAndSetPlayers(){
        if(playerChoice.toUpperCase().matches("HH")){
            Player1 = new HumanPlayer();
            Player2 = new HumanPlayer();
            return true;
        }
        if(playerChoice.toUpperCase().matches("HC")){
            Player1 = new HumanPlayer();
            Player2 = new ComputerPlayer();
            return true;
        }
        if(playerChoice.toUpperCase().matches("CC")){
            Player1 = new ComputerPlayer();
            Player2 = new ComputerPlayer();
            return true;
        }
        return false;
    }

    /**
     * This is the running method of the game. All base logic of the game is done within this method.
     */
    public void startGame(){

        updateDisplay();

        int player1Move = -1;
        int player2Move = -1;

        do{
            do{
                 player1Move = Player1.move();

            }while(!validateMove(player1Move));

            board[player1Move] = "X";
            Player.incrementMoves();
            updateDisplay();

            if(gameInProgress()){
                do{
                    player2Move = Player2.move();
                }while (!validateMove(player2Move));
                board[player2Move] = "O";
                Player.incrementMoves();
                updateDisplay();
            }
        }while (gameInProgress()); //Check for winner
    }


    /**
     * Checks to see if the next attempted move is a valid one.
     * @param move Attempted next move
     * @return Boolean: If the attempted move is valid
     */
    public Boolean validateMove(int move){
        if(move<0 || move > 8){
            return false;
        }
        if(board[move].equals("_")){
            return true;
        }
        return false;
    }

    /**
     * Checks to see if the current game state is a winning game state.
     * @return Boolean, If the game is in progress or over
     */
    public Boolean gameInProgress(){

        if(gameOver == false) {
            if (!board[0].equals("_") && board[0].equals(board[1]) && board[1].equals(board[2])) {
                displayWinner(0);
                gameOver = true;

                return false;
            }
            if (!board[3].equals("_") && board[3].equals(board[4]) && board[4].equals(board[5])) {
                displayWinner(3);
                gameOver = true;

                return false;
            }
            if (!board[6].equals("_") && board[6].equals(board[7]) && board[7].equals(board[8])) {
                displayWinner(6);
                gameOver = true;

                return false;
            }
            if (!board[0].equals("_") && board[0].equals(board[3]) && board[3].equals(board[6])) {
                displayWinner(0);
                gameOver = true;

                return false;
            }
            if (!board[1].equals("_") && board[1].equals(board[4]) && board[4].equals(board[7])) {
                displayWinner(1);
                gameOver = true;

                return false;
            }
            if (!board[2].equals("_") && board[2].equals(board[5]) && board[5].equals(board[8])) {
                displayWinner(2);
                gameOver = true;

                return false;
            }
            if (!board[0].equals("_") && board[0].equals(board[4]) && board[4].equals(board[8])) {
                displayWinner(0);
                gameOver = true;

                return false;
            }
            if (!board[6].equals("_") && board[6].equals(board[4]) && board[4].equals(board[2])) {
                displayWinner(6);
                gameOver = true;

                return false;
            }

            if (Player.getMoves() == Player.getMAX_MOVES()) {
                gameOver = true;

                System.out.println("Cats Game");
                return false;
            }
        }
        if(gameOver == true){
            return false;
        }
        return true;

    }

    /**
     * Displays which player won to the console.
     * @param index A position of the current winner's line.
     */
    private void displayWinner(int index){
        if(board[index].equals("X")){
           System.out.println("Player 1 Won!");
        }
        else{
            System.out.println("Player 2 Won!");
        }
    }

    /**
     * Updates the display by printing the current board.
     */
    public void updateDisplay(){
        System.out.println(this.toString());
    }

    /**
     * Converts the current board to a string.
     * @return String, the current board as a string.
     */
    @Override
    public String toString() {
        String outputString = "";

        outputString += board[0] + " " + board[1] + " " + board[2] + "\n";
        outputString += board[3] + " " + board[4] + " " + board[5] + "\n";
        outputString += board[6] + " " + board[7] + " " + board[8] + "\n";

        return outputString;
    }
}
