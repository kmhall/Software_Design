import java.util.Scanner;

//To do
// 1.Test if input is  a number
// 2.JUnit Test

public class EasterTest {

    public static void main(String[] args) {

        // create a Scanner object to obtain input from the command window
        Scanner input = new Scanner(System.in);

        // prompt for and read year
        System.out.println("Enter Year:");

        int theYear = input.nextInt(); // read a line of text
        System.out.println("x");


        EasterTest.easterCycle(theYear);

    }

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