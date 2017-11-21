package mttoolkit.widget;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import event.ChangedSideEvent;
import event.ChangedSideListener;
import mttoolkit.mygeom.Point2;
import mttoolkit.tuio.MTEdt;
import mttoolkit.mygeom.Path;

public class MTSurface extends JPanel {
	
	private MTEdt mtedt;
	private MTContainer container;
	private ComponentMap componentMap;
	private ArrayList<ChangedSideListener> sideListeners;
	
	private boolean cursorsVisible;
	
	public MTSurface() {
		super();
		this.mtedt = new MTEdt(this);
		this.container = new MTContainer(new Point2(getWidth(), getHeight()));
		this.componentMap = new ComponentMap();
	}
	
	public void add(MTComponent component) {
		container.add(component);
	}
	
	public synchronized void addCursor(int id, Point2 p) {
		MTComponent component = container.whichIs(p);
		if (component != null) {
			componentMap.addBlob(component, id, p);
		}
	}
	
	public void updateCursor(int cursorID, Point2 point2) {
		// TODO Auto-generated method stub
		
	}

	public void removeCursor(int cursorID, Point2 point2) {
		// TODO Auto-generated method stub
		
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
		// drawing instructions with g2.
		for (BlobQueue b : componentMap.getBlobQueue().values()) {
			b.draw(g2, cursorsVisible);
		}
	}
	
}
