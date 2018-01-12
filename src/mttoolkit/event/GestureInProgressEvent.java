package mttoolkit.event;

import mttoolkit.mygeom.Tuple2;

import java.util.EventObject;
import java.util.List;

public class GestureInProgressEvent extends EventObject {

    private List<Tuple2> points;

    public GestureInProgressEvent(List<Tuple2> points) {
        super(points);
        this.points = points;
    }

    public List<Tuple2> getPoints() {
        return points;
    }
}
