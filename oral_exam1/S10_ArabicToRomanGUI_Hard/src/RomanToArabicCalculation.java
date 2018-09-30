import java.util.HashMap;

public class RomanToArabicCalculation {

    private String arabicNumeral; //String so that invalid input can return a message
    private static String romanNumeral;
    private static  HashMap<String,Integer> singleValues;

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

    //Tests
    private static Boolean containsValidValues(){
        for(int i=0;i < romanNumeral.length();i++){
            if(!singleValues.containsKey(Character.toString(romanNumeral.charAt(i)))){
                return false;
            }
        }
        return true;
    }
    private static Boolean correctValuesInARow(){

        for(int i = 0; i<romanNumeral.length()-3;i++){
            if(romanNumeral.charAt(i) == romanNumeral.charAt(i+1) && romanNumeral.charAt(i) == romanNumeral.charAt(i+2)&& romanNumeral.charAt(i) == romanNumeral.charAt(i+3)){
                return false;
            }
        }
        return true;
    }

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

    private static Boolean correctAscending(){
        for (int i=0;i<romanNumeral.length()-2;i++){
            if(Integer.parseInt(String.valueOf(singleValues.get(Character.toString(romanNumeral.charAt(i))))) < Integer.parseInt(String.valueOf(singleValues.get(Character.toString(romanNumeral.charAt(i+1))))) && Integer.parseInt(String.valueOf(singleValues.get(Character.toString(romanNumeral.charAt(i))))) < Integer.parseInt(String.valueOf(singleValues.get(Character.toString(romanNumeral.charAt(i+2))))) ){
                return false;
            }
        }
        return true;

    }

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

    public String getArabicNumeral() {
        return arabicNumeral;
    }

    @Override
    public String toString() {
        return getArabicNumeral();
    }
}

