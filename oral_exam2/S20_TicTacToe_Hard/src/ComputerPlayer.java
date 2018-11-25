import java.util.Random;

/**
 * ComputerPlayer is a class that represents a computer playing tic tac toe.
 * @see Player
 * @see Runnable
 */
public class ComputerPlayer extends Player implements Runnable {


    /**
     * The move that a computer makes
     * @return int, what position to try next
     */
    @Override
    public int move() {
        run();
        Random rand = new Random();

        return rand.nextInt(9);
    }

    /**
     * This method uses the Runnable class to allow the computer to take time in between moves
     */
    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
