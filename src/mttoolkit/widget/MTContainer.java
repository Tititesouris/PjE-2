package mttoolkit.widget;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class MTContainer extends MTComponent {
	
	public Rectangle bounds;

	private List<MTComponent> components = new ArrayList<>();

	public MTComponent whichIs(Point p) {
		for (MTComponent mtComponent : components) {
			if (mtComponent.isInside(p)) {
				return mtComponent;
			}
		}
		return null;
	}

	@Override
	public boolean isInside(Point p) {
		return bounds.contains(p);
	}
	
	@Override
	public void draw(Graphics2D g) {
		for (MTComponent mtComponent : components) {
			mtComponent.draw(g);
		}
	}
	
}
