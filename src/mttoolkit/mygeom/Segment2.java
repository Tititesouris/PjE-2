package mttoolkit.mygeom;

public class Segment2 {

    Point2 init, end;

    public Segment2(Point2 a, Point2 b) {
        init = a;
        end = b;
    }

    public Segment2(Tuple2 a, Tuple2 b) {
        init = new Point2(a.getX(), a.getY());
        end = new Point2(b.getX(), b.getY());
    }

    public Segment2(Point2 a, Vector2 b) {
        init = a;
        end = new Point2(a);
        end.add(b);
    }

    public Point2 init() {
        return init;
    }

    public Point2 end() {
        return end;
    }

    public void init(Point2 a) {
        init = a;
    }

    public void end(Point2 a) {
        end = a;
    }

    public void copy(Segment2 s) {
        init.set(s.init());
        end.set(s.end());
    }

    public static double computeAngle(Segment2 s1, Segment2 s2) {

        Tuple2 u = new Tuple2(s1);
        Tuple2 v = new Tuple2(s2);

        u = u.getNormalized();
        v = v.getNormalized();

        double ac = u.dot(v);
        if (ac < -1) ac = -1;
        if (ac > 1) ac = 1;
        double angle = Math.acos(ac);

        double det = u.det(v);
        if (det < 0) angle = -angle;

        return angle;

    }


    /// calcul du scale suivant les 2 segments
    public static double computeScale(Segment2 s1, Segment2 s2) {
        Tuple2 u = new Tuple2(s1);
        Tuple2 v = new Tuple2(s2);
        double k;
        double lu = u.getEuclidianDistance();
        if (Math.abs(lu) < 0.00001) return 1.0;  // TODO : pas terrible, mais correspond à l'identité.
        else return v.getEuclidianDistance() / u.getEuclidianDistance();
    }

    public String toString() {
        return "[" + init + "," + end + "]";
    }

}