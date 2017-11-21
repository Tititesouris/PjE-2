package mttoolkit.widget;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import mttoolkit.mygeom.Point2;

@SuppressWarnings("serial")
public class MTContainer extends MTComponent {
	
	public Point2 position, size;

	private List<MTComponent> components = new ArrayList<>();
	
	public MTContainer(Point2 pos, Point2 size)  {
		this.position = pos;
		this.size = size;
	}

	public MTComponent whichIs(Point2 p) {
		for (MTComponent mtComponent : components) {
			if (mtComponent.isInside(p)) {
				return mtComponent;
			}
		}
		return null;
	}

	@Override
	public boolean isInside(Point2 p) {
		return false;
	}
	
	@Override
	public void draw(Graphics2D g) {
		for (MTComponent mtComponent : components) {
			mtComponent.draw(g);
		}
	}
	
}
