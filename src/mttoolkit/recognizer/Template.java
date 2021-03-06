package mttoolkit.recognizer;

import mttoolkit.mygeom.Tuple2;

import java.util.Vector;

/**
 * @author <a href="mailto:gery.casiez@lifl.fr">Gery Casiez</a>
 */

public class Template {
    private String name;
    private Vector<Tuple2> points;

    Template(String name, Vector<Tuple2> points) {
        this.name = name;
        this.points = points;
    }

    public void setPoints(Vector<Tuple2> points) {
        this.points = points;
    }

    public Vector<Tuple2> getPoints() {
        return points;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
