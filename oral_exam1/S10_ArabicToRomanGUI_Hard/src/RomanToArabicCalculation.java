import java.util.HashMap;

/**
 * Class to convert a roman numeral to an arabic number
 */
public class RomanToArabicCalculation {

    private String arabicNumeral; //String so that invalid input can return a message
    private static String romanNumeral;
    private static  HashMap<String,Integer> singleValues;

    /**
     * Constructor for RomanToArabicCalculation
     * @param romanNumeral input roman numeral to convert to arabic numeral
     */
    public RomanToArabicCalculation(String romanNumeral){
        this.romanNumeral = romanNumeral.toUpperCase();
        this.romanNumeral.replace("\\s+","");

        this.singleValues = new HashMap<>();
        this.singleValues.put("M",1000);
        this.singleValues.put("D",500);
        this.singleValues.put("C",100);
        this.singleValues.put("L",50);
        this.singleValues.put("X",10);
        this.singleValues.put("V",5);
        this.singleValues.put("I",1);

        if(validateRomanNumeral()){
            this.arabicNumeral = Integer.toString(calculateArabicNumeral());
        }
        else{
            this.arabicNumeral = "Not a Valid Roman Numeral";
        }
    }

    /**
     * Validates whether a roman numeral is a valid one.
     * @return Boolean, whether the roman numeral is valid or not.
     */
    private static Boolean validateRomanNumeral(){
        //Check that the input is all valid keys,the size is greater than 0,
        if(romanNumeral.length() == 0){
            return false;
        }
        else if(romanNumeral.length() == 1) {
            return containsValidValues();
        }
        else if(romanNumeral.length() == 2){
            //Check previous condition and valid letters
            if(containsValidValues() == false){
                return false;
            }
            if(correctValueOrder() == false){
                return false;
            }

            return true;
        }

        else if(romanNumeral.length() == 3){
            //Check previous condition and valid letters
            if(containsValidValues() == false){
                return false;
            }
            if(correctValueOrder() == false){
                return false;
            }
            if(correctAscending() == false){
                return false;
            }


            return true;
        }

        if(containsValidValues() == false){
            return false;
        }
        if(correctValuesInARow() == false){
            return false;
        }
        if(correctValueOrder() == false){
            return false;
        }
        if(correctAscending() == false){
            return false;
        }

        return true;
    }

    /**
     * Validation check to see if the romanNumeral contains a character that is not a roman numeral character
     * @return Boolean, whether the roman numeral passed the test or not
     */
    private static Boolean containsValidValues(){
        for(int i=0;i < romanNumeral.length();i++){
            if(!singleValues.containsKey(Character.toString(romanNumeral.charAt(i)))){
                return false;
            }
        }
        return true;
    }
    /**
     * Validation check to see if the roman numeral has too many of the same character in a row
     * @return Boolean, whether the roman numeral passed the test or not
     */
    private static Boolean correctValuesInARow(){

        for(int i = 0; i<romanNumeral.length()-3;i++){
            if(romanNumeral.charAt(i) == romanNumeral.charAt(i+1) && romanNumeral.charAt(i) == romanNumeral.charAt(i+2)&& romanNumeral.charAt(i) == romanNumeral.charAt(i+3)){
                return false;
            }
        }
        return true;
    }

    /**
     * Validation check to see if roman numerals pairs are in the correct order.
     * Calls a method to validate if the previous character is the correct magnitude less.
     * @return Boolean, whether the roman numeral passed the test or not
     */
    private static Boolean correctValueOrder(){
        String currentCharacter;
        for(int i =0; i<romanNumeral.length()-1;i++){
            currentCharacter = Character.toString(romanNumeral.charAt(i));
            if(Integer.parseInt(String.valueOf(singleValues.get(Character.toString(romanNumeral.charAt(i))))) < Integer.parseInt(String.valueOf(singleValues.get(Character.toString(romanNumeral.charAt(i+1)))))){
                Boolean oneMagnitudeLess;

                oneMagnitudeLess = compareCurrentAndPrevious(currentCharacter,i);

                if(oneMagnitudeLess == false){
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Helper method for correctValueOrder. Checks to see if the previous character is the correct
     * magnitude less than current one.
     * @param currentCharacter The current character being checked
     * @param i The position of the character being checked
     * @return Boolean, whether the roman numeral passed the test or not
     */
    private static Boolean compareCurrentAndPrevious(String currentCharacter, int i){

        //4 Case
        if(currentCharacter.equals("I") && Character.toString(romanNumeral.charAt(i+1)).equals("V")){
            return true;
        }
        if(currentCharacter.equals("X") && Character.toString(romanNumeral.charAt(i+1)).equals("L")){
            return true;

        }
        if(currentCharacter.equals("C") && Character.toString(romanNumeral.charAt(i+1)).equals("D")){
            return true;

        }
        if(currentCharacter.equals("I") && Character.toString(romanNumeral.charAt(i+1)).equals("X")){
            return true;

        }
        if(currentCharacter.equals("X") && Character.toString(romanNumeral.charAt(i+1)).equals("C")){
            return true;

        }
        if(currentCharacter.equals("C") && Character.toString(romanNumeral.charAt(i+1)).equals("M")){
            return true;

        }

        return false;

    }

    /**
     * Validation check to make sure that three characters in a row are not in accending order.
     * IE: IVX is an invalid roman numeral.
     * @return Boolean, whether the roman numeral passed the test or not
     */
    private static Boolean correctAscending(){
        for (int i=0;i<romanNumeral.length()-2;i++){
            if(Integer.parseInt(String.valueOf(singleValues.get(Character.toString(romanNumeral.charAt(i)))))
                    < Integer.parseInt(String.valueOf(singleValues.get(Character.toString(romanNumeral.charAt(i+1)))))
                    && Integer.parseInt(String.valueOf(singleValues.get(Character.toString(romanNumeral.charAt(i)))))
                    < Integer.parseInt(String.valueOf(singleValues.get(Character.toString(romanNumeral.charAt(i+2)))))){
                return false;
            }
        }
        return true;

    }

    /**
     * Once validation is completed, this method will convert a roman numeral to a arabic numeral
     * @return int, represents an arabic numeral.
     */
    private static int calculateArabicNumeral(){

        //Loop compares the next and adds/subtracts current so the last number is not included. Initialization takes care of this.
        int arabicNumeral = singleValues.get(Character.toString(romanNumeral.charAt(romanNumeral.length()-1)));

        //Loop through entire RomanNumeral adding each
        // element unless the current "digit" is less than the next one.
        for(int i=0;i< romanNumeral.length()-1;i++){
            if(Integer.parseInt(String.valueOf(singleValues.get(Character.toString(romanNumeral.charAt(i))))) < Integer.parseInt(String.valueOf(singleValues.get(Character.toString(romanNumeral.charAt(i+1)))))){
                arabicNumeral -= Integer.parseInt(String.valueOf(singleValues.get(Character.toString(romanNumeral.charAt(i)))));
            }
            else arabicNumeral += Integer.parseInt(String.valueOf(singleValues.get(Character.toString(romanNumeral.charAt(i)))));
        }
        return arabicNumeral;
    }

    /**
     * Gets arabic numeral
     * @return String representation of arabic numeral
     */
    public String getArabicNumeral() {
        return arabicNumeral;
    }

    /**
     * Converts an arabic numeral to a String to easily read
     * @return String, arabic numeral
     */
    @Override
    public String toString() {
        return getArabicNumeral();
    }
}

