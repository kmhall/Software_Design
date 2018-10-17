public abstract class Player {

    private static int currentPlayerTurn;
    private static Player[] currentPlayers;

    private static int moves;


    public Player(){
        currentPlayers = new Player[2];
        currentPlayerTurn = 0;
        moves = 0;
    }

    public static void resetPlayer(){
        moves = 0;
        currentPlayerTurn = 0;
    }

    public static void setCurrentPlayers(Player player1, Player player2) {
        currentPlayers[0] = player1;
        currentPlayers[1] = player2;
    }

    public static void setNextTurn() {
        if(currentPlayerTurn == 0){
            currentPlayerTurn = 1;
        }
        else{
            currentPlayerTurn = 0;
        }
    }

    public static int getCurrentPlayerTurn() {
        return currentPlayerTurn;
    }

    public static Player getCurrentPlayer(int index) {
        return currentPlayers[index];
    }

    public static void incrementMoves() {
        moves++;
    }

    public static int getMoves() {
        return moves;
    }

    public abstract int move();
}
