import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ArabicToRomanFrame extends JFrame{

    private final JLabel arabicLabel;
    private final JTextArea arabicTextArea;
    private final JLabel romanLabel;
    private final JTextArea romanTextArea;
    
    public ArabicToRomanFrame(){

        //Set up ArabicRORomanGUI
        super("Roman to Arabic || Arabic to Roman");
        setLayout(new FlowLayout());

        arabicLabel = new JLabel("Arabic Number");
        add(arabicLabel);
        arabicTextArea = new JTextArea(4,20);
        add(arabicTextArea);
        romanLabel = new JLabel(("Roman Number"));
        add(romanLabel);
        romanTextArea = new JTextArea(4,20);
        add(romanTextArea);

        //Create a new KeyHandler for key event handling 
        KeyHandler handler = new KeyHandler();
        arabicTextArea.addKeyListener(handler);
        romanTextArea.addKeyListener(handler);
    }

    // inner class for key event handling
    private class KeyHandler implements KeyListener{
        
        @Override
        public void keyTyped(KeyEvent e) {
        }
        @Override
        public void keyPressed(KeyEvent e) {

        }
        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getSource() == arabicTextArea){
                ArabicToRomanCalculation result = new ArabicToRomanCalculation(arabicTextArea.getText());
                romanTextArea.setText(result.toString());
            }
            else if(e.getSource() == romanTextArea){
                RomanToArabicCalculation result = new RomanToArabicCalculation(romanTextArea.getText());
                arabicTextArea.setText(result.toString());
            }
            else{
                System.out.println("KeyEvent source not found");
            }
        }
    }




    }
