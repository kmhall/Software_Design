public class Circle {

    private int diameter;
    private int area;
    private int circumference;
    private int radius;

    public Circle(int diameter){
        this.diameter = diameter;
        this.radius = diameter/2;
        this.area = (int) (Math.PI * radius*radius);
        this.circumference =  (int) (2* Math.PI * radius);
    }

    public int getArea() {
        return area;
    }

    public int getCircumference() {
        return circumference;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getRadius() {
        return radius;
    }


}
