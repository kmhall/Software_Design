import org.junit.Test;

import static org.junit.Assert.*;


public class SimilarityFunctionsTest {

    public SimilarityFunctionsTest() {
    }

    @Test
    public void testCosineSimilarity() {
        SimilarityFunctions s = new SimilarityFunctions();

        double[] vector1 = new double[]{1, 2, 3};
        double[] vector2 = new double[]{2, 6, 3};
        double[] vector3 = new double[]{0, 1, 2};
        double[] vector4 = new double[]{2, 0, 0, 0};
        double[] vector5 = new double[]{1, 2.4, 3.9, 1};
        double[] vector6 = new double[]{1};
        double[] vector7 = new double[]{-1};

        assertEquals(0.8781440805693944, s.cosineSimilarity(vector1, vector2), .001);
        assertEquals(0.9561828874675149, s.cosineSimilarity(vector1, vector3), .001);
        assertEquals(0.7666518779999278, s.cosineSimilarity(vector2, vector3), .001);
        assertEquals(0.20865053489458874, s.cosineSimilarity(vector4, vector5), .001);
        assertEquals(-1.0, s.cosineSimilarity(vector6, vector7), .001);
    }

    @Test
    public void testHammingDistance() {
        SimilarityFunctions s = new SimilarityFunctions();

        String a = "0110101";
        String b = "1110010";
        String c = "0100001";
        String d = "1111110";
        String e = "1110101";
        String f = "1";
        String g = "0";

        assertEquals(4,s.hammingDistance(a,b));
        assertEquals(4,s.hammingDistance(b,c));
        assertEquals(6,s.hammingDistance(c,d));
        assertEquals(3,s.hammingDistance(d,e));
        assertEquals(1,s.hammingDistance(f,g));
    }

    @Test
    public void testEuclideanDistance(){
        SimilarityFunctions s = new SimilarityFunctions();

        double[] vector1 = new double[]{1, 2, 3};
        double[] vector2 = new double[]{2, 6, 3};
        double[] vector3 = new double[]{0, 1, 2};
        double[] vector4 = new double[]{2, 0, 0, 0};
        double[] vector5 = new double[]{1, 2.4, 3.9, 1};
        double[] vector6 = new double[]{1};
        double[] vector7 = new double[]{-1};

        assertEquals(4.1231, s.euclideanDistance(vector1, vector2), .001);
        assertEquals(1.7320508075688772, s.euclideanDistance(vector1, vector3), .001);
        assertEquals(5.477225575051661, s.euclideanDistance(vector2, vector3), .001);
        assertEquals(4.792702786528704, s.euclideanDistance(vector4, vector5), .001);
        assertEquals(2.0, s.euclideanDistance(vector6, vector7), .001);

    }
}
