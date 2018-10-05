import java.util.ArrayList;

/**
 * Class that holds all the logic of the HangmanGame
 */
public class HangmanGame {

    private Boolean winStatus;
    private String wordToGuess;
    private int remainingGuesses;
    private String[] wordDisplay;
    private ArrayList<String> incorrectGuesses;
    private Boolean gameStatus;

    /**
     * Constructor of a hangman game
     * @param wordToGuess String, the word to be guessed
     */
    public HangmanGame(String wordToGuess){
        this.wordToGuess = wordToGuess.toUpperCase();
        this.remainingGuesses = 6;
        this.incorrectGuesses = new ArrayList<>();
        this.gameStatus = true;
        this.winStatus = false;


        //Initialization of blanks
        this.wordDisplay = new String[wordToGuess.length()];
        for (int i =0;i<wordDisplay.length;i++){
            wordDisplay[i] = "_";
        }
    }

    /**
     * Method to add a new letter to the display string.
     * @param letter String, new letter to be added
     */
    public void newLetterAdded(String letter){
        if(wordToGuess.contains(letter.toUpperCase())){
            for(int i=0;i<wordToGuess.length();i++){
                if(Character.toString(wordToGuess.charAt(i)).equals(letter.toUpperCase())){
                    wordDisplay[i] = letter.toUpperCase();
                }
            }
            winStatus();
        }
        else{
            incorrectGuesses.add(letter.toUpperCase());
            if(remainingGuesses>0){
                remainingGuesses -= 1;
            }
            if(remainingGuesses == 0){
                gameStatus = false;
            }
        }

    }

    /**
     * Checks the win status and updates the private instance variable if there was a state change.
     */
    private void winStatus(){
        for(int i=0;i< wordDisplay.length;i++){
            if(wordDisplay[i] == "_"){
                winStatus = false;
                return;
            }
        }
        winStatus = true;
    }

    /**
     * Gets win status
     * @return Boolean, win status
     */
    public Boolean getWinStatus() {
        return winStatus;
    }

    /**
     * Gets word with correct guesses so far
     * @return String, word with correct guesses
     */
    public String getWordDisplay() {
        String req = "Word: ";
        for(int i=0;i<wordDisplay.length;i++){
            req += " "+wordDisplay[i];
        }
        return req;
    }

    /**
     * Gets remaining guesses as a displayable string
     * @return String, remaining guesses as a displayable string
     */
    public String getRemainingGuesses(){
        return "Remaining Guesses: "+remainingGuesses;
    }

    /**
     * Gets remaining guesses
     * @return int, remaining guesses
     */
    public int getRemainingGuessesInt(){
        return remainingGuesses;
    }
    /**
     * Gets incorrect guesses as a displayable string
     * @return String, incorrect guesses as a displayable string
     */
    public String getIncorrectGuesses(){
        String req = "Incorrect Letters: ";

        for(int i=0; i<incorrectGuesses.size();i++){
            req += " "+incorrectGuesses.get(i);
        }
        return req;
    }

    /**
     * Gets the game status, this differs from the win status in that if the user looses,
     * the frame doesn't update the incorrect guesses or the score.
     * @return Boolean, gameStatus
     */
    public Boolean getGameStatus() {
        return gameStatus;
    }
}
