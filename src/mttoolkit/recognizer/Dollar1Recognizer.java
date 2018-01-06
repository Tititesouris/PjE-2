package mttoolkit.recognizer;


import mttoolkit.event.GestureEvent;
import mttoolkit.mygeom.Tuple2;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Dollar1Recognizer {

    int size = 200;

    int sample = 64;

    private List<Template> templates = new ArrayList<>();

    public Dollar1Recognizer() {
        TemplateManager templateManager = new TemplateManager("./data/gestures.xml");
        for (Template template : templateManager.getTemplates()) {
            List<Tuple2> points = template.getPoints();
            points = resample(points, sample);
            points = rotateToZero(points);
            points = scaleToSquare(points, size);
            points = translateToOrigin(points);
            templates.add(new Template(template.getName(), new Vector<>(points))); // Error list cannot be cast to vector
        }
    }

    public GestureEvent recognize(List<Tuple2> points) {
        Template t = null;
        points = resample(points, sample);
        points = rotateToZero(points);
        points = scaleToSquare(points, size);
        points = translateToOrigin(points);
        double match = Double.MAX_VALUE;
        for (Template template : templates) {
            double distance = distanceAtBestAngle(points, template, -45, 45, 2);
            if (distance < match) {
                match = distance;
                t = template;
            }
        }
        double score = 1 - (match / (0.5 * Math.sqrt(size * size * 2)));

        return new GestureEvent(t, score);
    }

    private double distanceAtBestAngle(List<Tuple2> points, Template template, double alpha, double beta, double gamma) {
        double phi = (Math.sqrt(5) - 1) / 2;
        double x1 = phi * alpha + (1 - phi) * beta;
        double f1 = distanceAtAngle(points, template, x1);
        double x2 = phi * beta + (1 - phi) * alpha;
        double f2 = distanceAtAngle(points, template, x2);
        while (Math.abs(beta - alpha) > gamma) {
            if (f1 < f2) {
                beta = x2;
                x2 = x1;
                f2 = f1;
                x1 = phi * alpha + (1 - phi) * beta;
                f1 = distanceAtAngle(points, template, x1);
            } else {
                alpha = x1;
                x1 = x2;
                f1 = f2;
                x2 = phi * beta + (1 - phi) * alpha;
                f2 = distanceAtAngle(points, template, x2);
            }
        }
        return Math.min(f1, f2);
    }

    private double distanceAtAngle(List<Tuple2> points, Template template, double angle) {
        return pathDistance(rotateBy(points, angle), template);
    }

    private double pathDistance(List<Tuple2> points, Template t) {
        double distance = 0;
        for (int i = 0; i < Math.min(points.size(), t.getPoints().size()); i++) {
            distance += t.getPoints().get(i).diff(points.get(i)).getEuclidianDistance();
        }
        return distance / points.size();
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

    private List<Tuple2> rotateBy(List<Tuple2> points, double angle) {
        Tuple2 centroid = isobarycentre(points);
        List<Tuple2> newPoints = new ArrayList<>();
        for (Tuple2 point : points) {
            newPoints.add(new Tuple2(
                    (point.getX() - centroid.getX()) * Math.cos(angle) - (point.getY() - centroid.getY()) * Math.sin(angle) + centroid.getX(),
                    (point.getX() - centroid.getX()) * Math.sin(angle) - (point.getY() - centroid.getY()) * Math.cos(angle) + centroid.getY()
            ));
        }
        return newPoints;
    }

    private List<Tuple2> rotateToZero(List<Tuple2> points) {
        Tuple2 centroid = isobarycentre(points);
        double angle = Math.atan2(centroid.getY() - points.get(0).getY(), centroid.getX() - points.get(0).getX());
        return rotateBy(points, angle);
    }

    private List<Tuple2> translateToOrigin(List<Tuple2> points) {
        Tuple2 centroid = isobarycentre(points);
        List<Tuple2> newPoints = new ArrayList<>();
        for (Tuple2 point : points) {
            newPoints.add(point.diff(centroid));
        }
        return newPoints;
    }

    private List<Tuple2> scaleToSquare(List<Tuple2> points, double size) {
        List<Tuple2> newPoints = new ArrayList<>();
        Tuple2 box = getBox(points);
        for (Tuple2 point : points) {
            newPoints.add(new Tuple2(point.getX() * (size / box.getX()), point.getY() * (size / box.getY())));
        }
        return newPoints;
    }

    private Tuple2 getBox(List<Tuple2> points) {
        double minX = Double.MAX_VALUE, minY = Double.MAX_VALUE, maxX = 0, maxY = 0;
        for (Tuple2 point : points) {
            if (point.getX() < minX) {
                minX = point.getX();
            } else if (point.getX() > maxX) {
                maxX = point.getX();
            }

            if (point.getY() < minY) {
                minY = point.getY();
            } else if (point.getY() > maxY) {
                maxY = point.getY();
            }
        }
        return new Tuple2(maxX - minX, maxY - minY);
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

    private List<Tuple2> resample(final List<Tuple2> points, int nbSteps) {
        double step = getPathStep(points) / (nbSteps - 1);
        double D = 0;
        List<Tuple2> newPoints = new ArrayList<>();
        newPoints.add(points.get(0));
        Tuple2 lastPoint = points.get(0);
        int i = 1;
        System.out.println(points.size());
        while (i < points.size()) {
            Tuple2 point = points.get(i);
            Tuple2 vector = lastPoint.diff(point);
            double d = vector.getEuclidianDistance();
            if (D + d >= step) {
                Tuple2 newPoint = lastPoint.sum(vector.times((step - D) / d));
                newPoints.add(newPoint);
                lastPoint = newPoint;
                //points.add(i+1, newPoint);
                D = 0;
            } else {
                D += d;
            }
            lastPoint = point;
            i++;
        }

        System.out.println("new   " + newPoints.size());
        return newPoints;
    }

    private List<Tuple2> resample2(final List<Tuple2> points, int nbSteps) {
        System.out.println(nbSteps + "     " + points.size());
        double step = getPathStep(points) / (nbSteps - 1);
        double distance = 0;
        List<Tuple2> newPoints = new ArrayList<>();
        newPoints.add(points.get(0));

        Tuple2 refPoint = newPoints.get(0);
        for (Tuple2 point : points.subList(1, points.size())) {
            Tuple2 diff = refPoint.diff(point);
            double d = diff.getEuclidianDistance();
            if (distance + d >= step) {
                Tuple2 newPoint = refPoint.sum(point.diff(refPoint).times((step - distance) / d));
                newPoints.add(newPoint);
                distance = 0;
                refPoint = newPoint;
            } else {
                distance += d;
                refPoint = point;
            }
        }
        System.out.println("new   " + newPoints.size());
        return newPoints;
    }

}
