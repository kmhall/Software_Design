import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangmanFrame extends JFrame {


    private final JLabel gameStatus;
    private final JTextField wordToBeGuessed;
    private final JLabel guessesLeft;
    private final JLabel incorrectGuesses;
    private final JTextField enterLetters;
    private final JLabel wordDisplay;
    private final JLabel letterFieldLabel;

    private HangmanGame hangman;

    public HangmanFrame(){
        super("Hangman Game");

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        gameStatus = new JLabel("Enter a word to be guessed followed by ENTER");
        add(gameStatus);

        wordToBeGuessed = new JTextField(20);
        add(wordToBeGuessed);


        letterFieldLabel = new JLabel("Enter a letter into the box followed by ENTER to make a guess");
        letterFieldLabel.setVisible(false);
        add(letterFieldLabel);

        enterLetters = new JTextField(20);
        enterLetters.setVisible(false);
        add(enterLetters);

        wordDisplay = new JLabel();
        wordDisplay.setVisible(false);
        add(wordDisplay);

        guessesLeft = new JLabel();
        guessesLeft.setVisible(false);
        add(guessesLeft);

        incorrectGuesses = new JLabel();
        incorrectGuesses.setVisible(false);
        add(incorrectGuesses);


        //Handle Actions in TextFields
        ActionHandler handler = new ActionHandler();
        wordToBeGuessed.addActionListener(handler);
        enterLetters.addActionListener(handler);
    }

    private class ActionHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == wordToBeGuessed && wordToBeGuessed.getText().length() > 0){

                gameStatus.setText("Enter a new word in the box followed by ENTER for a new game");
                hangman = new HangmanGame(wordToBeGuessed.getText());
                wordToBeGuessed.setText("");

                guessesLeft.setVisible(true);
                guessesLeft.setText(hangman.getRemainingGuesses());

                incorrectGuesses.setVisible(true);
                incorrectGuesses.setText("Incorrect Guesses: ");

                letterFieldLabel.setVisible(true);
                enterLetters.setVisible(true);

                wordDisplay.setVisible(true);
                wordDisplay.setText(hangman.getWordDisplay());

            }
            else if(e.getSource() == enterLetters){
                //Call hangman function
                hangman.newLetterAdded(enterLetters.getText());
                enterLetters.setText("");
                //Update GUI
                if(hangman.getGameStatus() == true) {
                    guessesLeft.setText(hangman.getRemainingGuesses());
                    incorrectGuesses.setText(hangman.getIncorrectGuesses());
                    wordDisplay.setText(hangman.getWordDisplay());
                    if(hangman.getWinStatus() == true){
                        gameStatus.setText("You Win!  Enter a new word into the box followed by ENTER for a new game");
                        letterFieldLabel.setVisible(false);
                        enterLetters.setVisible(false);
                    }

                }
                else{
                    guessesLeft.setText(hangman.getRemainingGuesses());
                    incorrectGuesses.setText(hangman.getIncorrectGuesses());
                    wordDisplay.setText(hangman.getWordDisplay());
                    gameStatus.setText("Game Over! Enter a new word into the box followed by ENTER for a new game");
                    letterFieldLabel.setVisible(false);
                    enterLetters.setVisible(false);

                }

            }
        }
    }
}
