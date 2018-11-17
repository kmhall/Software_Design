import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Canvas is a class that does the painting on a JPanel.
 * @see JPanel
 */
public class Canvas extends JPanel {

  private Circle circle;

    /**
     * Paint creates a random sized circle centered int the middle of the JPanel.
     * @param g An instance of the graphics which allows drawing on components.
     */
    public void paint(Graphics g){
        Random rand = new Random();

        int x = getWidth();
        int y = getHeight();

        g.setColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
        g.fillOval(x/2-circle.getDiameter()/2,y/2-circle.getDiameter()/2,circle.getDiameter(),circle.getDiameter());

    }

    /**
     * Takes a Circle as a parameter and sets the current circle to be displayed.
     * @param circle The current circle
     */
    public void setCircle(Circle circle) {
        this.circle = circle;
    }
}
