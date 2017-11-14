package mttoolkit.widget;

import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public abstract class MTComponent extends JComponent {

	public abstract boolean isInside(Point p);
	
	public abstract void draw(Graphics2D g);
	
}
