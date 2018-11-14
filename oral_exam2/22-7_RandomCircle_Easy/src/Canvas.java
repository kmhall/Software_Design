import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Canvas extends JPanel {

  private Circle circle;

    public void paint(Graphics g){
        Random rand = new Random();

        int x = getWidth();
        int y = getHeight();

        g.setColor(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
        g.fillOval(x/2-circle.getDiameter()/2,y/2-circle.getDiameter()/2,circle.getDiameter(),circle.getDiameter());

    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }
}
