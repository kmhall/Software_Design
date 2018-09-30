import java.util.ArrayList;

public class HangmanGame {

    private Boolean winStatus;
    private String wordToGuess;
    private int remainingGuesses;
    private String[] wordDisplay;
    private ArrayList<String> incorrectGuesses;
    private Boolean gameStatus;

    public HangmanGame(String wordToGuess){
        this.wordToGuess = wordToGuess.toUpperCase();
        this.remainingGuesses = 7;
        this.incorrectGuesses = new ArrayList<>();
        this.gameStatus = true;
        this.winStatus = false;


        //Initialization of blanks
        this.wordDisplay = new String[wordToGuess.length()];
        for (int i =0;i<wordDisplay.length;i++){
            wordDisplay[i] = "_";
        }
    }


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

    private void winStatus(){
        for(int i=0;i< wordDisplay.length;i++){
            if(wordDisplay[i] == "_"){
                winStatus = false;
                return;
            }
        }
        winStatus = true;
    }


    public Boolean getWinStatus() {
        return winStatus;
    }

    public String getWordDisplay() {
        String req = "Word: ";
        for(int i=0;i<wordDisplay.length;i++){
            req += " "+wordDisplay[i];
        }
        return req;
    }

    public String getRemainingGuesses(){
        return "Remaining Guesses: "+remainingGuesses;
    }

    public String getIncorrectGuesses(){
        String req = "Incorrect Letters: ";

        for(int i=0; i<incorrectGuesses.size();i++){
            req += " "+incorrectGuesses.get(i);
        }
        return req;
    }

    public Boolean getGameStatus() {
        return gameStatus;
    }
}
