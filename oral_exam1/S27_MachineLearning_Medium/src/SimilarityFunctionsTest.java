import java.io.File;

/**
 * Driver class for Similarity Functions. THis is where an instance of a SimilarityFunction is created.
 */
public class SimilarityFunctionsTest {

    /**
     * Main method creates a new SimilarityFunction, creates a new File,
     * and a new Data-point. Medium problem is tested (Should return "New data point belongs to class2").
     * @param args Main method arguments
     */
    public static void main(String[] args){
        SimilarityFunctions s = new SimilarityFunctions();
        File supervisedDataset = new File("./oral_exam1/S27_MachineLearning_Medium/files/S27-MLMedium.csv");
        double[] newDataPoint = new double[]{3, 3, 2, 2, 1};

        //Test Supervised Learning
        s.knearest(supervisedDataset, newDataPoint,5);
    }
}
