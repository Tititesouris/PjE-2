package mttoolkit.widget;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mttoolkit.mygeom.OBB;
import mttoolkit.mygeom.Point2;

@SuppressWarnings("serial")
public class MTContainer extends MTComponent {

    private Point2 size;

    private List<MTComponent> components = new ArrayList<>();

    public MTContainer(Point2 size) {
        this.size = size;
    }

    public void addComponent(MTComponent component) {
        components.add(component);
    }

    public MTComponent whichIs(Point2 p) {
        for (MTComponent mtComponent : components) {
            if (mtComponent.isInside(p)) {
                mtComponent.setContainer(this);
                return mtComponent;
            }
        }
        return null;
    }

    public void select(MTComponent component) {
        if (components.contains(component)) {
            components.remove(component);
            components.add(0, component);
        }
    }

    @Override
    public boolean isInside(Point2 p) {
        for (MTComponent mtComponent : components) {
            if (mtComponent.isInside(p)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void draw(Graphics2D g) {
    	for (int i = components.size() - 1; i >= 0; i--) {
    		AffineTransform save = g.getTransform();
    		OBB compoObb = components.get(i).obb;
        	
        	g.translate(compoObb.getOrigin().getX(), compoObb.getOrigin().getX());
        	g.rotate(compoObb.getAngle());
        	
    		components.get(i).draw(g);
    		g.setTransform(save);
    		
		}
    }

}
