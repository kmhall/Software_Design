import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;



public class SimilarityFunctions {

    /*
    Implementation of all three of all three similarity functions.
     */

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
    } //Calculate magnitude for cosineSimilarity

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

    /*Supervised learning
    Given a dataset, a new data point, and a parameter k, your solution will need to
    find the k data points that are closest to the new data point as per your Euclidean
    similarity function, and choose the most common class among them to classify the
    new data point.
     */

    public void knearest(File dataset, double[] newDataPoint,int k){

        //dataArray is a list of lists containing the dataset in the form [data point 1, ... ,data point 5, class]
        ArrayList<List<String>> dataList = csvToList(dataset);

        //dataArray is a 2D array of arrays in the form [euclideanDistance between the newDataPoint and the dataset points, dataset class]
        String[][] dataArray = listToArray(dataList, newDataPoint);

        orderArray(dataArray);

        calcClosest(dataArray,k);

    }

    //Converts
    private ArrayList <List<String>> csvToList(File dataset){
        //Documentation
        //https://stackoverflow.com/questions/40074840/reading-a-csv-file-into-a-array

        //2D ArrayList (Used so that size of the .csv can be variable)
        ArrayList<List<String>> lines = new ArrayList<>();
        try{
            System.out.println("***************************");
            System.out.println("All lines of the ArrayList:");


            Scanner scanner = new Scanner(dataset);
            while(scanner.hasNext()){
                String line = scanner.next();
                /*
                All lines of he ArrayList
                 */
                System.out.println(line);

                String[] values = line.split(",");
                lines.add(Arrays.asList(values));
            }
            scanner.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        System.out.println("***************************");

        return lines;

    }

    private String[][] listToArray(ArrayList<List<String>> dataList, double[] newDataPoint) {

        //Declare a 2D array with the same length as the list
        String [][] dataArray = new String[dataList.size()][2];
        int dataArrayIndex = 0;

        double[] currentList = new double[5];
        int currentListIndex = 0;

        String test = "";

        //For line in dataList. Used so that the length of the List doesn't need to be known.
        for(List<String> line: dataList) {
            //For value in line. This could be a normal loop because the length is known.
            for(String value: line) {
                if(value.equals("\"class1\"") || value.equals("\"class2\"")){
                    dataArray[dataArrayIndex][1] = value;
                    currentListIndex = 0;
                }else{
                    //Current list is filled with all the data points that were in the list.
                    currentList[currentListIndex] = Double.parseDouble(value);
                    currentListIndex++;
                }



            }
            /*
                Loop used to check if the list was converted properly to an array
            */
            for (int i =0;i<5;i++){
                test += currentList[i] + ", ";
            }
            test += "\n";

            //Calculate the euclideanDistance between the currentList and the newDataPoint.
            double e = euclideanDistance(newDataPoint,currentList);
            dataArray[dataArrayIndex][0] = Double.toString(e);//Distance of currentList goes in here
            dataArrayIndex++;
        }
        System.out.println(test);


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

        for(int i = 0;i<k;i++){
            if(dataArray[i][1].equals("\"class1\"")){

                System.out.println(dataArray[i][1]);
                numClass1++;
            }
            else {
                System.out.println(dataArray[i][1]);
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



