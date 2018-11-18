import java.util.Scanner;

public class HumanPlayer extends Player{


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
