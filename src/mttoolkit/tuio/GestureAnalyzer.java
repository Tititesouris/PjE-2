package mttoolkit.tuio;

import java.awt.geom.AffineTransform;

import mttoolkit.event.DiscreteEvent;
import mttoolkit.event.SRTEvent;
import mttoolkit.mygeom.Point2;
import mttoolkit.widget.BlobQueue;
import mttoolkit.widget.MTComponent;

public class GestureAnalyzer {

    public void analyse(MTComponent c, BlobQueue b, String state, int id, Point2 p) {
        switch (state) {
            case "add":
                c.fireDiscretePerformed(new DiscreteEvent(p));
                break;
                
            case "update":
            	c.fireSRTPerformed(new SRTEvent(p, new AffineTransform(0.0, 0.0, 0.0, 0.0, p.getX(), p.getY())));
            	break;
        }
    }

}
