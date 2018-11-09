import java.util.ArrayList;

public class MazeTraversal implements Runnable{


    private String[][] map;

    private final int[] startLocation;
    private int currentY;
    private int currentX;

    public MazeTraversal(){

        map = new String[12][12];

        startLocation = new int[]{2,0};

        currentY = 2;
        currentX = 0;



        String[] line0 = new String[]{"#","#","#","#","#","#","#","#","#","#","#","#"}; //Line currentY = 0

        String[] line1 = new String[]{"#",".",".",".","#",".",".",".",".",".",".","#"};

        String[] line2 = new String[]{".",".","#",".","#",".","#","#","#","#",".","#"};

        String[] line3 = new String[]{"#","#","#",".","#",".",".",".",".","#",".","#"};

        String[] line4 = new String[]{"#",".",".",".",".","#","#","#",".","#",".","."};

        String[] line5 = new String[]{"#","#","#","#",".","#",".","#",".","#",".","#"};

        String[] line6 = new String[]{"#",".",".","#",".","#",".","#",".","#",".","#"};

        String[] line7 = new String[]{"#","#",".","#",".","#",".","#",".","#",".","#"};

        String[] line8 = new String[]{"#",".",".",".",".",".",".",".",".","#",".","#"};

        String[] line9 = new String[]{"#","#","#","#","#","#",".","#","#","#",".","#"};

        String[] line10 = new String[]{"#",".",".",".",".",".",".","#",".",".",".","#"};

        String[] line11 = new String[]{"#","#","#","#","#","#","#","#","#","#","#","#"}; //Line currentY = 11

        map[0] = line0;
        map[1] = line1;
        map[2] = line2;
        map[3] = line3;
        map[4] = line4;
        map[5] = line5;
        map[6] = line6;
        map[7] = line7;
        map[8] = line8;
        map[9] = line9;
        map[10] = line10;
        map[11] = line11;

    }

    public void mazeTraversal(String[][] map,int currentY,int currentX) throws InterruptedException {

        Thread.sleep(1000);

        map[currentY][currentX] = "X";


        if (currentX != 11) {

            printMap();
            int possibleMoves = 0;


            //Test to move left
            if (currentX - 1 >= 0 && map[currentY][currentX - 1].equals(".")) {
                possibleMoves++;
            }

            //Test to move up
            if (map[currentY + 1][currentX].equals(".")) {
                possibleMoves++;
            }

            //Test to move down
            if (map[currentY - 1][currentX].equals(".")) {
                possibleMoves++;
            }

            //Test to move right
            if (currentX + 1 <= 11 && map[currentY][currentX + 1].equals(".")) {
                possibleMoves++;
            }

            //Make a new move
            if (possibleMoves >= 1) {


                //Move Left
                if (currentX - 1 >= 0 && map[currentY][currentX - 1].equals(".")) {
                    mazeTraversal(map, currentY, currentX - 1);
                }

                //Move Up
                else if (map[currentY + 1][currentX].equals(".")) {

                    mazeTraversal(map, currentY + 1, currentX);
                }

                //Move Down
                else if (map[currentY - 1][currentX].equals(".")) {
                    mazeTraversal(map, currentY - 1, currentX);
                }
                //Move Right
                else if (currentX + 1 <= 11 && map[currentY][currentX + 1].equals(".")) {
                    mazeTraversal(map, currentY, currentX + 1);
                }


            }

            //Traverse backwards
            else {

                map[currentY][currentX] = "O";

                //Move Right
                if (currentX + 1 <= 11 && map[currentY][currentX + 1].equals("X")) {
                    mazeTraversal(map, currentY, currentX + 1);
                }
                //Move Up
                else if (map[currentY + 1][currentX].equals("X")) {
                    mazeTraversal(map, currentY + 1, currentX);
                }

                //Move Down
                else if (map[currentY - 1][currentX].equals("X")) {
                    mazeTraversal(map, currentY - 1, currentX);
                }

                //Move Left
                else if (currentX - 1 >= 0 && map[currentY][currentX - 1].equals("X")) {
                    mazeTraversal(map, currentY, currentX - 1);
                }
            }
        }
    }






    public void sanityCheck(){
        System.out.print(map[0][0]);
        System.out.print(map[0][11]);

        System.out.print(map[11][0]);
        System.out.print(map[11][11]);

    }

    public void printMap(){
        System.out.println();
        for(String[] line: map){
            for(String unit: line){
                System.out.print(unit + " ");
            }
            System.out.println();
        }
    }

    public String[][] getMap() {
        return map;
    }


    @Override
    public void run() {

        try {
            mazeTraversal(map,currentY,currentX);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
