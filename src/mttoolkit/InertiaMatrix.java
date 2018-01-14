package mttoolkit;

import mttoolkit.mygeom.OBB;
import mttoolkit.mygeom.Point2;
import mttoolkit.mygeom.Segment2;
import mttoolkit.mygeom.Tuple2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InertiaMatrix {

    List<Tuple2> points;

    public InertiaMatrix(List<Tuple2> points) {
        this.points = points;
    }

    public List<Tuple2> getPoints() {
        return points;
    }

    public void addPoint(Tuple2 point) {
        this.points.add(point);
    }

    public void setPoints(List<Tuple2> points) {
        this.points = points;
    }

    public OBB getOBB() {
        OBB obb = new OBB();
        List<Tuple2> points = this.points; // TODO add resample
        Tuple2 centroid = isobarycentre(points);
        for (Tuple2 point : points) {
            point.setX(point.getX() - centroid.getX());
            point.setX(point.getY() - centroid.getY());
        }

        double a = A(points);
        double b = B(points);
        double f = F(points);
        double r1 = (a + b + Math.sqrt((a - b) * (a - b) + 4 * f * f)) / 2;
        double r2 = (a + b - Math.sqrt((a - b) * (a - b) + 4 * f * f)) / 2;


        Tuple2 minX = points.get(0);
        Tuple2 minY = points.get(0);
        Tuple2 maxX = points.get(0);
        Tuple2 maxY = points.get(0);
        for (Tuple2 point : points) {
            if (point.getX() < minX.getX()) {
                minX = point;
            }
            if (point.getY() < minY.getY()) {
                minY = point;
            }
            if (point.getX() > maxX.getX()) {
                maxX = point;
            }
            if (point.getY() > maxY.getY()) {
                maxY = point;
            }
        }

        obb.setHeight(minX.diff(maxY).getEuclidianDistance());
        obb.setWidth(minX.diff(minY).getEuclidianDistance());

        return obb;
    }

    private double A(List<Tuple2> points) {
        double a = 0;
        for (Tuple2 point : points) {
            a += point.getY() * point.getY();
        }
        return a;
    }

    private double B(List<Tuple2> points) {
        double b = 0;
        for (Tuple2 point : points) {
            b += point.getX() * point.getY();
        }
        return b;
    }

    private double F(List<Tuple2> points) {
        double f = 0;
        for (Tuple2 point : points) {
            f += point.getX() * point.getX();
        }
        return f;
    }

    private Tuple2 isobarycentre(List<Tuple2> points) {
        Tuple2 p = new Tuple2();

        for (Tuple2 point2 : points) {
            p.setX(p.getX() + point2.getX());
            p.setY(p.getY() + point2.getY());
        }

        p.setX(p.getX() / points.size());
        p.setY(p.getY() / points.size());

        return p;
    }

    private List<Tuple2> resample(final List<Tuple2> points, int nbSteps) {
        double step = getPathStep(points) / (nbSteps - 1);
        double D = 0;
        List<Tuple2> newPoints = new ArrayList<>();
        newPoints.add(points.get(0));
        Tuple2 lastPoint = points.get(0);
        int i = 1;
        while (i < points.size()) {
            Tuple2 point = points.get(i);
            Tuple2 vector = lastPoint.diff(point);
            double d = vector.getEuclidianDistance();
            if (D + d >= step) {
                Tuple2 newPoint = lastPoint.sum(vector.times((step - D) / d));
                newPoints.add(newPoint);
                points.add(i, newPoint);
                lastPoint = newPoint;
                D = 0;
            } else {
                D += d;
                lastPoint = point;
            }
            i++;
        }

        if (newPoints.size() == nbSteps - 1) {
            newPoints.add(points.get(points.size() - 1));
        }

        return newPoints;
    }

    private double getPathStep(List<Tuple2> points) {
        double distance = 0;
        Tuple2 lastPoint = points.get(0);
        for (Tuple2 point : points) {
            distance += lastPoint.diff(point).getEuclidianDistance();
            lastPoint = point;
        }
        return distance;
    }
}
