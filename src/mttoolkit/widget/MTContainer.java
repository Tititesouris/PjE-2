package mttoolkit.widget;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

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
        for (MTComponent mtComponent : components) {
            mtComponent.draw(g);
        }
    }

}
