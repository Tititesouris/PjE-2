package mttoolkit.widget;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.EventObject;

import javax.swing.JComponent;
import javax.swing.event.EventListenerList;

import mttoolkit.event.*;
import mttoolkit.mygeom.Point2;

@SuppressWarnings("serial")
public abstract class MTComponent extends JComponent {

    private MTContainer container;

    private EventListenerList listeners = new EventListenerList();

    public abstract boolean isInside(Point2 p);

    public abstract void draw(Graphics2D g);

    public void click() {
        container.select(this);
    }

    public void addDiscreteEventListener(DiscreteEventListener l) {
        listeners.add(DiscreteEventListener.class, l);
    }

    public void addSRTEventListener(SRTEventListener l) {
        listeners.add(SRTEventListener.class, l);
    }

    public void addGestureEventListener(GestureEventListener l) {
        listeners.add(GestureEventListener.class, l);
    }

    public void fireDiscretePerformed(EventObject object) {
        Object[] listenersList = listeners.getListenerList();
        for (int i = listenersList.length - 2; i >= 0; i -= 2) {
            if (listenersList[i] == DiscreteEventListener.class) {
                ((DiscreteEventListener) listenersList[i + 1]).gesturePerformed((DiscreteEvent) object);
            }
        }
    }

    public void fireSRTPerformed(EventObject object) {
        Object[] listenersList = listeners.getListenerList();
        for (int i = listenersList.length - 2; i >= 0; i -= 2) {
            if (listenersList[i] == SRTEventListener.class) {
                ((SRTEventListener) listenersList[i + 1]).gesturePerformed((SRTEvent) object);
            }
        }
    }

    public void fireGesturePerformed(EventObject object) {
        Object[] listenersList = listeners.getListenerList();
        for (int i = listenersList.length - 2; i >= 0; i -= 2) {
            if (listenersList[i] == GestureEventListener.class) {
                ((GestureEventListener) listenersList[i + 1]).gesturePerformed((GestureEvent) object);
            }
        }
    }

    public void setContainer(MTContainer container) {
        this.container = container;
    }
}
