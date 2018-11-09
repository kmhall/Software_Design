import javax.swing.*;

public class MazeTraversal extends SwingWorker<String,Object>{


    private String[][] map;

    private int currentY;
    private int currentX;

    private final JTextArea mapDisplay;

    public MazeTraversal(JTextArea mapDisplay){

        this.mapDisplay = mapDisplay;

        map = new String[12][12];

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

    public void mazeTraversal(String[][] map,int currentY,int currentX) throws Exception {

        Thread.sleep(200);

        map[currentY][currentX] = "X";


        if (currentX != 11) {

            done();

            int possibleMoves = 0;

            /**
             * Check to see if there is at least one possible direction to travel
             */
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

            /**
             * If there is a possible direction to travel, try: left-up-down-right.
             */
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

            /**
             * If there is not a direction to travel, backtrack to the previous position.
             */
            //Traverse backwards
            else {

                map[currentY][currentX] = " ";

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


    @Override
    public String toString(){
        String lines = "\t";
        for(String[] line: map){
            for(String unit: line){
                lines = lines +unit + " ";
            }
            lines = lines + "\r\n\t";
        }
        return lines;
    }

    @Override
    protected String doInBackground() throws Exception {
         mazeTraversal(map,currentY,currentX);
        return null;
    }


    protected void done(){
        mapDisplay.setText(this.toString());

    }




}
