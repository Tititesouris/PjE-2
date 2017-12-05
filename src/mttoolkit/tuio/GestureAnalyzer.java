package mttoolkit.tuio;

import java.awt.geom.AffineTransform;

import mttoolkit.event.DiscreteEvent;
import mttoolkit.event.SRTEvent;
import mttoolkit.mygeom.Point2;
import mttoolkit.mygeom.Vector2;
import mttoolkit.widget.BlobQueue;
import mttoolkit.widget.MTComponent;

public class GestureAnalyzer {
	
	private int currentId;

	public void analyse(MTComponent c, BlobQueue b, String state, int id, Point2 p) {
		switch (state) {
		case "add":
			add(c, b, p, id);
			break;

		case "update":
			update(c, b, p, id);
			break;

		case "remove":
			remove(c, b, p, id);
			break;
		}
	}

	public void add(MTComponent c, BlobQueue b, Point2 p, int id) {
		if (b.length() == 1) {
			currentId = id;
			c.gestureState.motionTranslateBegin(new Vector2(p.getX(), p.getY()));
		}

		c.fireDiscretePerformed(new DiscreteEvent(c));
	}

	public void update(MTComponent c, BlobQueue b, Point2 p, int id) {
		if (b.length() == 1 && id == currentId) {
			c.gestureState.motionTranslateUpdate(new Vector2(p.getX(), p.getY()));
			
			Vector2 translate = c.gestureState.computeTranslation();
			c.fireSRTPerformed(new SRTEvent(c, translate, 0.0, 1.0));
		}
	}

	public void remove(MTComponent c, BlobQueue b, Point2 p, int id) {
		if (b.length() > 0) {
			c.gestureState.motionTranslateBegin(new Vector2(p.getX(), p.getY()));
			currentId = id;
		}
	}

}
