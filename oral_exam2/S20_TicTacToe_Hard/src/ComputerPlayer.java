import java.util.Random;

public class ComputerPlayer extends Player {

    @Override
    public int move(){

        System.out.println("Computer Move");
        Random generator = new Random();
        return generator.nextInt(9);
    }
}
