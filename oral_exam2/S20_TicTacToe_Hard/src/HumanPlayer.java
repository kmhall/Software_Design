import java.util.Scanner;

/**
 * HumanPlayer is a class that represents a human playing a game of tic tac toe.
 */
public class HumanPlayer extends Player{


    /**
     * The move that a human makes
     * @return int, what position to try next
     */
    @Override
    public int move() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Make a move");
        int move = -1;
        if (scanner.hasNextInt()){
           move = scanner.nextInt();
        }
        return move;
    }
}
