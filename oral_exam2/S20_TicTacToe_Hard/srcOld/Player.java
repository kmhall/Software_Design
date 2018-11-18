public abstract class Player {

    public  static int moves;

    public static final int MAX_MOVES = 9;


    public Player(){
        this.moves = 0;
    }


    public abstract int move();


    public static int getMoves() {
        return moves;
    }

    public static int getMAX_MOVES(){
        return MAX_MOVES;
    }

    public static void incrementMoves(){
        moves++;
    }
}
