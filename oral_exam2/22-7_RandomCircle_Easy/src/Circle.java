/**
 * Circle is a class that computes the attributes of a Circle.
 */
public class Circle {

    private int diameter;
    private int area;
    private int circumference;
    private int radius;

    /**
     * Constructs a Circle. Computes the radius, area, and circumference.
     * @param diameter Diameter of the wanted circle
     */
    public Circle(int diameter){
        this.diameter = diameter;
        this.radius = diameter/2;
        this.area = (int) (Math.PI * radius*radius);
        this.circumference =  (int) (2* Math.PI * radius);
    }

    /**
     * Gets the area
     * @return area
     */
    public int getArea() {
        return area;
    }

    /**
     * Gets the circumference
     * @return circumference
     */
    public int getCircumference() {
        return circumference;
    }

    /**
     * Gets the diameter
     * @return diameter
     */
    public int getDiameter() {
        return diameter;
    }

    /**
     * Gets the radius
     * @return radius
     */
    public int getRadius() {
        return radius;
    }


}
