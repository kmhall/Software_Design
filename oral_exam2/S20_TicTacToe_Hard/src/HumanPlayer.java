import java.util.Scanner;

public class HumanPlayer extends Player{

    @Override
    public int move(){

        System.out.println("Human Move");
        Scanner a = new Scanner(System.in);
        return a.nextInt();
    }

}
