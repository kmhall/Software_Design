/**
 * Class that converts an Arabic number to a Roman numeral.
 */
public class ArabicToRomanCalculation {

    private String romanNumeral;

    /**
     * Constructor for ArabicToRomanCalculation
     * @param arabicString The arabic number that wants to be converted
     */
    public ArabicToRomanCalculation(String arabicString){
        //Validate that the input is a number
        if(arabicString.matches("\\d+")) {
            int arabicNumber = Integer.parseInt(arabicString);
            if (arabicNumber > 0 && arabicNumber < 4000) {
                this.romanNumeral = calculateRomanNumeral(arabicNumber);
            }
            else {
                this.romanNumeral = "Not in Range [1,3999]";
            }
        }
        else{
            this.romanNumeral = "NAN";
        }
    }

    /**
     * Calculates a Roman numeral given an arabic numeral
     * @param arabicNumber The arabic number that wants to be converted
     * @return String of a roman numeral
     */
    private static String calculateRomanNumeral(int arabicNumber){
        String romanNumeral = "";
        int significantDigit;
        int currentArabicNumber = arabicNumber;

        String[] romanThousands = new String[]{"M","MM","MMM"};
        String[] romanHundreds = new String[]{"C","CC","CCC","CD","D","DC","DCC","DCCC","CM"};
        String[] romanTens = new String[]{"X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};
        String[] romanOnes = new String[]{"I","II","III","IV","V","VI","VII","VIII","IX"};

        if(currentArabicNumber >999){
            significantDigit = currentArabicNumber/1000;
            romanNumeral+= romanThousands[significantDigit-1];
            currentArabicNumber -= significantDigit*1000;
        }
        if(currentArabicNumber >99){
            significantDigit = currentArabicNumber/100;
            romanNumeral += romanHundreds[significantDigit-1];
            currentArabicNumber -= significantDigit*100;

        }
        if(currentArabicNumber >9){
            significantDigit = currentArabicNumber/10;
            romanNumeral += romanTens[significantDigit-1];
            currentArabicNumber -= significantDigit * 10;
        }
        if(currentArabicNumber != 0) {
            significantDigit = currentArabicNumber;
            romanNumeral += romanOnes[significantDigit - 1];
        }
        return romanNumeral;
    }

    /**
     * Gets the Roman Numeral
     * @return Roman Numeral
     */
    private String getRomanNumber() {
        return romanNumeral;
    }

    /**
     *
     * @return String of the roman Numeral
     */
    @Override
    public String toString() {
        return getRomanNumber();
    }
}
