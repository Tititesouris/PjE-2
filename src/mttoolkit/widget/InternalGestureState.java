package mttoolkit.widget;

import mttoolkit.mygeom.OBB;
import mttoolkit.mygeom.Tuple2;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class InternalGestureState {

    private OBB obb;

    private Tuple2 oldPos_A, oldPos_B, currentPos_A, currentPos_B;

    public InternalGestureState(MTComponent c) {
        oldPos_A = new Tuple2();
        oldPos_B = new Tuple2();

        currentPos_A = new Tuple2();
        currentPos_B = new Tuple2();

        obb = c.obb;
    }

    public void motionTranslateBegin(Tuple2 cursor) {
        currentPos_A = cursor;
    }

    public void motionTranslateUpdate(Tuple2 cursor) {
        oldPos_A = currentPos_A;
        currentPos_A = cursor;
    }

    public Tuple2 computeTranslation() {
        Tuple2 tmp = new Tuple2();
        tmp.set(oldPos_A, currentPos_A);
        return tmp;
    }

    public void motionTRSBegin(Tuple2 cursorA, Tuple2 cursorB) {
        currentPos_A = cursorA;
        currentPos_B = cursorB;
    }

    public void motionTRSUpdate(Tuple2 cursorA, Tuple2 cursorB) {
        oldPos_A = currentPos_A;
        currentPos_A = cursorA;
        oldPos_B = currentPos_B;
        currentPos_B = cursorB;
    }

    public double computeTRSScale() {
        double current = currentPos_B.diff(currentPos_A).getEuclidianDistance();
        double old = oldPos_B.diff(oldPos_A).getEuclidianDistance();
        return current / old;
    }

    public double computeTRSRotation() {
        Tuple2 old = oldPos_B.diff(oldPos_A).getNormalized();
        Tuple2 current = currentPos_B.diff(currentPos_A).getNormalized();

        double angle = Math.acos(old.getX() * current.getX() + old.getY() * current.getY());
        if (Double.isNaN(angle)){
            angle = 0;
        }

        if ((old.getX() * current.getY() - old.getY() * current.getX()) > 0) {
            return angle;
        } else {
            return -angle;
        }
    }

    public Tuple2 computeTRSTranslation() {
        AffineTransform transform = new AffineTransform();
        transform.setToTranslation(oldPos_A.getX(), oldPos_A.getY());
        transform.translate(currentPos_A.getX() - oldPos_A.getX(), currentPos_A.getY() - oldPos_A.getY());
        double angle = computeTRSRotation();
        transform.rotate(angle);
        double scale = computeTRSScale();
        transform.scale(scale, scale);
        transform.translate(-oldPos_A.getX(), -oldPos_A.getY());
        Point2D p0 = new Point2D.Double(obb.getOrigin().getX(), obb.getOrigin().getY());
        Point2D pp0 = new Point2D.Double();
        transform.transform(p0, pp0);
        return new Tuple2(pp0.getX() - p0.getX(), pp0.getY() - p0.getY());
    }

}
