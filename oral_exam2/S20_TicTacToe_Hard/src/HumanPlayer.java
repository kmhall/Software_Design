import java.util.Random;
import java.util.Scanner;

public class HumanPlayer extends Player{

    @Override
    public int move(){

        System.out.println("Human Move");

        Random generator = new Random();
        return generator.nextInt(9);

//        Scanner a = new Scanner(System.in);
//        return a.nextInt();
    }

}
