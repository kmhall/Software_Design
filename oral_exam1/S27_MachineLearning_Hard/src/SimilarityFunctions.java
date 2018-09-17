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

        s.knearest(dataset, newDataPoint,5);
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

    public void knearest(File dataset, double[] newDataPoint,int k){

        //dataArray is a list of lists containing the dataset in the form [data point 1, ... ,data point 5, class]
        List<List<String>> dataArrayList = csvToArrayList(dataset);

        //dataArray is a 2D array of arrays in the form [euclideanDistance between the newDataPoint and the dataset points, dataset class]
        String[][] dataArray = arraylistToArray(dataArrayList, newDataPoint);

        orderArray(dataArray);

        calcClosest(dataArray,k);

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

    private void orderArray( String[][] dataArray){

        double key;
        String keyClass;

        //Insertion sort
        for (int i = 1; i < dataArray.length; i++)
        {
            key = Double.parseDouble(dataArray[i][0]);
            keyClass = dataArray[i][1];
            int j = i-1;

            while (j >= 0 && Double.parseDouble(dataArray[j][0]) > key)
            {
                dataArray[j+1][0] = dataArray[j][0];
                dataArray[j+1][1] = dataArray[j][1];
                j = j-1;
            }
            dataArray[j+1][0] = Double.toString(key);
            dataArray[j+1][1] = keyClass;

        }

    }

    private void calcClosest(String[][] dataArray,int k){
        int numClass1=0;
        int numClass2=0;

        for(int i =0;i<k;i++){
            if(dataArray[k][1] == "\"class1\""){
                numClass1++;
            }
            else {
                numClass2++;
            }
        }
        if(numClass1>numClass2){
            System.out.println("New data point belongs to class1");
        }
        else if(numClass2>numClass1){
            System.out.println("New data point belongs to class2");
        }
        else{
            System.out.println("New data point belongs is evenly split");
        }
    }

}

