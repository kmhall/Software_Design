import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * RandomCircleFrame is a class that creates the frame for displaying a circle and its attributes.
 * @see JFrame
 */
public class RandomCircleFrame extends JFrame {

    private final Canvas canvas;
    private final JButton submit;
    private final JTextArea circleAttributes;

    private Circle circle;

    /**
     * Constructs a RandomCircleFrame. Sets the layout, adds the components to the JFrame,
     * adds a handler for the button.
     */
    public RandomCircleFrame(){
        super("Random Circle");

        setLayout(new GridLayout(3,1));

        canvas = new Canvas();
        add(canvas);
        canvas.setVisible(false);

        circleAttributes = new JTextArea();
        circleAttributes.setEditable(false);

        add(circleAttributes);

        submit = new JButton("Create Circle");
        add(submit);
        ButtonHandler handler = new ButtonHandler();
        submit.addActionListener(handler);
    }

    /**
     * ButtonHandler is a private inner class used to add an ActionListener to the submit button.
     * @see ActionListener
     */
    private class ButtonHandler implements ActionListener{

        /**
         * actionPerformed waits for an action to be performed.
         * This is where all updates to the board are performed.
         * @param e ActionEvent performed
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            Random rand = new Random();

            circle = new Circle(rand.nextInt(canvas.getHeight()));

            canvas.setCircle(circle);

            canvas.setVisible(true);
            canvas.revalidate();
            canvas.repaint();

            updateAttributes();
        }
    }

    /**
     *updateAttributes updates the JTextArea with the current circle's attributes.
     */
    private void updateAttributes(){
        circleAttributes.setText("Radius:"+ circle.getRadius()+ "\n " +
                "Diameter: "+circle.getDiameter()+ "\n " +
                "Area:"+circle.getArea()+"\n " +
                "Circumference:"+circle.getCircumference());
    }


}
