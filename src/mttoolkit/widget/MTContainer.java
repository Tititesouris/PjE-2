package mttoolkit.widget;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mttoolkit.event.GestureEvent;
import mttoolkit.event.GestureEventListener;
import mttoolkit.mygeom.OBB;
import mttoolkit.mygeom.Point2;

@SuppressWarnings("serial")
public class MTContainer extends MTComponent {

    private Point2 size;

    private List<MTComponent> components = new ArrayList<>();

    public MTContainer(Point2 size) {
        this.size = size;
        initContainer();

        addGestureEventListener(new GestureEventListener() {
            @Override
            public void gesturePerformed(GestureEvent event) {
                if ("circle".equals(event.getTemplate().getName())) {
                    initContainer();
                }
            }
        });
    }

    public void initContainer() {
        components = new ArrayList<>();
        MTPicture picture = new MTPicture(new Point2(50, 50), new Point2(300, 100), "data/Bird.jpg");
        MTPicture picture2 = new MTPicture(new Point2(100, 80), new Point2(50, 150), "data/Thunder.jpg");
        addComponent(picture);
        addComponent(picture2);
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
        return this;
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

            g.translate(compoObb.getOrigin().getX(), compoObb.getOrigin().getY());
            g.rotate(compoObb.getAngle());

            components.get(i).draw(g);
            g.setTransform(save);
        }
    }

}
