package mttoolkit.mygeom;

public class OBB {

    private Tuple2 origin;

    private double angle;

    private double width, height;

    public OBB() {
        origin = new Tuple2();
        angle = 0.0;
        width = 0.0;
        height = 0.0;
    }

    public Tuple2 getOrigin() {
        return origin;
    }

    public void setOrigin(Tuple2 origin) {
        this.origin = origin;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

}
