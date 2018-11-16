public class LevenshteinDistance {

    private String firstWord;
    private String secondWord;

    private char[] firstWordArray;
    private char[] secondWordArray;

    private int[][] matrix;

    private int minimumDistance;






    public static void main(String[] args){

        LevenshteinDistance distance = new LevenshteinDistance("abcdef","azced");
        distance.fillMatrix();
        distance.toString();
        distance.backTrack();
    }


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
