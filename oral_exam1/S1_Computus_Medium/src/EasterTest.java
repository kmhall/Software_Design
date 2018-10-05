import java.util.Scanner;

//To do
// 1.Test if input is  a number

/**
 * This is the driver class for testing Easter instances.
 */
public class EasterTest {

    /**
     * Main method of the driver class, all instances of Easter are contained within it.
     * @param args Arguments passed into the main method
     *
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // create a Scanner object to obtain input from the command window
        System.out.println("Enter Year:"); // prompt for and read year

            EasterTest.easterCycle(input.nextInt());
    }

    /**
     * Static method for looping an entire cycle of easter dates.
     * @param theYear Starting year for an easter cycle.
     */
    private static void easterCycle(int theYear){
        int [] marchOccurrences = new int[31];
        int [] aprilOccurrences = new int[30];

        //Loops exactly from the input year to 5700000 years in the future
        for(int i = theYear; i< 5700000 + theYear; i++) {
            Easter e = new Easter(i);
            if (e.getMonth() == 3) {
                marchOccurrences[e.getDay() - 1] += 1;
            } else {
                aprilOccurrences[e.getDay() - 1] += 1;
            }
        }

        for(int i=0;i<marchOccurrences.length;i++){
            if(marchOccurrences[i]!=0){
                System.out.println("March " + (i+1) + " - "+ marchOccurrences[i]);
            }
        }

        for(int i=0;i<aprilOccurrences.length;i++){
            if(aprilOccurrences[i]!=0){
                System.out.println("April " + (i+1) + " - "+ aprilOccurrences[i]);
            }
        }

    }
}
