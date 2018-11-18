import java.util.Random;

public class ComputerPlayer extends Player implements Runnable {


    @Override
    public int move() {
        run();
        Random rand = new Random();

        return rand.nextInt(9);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
