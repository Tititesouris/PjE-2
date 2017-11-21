package mttoolkit.widget;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JComponent;
import javax.swing.event.EventListenerList;

import mttoolkit.event.DiscreteEvent;
import mttoolkit.event.DiscreteEventListener;
import mttoolkit.event.SRTEvent;
import mttoolkit.event.SRTEventListener;
import mttoolkit.mygeom.Point2;

@SuppressWarnings("serial")
public abstract class MTComponent extends JComponent {

	public EventListenerList listeners;

	public abstract boolean isInside(Point2 p);

	public abstract void draw(Graphics2D g);

	public void addDiscretEventListener(DiscreteEventListener l) {
		listeners.add(DiscreteEventListener.class, l);
	}

	public void addSRTEventListener(SRTEventListener l) {
		listeners.add(SRTEventListener.class, l);
	}

	public void fireDiscretPerformed() {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == DiscreteEventListener.class) {
				// Lazily create the event:
				((DiscreteEventListener) listeners[i + 1]).gesturePerformed(new DiscreteEvent(this));
			}
		}
	}

	public void fireSRTPerformed(AffineTransform t) {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == SRTEventListener.class) {
				// Lazily create the event:
				((SRTEventListener) listeners[i + 1]).gesturePerformed(new SRTEvent(this, t));
			}
		}
	}

}
