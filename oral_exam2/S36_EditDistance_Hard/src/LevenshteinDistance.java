/**
 * LevenshteinDistance is a class that calculates the edit distance between two words using the Levenshtein algorithm.
 * https://en.wikipedia.org/wiki/Levenshtein_distance
 */
public class LevenshteinDistance {

    private String firstWord;
    private String secondWord;

    private char[] firstWordArray;
    private char[] secondWordArray;

    private int[][] matrix;

    private int minimumDistance;


    /**
     * Main method for LevensheteinDistance
     * @param args System inputs
     */
    public static void main(String[] args){

        LevenshteinDistance distance = new LevenshteinDistance("rat","cats");
        distance.fillMatrix();
        distance.toString();
        distance.backTrack();
    }


    /**
     * Constructs a LevenshteinDistance. Initilizes matrix with numbers counting
     * from 0-length for first column and first row.
     * @param firstWord String word to convert from
     * @param secondWord String word to convert to
     */
    public LevenshteinDistance(String firstWord, String secondWord){

        this.firstWord = firstWord;
        this.secondWord = secondWord;

        this.firstWordArray = firstWord.toCharArray();
        this.secondWordArray = secondWord.toCharArray();

        this.matrix = new int[secondWord.length()+1][firstWord.length()+1];

        for(int i=0;i< firstWord.length()+1;i++){
            this.matrix[0][i] = i;
        }

        for(int j=0;j< secondWord.length()+1;j++){
            this.matrix[j][0] = j;
        }

    }

    /**
     * Fills the matrix by by checking if the values in the [i-1] and [j-1] position are the same.
     * If they are the same then convert the current to that. Otherwise find the min and convert current spot to
     * the min +1 of the three
     */
    public void fillMatrix(){
        for(int i=1;i<secondWord.length()+1;i++){
            for(int j=1;j<firstWord.length()+1;j++){
                if(firstWordArray[j-1] == secondWordArray[i-1]){
                    matrix[i][j] = matrix[i-1][j-1];
                }
                else{
                    matrix[i][j] = min(i,j) +1;
                }
            }
        }
        minimumDistance = matrix[secondWord.length()][firstWord.length()];
    }

    /**
     * Calculates the min of the left, up, and top left positions.
     * @param i int: Current i position
     * @param j int: Current j position
     * @return int: min of three positions
     */
    private int min(int i,int j){
        int min = matrix[i-1][j];
        if(matrix[i-1][j-1] < min){
            min = matrix[i-1][j-1];
        }
        if(matrix[i][j-1] < min){
            min = matrix[i][j-1];
        }
        return min;
    }


    /**
     * Tracks back through all the position changes to print out what was changed. Each direction change corresponds to a
     * replace/add/remove.
     */
    public void backTrack(){

        int i = secondWord.length();
        int j = firstWord.length();

        int startingPlace = matrix[secondWord.length()][firstWord.length()];

        while (startingPlace != 0 ){
            if(firstWordArray[j-1] == secondWordArray[i-1]){
                i--;
                j--;
            }
            else{
                if (backTrackMin(i, j).equals("diagonal")) {
                    System.out.println("edit distance: "+minimumDistance+" --> replace \'"+firstWordArray[j-1]+"\' in str1 with a \'"+secondWordArray[i-1]+"\'");
                    i--;
                    j--;
                }
                else if(backTrackMin(i, j).equals("left")) {
                    System.out.println("edit distance: "+minimumDistance+" --> add \'"+secondWordArray[i-1]+"\' to str1");
                    i--;
                }
                else if(backTrackMin(i, j).equals("up")) {
                    System.out.println("edit distance: "+minimumDistance+" --> remove \'"+firstWordArray[j-1]+"\' from str1");
                    j--;

                }
            }
            startingPlace = matrix[i][j];
        }
    }

    /**
     * Calcualates what position the current value was calculated from
     * @param i int: Current i position
     * @param j int Current j position
     * @return String: corresponds to what position
     */
    public String backTrackMin(int i, int j){
        int min = min(i,j);
        if(matrix[i-1][j-1] == min){
            return "diagonal";
        }
        else if(matrix[i-1][j] == min){
            return "left";
        }
        else {
            return "up";
        }
    }

    /**
     * Converts the matrix and words to a map to easy see the process in the terminal
     * @return String of matrix with words
     */
    @Override
    public String toString() {

        int index = 0;
        //Print first word
        System.out.print("    ");
        for(char letter: firstWordArray ){
            System.out.print(letter+ " ");
        }
        System.out.println();

        //Print Matrix
        for(int[] row: matrix){
            if(index>0){
               System.out.print(secondWordArray[index-1]+" ");
            }
            else {
                System.out.print("  ");
            }
            index++;

            for (int num: row){
                System.out.print(num +" ");
            }
            System.out.println();
        }
        return null;
    }
}
