import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * AdjacencyList is a class that takes a list of 5 letter words and creates a web connecting words with an edit distance of one.
 * It prints the average number of connections, number of nodes with no edges, and the nodes with the most edges.
 */
public class AdjacencyList {

    /**
     * Main method for AdjacencyList
     * @param args System inputs
     */
    public static void main(String args[]) {

        File file = new File("./oral_exam2/S35_GraphAlgos_Easy/words.dat");

        List<String> words = fileToListOfWords(file);

        HashMap<String,List<String>> h1 = listOfWordsToHashMap(words);

        String[] outputData = calculateData(words,h1);

        System.out.println("avg num of connections: "+outputData[0]);
        System.out.println("num nodes with no edges: "+outputData[1]);
        System.out.println("nodes with the most edges: "+ outputData[2] + " with "+outputData[3] +" vertices each");

    }

    /**
     * fileToListOfWords converts a csv file to a list
     * @param file CSV file
     * @return List of words
     */
    private static List<String> fileToListOfWords(File file){

        List<String> words = new LinkedList<>();

        Scanner inputStream = null;

        try {
            inputStream = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        inputStream.useDelimiter("\n");

        //Iterate through file and add to a list of words
        if (inputStream.hasNext()) {
            while (inputStream.hasNext()) {

                words.add(inputStream.next());
            }
        }

        return words;
    }

    /**
     * Converts a list of words to a hash map
     * @param words List of words
     * @return HashMap with each word as a key and a list of words as the value.
     */
    private static  HashMap<String,List<String>> listOfWordsToHashMap( List<String> words){

        HashMap<String,List<String>> h1 = new HashMap<>();

        //Create an iterator for the list of words
        Iterator iter = words.iterator();

        while (iter.hasNext()){
            String currentWord = iter.next().toString();

            List<String> editOfOne = arrayOfEditDistanceOfOne(currentWord,words);

            h1.put(currentWord,editOfOne);
        }
        return h1;

    }

    /**
     * Makes a list of words with an edit distance of one.
     * @param currentWord String: word to find adjacent words
     * @param words List: full list of words
     * @return List of adjacent words
     */
    private static List<String> arrayOfEditDistanceOfOne(String currentWord,List<String> words ){

        List<String> oneDistance = new LinkedList<>();

        for (String word: words) {
            int editDistance = 0;

            String[] wordArray = word.split("");
            String[] currentWordArray = currentWord.split("");

            for(int i=0;i<5;i++){
                if(!wordArray[i].equals(currentWordArray[i])){
                    editDistance++;
                }
            }
            if(editDistance == 1){
                oneDistance.add((word));
            }
        }

        return oneDistance;
    }

    /**
     * Calculate the average number of connections, number of nodes with no edges, and the nodes with the most edges.
     * @param words List of words
     * @param h1 Hashmap of all keys with values
     * @return String[] of all data
     */
    private static String[] calculateData(List<String> words ,HashMap<String,List<String>> h1){

        String[] outputData = new String[4];


        int noVerticies = 0;

        List<String> wordsWithMostVerticies = new LinkedList<>();
        int mostVerticesCount = 0;
        int count = 0;
        float sumVerticies;


        Iterator iter = words.iterator();
        String currentWord = iter.next().toString();
        wordsWithMostVerticies.add(currentWord);
        List<String> currentMatch = h1.get(currentWord);
        mostVerticesCount = currentMatch.size();
        if(currentMatch.size() == 0){
            noVerticies++;
        }
        count++;
        sumVerticies =  currentMatch.size();

        while (iter.hasNext()){
            currentWord = iter.next().toString();
            currentMatch = h1.get(currentWord);

            if(currentMatch.size() == mostVerticesCount){
                wordsWithMostVerticies.add(currentWord);
            }

            if(currentMatch.size() > mostVerticesCount){
                wordsWithMostVerticies = new LinkedList<>();
                wordsWithMostVerticies.add(currentWord);
                mostVerticesCount = currentMatch.size();
            }
            if(currentMatch.size() == 0){
                noVerticies++;
            }
            count++;
            sumVerticies += currentMatch.size();


        }
        float averageVertices = sumVerticies/count;

        outputData[0] = Float.toString(averageVertices);
        outputData[1] = Integer.toString(noVerticies);
        outputData[2] = wordsWithMostVerticies.toString();
        outputData[3] = Integer.toString(mostVerticesCount);

        return outputData;
    }

}




