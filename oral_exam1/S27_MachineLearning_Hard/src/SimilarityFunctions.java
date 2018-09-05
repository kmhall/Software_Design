import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;



public class SimilarityFunctions {

    public static void main(String[] args){
        SimilarityFunctions s = new SimilarityFunctions();
        File dataset = new File("./oral_exam1/S27_MachineLearning_Hard/src/S27-MLMedium.csv");
        double[] newDataPoint = new double[]{1.5, 3.5, 2, 2, 8};
        String output = s.knearest(dataset, newDataPoint,5);
    }

    public double cosineSimilarity(double[] vector1, double[] vector2) {

        double similarity;
        double dotProduct = 0;
        double vector1Magnitude;
        double vector2Magnitude;

        //Dot product
        for (int i = 0; i < vector1.length; i++) {
            dotProduct += vector1[i] * vector2[i];
        }

        vector1Magnitude = magnitude(vector1);
        vector2Magnitude = magnitude(vector2);

        similarity = dotProduct / (vector1Magnitude * vector2Magnitude);

        return similarity;
    }

    private double magnitude(double[] vector) {

        double sumOfSquares = 0;
        for (int i = 0; i < vector.length; i++) {
            sumOfSquares += (vector[i] * vector[i]);
        }
        return Math.sqrt(sumOfSquares);
    }

    public int hammingDistance(String input1, String input2) {

        int numDifference = 0;
        for (int i = 0; i < input1.length(); i++) {
            if (input1.charAt(i) != input2.charAt(i)) {
                numDifference++;
            }
        }
        return numDifference;
    }

    public double euclideanDistance(double[] vector1, double[] vector2) {

        double similarity;
        double sumOfSquares = 0;
        for (int i = 0; i < vector1.length; i++) {
            sumOfSquares += (vector2[i] - vector1[i]) * (vector2[i] - vector1[i]);
        }
        similarity = Math.sqrt(sumOfSquares);

        return similarity;
    }


    public String knearest(File dataset, double[] newDataPoint,int k){

        List<List<String>> dataArrayList = csvToArrayList(dataset);

        String[][] dataArray = arraylistToArray(dataArrayList, newDataPoint);
        for(int i=0;i<dataArray.length;i++){
                System.out.println("Entry: "+ i + " Value: " +dataArray[i][0]+ " Class: " + dataArray[i][1]);
            }


        String output = "";
        return output;
    }


    private List<List<String>> csvToArrayList(File dataset){
        //Documentation
        //https://stackoverflow.com/questions/40074840/reading-a-csv-file-into-a-array

        //2D ArrayList (Used so that size of the .csv can be variable)
        List<List<String>> lines = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(dataset);
            while(scanner.hasNext()){
                String line = scanner.next();
                String[] values = line.split(",");
                lines.add(Arrays.asList(values));
            }
            scanner.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return lines;

    }

    private String[][] arraylistToArray(List<List<String>> dataArrayList, double[] newDataPoint) {

        String [][] dataArray = new String[dataArrayList.size()][2];
        int returnArrayIndex = 0;

        double[] currentList = new double[5];
        int currentListIndex = 0;

        for(List<String> line: dataArrayList) {
            for(String value: line) {
                if(value.equals("\"class1\"") || value.equals("\"class2\"")){
                    dataArray[returnArrayIndex][1] = value;
                    currentListIndex = 0;
                }else{
                    currentList[currentListIndex] = Double.parseDouble(value);
                    currentListIndex++;
                }
            }
            double e = euclideanDistance(newDataPoint,currentList);
            dataArray[returnArrayIndex][0] = Double.toString(e);//Distance of currentList goes in here
            returnArrayIndex++;
        }

        return dataArray;
    }
}

