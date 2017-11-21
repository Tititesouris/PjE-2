package mttoolkit.widget;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import mttoolkit.event.ChangedSideEvent;
import mttoolkit.event.ChangedSideListener;
import mttoolkit.event.Side;
import mttoolkit.mygeom.Point2;
import mttoolkit.tuio.MTEdt;

public class MTSurface extends JPanel {

    private MTEdt mtedt;

    private MTContainer container;

    private ComponentMap componentMap;

    private List<ChangedSideListener> sideListeners;

    private boolean cursorsVisible;

    public MTSurface() {
        super();
        this.mtedt = new MTEdt(this);
        this.container = new MTContainer(new Point2(getWidth(), getHeight()));
        this.componentMap = new ComponentMap();
    }

    public void add(MTComponent component) {
        container.addComponent(component);
    }

    public synchronized void addCursor(int id, Point2 p) {
        MTComponent component = container.whichIs(p);
        if (component != null) {
            System.out.println("ON IMAGE");
            componentMap.addBlob(component, id, p);
        }
        else {
            componentMap.addBlob(container, id, p);
        }
        this.repaint();
    }

    public synchronized void updateCursor(int id, Point2 p) {
        componentMap.updateBlob(id, p);
        this.repaint();
    }

    public synchronized void removeCursor(int id, Point2 p) {
        componentMap.removeBlob(id, p);
        this.repaint();
    }

    public boolean areCursorsVisible() {
        return cursorsVisible;
    }

    public void setCursorsVisible(boolean visible) {
        this.cursorsVisible = visible;
    }

    public void addChangedSideListener(ChangedSideListener list) {
        sideListeners.add(list);
    }

    public void fireChangedSideListener(ChangedSideEvent event) {
        for (ChangedSideListener actionListener : sideListeners) {
            actionListener.changedSidePerformed(event);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (BlobQueue b : componentMap.getBlobQueue().values()) {
            b.draw(g2, cursorsVisible);
        }
        container.draw(g2);
    }
}
