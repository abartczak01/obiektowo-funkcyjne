public class Walec {
    private double r;
    private double h;

    public Walec(double r, double h) {
        this.r = r;
        this.h = h;
    }

    public Walec(){}

    public double getHeight() {
        return h;
    }

    public double getRadius() {
        return r;
    }

    public void setHeight(double newHeight) {
        h = newHeight;
    }

    public void setRadius(double newRadius) {
        r = newRadius;
    }

    public double areaOfBase() {
        return Math.PI * Math.pow(r, 2);
    }

    public double lateralArea() {
        return 2 * Math.PI * r * h;
    }

    public double totalArea() {
        double base = areaOfBase();
        double lateral = lateralArea();
        return 2 * base + lateral;
    }

    public double volume() {
        double base = areaOfBase();
        return base * h;
    }
}
