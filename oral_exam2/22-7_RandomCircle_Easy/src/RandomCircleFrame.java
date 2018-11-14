import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RandomCircleFrame extends JFrame {

    private final Canvas canvas;
    private final JButton submit;
    private final JTextArea circleAttributes;

    private Circle circle;

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

    private void updateAttributes(){
        circleAttributes.setText("Radius:"+ circle.getRadius()+ "\n " +
                "Diameter: "+circle.getDiameter()+ "\n " +
                "Area:"+circle.getArea()+"\n " +
                "Circumference:"+circle.getCircumference());
    }


    private class ButtonHandler implements ActionListener{

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


}
