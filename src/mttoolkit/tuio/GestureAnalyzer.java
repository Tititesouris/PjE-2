package mttoolkit.tuio;

import mttoolkit.event.DiscreteEvent;
import mttoolkit.mygeom.Point2;
import mttoolkit.widget.BlobQueue;
import mttoolkit.widget.MTComponent;

public class GestureAnalyzer {

    public void analyse(MTComponent c, BlobQueue b, String state, int id, Point2 p) {
        switch (state) {
            case "add":
                c.fireDiscretePerformed(new DiscreteEvent(p));
                break;
        }
    }

}
