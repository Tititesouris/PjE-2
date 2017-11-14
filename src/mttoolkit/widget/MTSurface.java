package mttoolkit.widget;

import java.awt.Point;

import javax.swing.JPanel;

import mttoolkit.tuio.MTEdt;
import mygeom.Path;

public class MTSurface extends JPanel{
	
	private MTEdt mtedt;
	
	private MTContainer container;
	
	private ComponentMap componentMap;
	
	public MTSurface(MTContainer container) {
		super();
		mtedt = new MTEdt(this);
		this.container = container;
	}
	
	public void add(MTComponent component) {
		container.add(component);
	}
	
	public synchronized void addCursor(int id, Point p) {
		MTComponent component = container.whichIs(p);
		if (component != null) {
			componentMap.addBlob(component, id, p);
		}
	}
}
