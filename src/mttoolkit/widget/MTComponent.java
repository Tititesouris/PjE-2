package mttoolkit.widget;

import java.awt.Graphics2D;

import javax.swing.JComponent;

import mttoolkit.mygeom.Point2;

@SuppressWarnings("serial")
public abstract class MTComponent extends JComponent {

	public abstract boolean isInside(Point2 p);
	
	public abstract void draw(Graphics2D g);
	
}
