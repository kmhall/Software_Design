/**
 * Player is an abstract class that represents a player playing tic tac toe.
 */
public abstract class Player {

    public  static int moves;

    public static final int MAX_MOVES = 9;


    public Player(){
        this.moves = 0;
    }


    /**
     * Player move position
     * @return int, move
     */
    public abstract int move();


    /**
     * Gets how many moves were played
     * @return int: moves
     */
    public static int getMoves() {
        return moves;
    }

    /**
     * Gets the max number of moves
     * @return int max number of moves
     */
    public static int getMAX_MOVES(){
        return MAX_MOVES;
    }

    /**
     * Increments the moves by one
     */
    public static void incrementMoves(){
        moves++;
    }
}
